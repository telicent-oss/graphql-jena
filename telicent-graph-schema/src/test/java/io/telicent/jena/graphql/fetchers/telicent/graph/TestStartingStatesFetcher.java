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
import io.telicent.jena.graphql.schemas.telicent.graph.models.State;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.telicent.jena.graphql.fetchers.telicent.graph.IesFetchers.*;
import static org.apache.jena.graph.NodeFactory.*;

public class TestStartingStatesFetcher {
    @Test
    public void test_get_blankNode() {
        // given
        StartingStatesFetcher fetcher = new StartingStatesFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), iesTerm("isStateOf"),
                         createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"),  IS_START_OF,
                         createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"),  IS_END_OF,
                         createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"),  iesTerm("isPartOf"),
                         createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"),  iesTerm("isParticipant"),
                         createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("mismatch"),  IS_END_OF,
                         createURI("object")));


        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .arguments(Map.of("uri", "object"))
                .source(new TelicentGraphNode(createBlankNode("object"), null))
                .build();
        // when
        List<State> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }

    @Test
    public void test_get_uriNode() {
        // given

        StartingStatesFetcher fetcher = new StartingStatesFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), iesTerm("isStateOf"),
                         createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"),  IS_START_OF,
                         createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"),  IS_END_OF,
                         createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"),  iesTerm("isPartOf"),
                         createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"),  iesTerm("isParticipant"),
                         createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), RDF.type.asNode(),
                         createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), RDF.type.asNode(),
                         createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), RDF.type.asNode(),
                         createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("mismatch"),  IS_END_OF,
                         createURI("object")));

        
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .arguments(Map.of("uri", "object"))
                .source(new TelicentGraphNode(createURI("object"), null))
                .build();
        // when
        List<State> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }

    @Test
    public void test_get_literalNode() {
        // given
        StartingStatesFetcher fetcher = new StartingStatesFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject"), iesTerm("isStateOf"),
                         createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject1"),  IS_START_OF,
                         createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject2"),  IS_END_OF,
                         createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject3"),  iesTerm("isPartOf"),
                         createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject4"),  iesTerm("isParticipant"),
                         createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject5"), RDF.type.asNode(),
                         createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createURI("subject"), RDF.type.asNode(),
                         createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("mismatch"),  IS_END_OF,
                         createURI("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("mismatch"),  RDF.type.asNode(),
                         createURI("object")));


        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .arguments(Map.of("uri", "object"))
                .source(new TelicentGraphNode(createLiteralString("object"), null))
                .build();
        // when
        List<State> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertTrue(actualList.isEmpty());
    }
}
