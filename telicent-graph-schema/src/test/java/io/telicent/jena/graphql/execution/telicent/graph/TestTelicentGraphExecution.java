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

import graphql.ExecutionResult;
import io.telicent.jena.graphql.execution.AbstractExecutionTests;
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import io.telicent.jena.graphql.server.model.GraphQLRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFParserBuilder;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sys.JenaSystem;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class TestTelicentGraphExecution extends AbstractExecutionTests {

    static {
        JenaSystem.init();
    }

    public static final String QUERY_BASE = "/queries/telicent/graph/";
    public static final String OBI_WAN_KENOBI = "https://starwars.com#person_Obi-WanKenobi";

    public static final String JABBA_THE_HUT = "https://starwars.com#hutt_JabbaDesilijicTiure";

    public static String loadQuery(String queryResource) {
        return loadQuery(QUERY_BASE, queryResource);
    }

    public static GraphQLRequest loadRequest(String requestResource) {
        return loadRequest(QUERY_BASE, requestResource);
    }

    public static final String SINGLE_NODE_QUERY = loadQuery("single-node.graphql");

    public static final String MULTIPLE_NODES_QUERY = loadQuery("multiple-nodes.graphql");

    public static final String INSTANCES_QUERY = loadQuery("instances.graphql");

    public static final String ALL_ENTITIES_QUERY = loadQuery("all-entities.graphql");

    public static final GraphQLRequest STATES_REQUEST = loadRequest("states.json");

    public static final String STATES_QUERY = loadQuery("states.graphql");

    public static final String SEARCH_QUERY = loadQuery("search.graphql");

    private final TelicentGraphExecutor starwars, falklands;

    public TestTelicentGraphExecution() throws IOException {
        DatasetGraph starwars = RDFParserBuilder.create()
                                                .lang(Lang.TURTLE)
                                                .source(TestTelicentGraphExecution.class.getResourceAsStream(
                                                        "/data/starwars.ttl"))
                                                .toDatasetGraph();
        this.starwars = new TelicentGraphExecutor(starwars);

        DatasetGraph sandyWoodward = RDFParserBuilder.create()
                                                     .lang(Lang.TURTLE)
                                                     .source(TestTelicentGraphExecution.class.getResourceAsStream(
                                                             "/data/sandy_woodward.ttl"))
                                                     .toDatasetGraph();
        this.falklands = new TelicentGraphExecutor(sandyWoodward);
    }

    @Test
    public void givenEmptyData_whenQueryingForSingleNode_thenEmptyResults() throws IOException {
        // Given
        DatasetGraph dsg = DatasetGraphFactory.empty();
        TelicentGraphExecutor execution = new TelicentGraphExecutor(dsg);

        // When
        ExecutionResult result = verifyExecution(execution, SINGLE_NODE_QUERY);

        // Then
        Assert.assertTrue(result.isDataPresent());
        Assert.assertNotNull(result.getData());
        Map<String, Object> data = result.getData();
        Assert.assertNull(data.get(TelicentGraphSchema.QUERY_SINGLE_NODE));
    }

    @Test
    public void givenStarWarsData_whenQueryingForSingleNode_thenSingleResult_andRelCountsAreAccurate() {
        // Given and When
        ExecutionResult result = verifyExecution(this.starwars, SINGLE_NODE_QUERY);

        // Then
        Assert.assertTrue(result.isDataPresent());
        Assert.assertNotNull(result.getData());
        Map<String, Object> data = result.getData();
        Assert.assertNotNull(data.get(TelicentGraphSchema.QUERY_SINGLE_NODE));
        data = (Map<String, Object>) data.get(TelicentGraphSchema.QUERY_SINGLE_NODE);
        verifyNodeResult(data, OBI_WAN_KENOBI, "starwars:person_Obi-WanKenobi");
        Assert.assertNotNull(data.get(TelicentGraphSchema.FIELD_INSTANCES));
        Assert.assertTrue(((List<Object>) data.get(TelicentGraphSchema.FIELD_INSTANCES)).isEmpty());

        // And
        Map<String, Object> relCounts = (Map<String, Object>) data.get(TelicentGraphSchema.FIELD_RELATIONSHIP_COUNTS);
        Assert.assertNotNull(relCounts);
        List<?> outRels = (List<?>)data.get(TelicentGraphSchema.FIELD_OUTBOUND_RELATIONSHIPS);
        List<?> inRels = (List<?>)data.get(TelicentGraphSchema.FIELD_INBOUND_RELATIONSHIPS);
        Assert.assertNotNull(outRels);
        Assert.assertNotNull(inRels);
        Assert.assertEquals(outRels.size(), (Integer)relCounts.get(TelicentGraphSchema.FIELD_OUT));
        Assert.assertEquals(inRels.size(), (Integer)relCounts.get(TelicentGraphSchema.FIELD_IN));
    }

    private static void verifyNodeResult(Map<String, Object> data, String fullUri, String shortUri) {
        Assert.assertEquals(data.get(TelicentGraphSchema.FIELD_URI), fullUri);
        Assert.assertEquals(data.get(TelicentGraphSchema.FIELD_ID), fullUri);
        Assert.assertEquals(data.get(TelicentGraphSchema.FIELD_SHORT_URI), shortUri);
    }

    @Test
    public void givenStarWarsData_whenQueryingForMultipleNodes_thenMultipleResults() {
        // Given and When
        ExecutionResult result = verifyExecution(this.starwars, MULTIPLE_NODES_QUERY);

        // Then
        Assert.assertTrue(result.isDataPresent());
        Assert.assertNotNull(result.getData());
        Map<String, Object> data = result.getData();
        List<Map<String, Object>> nodes = verifyStates(data, TelicentGraphSchema.QUERY_MULTIPLE_NODES, 2);
        data = nodes.get(0);
        verifyNodeResult(data, OBI_WAN_KENOBI, "starwars:person_Obi-WanKenobi");
        data = nodes.get(1);
        verifyNodeResult(data, JABBA_THE_HUT, "starwars:hutt_JabbaDesilijicTiure");
    }

    @Test
    public void givenStarWarsData_whenQueryingForInstances_thenInstanceResults() {
        // Given and When
        ExecutionResult result = verifyExecution(this.starwars, INSTANCES_QUERY);

        // Then
        Assert.assertTrue(result.isDataPresent());
        Assert.assertNotNull(result.getData());
        Map<String, Object> data = result.getData();
        Assert.assertNotNull(data.get(TelicentGraphSchema.QUERY_SINGLE_NODE));
        data = (Map<String, Object>) data.get(TelicentGraphSchema.QUERY_SINGLE_NODE);
        Assert.assertEquals(data.get(TelicentGraphSchema.FIELD_SHORT_URI), "ies:Person");
        Assert.assertNotNull(data.get(TelicentGraphSchema.FIELD_INSTANCES));
        List<Map<String, Object>> instances = (List<Map<String, Object>>) data.get(TelicentGraphSchema.FIELD_INSTANCES);
        Assert.assertFalse(instances.isEmpty());
    }

    @Test
    public void givenSearchQueryAndNoSearchBackend_whenExecutingGraphQL_thenGraphQLErrorResponseIsReturned() {
        // Given
        GraphQLRequest request = new GraphQLRequest();
        request.setQuery(SEARCH_QUERY);

        // When
        ExecutionResult result = this.starwars.execute(request);

        // Then
        Assert.assertFalse(result.getErrors().isEmpty());
        Assert.assertTrue(
                result.getErrors().stream().anyMatch(e -> StringUtils.contains(e.getMessage(), "unavailable")));
    }

    @Test
    public void givenStarWarsData_whenQueryingForLukeStates_thenStatesAreReturned() {
        // Given and When
        ExecutionResult result = verifyExecution(this.starwars, STATES_REQUEST);

        // Then
        Map<String, Object> data = result.getData();
        verifyStates(data, TelicentGraphSchema.QUERY_STATES, 71);
    }

    private static List<Map<String, Object>> verifyStates(Map<String, Object> data, String queryStates,
                                                          int expectedStates) {
        Assert.assertNotNull(data.get(queryStates));
        List<Map<String, Object>> states = (List<Map<String, Object>>) data.get(queryStates);
        Assert.assertFalse(states.isEmpty());
        Assert.assertEquals(states.size(), expectedStates);
        return states;
    }

    @Test
    public void givenStarWarsData_whenQueryingForTatooineStates_thenEmptyResults() {
        // Given
        GraphQLRequest request = new GraphQLRequest();
        request.setQuery(STATES_QUERY);
        request.setVariables(Map.of("stateUri", "https://starwars.com#planet_Tatooine"));
        request.setOperationName("State");

        // When
        ExecutionResult result = verifyExecution(this.starwars, request);

        // Then
        Map<String, Object> data = result.getData();
        Assert.assertNotNull(data.get(TelicentGraphSchema.QUERY_STATES));
        List<Map<String, Object>> states = (List<Map<String, Object>>) data.get(TelicentGraphSchema.QUERY_STATES);
        Assert.assertTrue(states.isEmpty());
    }

    @Test
    public void givenFalklandsData_whenQueryingForAdmiralWoodward_thenStatesAreReturned_andMarriageIsAState() {
        // Given
        GraphQLRequest request = new GraphQLRequest();
        request.setQuery(STATES_QUERY);
        request.setVariables(Map.of("stateUri", "http://telicent.io/data#AdmiralWoodward"));
        request.setOperationName("State");

        // When
        ExecutionResult result = verifyExecution(this.falklands, request);

        // Then
        Map<String, Object> data = result.getData();
        List<Map<String, Object>> states = verifyStates(data, TelicentGraphSchema.QUERY_STATES, 13);

        // And
        Assert.assertTrue(states.stream()
                                .anyMatch(s -> Objects.equals(s.get(TelicentGraphSchema.FIELD_URI),
                                                              "http://telicent.io/data#4cf1a38a-8c6f-45c9-be8f-915120279399")));
    }
}
