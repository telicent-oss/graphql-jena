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
package io.telicent.jena.graphql.access;

import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.system.Txn;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * The contract of {@link GraphAccess}, for any implementation
 * <p>
 * A store that provides its own implementation can extend this class, supplying a dataset of its own holding the given
 * quads, to show that its implementation gives the same answers as {@link DefaultGraphAccess}.
 * </p>
 */
public abstract class AbstractGraphAccessTests {

    /**
     * Namespace for test terms
     */
    protected static final String EX = "https://example.org/";

    /**
     * The hub node that the test data is centred on
     */
    protected static final Node HUB = uri("hub");
    private static final Node OTHER = uri("other");
    private static final Node SINGLE = uri("single");
    private static final Node ABSENT = uri("absent");
    private static final Node BLANK = NodeFactory.createBlankNode("b1");
    private static final Node KNOWS = uri("knows");
    private static final Node LIKES = uri("likes");
    private static final Node NAME = uri("name");
    private static final Node G1 = uri("g1");
    private static final Node G2 = uri("g2");

    /**
     * Creates a URI node in the test namespace
     *
     * @param local Local name
     * @return URI node
     */
    protected static Node uri(String local) {
        return NodeFactory.createURI(EX + local);
    }

    /**
     * Creates a dataset holding exactly the given quads
     *
     * @param quads Quads
     * @return Dataset graph
     */
    protected abstract DatasetGraph createDataset(Collection<Quad> quads);

    /**
     * Gets the graph access implementation under test for the given dataset
     *
     * @param dsg Dataset graph created by {@link #createDataset(Collection)}
     * @return Graph access
     */
    protected abstract GraphAccess createAccess(DatasetGraph dsg);

