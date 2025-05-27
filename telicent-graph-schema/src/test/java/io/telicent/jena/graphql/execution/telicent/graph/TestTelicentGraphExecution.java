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
import io.telicent.jena.graphql.fetchers.telicent.graph.IesFetchers;
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import io.telicent.jena.graphql.server.model.GraphQLRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFParserBuilder;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sys.JenaSystem;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

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

    public static final String PAGED_NODE_QUERY = loadQuery("single-node-with-paging.graphql");

    public static final String FILTERED_NODE_QUERY = loadQuery("single-node-with-filters.graphql");

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
        verifyRelCounts(data, TelicentGraphSchema.FIELD_OUTBOUND_RELATIONSHIPS, (int) TelicentGraphSchema.DEFAULT_LIMIT,
                        1);
        verifyRelCounts(data, TelicentGraphSchema.FIELD_INBOUND_RELATIONSHIPS, (int) TelicentGraphSchema.DEFAULT_LIMIT,
                        1);
        verifyRelCounts(data, TelicentGraphSchema.FIELD_PROPERTIES, (int) TelicentGraphSchema.DEFAULT_LIMIT, 1);
        verifyRelCounts(data, TelicentGraphSchema.FIELD_INSTANCES, (int) TelicentGraphSchema.DEFAULT_LIMIT, 1);
    }

    @DataProvider(name = "paging")
    public Object[][] pageParameters() {
        return new Object[][] {
                // Limit 10 with varying offsets
                { 10, 1 }, { 10, 2 }, { 10, 3 }, { 10, 4 }, { 10, 5 }, { 10, 100 },
                // Limit 25 paging through
                { 25, 1 }, { 25, 26 }, { 25, 51 },
                // Limit 100 with varying offsets
                { 100, 1 }, { 100, 50 }, { 100, 100 }
        };
    }

    private int verifyRelCounts(Map<String, Object> data, String field, int limit, int offset) {
        Map<String, Object> relCounts = (Map<String, Object>) data.get(TelicentGraphSchema.FIELD_RELATIONSHIP_COUNTS);
        Assert.assertNotNull(relCounts);
        List<?> fieldData = (List<?>) data.get(field);
        Assert.assertNotNull(fieldData);
        verifyPagedResults(fieldData, (Integer) relCounts.get(field), limit, offset);

        return fieldData.size();
    }

    @Test(dataProvider = "paging")
    public void givenStarWarsData_whenQueryingForSingleNodeWithPaging_thenPagedResultsCorrectly(int limit, int offset) {
        // Given and When
        ExecutionResult result =
                verifyExecution(this.starwars, PAGED_NODE_QUERY, Map.of("limit", limit, "offset", offset));

        // Then
        Assert.assertTrue(result.isDataPresent());
        Assert.assertNotNull(result.getData());
        Map<String, Object> data = result.getData();
        Assert.assertNotNull(data.get(TelicentGraphSchema.QUERY_SINGLE_NODE));
        data = (Map<String, Object>) data.get(TelicentGraphSchema.QUERY_SINGLE_NODE);
        verifyNodeResult(data, OBI_WAN_KENOBI, "starwars:person_Obi-WanKenobi");
        verifyRelCounts(data, TelicentGraphSchema.FIELD_OUTBOUND_RELATIONSHIPS, limit, offset);
        verifyRelCounts(data, TelicentGraphSchema.FIELD_INBOUND_RELATIONSHIPS, limit, offset);
        verifyRelCounts(data, TelicentGraphSchema.FIELD_PROPERTIES, limit, offset);
        verifyRelCounts(data, TelicentGraphSchema.FIELD_INSTANCES, limit, offset);
    }

    static void updatePagedResults(Map<String, Object> data, Map<String, Set<String>> pagedResults, String field,
                                   String subField) {
        List<Map<String, Object>> list = (List<Map<String, Object>>) data.get(field);
        Set<String> resultSet = pagedResults.computeIfAbsent(field, k -> new HashSet<>());
        for (Map<String, Object> item : list) {
            resultSet.add(item.get(subField).toString());
        }
    }

    @DataProvider(name = "pageSizes")
    private static Object[][] pageSizes() {
        return new Object[][] {
                { 1 }, { 5 }, { 10 }, { 25 }, { 50 }, { 100 }, { (int) TelicentGraphSchema.MAX_LIMIT }, { 5_000 }
        };
    }

    @Test(dataProvider = "pageSizes")
    public void givenStarWarsData_whenQueryingForSingleNodeWithSequentialPaging_thenPagedResultsCorrectly(
            int pageSize) {
        // Given
        Map<String, Set<String>> pagedResults = new HashMap<>();
        int offset = 1;
        int pagesRetrieved = 0;
        int expectedPages = 68 > pageSize ? (68 / pageSize) + (68 % pageSize != 0 ? 1 : 0) + 1 : 2;

        if (pageSize > TelicentGraphSchema.MAX_LIMIT) {
            GraphQLRequest request = new GraphQLRequest();
            request.setQuery(PAGED_NODE_QUERY);
            request.setVariables(Map.of("limit", pageSize, "offset", offset));
            ExecutionResult failedResult = verifyExecutionErrors(this.starwars, request);
            Assert.assertTrue(failedResult.getErrors()
                                          .stream()
                                          .anyMatch(e -> e.getMessage().contains("exceeds the maximum limit")));
            return;
        }

        // When
        while (true) {
            ExecutionResult result =
                    verifyExecution(this.starwars, PAGED_NODE_QUERY, Map.of("limit", pageSize, "offset", offset));
            pagesRetrieved++;

            Assert.assertTrue(result.isDataPresent());
            Assert.assertNotNull(result.getData());
            Map<String, Object> data = result.getData();
            Assert.assertNotNull(data.get(TelicentGraphSchema.QUERY_SINGLE_NODE));
            data = (Map<String, Object>) data.get(TelicentGraphSchema.QUERY_SINGLE_NODE);
            verifyNodeResult(data, OBI_WAN_KENOBI, "starwars:person_Obi-WanKenobi");
            int outRels = verifyRelCounts(data, TelicentGraphSchema.FIELD_OUTBOUND_RELATIONSHIPS, pageSize, offset);
            updatePagedResults(data, pagedResults, TelicentGraphSchema.FIELD_OUTBOUND_RELATIONSHIPS,
                               TelicentGraphSchema.FIELD_ID);
            int inRels = verifyRelCounts(data, TelicentGraphSchema.FIELD_INBOUND_RELATIONSHIPS, pageSize, offset);
            updatePagedResults(data, pagedResults, TelicentGraphSchema.FIELD_INBOUND_RELATIONSHIPS,
                               TelicentGraphSchema.FIELD_ID);
            int props = verifyRelCounts(data, TelicentGraphSchema.FIELD_PROPERTIES, pageSize, offset);
            updatePagedResults(data, pagedResults, TelicentGraphSchema.FIELD_PROPERTIES,
                               TelicentGraphSchema.FIELD_VALUE);
            int instances = verifyRelCounts(data, TelicentGraphSchema.FIELD_INSTANCES, pageSize, offset);
            updatePagedResults(data, pagedResults, TelicentGraphSchema.FIELD_INSTANCES, TelicentGraphSchema.FIELD_ID);

            // Increment page size for next go around
            offset += pageSize;

            // Check whether to stop asking for more pages, basically are all the fields we care about now empty lists?
            if (outRels + inRels + props + instances == 0) {
                break;
            }
        }

        // Then
        Assert.assertEquals(pagedResults.get(TelicentGraphSchema.FIELD_INBOUND_RELATIONSHIPS).size(), 68);
        Assert.assertEquals(pagedResults.get(TelicentGraphSchema.FIELD_OUTBOUND_RELATIONSHIPS).size(), 4);
        Assert.assertEquals(pagedResults.get(TelicentGraphSchema.FIELD_PROPERTIES).size(), 3);
        Assert.assertEquals(pagedResults.get(TelicentGraphSchema.FIELD_INSTANCES).size(), 0);
        Assert.assertEquals(pagesRetrieved, expectedPages);
    }

    private static void verifyPagedResults(List<?> actualResults, Integer totalResults, long limit, int offset) {
        Assert.assertNotNull(actualResults);
        Assert.assertNotNull(totalResults);

        // Calculate how many results we expect to see in this page
        int expected;
        if (limit > totalResults) {
            if (offset == 1) {
                // Limit greater than total available, and offset=1, so expect all results
                expected = totalResults;
            } else if (offset > totalResults) {
                // Offset greater than total available, so expect no results
                expected = 0;
            } else {
                // Limit greater than total available, and offset>1 so expect fewer than total results
                expected = totalResults - offset + 1;
            }
        } else {
            if (offset == 1) {
                // More results than limit, and offset=1, so expect limit results
                expected = Math.toIntExact(limit);
            } else if (offset > totalResults) {
                // Offset greater than total available, so expect no results
                expected = 0;
            } else {
                expected = Math.toIntExact(Math.min(totalResults - offset + 1, limit));
            }
        }
        Assert.assertEquals(actualResults.size(), expected);
    }

    @DataProvider(name = "filters")
    private Object[][] filters() {
        Map<String, Object> isStateOfFilter =
                Map.of(TelicentGraphSchema.ARGUMENT_MODE, "INCLUDE", TelicentGraphSchema.ARGUMENT_VALUES,
                       List.of(IesFetchers.IS_STATE_OF.getURI()));
        Map<String, Object> isStartOfFilter =
                Map.of(TelicentGraphSchema.ARGUMENT_MODE, "INCLUDE", TelicentGraphSchema.ARGUMENT_VALUES,
                       List.of(IesFetchers.IS_START_OF.getURI()));
        Map<String, Object> birthStateTypeFilter =
                Map.of(TelicentGraphSchema.ARGUMENT_MODE, "INCLUDE", TelicentGraphSchema.ARGUMENT_VALUES,
                       List.of(IesFetchers.iesTerm("BirthState").getURI()));
        Map<String, Object> birthOrDeathStateTypeFilter =
                Map.of(TelicentGraphSchema.ARGUMENT_MODE, "INCLUDE", TelicentGraphSchema.ARGUMENT_VALUES,
                       List.of(IesFetchers.iesTerm("BirthState").getURI(), IesFetchers.iesTerm("DeathState").getURI()));
        Map<String, Object> birthOrDeathNodeFilter =
                Map.of(TelicentGraphSchema.ARGUMENT_MODE, "INCLUDE", TelicentGraphSchema.ARGUMENT_VALUES,
                       List.of("https://starwars.com#person_Obi-WanKenobi_BIRTH",
                               "https://starwars.com#person_Obi-WanKenobi_DEATH"));

        return new Object[][] {
                {
                        Map.of("predFilter", isStateOfFilter), 50, 0
                },
                // While a BirthState exists, it is not linked via isStateOf predicate
                {
                        Map.of("predFilter", isStateOfFilter, "typeFilter", birthStateTypeFilter), 0, 0
                },
                // BirthState exists and linked via isStartOfPredicate
                {
                        Map.of("predFilter", isStartOfFilter, "typeFilter", birthStateTypeFilter), 1, 0
                },
                // Birth and Death State exists
                {
                        Map.of("typeFilter", birthOrDeathStateTypeFilter), 2, 0
                },
                // Only relationships involving the Birth or Death State nodes
                {
                        Map.of("nodeFilter", birthOrDeathNodeFilter), 2, 0
                }
        };
    }

    @Test(dataProvider = "filters")
    public void givenStarWarsData_whenQueryingForSingleNodeWithFilters_thenResultsFilteredCorrectly(
            Map<String, Object> variables, int expectedInRels, int expectedOutRels) {
        // Given and When
        ExecutionResult result = verifyExecution(this.starwars, FILTERED_NODE_QUERY, variables);

        // Then
        Assert.assertTrue(result.isDataPresent());
        Assert.assertNotNull(result.getData());
        Map<String, Object> data = result.getData();
        Assert.assertNotNull(data.get(TelicentGraphSchema.QUERY_SINGLE_NODE));
        data = (Map<String, Object>) data.get(TelicentGraphSchema.QUERY_SINGLE_NODE);
        verifyNodeResult(data, OBI_WAN_KENOBI, "starwars:person_Obi-WanKenobi");
        int outRels = verifyRelCounts(data, TelicentGraphSchema.FIELD_OUTBOUND_RELATIONSHIPS,
                                      (int) TelicentGraphSchema.DEFAULT_LIMIT, 1);
        Assert.assertEquals(outRels, expectedOutRels);
        int inRels = verifyRelCounts(data, TelicentGraphSchema.FIELD_INBOUND_RELATIONSHIPS,
                                     (int) TelicentGraphSchema.DEFAULT_LIMIT, 1);
        Assert.assertEquals(inRels, expectedInRels);
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
    public void givenStarWarsData_whenQueryingForInstances_thenInstanceResults_andRelCountsAreCorrect() {
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

        // And
        verifyRelCounts(data, TelicentGraphSchema.FIELD_INSTANCES, 10, 1);
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
        verifyStates(data, TelicentGraphSchema.QUERY_STATES, 50);
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
