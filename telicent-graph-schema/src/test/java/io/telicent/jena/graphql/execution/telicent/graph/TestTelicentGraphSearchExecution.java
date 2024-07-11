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
package io.telicent.jena.graphql.execution.telicent.graph;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import graphql.ExecutionResult;
import io.telicent.jena.graphql.execution.AbstractExecutionTests;
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFParserBuilder;
import org.apache.jena.sparql.core.DatasetGraph;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@SuppressWarnings("unchecked")
public class TestTelicentGraphSearchExecution extends AbstractExecutionTests {

    private static ObjectMapper MAPPER = new ObjectMapper();

    private static WireMockServer WIRE_MOCK_SERVER;

    @BeforeClass
    public static void setupClass() throws JsonProcessingException {
        WIRE_MOCK_SERVER = new WireMockServer(wireMockConfig().port(8181));
        WIRE_MOCK_SERVER.start();

        Map<String, ?> pagedData = Map.of("limit", "10", "offset", "20", "results", List.of(Map.of("document",
                                                                                                   Map.of("uri",
                                                                                                          TestTelicentGraphExecution.OBI_WAN_KENOBI))));
        WIRE_MOCK_SERVER.stubFor(get(urlEqualTo("/documents?query=test&limit=10&offset=20")).willReturn(
                ok().withBody(MAPPER.writeValueAsString(pagedData))));
    }

    @AfterClass
    public static void cleanUp() {
        WIRE_MOCK_SERVER.stop();
    }

    public static final String PAGED_SEARCH_QUERY = TestTelicentGraphExecution.loadQuery("paged-search.graphql");
    public static final String MALFORMED_PAGED_SEARCH_QUERY =
            TestTelicentGraphExecution.loadQuery("malformed-paged-search.graphql");

    private final TelicentGraphExecutor executor;

    public TestTelicentGraphSearchExecution() throws IOException {
        DatasetGraph dsg = RDFParserBuilder.create()
                                           .lang(Lang.TURTLE)
                                           .source(TestTelicentGraphSearchExecution.class.getResourceAsStream(
                                                   "/data/starwars.ttl"))
                                           .toDatasetGraph();
        this.executor = new TelicentGraphExecutor(dsg);
    }

    @Test
    public void givenPagedSearchQuery_whenExecuting_thenSuccess() {
        // Given and When
        ExecutionResult result = verifyExecution(this.executor, PAGED_SEARCH_QUERY);

        // Then
        Assert.assertTrue(result.isDataPresent());
        Assert.assertNotNull(result.getData());
        Map<String, Object> data = result.getData();
        Assert.assertNotNull(data.get(TelicentGraphSchema.QUERY_SEARCH));
        List<Map<String, Object>> searchResults =
                (List<Map<String, Object>>) data.get(TelicentGraphSchema.QUERY_SEARCH);
        Assert.assertFalse(searchResults.isEmpty());
        Assert.assertEquals(searchResults.size(), 1);
        Map<String, Object> firstResult = searchResults.get(0);
        Assert.assertEquals(firstResult.get("uri"), TestTelicentGraphExecution.OBI_WAN_KENOBI);
    }

    @Test
    public void givenMalformedPagedSearchQuery_whenExecuting_thenFails() {
        // Given and When
        ExecutionResult result = this.executor.execute(MALFORMED_PAGED_SEARCH_QUERY);

        // Then
        Assert.assertFalse(result.isDataPresent());
        Assert.assertFalse(result.getErrors().isEmpty());
        Assert.assertTrue(
                result.getErrors().stream().allMatch(e -> StringUtils.contains(e.getMessage(), "not a valid 'Int'")));
    }
}
