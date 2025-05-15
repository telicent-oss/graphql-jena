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
import io.telicent.jena.graphql.schemas.telicent.graph.models.NodePlaceholder;
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

public class TestInstancesFetcher extends AbstractFetcherTests {
    @Test
    public void test_get_blankNode() throws Exception {
        // given
        InstancesFetcher fetcher = new InstancesFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createURI("object")));

        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new TelicentGraphNode(createBlankNode("object"), null));
        // when
        List<TelicentGraphNode> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }

    @Test
    public void test_get_uriNode() throws Exception {
        // given
        InstancesFetcher fetcher = new InstancesFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), RDF.type.asNode(),
                         createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), RDF.type.asNode(),
                         createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), RDF.type.asNode(),
                         createURI("object")));

        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new TelicentGraphNode(createURI("object"), null));
        // when
        List<TelicentGraphNode> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }

    @Test
    public void test_get_literalNode() throws Exception {
        // given
        InstancesFetcher fetcher = new InstancesFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject"), RDF.type.asNode(),
                         createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject"), RDF.type.asNode(),
                         createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject"), RDF.type.asNode(),
                         createURI("object")));

        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new TelicentGraphNode(createLiteralString("object"), null));
        // when
        List<TelicentGraphNode> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertTrue(actualList.isEmpty());
    }

    @Test
    public void givenGraphWithManyInstances_whenFetchingInstances_thenPagingIsApplied() throws Exception {
        // Given
        InstancesFetcher fetcher = new InstancesFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graph = createURI("graph");
        Node type = createURI("SomeType");
        generateManyInstances(dsg, graph, type);
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new TelicentGraphNode(type, null));

        // When
        List<TelicentGraphNode> types = fetcher.get(environment);

        // Then
        Assert.assertNotNull(types);
        Assert.assertEquals(types.size(), TelicentGraphSchema.DEFAULT_LIMIT);
    }

    @Test
    public void givenGraphWithManyInstances_whenCountingInstances_thenCountIsCorrect() throws Exception {
        // Given
        DataFetcher<Integer> fetcher = new InstancesCountFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graph = createURI("graph");
        Node type = createURI("SomeType");
        generateManyInstances(dsg, graph, type);
        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new NodePlaceholder(new TelicentGraphNode(type, null)));

        // When
        Integer count = fetcher.get(environment);

        // Then
        Assert.assertNotNull(count);
        Assert.assertEquals(count, 1_000);
    }


    private static void generateManyInstances(DatasetGraph dsg, Node graph, Node type) {
        for (int i = 0; i < 1_000; i++) {
            dsg.add(graph, createURI("subject" + i), RDF.type.asNode(), type);
        }
    }
}
