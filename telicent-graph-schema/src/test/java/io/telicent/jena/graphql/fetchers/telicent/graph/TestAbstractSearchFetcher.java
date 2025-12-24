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

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentImpl;
import io.telicent.jena.graphql.execution.telicent.graph.TelicentExecutionContext;
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import io.telicent.jena.graphql.schemas.telicent.graph.models.SearchType;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.apache.jena.sparql.core.DatasetGraph;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TestAbstractSearchFetcher {

    private final DatasetGraph dsg = TestStartingSearchFetcher.createPagedSearchTestDataset();

    @Test
    public void givenMinimalArguments_whenFormingSearchUrl_thenMinimalUrl() {
        // Given
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                                         .localContext(context)
                                                                         .arguments(
                                                                                 Map.of(TelicentGraphSchema.ARGUMENT_SEARCH_TERM,
                                                                                        "test"))
                                                                         .build();

        // When
        URI searchUrl =
                AbstractSearchFetcher.buildSearchApiRequestUri(AbstractSearchFetcher.DEFAULT_SEARCH_API_URL, "test",
                                                               environment);

        // Then
        Assert.assertEquals(searchUrl.getPath(), "/documents");
        Assert.assertEquals(searchUrl.getQuery(), "query=test");
    }

    @DataProvider(name = "searchArguments")
    public Object[][] searchArguments() {
        return new Object[][] {
                { Map.of(TelicentGraphSchema.ARGUMENT_SEARCH_TYPE, SearchType.TERM), Map.of("type", "term") },
                {
                        Map.of(TelicentGraphSchema.ARGUMENT_LIMIT, 10, TelicentGraphSchema.ARGUMENT_OFFSET, 100),
                        Map.of(TelicentGraphSchema.ARGUMENT_LIMIT, "1", TelicentGraphSchema.ARGUMENT_OFFSET, "100")
                },
                {
                        Map.of(TelicentGraphSchema.ARGUMENT_LIMIT, 100),
                        Map.of(TelicentGraphSchema.ARGUMENT_LIMIT, "100")
                },
                {
                        Map.of(TelicentGraphSchema.ARGUMENT_TYPE_FILTER, "Person"),
                        Map.of("type-filter",
                               Base64.encodeBase64URLSafeString("Person".getBytes(StandardCharsets.UTF_8)),
                               "is-type-filter-base64", "true")
                }
        };
    }

    @Test(dataProvider = "searchArguments")
    public void givenValidArguments_whenFormingSearchUrl_thenReflectedInUrl(Map<String, Object> arguments,
                                                                            Map<String, String> expectedQuerystringParameters) {
        // Given
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                                         .localContext(context)
                                                                         .arguments(arguments)
                                                                         .build();

        // When
        URI searchUrl = AbstractSearchFetcher.buildSearchApiRequestUri("https://some-deployment/api/search", "test",
                                                                       environment);

        // Then
        Assert.assertEquals(searchUrl.getPath(), "/api/search/documents");
        Assert.assertTrue(Strings.CS.contains(searchUrl.getQuery(), "query=test"));
        for (Map.Entry<String, String> entry : expectedQuerystringParameters.entrySet()) {
            Assert.assertTrue(Strings.CS.contains(searchUrl.getQuery(),
                                                   String.format("&%s=%s", entry.getKey(), entry.getValue())),
                              "Expected querystring parameter " + entry.getKey() + " was not present in generated URL");
        }
    }
}
