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
import io.telicent.jena.graphql.schemas.telicent.graph.models.NonDirectionalRelationship;
import io.telicent.jena.graphql.schemas.telicent.graph.models.State;
import io.telicent.jena.graphql.schemas.telicent.graph.models.StateRelationshipCounts;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.apache.jena.graph.NodeFactory.*;

public class TestStateRelationshipsFetcher {
    @Test
    public void givenStateRelationshipAssertedInSeveralGraphs_whenFetching_thenRelationshipAndCountAppearOnce() throws Exception {
        // Given
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node stateNode = createURI("state");
        Node target = createURI("target");
        Node predicate = createURI("predicate");
        State state = new State(stateNode, Node.ANY, Node.ANY);
        for (String graph : List.of("graph-a", "graph-b", "graph-c")) {
            dsg.add(new Quad(createURI(graph), stateNode, predicate, target));
        }

        // When
        List<NonDirectionalRelationship> relationships = new StateRelationshipsFetcher().get(
                prepareEnvironment(dsg, state));
        Integer count = new StateRelationshipCountsFetcher().get(
                prepareEnvironment(dsg, new StateRelationshipCounts(state)));

        // Then
        Assert.assertEquals(relationships.size(), 1);
        Assert.assertEquals(count, Integer.valueOf(1));
    }

    private static DataFetchingEnvironment prepareEnvironment(DatasetGraph dsg, Object source) {
        return DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                          .localContext(new TelicentExecutionContext(dsg, ""))
                                          .source(source)
                                          .build();
    }

    @Test
    public void test_get_literalNode() throws Exception {
        // given
        StateRelationshipsFetcher fetcher = new StateRelationshipsFetcher();
        DatasetGraph dsg  = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject"), createLiteralString("predicate1"), createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject"), createLiteralString("predicate2"), createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject"), createLiteralString("predicate3"), createURI("object")));

        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .source(new State(createLiteralString("subject"), Node.ANY, Node.ANY))
                .build();
        // when
        List<NonDirectionalRelationship>actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }

    @Test
    public void test_get_blankNode() throws Exception {
        // given
        StateRelationshipsFetcher fetcher = new StateRelationshipsFetcher();
        DatasetGraph dsg  = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), createLiteralString("predicate1"), createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), createLiteralString("predicate2"), createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), createLiteralString("predicate3"), createURI("object")));

        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .source(new State(createBlankNode("subject"), Node.ANY, Node.ANY))
                .build();
        // when
        List<NonDirectionalRelationship>actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }

    @Test
    public void test_get_uriNode_outbound() throws Exception {
        // given
        StateRelationshipsFetcher fetcher = new StateRelationshipsFetcher();
        DatasetGraph dsg  = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), createLiteralString("predicate1"), createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), createLiteralString("predicate2"), createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), createLiteralString("predicate3"), createURI("object")));

        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .source(new State(createURI("subject"), Node.ANY, Node.ANY))
                .build();
        // when
        List<NonDirectionalRelationship> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }

    @Test
    public void test_get_uriNode_inbound() throws Exception {
        // given
        StateRelationshipsFetcher fetcher = new StateRelationshipsFetcher();
        DatasetGraph dsg  = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), RDF.type.asNode(), createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), RDF.type.asNode(), createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(), createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject"), RDF.type.asNode(), createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), RDF.type.asNode(), createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), createURI("predicate"), createURI("object")));

        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .source(new State(createURI("object"), Node.ANY, Node.ANY))
                .build();
        // when
        List<NonDirectionalRelationship> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }
}
