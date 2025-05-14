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
package io.telicent.jena.graphql.fuseki;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.telicent.jena.graphql.server.model.GraphQLOverHttp;
import io.telicent.jena.graphql.server.model.GraphQLRequest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.fuseki.main.FusekiServer;
import org.apache.jena.fuseki.main.sys.FusekiModules;
import org.apache.jena.fuseki.server.DataAccessPoint;
import org.apache.jena.fuseki.servlets.CrossOriginFilter;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFuseki;
import org.apache.jena.riot.WebContent;
import org.apache.jena.riot.web.HttpMethod;
import org.apache.jena.riot.web.HttpNames;
import org.apache.jena.web.HttpSC;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestFusekiGraphQL {

    private static final ObjectMapper JSON = new ObjectMapper();
    private final HttpClient client = HttpClient.newBuilder().build();

    private FusekiServer.Builder build(File configFile) {
        return FusekiServer.create()
                           .fusekiModules(FusekiModules.create(new FMod_GraphQL()))
                           .enableCors(true, null)
                           .parseConfigFile(configFile.getAbsolutePath());
    }

    private void uploadTestData(FusekiServer server, String datasetName, String file) {
        //@formatter:off
        try (RDFConnection conn = RDFConnectionFuseki.create()
                                                .destination(server.datasetURL(datasetName))
                                                .gspEndpoint("gsp")
                                                .build()) {
            //@formatter:on
            conn.load(file);
        }
    }

    private byte[] makeGraphQLRequest(FusekiServer server, String datasetName, String graphQLEndpoint,
                                      GraphQLRequest request, int expectedStatus) throws IOException,
            InterruptedException {
        byte[] requestBytes = JSON.writeValueAsBytes(request);
        //@formatter:off
        HttpRequest httpRequest =
                HttpRequest.newBuilder(URI.create(server.datasetURL(datasetName) + "/" + graphQLEndpoint))
                           .header(HttpNames.hContentType, WebContent.contentTypeJSON)
                           .POST(HttpRequest.BodyPublishers.ofByteArray(requestBytes))
                           .build();
        //@formatter:on

        HttpResponse<byte[]> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofByteArray());
        Assert.assertEquals(response.statusCode(), expectedStatus);
        return response.body();
    }

    @Test
    public void simple_config() throws IOException, InterruptedException {
        FusekiServer server = build(new File("src/test/files/config-graphql.ttl")).build();
        Assert.assertEquals(server.getDataAccessPointRegistry().accessPoints().size(), 1);
        DataAccessPoint dap = server.getDataAccessPointRegistry().accessPoints().get(0);
        Assert.assertFalse(dap.getDataService().getEndpoints(SysGraphQL.OP_GRAPHQL).isEmpty(),
                           "Expected a GraphQL endpoint to be registered");
        Assert.assertEquals(dap.getDataService().getEndpoints(SysGraphQL.OP_GRAPHQL).size(), 1);
        try {
            server.start();
            uploadTestData(server, "ds", "src/test/files/starwars.ttl");

            verifySuccessfulQuery(server, "/simple.graphql", "graphql");
        } finally {
            server.stop();
        }
    }

    @Test
    public void givenFusekiAutoModules_whenStartingFuseki_thenGraphQLIsAvailable() throws IOException,
            InterruptedException {
        // Given
        FusekiServer server =
                build(new File("src/test/files/config-graphql.ttl")).fusekiModules(FusekiModules.getSystemModules())
                                                                    .build();

        // When
        try {
            server.start();
            uploadTestData(server, "ds", "src/test/files/starwars.ttl");

            verifySuccessfulQuery(server, "/simple.graphql", "graphql");
        } finally {
            server.stop();
        }

    }

    private Map<String, Object> verifySuccessfulQuery(FusekiServer server, String queryResource, String endpoint) throws
            IOException, InterruptedException {
        String testQuery = IOUtils.resourceToString(queryResource, StandardCharsets.UTF_8);
        GraphQLRequest request = new GraphQLRequest();
        request.setQuery(testQuery);
        byte[] rawResult = makeGraphQLRequest(server, "ds", endpoint, request, 200);
        Assert.assertNotNull(rawResult);
        Assert.assertNotEquals(rawResult.length, 0);

        Map<String, Object> result = JSON.readValue(rawResult, GraphQLOverHttp.GENERIC_MAP_TYPE);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.containsKey("data"));
        Assert.assertFalse(result.containsKey("errors"));
        return result;
    }

    private Map<String, Object> verifyFailingQuery(FusekiServer server, String queryResource, String endpoint) throws
            IOException, InterruptedException {
        String testQuery = IOUtils.resourceToString(queryResource, StandardCharsets.UTF_8);
        GraphQLRequest request = new GraphQLRequest();
        request.setQuery(testQuery);
        byte[] rawResult = makeGraphQLRequest(server, "ds", endpoint, request, 400);
        Assert.assertNotNull(rawResult);
        Assert.assertNotEquals(rawResult.length, 0);

        Map<String, Object> result = JSON.readValue(rawResult, GraphQLOverHttp.GENERIC_MAP_TYPE);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.containsKey("errors"));
        return result;
    }

    @Test
    public void multiple_config() throws IOException, InterruptedException {
        FusekiServer server = build(new File("src/test/files/config-graphql-multiple.ttl")).build();
        Assert.assertEquals(server.getDataAccessPointRegistry().accessPoints().size(), 1);
        DataAccessPoint dap = server.getDataAccessPointRegistry().accessPoints().get(0);
        Assert.assertFalse(dap.getDataService().getEndpoints(SysGraphQL.OP_GRAPHQL).isEmpty(),
                           "Expected a GraphQL endpoint to be registered");
        Assert.assertEquals(dap.getDataService().getEndpoints(SysGraphQL.OP_GRAPHQL).size(), 4);
        try {
            server.start();
            uploadTestData(server, "ds", "src/test/files/starwars.ttl");

            // Send a query that we expect to succeed
            verifySuccessfulQuery(server, "/simple.graphql", "dataset-graphql");

            // Send a query using the dataset schema to the traversal schema endpoint which should lead to failure
            Map<String, Object> failingResult = verifyFailingQuery(server, "/simple.graphql", "traversal-graphql");
            @SuppressWarnings("unchecked") List<Object> errors = (List<Object>) failingResult.get("errors");
            Assert.assertNotNull(errors);
            Assert.assertEquals(errors.size(), 1);

            // Send a query using the count schema to the correct endpoint and verify the returned count
            Map<String, Object> countResult = verifySuccessfulQuery(server, "/count.graphql", "count-graphql");
            @SuppressWarnings("unchecked") Map<String, Object> data = (Map<String, Object>) countResult.get("data");
            Assert.assertNotNull(data);
            Assert.assertTrue(data.containsKey("count"));
            Assert.assertEquals(data.get("count"), 28700);

            // Send a query that we expect to succeed (to default no-name endpoint)
            verifySuccessfulQuery(server, "/simple.graphql", "");

        } finally {
            server.stop();
        }
    }

    @Test
    public void bad_config() throws IOException {
        FusekiServer server = build(new File("src/test/files/config-graphql-bad.ttl")).build();
        Assert.assertEquals(server.getDataAccessPointRegistry().accessPoints().size(), 1);
        DataAccessPoint dap = server.getDataAccessPointRegistry().accessPoints().get(0);
        Assert.assertFalse(dap.getDataService().getEndpoints(SysGraphQL.OP_GRAPHQL).isEmpty(),
                           "Expected a GraphQL endpoint to be registered");

        try {
            server.start();

            // Load the test query
            String testQuery = IOUtils.resourceToString("/simple.graphql", StandardCharsets.UTF_8);
            GraphQLRequest request = new GraphQLRequest();
            request.setQuery(testQuery);

            dap.getDataService().getEndpoints(SysGraphQL.OP_GRAPHQL).forEach(e -> {
                Assert.assertNull(e.getProcessor(),
                                  "Expected no processor registered for GraphQL endpoint " + e.getName());

                // Make a request and ensure we get back an error as expected since no processor is registered
                try {
                    byte[] rawResult = makeGraphQLRequest(server, "ds", e.getName(), request, 400);
                    String error = new String(rawResult, StandardCharsets.UTF_8);
                    Assert.assertTrue(StringUtils.contains(error, "No processor"),
                                      "Error message not as expected: " + error);
                } catch (Throwable ex) {
                    throw new RuntimeException(ex);
                }
            });
        } finally {
            server.stop();
        }
    }

    @Test
    public void test_FMod_GraphQL_hasName() {
        // given
        FMod_GraphQL fModGraphQL = new FMod_GraphQL();
        // when
        String actual = fModGraphQL.name();
        // then
        Assert.assertEquals(actual, "GraphQL Queries");
    }

    @Test
    public void options_request() throws IOException, InterruptedException {
        FusekiServer server = build(new File("src/test/files/config-graphql.ttl")).build();
        Assert.assertEquals(server.getDataAccessPointRegistry().accessPoints().size(), 1);
        DataAccessPoint dap = server.getDataAccessPointRegistry().accessPoints().get(0);
        Assert.assertFalse(dap.getDataService().getEndpoints(SysGraphQL.OP_GRAPHQL).isEmpty(),
                           "Expected a GraphQL endpoint to be registered");
        Assert.assertEquals(dap.getDataService().getEndpoints(SysGraphQL.OP_GRAPHQL).size(), 1);
        try {
            server.start();

            //@formatter:off
            HttpRequest httpRequest =
                    HttpRequest.newBuilder(URI.create(server.datasetURL("ds") + "/graphql"))
                               .header(HttpNames.hContentType, WebContent.contentTypeJSON)
                               .header(HttpNames.hAccept, WebContent.contentTypeJSON)
                               .method(HttpMethod.METHOD_OPTIONS.method(), HttpRequest.BodyPublishers.noBody())
                               .header("Origin", "https://example.org")
                               .build();
            //@formatter:on

            HttpResponse<Void> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.discarding());
            Assert.assertEquals(httpResponse.statusCode(), HttpSC.OK_200);

            Assert.assertNotNull(httpResponse.headers()
                                             .firstValue(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER)
                                             .orElse(null),
                                 "No " + CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER);
        } finally {
            server.stop();
        }
    }
}