    /**
     * The test data: relationships of the hub repeated across the default graph and named graphs, literals, blank
     * nodes, and a few other nodes
     *
     * @return Quads
     */
    protected static List<Quad> testQuads() {
        List<Quad> quads = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Node n = uri("n" + i);
            Node p = i % 2 == 0 ? KNOWS : LIKES;
            // Outbound from the hub, and inbound to it, in the default graph
            quads.add(Quad.create(Quad.defaultGraphIRI, HUB, p, n));
            quads.add(Quad.create(Quad.defaultGraphIRI, n, p, HUB));
            // Some of the same triples again in named graphs
            if (i % 3 == 0) {
                quads.add(Quad.create(G1, HUB, p, n));
                quads.add(Quad.create(G2, n, p, HUB));
            }
            if (i % 5 == 0) {
                quads.add(Quad.create(G2, HUB, p, n));
                quads.add(Quad.create(G1, n, p, HUB));
            }
        }
        // Literals are not relationships
        quads.add(Quad.create(Quad.defaultGraphIRI, HUB, NAME, NodeFactory.createLiteralString("Hub")));
        quads.add(Quad.create(G1, HUB, NAME, NodeFactory.createLiteralString("A rather longer name for the hub")));
        quads.add(Quad.create(G1, HUB, KNOWS, NodeFactory.createLiteralDT("12", org.apache.jena.datatypes.xsd.XSDDatatype.XSDinteger)));
        // Blank nodes are
        quads.add(Quad.create(Quad.defaultGraphIRI, HUB, LIKES, BLANK));
        quads.add(Quad.create(G2, BLANK, KNOWS, HUB));
        // Other nodes
        quads.add(Quad.create(G1, OTHER, KNOWS, SINGLE));
        quads.add(Quad.create(G2, OTHER, KNOWS, SINGLE));
        return quads;
    }

    /**
     * The expected relationships, computed directly from the quads
     */
    private static Set<Triple> expected(List<Quad> quads, Node node, EdgeDirection direction,
                                        Collection<Node> predicates) {
        Set<Triple> triples = new HashSet<>();
        for (Quad q : quads) {
            Node self = direction == EdgeDirection.OUT ? q.getSubject() : q.getObject();
            Node other = direction == EdgeDirection.OUT ? q.getObject() : q.getSubject();
            if (self.equals(node) && (other.isURI() || other.isBlank()) && (predicates == null || predicates.isEmpty()
                                                                             || predicates.contains(
                    q.getPredicate()))) {
                triples.add(q.asTriple());
            }
        }
        return triples;
    }

    private <T> T read(DatasetGraph dsg, Supplier<T> action) {
        return Txn.calculateRead(dsg, action::get);
    }

    @DataProvider(name = "questions")
    public Object[][] questions() {
        List<Object[]> out = new ArrayList<>();
        List<Collection<Node>> predicateSets = new ArrayList<>();
        predicateSets.add(null);
        predicateSets.add(List.of());
        predicateSets.add(List.of(KNOWS));
        predicateSets.add(List.of(KNOWS, LIKES));
        predicateSets.add(List.of(NAME));
        predicateSets.add(List.of(ABSENT));
        for (Node node : List.of(HUB, OTHER, SINGLE, BLANK, ABSENT)) {
            for (EdgeDirection direction : EdgeDirection.values()) {
                for (Collection<Node> predicates : predicateSets) {
                    out.add(new Object[] { node, direction, predicates });
                }
            }
        }
        return out.toArray(new Object[0][]);
    }

    @Test(dataProvider = "questions")
    public void givenNode_whenCountingRelationships_thenDistinctTriplesAcrossGraphsAreCounted(Node node,
                                                                                               EdgeDirection direction,
                                                                                               Collection<Node> predicates) {
        // Given
        List<Quad> quads = testQuads();
        DatasetGraph dsg = createDataset(quads);
        GraphAccess access = createAccess(dsg);

        // When
        long[] counts = read(dsg, () -> access.countRelationships(dsg, List.of(node), direction, predicates));

        // Then
        Assert.assertEquals(counts.length, 1);
        Assert.assertEquals(counts[0], expected(quads, node, direction, predicates).size());
    }

    @Test(dataProvider = "questions")
    public void givenNode_whenListingRelationships_thenEachDistinctTripleIsReturnedOnce(Node node,
                                                                                         EdgeDirection direction,
                                                                                         Collection<Node> predicates) {
        // Given
        List<Quad> quads = testQuads();
        DatasetGraph dsg = createDataset(quads);
        GraphAccess access = createAccess(dsg);

        // When
        List<Triple> all = read(dsg, () -> access.relationships(dsg, node, direction, predicates, 0, Long.MAX_VALUE)
                                                 .map(Quad::asTriple)
                                                 .toList());

        // Then
        Assert.assertEquals(new HashSet<>(all), expected(quads, node, direction, predicates));
        Assert.assertEquals(all.size(), new HashSet<>(all).size());
    }

    @Test(dataProvider = "questions")
    public void givenNode_whenPaging_thenPagesAreStableAndMakeUpTheWholeList(Node node, EdgeDirection direction,
                                                                            Collection<Node> predicates) {
        // Given
        DatasetGraph dsg = createDataset(testQuads());
        GraphAccess access = createAccess(dsg);
        List<Triple> all = read(dsg, () -> access.relationships(dsg, node, direction, predicates, 0, Long.MAX_VALUE)
                                                 .map(Quad::asTriple)
                                                 .toList());

        for (int limit : new int[] { 1, 3, 7 }) {
            // When
            List<Triple> paged = new ArrayList<>();
            for (int offset = 0; offset <= all.size(); offset += limit) {
                long from = offset;
                paged.addAll(read(dsg, () -> access.relationships(dsg, node, direction, predicates, from, limit)
                                                   .map(Quad::asTriple)
                                                   .toList()));
            }

            // Then
            Assert.assertEquals(paged, all);
        }
        Assert.assertEquals(read(dsg, () -> access.relationships(dsg, node, direction, predicates, 0, Long.MAX_VALUE)
                                                  .map(Quad::asTriple)
                                                  .toList()), all);
    }

    @Test
    public void givenManyNodes_whenCountingRelationships_thenCountsAreInNodeOrderAndEqualListSizes() {
        // Given
        List<Quad> quads = testQuads();
        DatasetGraph dsg = createDataset(quads);
        GraphAccess access = createAccess(dsg);
        List<Node> nodes = new ArrayList<>(List.of(HUB, ABSENT, OTHER, SINGLE, BLANK, HUB));
        for (int i = 0; i < 25; i++) {
            nodes.add(uri("n" + i));
        }

        for (EdgeDirection direction : EdgeDirection.values()) {
            // When
            long[] counts = read(dsg, () -> access.countRelationships(dsg, nodes, direction, null));

            // Then
            Assert.assertEquals(counts.length, nodes.size());
            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                long listed = read(dsg, () -> access.relationships(dsg, node, direction, null, 0, Long.MAX_VALUE)
                                                    .count());
                Assert.assertEquals(counts[i], listed);
                Assert.assertEquals(counts[i], expected(quads, node, direction, null).size());
            }
        }
    }

    @Test
    public void givenNoNodes_whenCountingRelationships_thenNoCounts() {
        // Given
        DatasetGraph dsg = createDataset(testQuads());
        GraphAccess access = createAccess(dsg);

        // When
        long[] counts = read(dsg, () -> access.countRelationships(dsg, List.of(), EdgeDirection.OUT, null));

        // Then
        Assert.assertEquals(counts.length, 0);
    }

    @Test
    public void givenPastTheEnd_whenPaging_thenEmpty() {
        // Given
        DatasetGraph dsg = createDataset(testQuads());
        GraphAccess access = createAccess(dsg);

        // When
        long beyond = read(dsg, () -> access.relationships(dsg, HUB, EdgeDirection.OUT, null, 1_000, 10).count());
        long none = read(dsg, () -> access.relationships(dsg, HUB, EdgeDirection.OUT, null, 0, 0).count());

        // Then
        Assert.assertEquals(beyond, 0);
        Assert.assertEquals(none, 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void givenNegativeOffset_whenPaging_thenRejected() {
        // Given
        DatasetGraph dsg = createDataset(testQuads());
        GraphAccess access = createAccess(dsg);

        // When and Then
        read(dsg, () -> access.relationships(dsg, HUB, EdgeDirection.OUT, null, -1, 10).count());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void givenNegativeLimit_whenPaging_thenRejected() {
        // Given
        DatasetGraph dsg = createDataset(testQuads());
        GraphAccess access = createAccess(dsg);

        // When and Then
        read(dsg, () -> access.relationships(dsg, HUB, EdgeDirection.OUT, null, 0, -1).count());
    }
}
