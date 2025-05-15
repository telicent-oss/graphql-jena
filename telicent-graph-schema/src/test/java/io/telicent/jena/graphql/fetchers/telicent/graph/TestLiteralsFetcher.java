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
import io.telicent.jena.graphql.schemas.telicent.graph.models.LiteralProperty;
import io.telicent.jena.graphql.schemas.telicent.graph.models.RelationshipCounts;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.apache.jena.graph.NodeFactory.*;

public class TestLiteralsFetcher extends AbstractFetcherTests {

    @Test
    public void givenGraphWithManyLiterals_whenFetchingProperties_thenPagingIsApplied() throws Exception {
        // Given
        LiteralPropertiesFetcher fetcher = new LiteralPropertiesFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graph = createURI("graph");
        Node subject = createURI("subject");
        generateManyLiterals(dsg, graph, subject);
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new TelicentGraphNode(subject, null));

        // When
        List<LiteralProperty> literals = fetcher.get(environment);

        // Then
        Assert.assertNotNull(literals);
        Assert.assertEquals(literals.size(), TelicentGraphSchema.DEFAULT_LIMIT);
    }

    @Test
    public void givenGraphWithManyLiterals_whenCountingProperties_thenCountIsCorrect() throws Exception {
        // Given
        DataFetcher<Integer> fetcher = new LiteralsCountFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graph = createURI("graph");
        Node subject = createURI("subject");
        generateManyLiterals(dsg, graph, subject);
        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new RelationshipCounts(new TelicentGraphNode(subject, null)));

        // When
        Integer count = fetcher.get(environment);

        // Then
        Assert.assertNotNull(count);
        Assert.assertEquals(count, 1_000);
    }

    private static void generateManyLiterals(DatasetGraph dsg, Node graph, Node subject) {
        Node predicate = createURI("predicate");
        for (int i = 0; i < 1_000; i++) {
            dsg.add(graph, subject, predicate, createLiteralDT(Integer.toString(i), XSDDatatype.XSDinteger));
        }
    }

}
