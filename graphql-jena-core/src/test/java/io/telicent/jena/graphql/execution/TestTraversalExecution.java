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
package io.telicent.jena.graphql.execution;

import graphql.ExecutionResult;
import io.telicent.jena.graphql.schemas.TraversalSchema;
import io.telicent.jena.graphql.schemas.models.NodeKind;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFParserBuilder;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class TestTraversalExecution extends AbstractExecutionTests {

    private static final String QUERY_BASE = "/queries/traversal/";
    public static final String ADAM_ID = "https://example.org/Adam";
    public static final String BENJAMIN_ID = "https://example.org/Benjamin";
    public static final String CHARLIE_ID = "https://example.org/Charlie";
    public static final String DANIEL_ID = "https://example.org/Daniel";
    public static final String EVE_ID = "https://example.org/Eve";

    private static String loadQuery(String queryResource) {
        return loadQuery(QUERY_BASE, queryResource);
    }

    private static final String SIMPLE_TRAVERSAL = loadQuery("simple-traversal.graphql");

    private static final String NAMES_TRAVERSAL = loadQuery("names.graphql");

    private static final String FRIENDS_TRAVERSAL = loadQuery("friends.graphql");

    private static final String LITERALS_TRAVERSAL = loadQuery("literals.graphql");

    private static final String BLANKS_TRAVERSAL = loadQuery("blanks.graphql");

    private static void verifyTargets(List<Object> edges, List<String> expected) {
        for (Object edge : edges) {
            Assert.assertTrue(edge instanceof Map<?, ?>);
            Map<String, Object> traversalEdge = (Map<String, Object>) edge;
            Assert.assertTrue(traversalEdge.containsKey(TraversalSchema.TARGET_FIELD));
            Map<String, Object> target = (Map<String, Object>) traversalEdge.get(TraversalSchema.TARGET_FIELD);
            Assert.assertTrue(target.containsKey(TraversalSchema.NODE_FIELD));
            Map<String, Object> node = (Map<String, Object>) target.get(TraversalSchema.NODE_FIELD);
            Assert.assertTrue(node.containsKey(TraversalSchema.VALUE_FIELD));
            String value = (String) node.get(TraversalSchema.VALUE_FIELD);
            Assert.assertTrue(expected.contains(value));
        }
    }

    private final TraversalExecutor executor;

    public TestTraversalExecution() throws IOException {
        DatasetGraph dsg = RDFParserBuilder.create()
                                           .lang(Lang.TRIG)
                                           .source(TestTraversalExecution.class.getResourceAsStream(
                                                   "/data/traversals.trig"))
                                           .toDatasetGraph();
        this.executor = new TraversalExecutor(dsg);
    }

    @Test
    public void traversal_empty_01() throws IOException {
        DatasetGraph dsg = DatasetGraphFactory.empty();
        TraversalExecutor execution = new TraversalExecutor(dsg);

        ExecutionResult result = verifyExecution(execution, SIMPLE_TRAVERSAL);
        List<Object> nodes = (List<Object>) ((Map<String, Object>) result.getData()).get(TraversalSchema.NODES_FIELD);
        Assert.assertTrue(nodes.isEmpty());
    }

    @Test
    public void traversal_01() {
        ExecutionResult result = verifyExecution(this.executor, SIMPLE_TRAVERSAL);
        Map<String, Object> data = result.getData();
        Assert.assertTrue(data.containsKey(TraversalSchema.NODES_FIELD));

        List<Object> nodes = (List<Object>) data.get(TraversalSchema.NODES_FIELD);
        Assert.assertFalse(nodes.isEmpty());
        Assert.assertEquals(nodes.size(), 1);

        Map<String, Object> node = (Map<String, Object>) nodes.get(0);
        Assert.assertNotNull(node);
        verifyNode(node, TraversalSchema.NODE_FIELD, NodeKind.URI, ADAM_ID, null, null);
        Assert.assertTrue(node.containsKey(TraversalSchema.OUTGOING_FIELD));
        List<Object> outgoing = (List<Object>) node.get(TraversalSchema.OUTGOING_FIELD);
        Assert.assertFalse(outgoing.isEmpty());
        Assert.assertEquals(outgoing.size(), 6);

        verifyTargets(outgoing, List.of(FOAF.Person.getURI(), "Adam", BENJAMIN_ID, CHARLIE_ID, DANIEL_ID, EVE_ID));
    }

    @Test
    public void traversal_02() {
        ExecutionResult result = verifyExecution(this.executor, NAMES_TRAVERSAL);
        Map<String, Object> data = result.getData();
        Assert.assertTrue(data.containsKey(TraversalSchema.NODES_FIELD));

        List<Object> nodes = (List<Object>) data.get(TraversalSchema.NODES_FIELD);
        Assert.assertFalse(nodes.isEmpty());
        Assert.assertEquals(nodes.size(), 5);

        for (Object node : nodes) {
            Map<String, Object> traversalNode = (Map<String, Object>) node;
            Assert.assertTrue(traversalNode.containsKey(TraversalSchema.NODE_FIELD));
            Assert.assertTrue(traversalNode.containsKey(TraversalSchema.OUTGOING_FIELD));

            String id =
                    (String) ((Map<String, Object>) traversalNode.get(TraversalSchema.NODE_FIELD)).get(
                            TraversalSchema.VALUE_FIELD);
            String name = id.substring(id.lastIndexOf('/') + 1);
            verifyTargets((List<Object>) traversalNode.get(TraversalSchema.OUTGOING_FIELD), List.of(name));
        }
    }

    @Test
    public void traversal_03() {
        ExecutionResult result = verifyExecution(this.executor, FRIENDS_TRAVERSAL);
        Map<String, Object> data = result.getData();
        Assert.assertTrue(data.containsKey(TraversalSchema.NODES_FIELD));

        List<Object> nodes = (List<Object>) data.get(TraversalSchema.NODES_FIELD);
        Assert.assertFalse(nodes.isEmpty());
        Assert.assertEquals(nodes.size(), 1);

        Map<String, Object> traversalNode = (Map<String, Object>) nodes.get(0);
        Assert.assertTrue(traversalNode.containsKey(TraversalSchema.NODE_FIELD));
        Assert.assertTrue(traversalNode.containsKey(TraversalSchema.OUTGOING_FIELD));
        Assert.assertTrue(traversalNode.containsKey(TraversalSchema.INCOMING_FIELD));

        verifyTargets((List<Object>) traversalNode.get(TraversalSchema.OUTGOING_FIELD),
                      List.of(BENJAMIN_ID, CHARLIE_ID, DANIEL_ID, EVE_ID));
        verifyTargets((List<Object>) traversalNode.get(TraversalSchema.INCOMING_FIELD),
                      List.of(BENJAMIN_ID, CHARLIE_ID, DANIEL_ID));
    }

    @Test
    public void traversal_04() {
        ExecutionResult result = verifyExecution(this.executor, LITERALS_TRAVERSAL);
        Map<String, Object> data = result.getData();
        Assert.assertTrue(data.containsKey(TraversalSchema.NODES_FIELD));

        List<Object> nodes = (List<Object>) data.get(TraversalSchema.NODES_FIELD);
        Assert.assertFalse(nodes.isEmpty());
        Assert.assertEquals(nodes.size(), 5);

        for (Object node : nodes) {
            Map<String, Object> traversalNode = (Map<String, Object>) node;
            Assert.assertTrue(traversalNode.containsKey(TraversalSchema.NODE_FIELD));
            Assert.assertTrue(traversalNode.containsKey(TraversalSchema.OUTGOING_FIELD));

            String id =
                    (String) ((Map<String, Object>) traversalNode.get(TraversalSchema.NODE_FIELD)).get(
                            TraversalSchema.VALUE_FIELD);
            String name = id.substring(id.lastIndexOf('/') + 1);
            verifyTargets((List<Object>) traversalNode.get(TraversalSchema.OUTGOING_FIELD), List.of(name));
        }
    }

    @Test
    public void traversal_05() {
        ExecutionResult result = verifyExecution(this.executor, BLANKS_TRAVERSAL);
        Map<String, Object> data = result.getData();
        Assert.assertTrue(data.containsKey(TraversalSchema.NODES_FIELD));

        List<Object> nodes = (List<Object>) data.get(TraversalSchema.NODES_FIELD);
        Assert.assertFalse(nodes.isEmpty());
        Assert.assertEquals(nodes.size(), 5);

        for (Object node : nodes) {
            Map<String, Object> traversalNode = (Map<String, Object>) node;
            Assert.assertTrue(traversalNode.containsKey(TraversalSchema.NODE_FIELD));
            Assert.assertTrue(traversalNode.containsKey(TraversalSchema.OUTGOING_FIELD));

            List<Object> outgoing = (List<Object>) traversalNode.get(TraversalSchema.OUTGOING_FIELD);
            // No outgoing edges go to blank nodes
            Assert.assertNull(outgoing);
        }
    }
}
