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
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import io.telicent.jena.graphql.schemas.telicent.graph.models.DatatypePropertyDefinition;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static org.apache.jena.graph.NodeFactory.createURI;

public class TestOntologyDatatypePropertyDefinitionFetcher extends AbstractFetcherTests {

    @Test
    public void testFetchDatatypePropertyDefinition() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node datatypeProperty = createURI("http://example.com/name");
        dsg.add(createURI("graph"), datatypeProperty, RDF.type.asNode(), OWL.DatatypeProperty.asNode());

        Map<String, Object> arguments = Map.of(TelicentGraphSchema.ARGUMENT_URI, datatypeProperty.getURI());
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new Object(), arguments);

        OntologyDatatypePropertyDefinitionFetcher fetcher = new OntologyDatatypePropertyDefinitionFetcher();
        DatatypePropertyDefinition definition = fetcher.get(environment);

        Assert.assertNotNull(definition);
        Assert.assertEquals(definition.getPredicate(), datatypeProperty);
    }

    @Test
    public void testFetchDatatypePropertyDefinitionMissing() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node objectProperty = createURI("http://example.com/locatedIn");
        dsg.add(createURI("graph"), objectProperty, RDF.type.asNode(), OWL.ObjectProperty.asNode());

        Map<String, Object> arguments = Map.of(TelicentGraphSchema.ARGUMENT_URI, objectProperty.getURI());
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new Object(), arguments);

        OntologyDatatypePropertyDefinitionFetcher fetcher = new OntologyDatatypePropertyDefinitionFetcher();
        DatatypePropertyDefinition definition = fetcher.get(environment);

        Assert.assertNull(definition);
    }

    @Test
    public void testFetchDatatypePropertyDefinitionWithGraphScope() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graphA = createURI("graphA");
        Node graphB = createURI("graphB");
        Node datatypeProperty = createURI("http://example.com/name");
        dsg.add(graphA, datatypeProperty, RDF.type.asNode(), OWL.DatatypeProperty.asNode());

        Map<String, Object> arguments = Map.of(TelicentGraphSchema.ARGUMENT_URI, datatypeProperty.getURI());

        OntologyDatatypePropertyDefinitionFetcher fetcher = new OntologyDatatypePropertyDefinitionFetcher();
        DatatypePropertyDefinition scoped =
                fetcher.get(prepareFetchingEnvironment(dsg, new io.telicent.jena.graphql.schemas.telicent.graph.models.OntologyPlaceholder(
                        graphA), arguments));
        DatatypePropertyDefinition missing =
                fetcher.get(prepareFetchingEnvironment(dsg, new io.telicent.jena.graphql.schemas.telicent.graph.models.OntologyPlaceholder(
                        graphB), arguments));

        Assert.assertNotNull(scoped);
        Assert.assertNull(missing);
    }
}
