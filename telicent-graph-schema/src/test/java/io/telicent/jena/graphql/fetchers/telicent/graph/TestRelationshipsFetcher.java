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
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.telicent.graph.models.Relationship;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.apache.jena.graph.NodeFactory.*;

public class TestRelationshipsFetcher {
    @Test
    public void test_get_blankNode() {
        // given
        RelationshipsFetcher fetcher = new RelationshipsFetcher(EdgeDirection.IN);
        DatasetGraph dsg  = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject1"), createLiteralString("predicate1"), createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject2"), createLiteralString("predicate2"), createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject3"), createLiteralString("predicate3"), createURI("object")));

        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .source(new TelicentGraphNode(createBlankNode("object"), null))
                .build();
        // when
        List<Relationship> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }

    @Test
    public void test_get_uriNode() {
        // given
        RelationshipsFetcher fetcher = new RelationshipsFetcher(EdgeDirection.IN);
        DatasetGraph dsg  = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject1"), createLiteralString("predicate1"), createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject2"), createLiteralString("predicate2"), createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createLiteralString("subject3"), createLiteralString("predicate3"), createURI("object")));

        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .source(new TelicentGraphNode(createURI("object"), null))
                .build();
        // when
        List<Relationship> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }
}
