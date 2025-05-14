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

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import io.telicent.jena.graphql.schemas.telicent.graph.models.RelationshipCounts;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.apache.jena.graph.NodeFactory.*;

public class TestNodeTypesFetcher extends AbstractFetcherTests {

    @Test
    public void test_get_blankNode() throws Exception {
        // given
        NodeTypesFetcher fetcher = new NodeTypesFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createURI("object")));

        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new TelicentGraphNode(createBlankNode("subject"), null));
        // when
        List<TelicentGraphNode> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }

    @Test
    public void test_get_uriNode() throws Exception {
        // given
        NodeTypesFetcher fetcher = new NodeTypesFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), RDF.type.asNode(),
                         createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), RDF.type.asNode(),
                         createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), RDF.type.asNode(),
                         createURI("object")));

        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new TelicentGraphNode(createURI("subject"), null));
        // when
        List<TelicentGraphNode> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }

    @Test
    public void test_get_literalNode() throws Exception {
        // given
        NodeTypesFetcher fetcher = new NodeTypesFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject"), RDF.type.asNode(),
                         createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject"), RDF.type.asNode(),
                         createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject"), RDF.type.asNode(),
                         createURI("object")));

        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new TelicentGraphNode(createLiteralString("subject"), null));
        // when
        List<TelicentGraphNode> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }

    @Test
    public void givenGraphWithManyTypes_whenFetchingNodeTypes_thenPagingIsApplied() throws Exception {
        // Given
        NodeTypesFetcher fetcher = new NodeTypesFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graph = createURI("graph");
        Node subject = createURI("subject");
        generateManyTypes(dsg, graph, subject);
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new TelicentGraphNode(subject, null));

        // When
        List<TelicentGraphNode> types = fetcher.get(environment);

        // Then
        Assert.assertNotNull(types);
        Assert.assertEquals(types.size(), TelicentGraphSchema.DEFAULT_LIMIT);
    }

    @Test
    public void givenGraphWithManyTypes_whenCountingNodeTypes_thenCountIsCorrect() throws Exception {
        // Given
        DataFetcher<Integer> fetcher = new NodeTypeCountsFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graph = createURI("graph");
        Node subject = createURI("subject");
        generateManyTypes(dsg, graph, subject);
        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new RelationshipCounts(new TelicentGraphNode(subject, null)));

        // When
        Integer count = fetcher.get(environment);

        // Then
        Assert.assertNotNull(count);
        Assert.assertEquals(count, 1_000);
    }

    private static void generateManyTypes(DatasetGraph dsg, Node graph, Node subject) {
        for (int i = 0; i < 1_000; i++) {
            dsg.add(graph, subject, RDF.type.asNode(), createURI("type" + i));
        }
    }

}
