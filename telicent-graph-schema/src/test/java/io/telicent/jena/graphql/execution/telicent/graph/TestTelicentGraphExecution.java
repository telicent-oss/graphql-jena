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

    private final TelicentGraphExecutor executor;

    public TestTelicentGraphExecution() throws IOException {
        DatasetGraph dsg = RDFParserBuilder.create()
                                           .lang(Lang.TURTLE)
                                           .source(TestTelicentGraphExecution.class.getResourceAsStream(
                                                   "/data/starwars.ttl"))
                                           .toDatasetGraph();
        this.executor = new TelicentGraphExecutor(dsg);
    }

    @Test
    public void telicent_graph_empty_01() throws IOException {
        DatasetGraph dsg = DatasetGraphFactory.empty();
        TelicentGraphExecutor execution = new TelicentGraphExecutor(dsg);

        ExecutionResult result = verifyExecution(execution, SINGLE_NODE_QUERY);
        Assert.assertTrue(result.isDataPresent());
        Assert.assertNotNull(result.getData());

        Map<String, Object> data = result.getData();
        Assert.assertNull(data.get(TelicentGraphSchema.QUERY_SINGLE_NODE));
    }

    @Test
    public void telicent_graph_single_node_01() {
        ExecutionResult result = verifyExecution(this.executor, SINGLE_NODE_QUERY);
        Assert.assertTrue(result.isDataPresent());
        Assert.assertNotNull(result.getData());

        Map<String, Object> data = result.getData();
        Assert.assertNotNull(data.get(TelicentGraphSchema.QUERY_SINGLE_NODE));
        data = (Map<String, Object>) data.get(TelicentGraphSchema.QUERY_SINGLE_NODE);
        Assert.assertEquals(data.get(TelicentGraphSchema.FIELD_URI), OBI_WAN_KENOBI);
        Assert.assertEquals(data.get(TelicentGraphSchema.FIELD_ID), OBI_WAN_KENOBI);
        Assert.assertEquals(data.get(TelicentGraphSchema.FIELD_SHORT_URI), "starwars:person_Obi-WanKenobi");
        Assert.assertNotNull(data.get(TelicentGraphSchema.FIELD_INSTANCES));
        Assert.assertTrue(((List<Object>) data.get(TelicentGraphSchema.FIELD_INSTANCES)).isEmpty());
    }

    @Test
    public void telicent_graph_multiple_nodes_01() {
        ExecutionResult result = verifyExecution(this.executor, MULTIPLE_NODES_QUERY);
        Assert.assertTrue(result.isDataPresent());
        Assert.assertNotNull(result.getData());

        Map<String, Object> data = result.getData();
        Assert.assertNotNull(data.get(TelicentGraphSchema.QUERY_MULTIPLE_NODES));
        List<Map<String, Object>> nodes =
                (List<Map<String, Object>>) data.get(TelicentGraphSchema.QUERY_MULTIPLE_NODES);
        Assert.assertFalse(nodes.isEmpty());
        Assert.assertEquals(nodes.size(), 2);

        data = nodes.get(0);
        Assert.assertEquals(data.get(TelicentGraphSchema.FIELD_URI), OBI_WAN_KENOBI);
        Assert.assertEquals(data.get(TelicentGraphSchema.FIELD_ID), OBI_WAN_KENOBI);
        Assert.assertEquals(data.get(TelicentGraphSchema.FIELD_SHORT_URI), "starwars:person_Obi-WanKenobi");

        data = nodes.get(1);
        Assert.assertEquals(data.get(TelicentGraphSchema.FIELD_URI), JABBA_THE_HUT);
        Assert.assertEquals(data.get(TelicentGraphSchema.FIELD_ID), JABBA_THE_HUT);
        Assert.assertEquals(data.get(TelicentGraphSchema.FIELD_SHORT_URI), "starwars:hutt_JabbaDesilijicTiure");
    }

    @Test
    public void telicent_graph_instances_01() {
        ExecutionResult result = verifyExecution(this.executor, INSTANCES_QUERY);
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
        ExecutionResult result = this.executor.execute(request);

        // Then
        Assert.assertFalse(result.getErrors().isEmpty());
        Assert.assertTrue(
                result.getErrors().stream().anyMatch(e -> StringUtils.contains(e.getMessage(), "unavailable")));
    }

    @Test
    public void telicent_graph_states_01() {
        ExecutionResult result = verifyExecution(this.executor, STATES_REQUEST);

        Map<String, Object> data = result.getData();
        Assert.assertNotNull(data.get(TelicentGraphSchema.QUERY_STATES));
        List<Map<String, Object>> states = (List<Map<String, Object>>) data.get(TelicentGraphSchema.QUERY_STATES);
        Assert.assertFalse(states.isEmpty());
        Assert.assertEquals(states.size(), 71);
    }

    @Test
    public void telicent_graph_states_02() {
        GraphQLRequest request = new GraphQLRequest();
        request.setQuery(STATES_QUERY);
        request.setVariables(Map.of("stateUri", "https://starwars.com#planet_Tatooine"));
        request.setOperationName("State");
        ExecutionResult result = verifyExecution(this.executor, request);

        Map<String, Object> data = result.getData();
        Assert.assertNotNull(data.get(TelicentGraphSchema.QUERY_STATES));
        List<Map<String, Object>> states = (List<Map<String, Object>>) data.get(TelicentGraphSchema.QUERY_STATES);
        Assert.assertTrue(states.isEmpty());
    }
}
