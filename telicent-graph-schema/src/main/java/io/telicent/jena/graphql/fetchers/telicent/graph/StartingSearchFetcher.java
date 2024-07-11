/**
 * Copyright (C) Telicent Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package io.telicent.jena.graphql.fetchers.telicent.graph;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.telicent.jena.graphql.execution.telicent.graph.TelicentExecutionContext;
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import io.telicent.jena.graphql.server.model.GraphQLOverHttp;
import io.telicent.servlet.auth.jwt.JwtHttpConstants;
import io.telicent.servlet.auth.jwt.verifier.aws.AwsConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.web.HttpSC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A GraphQL {@link DataFetcher} that finds the starting points for a query based upon search terms which are passed on
 * to the Telicent Search REST API to find the matching entities
 */
public class StartingSearchFetcher implements DataFetcher<List<TelicentGraphNode>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartingSearchFetcher.class);

    /**
     * The environment variable/system property used to configure the Search API URL
     */
    public static final String ENV_SEARCH_API_URL = "SEARCH_API_URL";

    /**
     * The default Search API URL used if none was supplied
     */
    public static final String DEFAULT_SEARCH_API_URL = "http://localhost:8181";

    private static final ObjectMapper JSON = new ObjectMapper();

    private String searchApiUrl = null;
    private final HttpClient client = HttpClient.newBuilder().build();

    /**
     * Creates a new fetcher that uses a search query to find nodes of interest
     */
    public StartingSearchFetcher() {

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TelicentGraphNode> get(DataFetchingEnvironment environment) {
        configureSearchApiUrl();

        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        String rawGraph = environment.getArgument(TelicentGraphSchema.ARGUMENT_GRAPH);
        Node graphFilter = StringUtils.isNotBlank(rawGraph) ? StartingNodesFetcher.parseStart(rawGraph) : Node.ANY;

        // Make a search to populate the starts filter
        String searchTerm = environment.getArgument("searchTerm");
        if (StringUtils.isBlank(searchTerm)) {
            throw new RuntimeException("Failed to make query as no 'searchTerm' argument provided");
        }
        Integer limit = environment.getArgument("limit");
        Integer offset = environment.getArgument("offset");
        List<Node> startFilters = new ArrayList<>();
        HttpRequest.Builder request =
                HttpRequest.newBuilder(buildSearchApiRequestUri(this.searchApiUrl, searchTerm, limit, offset)).GET();
        if (context.hasAuthToken()) {
            // If we have an authentication token need to copy it to our search request so that our request reflects the
            // requesting users data access
            // Note that since this code doesn't know its deployment context we don't know how the token originally
            // was provided to us so when talking to another HTTP service within the platform pass it in the two main
            // ways that service might expect to receive it. In an AWS context this might mean that the requests pass
            // back via the ELB which is applying the necessary token(s) anyway but no harm in manually adding it
            // ourselves.
            request = request.header(JwtHttpConstants.HEADER_AUTHORIZATION,
                                     JwtHttpConstants.AUTH_SCHEME_BEARER + " " + context.getAuthToken())
                             .header(AwsConstants.HEADER_DATA, context.getAuthToken());
        }
        try {
            HttpResponse<InputStream> response =
                    client.send(request.build(), HttpResponse.BodyHandlers.ofInputStream());
            if (response.statusCode() == HttpSC.OK_200) {
                Map<String, Object> results = JSON.readValue(response.body(), GraphQLOverHttp.GENERIC_MAP_TYPE);

                if (results.containsKey("results")) {
                    List<Map<String, Object>> searchResults = (List<Map<String, Object>>) results.get("results");
                    for (Map<String, Object> result : searchResults) {
                        if (result.containsKey("document")) {
                            String docUri = (String) ((Map<String, Object>) result.get("document")).get("uri");
                            if (StringUtils.isNotBlank(docUri)) {
                                startFilters.add(StartingNodesFetcher.parseStart(docUri));
                            }
                        }
                    }
                }
            } else {
                throw new RuntimeException("Failed to make query for search term " + environment.getArgument(
                        "searchTerm") + ", received status " + response.statusCode());
            }
        } catch (Throwable e) {
            throw new RuntimeException("Failed to make query for search term " + environment.getArgument(
                    "searchTerm") + ".  Search service may be unavailable in your environment.", e);
        }

        return startFilters.stream()
                           .distinct()
                           .flatMap(n -> dsg.stream(graphFilter, n, Node.ANY, Node.ANY))
                           .map(Quad::getSubject)
                           .distinct()
                           .map(n -> new TelicentGraphNode(n, dsg.prefixes()))
                           .collect(Collectors.toList());
    }

    /**
     * Builds the API used to issue Search Requests to the underlying Search API
     * @param searchApiUrl Base Search API URL
     * @param searchTerm Search Term
     * @param limit Optional limit, ignored if {@code null} or not greater than {@code 0}
     * @param offset Optional offset, ignored if {@code null} or not greater than {@code 0}
     * @return Search API Request URL
     */
    public static URI buildSearchApiRequestUri(String searchApiUrl, String searchTerm, Integer limit, Integer offset) {
        StringBuilder builder = new StringBuilder();
        builder.append(searchApiUrl)
               .append("/documents?query=")
               .append(URLEncoder.encode(searchTerm, StandardCharsets.UTF_8));
        if (limit != null && limit > 0) {
            builder.append("&limit=").append(limit);
        }
        if (offset != null && offset >= 1) {
            builder.append("&offset=").append(offset);
        }
        return URI.create(builder.toString());
    }

    private void configureSearchApiUrl() {
        // Get the Search API URL trimming off any trailing slash
        this.searchApiUrl = findSearchApiUrl();
        if (this.searchApiUrl.endsWith("/")) {
            this.searchApiUrl = this.searchApiUrl.substring(0, this.searchApiUrl.length() - 1);
        }
        LOGGER.info("Configured Search API URL as {}", this.searchApiUrl);
    }

    private static String findSearchApiUrl() {
        // Try the environment variable first
        String envValue = System.getenv(ENV_SEARCH_API_URL);
        if (StringUtils.isNotBlank(envValue)) {
            return envValue;
        }
        // Try the system property second, falling back to the default value if not available
        return System.getProperty(ENV_SEARCH_API_URL, DEFAULT_SEARCH_API_URL);
    }
}
