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
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.telicent.graph.models.FacetInfo;
import io.telicent.jena.graphql.schemas.telicent.graph.models.FacetInfoPlaceholder;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.apache.jena.graph.NodeFactory.createURI;

public class TestRelationshipTypeFacetsFetcher extends AbstractFetcherTests {

    private static final Node GRAPH = createURI("graph");

    @Test
    public void givenRepeatedRelationshipsToSameNode_whenFetchingTypeFacets_thenTypesAreLookedUpOncePerRelatedNode()
            throws Exception {
        // Given
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node subject = createURI("subject");
        Node object1 = createURI("object1");
        Node object2 = createURI("object2");
        Node predicate1 = createURI("predicate1");
        Node predicate2 = createURI("predicate2");
        Node type1 = createURI("type1");
        Node type2 = createURI("type2");

        dsg.add(new Quad(GRAPH, subject, predicate1, object1));
        dsg.add(new Quad(GRAPH, subject, predicate2, object1));
        dsg.add(new Quad(GRAPH, subject, predicate1, object2));

        dsg.add(new Quad(GRAPH, object1, RDF.type.asNode(), type1));
        dsg.add(new Quad(GRAPH, object1, RDF.type.asNode(), type2));
        dsg.add(new Quad(GRAPH, object2, RDF.type.asNode(), type1));

        AtomicInteger typeLookupCount = new AtomicInteger();
        RelationshipTypeFacetsFetcher fetcher = new RelationshipTypeFacetsFetcher(EdgeDirection.OUT) {
            @Override
            protected Stream<Quad> streamTypes(DatasetGraph dsg, Node relatedNode) {
                typeLookupCount.incrementAndGet();
                return super.streamTypes(dsg, relatedNode);
            }
        };

        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg,
                                                                         new FacetInfoPlaceholder(
                                                                                 new TelicentGraphNode(subject, null),
                                                                                 null, null),
                                                                         Map.of());

        // When
        List<FacetInfo> facets = fetcher.get(environment);

        // Then
        Assert.assertEquals(typeLookupCount.get(), 2,
                            "Expected type lookups to run once per unique related node");
        Assert.assertEquals(facets.size(), 2);
        Assert.assertEquals(facets.stream().collect(java.util.stream.Collectors.toMap(FacetInfo::getUri,
                                                                                      FacetInfo::getCount)),
                            Map.of("type1", 3, "type2", 2));
    }
}
