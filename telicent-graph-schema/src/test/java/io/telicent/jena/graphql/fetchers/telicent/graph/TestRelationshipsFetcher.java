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
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import io.telicent.jena.graphql.schemas.telicent.graph.models.Relationship;
import io.telicent.jena.graphql.schemas.telicent.graph.models.NodePlaceholder;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.apache.jena.graph.NodeFactory.*;

public class TestRelationshipsFetcher extends AbstractFetcherTests {
    @Test
    public void test_get_blankNode() throws Exception {
        // given
        RelationshipsFetcher fetcher = new RelationshipsFetcher(EdgeDirection.IN);
        DatasetGraph dsg  = DatasetGraphFactory.create();
        dsg.add(new Quad(createURI("graph"), createURI("subject1"), createURI("predicate1"), createLiteralString("object")));
        dsg.add(new Quad(createURI("graph"), createURI("subject2"), createURI("predicate2"), createBlankNode("object")));
        dsg.add(new Quad(createURI("graph"), createURI("subject3"), createURI("predicate3"), createURI("object")));
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new TelicentGraphNode(createBlankNode("object"), null));

        // when
        List<Relationship> actualList = fetcher.get(environment);

        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }

    @Test
    public void test_get_uriNode() throws Exception {
        // given
        RelationshipsFetcher fetcher = new RelationshipsFetcher(EdgeDirection.IN);
        DatasetGraph dsg  = DatasetGraphFactory.create();
        dsg.add(new Quad(createURI("graph"), createURI("subject1"), createURI("predicate1"), createLiteralString("object")));
        dsg.add(new Quad(createURI("graph"), createURI("subject2"), createURI("predicate2"), createBlankNode("object")));
        dsg.add(new Quad(createURI("graph"), createURI("subject3"), createURI("predicate3"), createURI("object")));
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new TelicentGraphNode(createURI("object"), null));

        // when
        List<Relationship> actualList = fetcher.get(environment);

        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }

    @Test
    public void givenGraphWithManyRelationships_whenFetchingNodeTypes_thenPagingIsApplied() throws Exception {
        // Given
        RelationshipsFetcher inFetcher = new RelationshipsFetcher(EdgeDirection.IN);
        RelationshipsFetcher outFetcher = new RelationshipsFetcher(EdgeDirection.OUT);
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graph = createURI("graph");
        Node subject = createURI("subject");
        generateManyRelationships(dsg, graph, subject);
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new TelicentGraphNode(subject, null));

        // When
        List<Relationship> inRels = inFetcher.get(environment);
        List<Relationship> outRels = outFetcher.get(environment);

        // Then
        Assert.assertNotNull(inRels);
        Assert.assertEquals(inRels.size(), TelicentGraphSchema.DEFAULT_LIMIT);
        Assert.assertNotNull(outRels);
        Assert.assertEquals(outRels.size(), TelicentGraphSchema.DEFAULT_LIMIT);
    }

    @Test
    public void givenGraphWithManyRelationships_whenCountingNodeTypes_thenCountIsCorrect() throws Exception {
        // Given
        DataFetcher<Integer> inFetcher = new RelationshipCountsFetcher(EdgeDirection.IN);
        DataFetcher<Integer> outFetcher = new RelationshipCountsFetcher(EdgeDirection.OUT);
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graph = createURI("graph");
        Node subject = createURI("subject");
        generateManyRelationships(dsg, graph, subject);
        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new NodePlaceholder(new TelicentGraphNode(subject, null)));

        // When
        Integer inCount = inFetcher.get(environment);
        Integer outCount = outFetcher.get(environment);

        // Then
        Assert.assertNotNull(inCount);
        Assert.assertEquals(inCount, 1_000);
        Assert.assertNotNull(outCount);
        Assert.assertEquals(outCount, 1_000);
    }

    private static void generateManyRelationships(DatasetGraph dsg, Node graph, Node subject) {
        Node predicate = createURI("predicate");
        for (int i = 0; i < 1_000; i++) {
            dsg.add(graph, subject, predicate, createURI("object" + i));
            dsg.add(graph, createURI("subject" + i), predicate, subject);
        }
    }
}
