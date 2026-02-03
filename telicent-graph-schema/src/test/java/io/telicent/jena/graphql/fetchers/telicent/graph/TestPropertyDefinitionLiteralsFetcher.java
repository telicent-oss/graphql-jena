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
import io.telicent.jena.graphql.schemas.telicent.graph.models.DatatypePropertyDefinition;
import io.telicent.jena.graphql.schemas.telicent.graph.models.ObjectPropertyDefinition;
import io.telicent.jena.graphql.schemas.telicent.graph.models.RdfLiteral;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.vocabulary.RDFS;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.apache.jena.graph.NodeFactory.createLiteralString;
import static org.apache.jena.graph.NodeFactory.createURI;

public class TestPropertyDefinitionLiteralsFetcher extends AbstractFetcherTests {

    @Test
    public void testReturnsEmptyWhenNoLiteralMatches() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node predicate = createURI("http://example.com/predicate");
        Node graph = createURI("graph");
        Node objectProperty = createURI("http://example.com/prop");
        dsg.add(graph, objectProperty, predicate, createURI("http://example.com/not-literal"));

        ObjectPropertyDefinition source = new ObjectPropertyDefinition(objectProperty, dsg.prefixes(), graph);
        PropertyDefinitionLiteralsFetcher fetcher = new PropertyDefinitionLiteralsFetcher(predicate);
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, source);

        List<RdfLiteral> literals = fetcher.get(environment);

        Assert.assertTrue(literals.isEmpty());
    }

    @Test
    public void testGraphScopeHonorsNodeAny() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graphA = createURI("graphA");
        Node graphB = createURI("graphB");
        Node datatypeProperty = createURI("http://example.com/prop");
        dsg.add(graphA, datatypeProperty, RDFS.label.asNode(), createLiteralString("A"));
        dsg.add(graphB, datatypeProperty, RDFS.label.asNode(), createLiteralString("B"));

        DatatypePropertyDefinition source = new DatatypePropertyDefinition(datatypeProperty, dsg.prefixes(), null);
        PropertyDefinitionLiteralsFetcher fetcher = new PropertyDefinitionLiteralsFetcher(RDFS.label.asNode());
        List<RdfLiteral> literals = fetcher.get(prepareFetchingEnvironment(dsg, source));

        Assert.assertEquals(literals.size(), 2);
    }
}
