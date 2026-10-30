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
import io.telicent.jena.graphql.execution.telicent.graph.TelicentExecutionContext;
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
import java.util.stream.Collectors;

import static org.apache.jena.graph.NodeFactory.createLiteralString;
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

        dsg.add(Quad.create(GRAPH, subject, predicate1, object1));
        dsg.add(Quad.create(GRAPH, subject, predicate2, object1));
        dsg.add(Quad.create(GRAPH, subject, predicate1, object2));

        dsg.add(Quad.create(GRAPH, object1, RDF.type.asNode(), type1));
        dsg.add(Quad.create(GRAPH, object1, RDF.type.asNode(), type2));
        dsg.add(Quad.create(GRAPH, object2, RDF.type.asNode(), type1));
        dsg.add(Quad.create(createURI("second-graph"), object1, RDF.type.asNode(), type1));

        AtomicInteger typeLookupCount = new AtomicInteger();
        RelationshipTypeFacetsFetcher fetcher = new RelationshipTypeFacetsFetcher(EdgeDirection.OUT) {
            @Override
            protected List<Node> loadTypes(DatasetGraph dsg, Node relatedNode) {
                typeLookupCount.incrementAndGet();
                return super.loadTypes(dsg, relatedNode);
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
        Assert.assertEquals(facets.stream().collect(Collectors.toMap(FacetInfo::getUri,
                                                                                      FacetInfo::getCount)),
                            Map.of("type1", 3, "type2", 2));
    }

    /**
     * A hub with incoming links from three entities, two of which also have outgoing links back from the hub
     */
    private static DatasetGraph hubDataset() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node hub = createURI("hub");
        Node link = createURI("link");
        for (int i = 1; i <= 3; i++) {
            Node e = createURI("e" + i);
            dsg.add(Quad.create(GRAPH, e, link, hub));
            dsg.add(Quad.create(GRAPH, e, RDF.type.asNode(), createURI(i == 3 ? "Location" : "Person")));
        }
        dsg.add(Quad.create(GRAPH, hub, link, createURI("e1")));
        dsg.add(Quad.create(GRAPH, hub, link, createURI("e2")));
        return dsg;
    }

    private static Map<String, Integer> counts(List<FacetInfo> facets) {
        return facets.stream().collect(Collectors.toMap(FacetInfo::getUri, FacetInfo::getCount));
    }

    /**
     * A type facets fetcher that counts how many related nodes it had to look up in the dataset
     */
    private static RelationshipTypeFacetsFetcher countingFacets(EdgeDirection direction, AtomicInteger lookups) {
        return new RelationshipTypeFacetsFetcher(direction) {
            @Override
            protected List<Node> loadTypes(DatasetGraph dsg, Node relatedNode) {
                lookups.incrementAndGet();
                return super.loadTypes(dsg, relatedNode);
            }
        };
    }

    private static DataFetchingEnvironment facetsEnvironment(TelicentExecutionContext context, Node node) {
        return prepareFetchingEnvironment(context, new FacetInfoPlaceholder(new TelicentGraphNode(node, null), null,
                                                                            null), Map.of());
    }

    @Test
    public void givenTypesAlreadyFetchedInRequest_whenFetchingTypeFacets_thenThoseNodesAreNotLookedUpAgain()
            throws Exception {
        // Given: one request in which another field (e.g. inRels { domain { types } }) already fetched e1's types
        DatasetGraph dsg = hubDataset();
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        new NodeTypesFetcher().get(prepareFetchingEnvironment(context, new TelicentGraphNode(createURI("e1"), null)));
        AtomicInteger lookups = new AtomicInteger();

        // When
        List<FacetInfo> facets = countingFacets(EdgeDirection.IN, lookups).get(facetsEnvironment(context,
                                                                                                 createURI("hub")));

        // Then: only e2 and e3 needed a lookup, and the facets are unchanged
        Assert.assertEquals(lookups.get(), 2);
        Assert.assertEquals(counts(facets), Map.of("Person", 2, "Location", 1));
    }

    @Test
    public void givenIncomingFacetsFetched_whenFetchingOutgoingFacets_thenSharedRelatedNodesAreNotLookedUpAgain()
            throws Exception {
        // Given
        DatasetGraph dsg = hubDataset();
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        AtomicInteger inLookups = new AtomicInteger(), outLookups = new AtomicInteger();

        // When: relFacets { inRels { types } outRels { types } } in one request
        List<FacetInfo> in = countingFacets(EdgeDirection.IN, inLookups).get(facetsEnvironment(context,
                                                                                             createURI("hub")));
        List<FacetInfo> out = countingFacets(EdgeDirection.OUT, outLookups).get(facetsEnvironment(context,
                                                                                                createURI("hub")));

        // Then: e1 and e2 are on both sides, so the outgoing facets needed no lookups
        Assert.assertEquals(inLookups.get(), 3);
        Assert.assertEquals(outLookups.get(), 0);
        Assert.assertEquals(counts(in), Map.of("Person", 2, "Location", 1));
        Assert.assertEquals(counts(out), Map.of("Person", 2));
    }

    @Test
    public void givenTypeFacetsFetched_whenFetchingRelatedNodeTypes_thenCachedTypesAreReused() throws Exception {
        // Given: the facets ran first in this request
        DatasetGraph dsg = hubDataset();
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        new RelationshipTypeFacetsFetcher(EdgeDirection.IN).get(facetsEnvironment(context, createURI("hub")));
        AtomicInteger lookups = new AtomicInteger();
        NodeTypesFetcher types = new NodeTypesFetcher() {
            @Override
            protected List<Node> loadNodeTypes(DatasetGraph dsg, TelicentGraphNode node) {
                lookups.incrementAndGet();
                return super.loadNodeTypes(dsg, node);
            }
        };

        // When: inRels { domain { types } } asks for e3's types
        List<TelicentGraphNode> e3Types = types.get(prepareFetchingEnvironment(context,
                                                                              new TelicentGraphNode(createURI("e3"),
                                                                                                    null)));

        // Then
        Assert.assertEquals(lookups.get(), 0);
        Assert.assertEquals(e3Types.stream().map(TelicentGraphNode::getUri).toList(), List.of("Location"));
    }

    @Test
    public void givenSeparateRequests_whenFetchingTypeFacets_thenTypesAreLookedUpAgain() throws Exception {
        // Given: the cache is per request, so a new request (new context) sees changes and other users' views apart
        DatasetGraph dsg = hubDataset();
        AtomicInteger lookups = new AtomicInteger();
        countingFacets(EdgeDirection.IN, lookups).get(facetsEnvironment(new TelicentExecutionContext(dsg, ""),
                                                                        createURI("hub")));

        // When
        countingFacets(EdgeDirection.IN, lookups).get(facetsEnvironment(new TelicentExecutionContext(dsg, ""),
                                                                        createURI("hub")));

        // Then
        Assert.assertEquals(lookups.get(), 6);
    }

    @Test
    public void givenLiteralTypeValue_whenFetchingTypeFacets_thenItIsNotAFacet() throws Exception {
        // Given: a related node with a literal rdf:type object (malformed data), as NodeTypesFetcher already ignores
        DatasetGraph dsg = hubDataset();
        dsg.add(Quad.create(GRAPH, createURI("e1"), RDF.type.asNode(), createLiteralString("NotAType")));

        // When
        List<FacetInfo> facets = new RelationshipTypeFacetsFetcher(EdgeDirection.IN).get(
                facetsEnvironment(new TelicentExecutionContext(dsg, ""), createURI("hub")));

        // Then
        Assert.assertEquals(counts(facets), Map.of("Person", 2, "Location", 1));
    }
}
