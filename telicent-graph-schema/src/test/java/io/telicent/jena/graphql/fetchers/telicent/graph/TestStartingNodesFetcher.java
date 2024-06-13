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

import graphql.execution.MergedField;
import graphql.language.Field;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentImpl;
import io.telicent.jena.graphql.execution.telicent.graph.TelicentExecutionContext;
import io.telicent.jena.graphql.schemas.telicent.graph.models.State;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.testng.annotations.Test;

import java.util.Map;

import static org.apache.jena.graph.NodeFactory.*;

public class TestStartingNodesFetcher {
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void test_get_illegalRawStart_null() {
        // given
        StartingNodesFetcher fetcher = new StartingNodesFetcher(false);
        DatasetGraph dsg  = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), IesFetchers.IS_START_OF, createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), IesFetchers.IS_START_OF, createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), IesFetchers.IS_START_OF, createURI("object")));
        MergedField mergedField = MergedField.newMergedField().addField(new Field("start")).build();
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .mergedField(mergedField)
                .source(new State(createBlankNode("object"), Node.ANY, Node.ANY))
                .build();
        // when
        // then
        fetcher.get(environment);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void test_get_illegalRawStart_nullMulti() {
        // given
        StartingNodesFetcher fetcher = new StartingNodesFetcher(true);
        DatasetGraph dsg  = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), IesFetchers.IS_START_OF, createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), IesFetchers.IS_START_OF, createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), IesFetchers.IS_START_OF, createURI("object")));
        MergedField mergedField = MergedField.newMergedField().addField(new Field("start")).build();
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .mergedField(mergedField)
                .source(new State(createBlankNode("object"), Node.ANY, Node.ANY))
                .build();
        // when
        // then
        fetcher.get(environment);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void test_get_illegalRawStart_badURIArgument() {
        // given
        StartingNodesFetcher fetcher = new StartingNodesFetcher(false);
        DatasetGraph dsg  = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), IesFetchers.IS_START_OF, createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), IesFetchers.IS_START_OF, createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), IesFetchers.IS_START_OF, createURI("object")));
        MergedField mergedField = MergedField.newMergedField().addField(new Field("start")).build();
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .mergedField(mergedField)
                .arguments(Map.of("graph", "_:uri", "uri", -1))
                .source(new State(createBlankNode("object"), Node.ANY, Node.ANY))
                .build();
        // when
        // then
        fetcher.get(environment);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void test_get_illegalRawStart_badURIArgumentList() {
        // given
        StartingNodesFetcher fetcher = new StartingNodesFetcher(true);
        DatasetGraph dsg  = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), IesFetchers.IS_START_OF, createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), IesFetchers.IS_START_OF, createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), IesFetchers.IS_START_OF, createURI("object")));
        MergedField mergedField = MergedField.newMergedField().addField(new Field("start")).build();
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .mergedField(mergedField)
                .arguments(Map.of("graph", "_:uri", "uris", "unsuitable"))
                .source(new State(createBlankNode("object"), Node.ANY, Node.ANY))
                .build();
        // when
        // then
        fetcher.get(environment);
    }
}
