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
import org.apache.jena.graph.Triple;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;

import java.util.*;
import java.util.stream.Stream;

/**
 * A {@link GraphAccess} implementation that answers every question by streaming quads from the dataset via
 * {@link DatasetGraph#stream(Node, Node, Node, Node)}
 * <p>
 * This defines the expected answers for any other implementation and is suitable for any dataset. It is never used
 * automatically: a dataset with no {@link GraphAccess} in its context is served by the fetchers' own code, which gives
 * the same answers.
 * </p>
 */
public class DefaultGraphAccess implements GraphAccess {

    /**
     * Creates a new default graph access
     */
    @SuppressWarnings("java:S1186")
    public DefaultGraphAccess() {

    }

    @Override
    public long[] countRelationships(DatasetGraph dsg, List<Node> nodes, EdgeDirection direction,
                                     Collection<Node> predicates) {
        Objects.requireNonNull(nodes, "Nodes cannot be null");
        long[] counts = new long[nodes.size()];
        for (int i = 0; i < counts.length; i++) {
            counts[i] = distinct(stream(dsg, nodes.get(i), direction, predicates)).count();
        }
        return counts;
    }

    @Override
    public Stream<Quad> relationships(DatasetGraph dsg, Node node, EdgeDirection direction,
                                      Collection<Node> predicates, long offset, long limit) {
        if (offset < 0) {
            throw new IllegalArgumentException("offset cannot be negative");
        }
        if (limit < 0) {
            throw new IllegalArgumentException("limit cannot be negative");
        }
        return distinct(stream(dsg, node, direction, predicates)).skip(offset).limit(limit);
    }

    /**
     * Streams the quads that represent relationships of a node, possibly including the same triple more than once
     *
     * @param dsg        Dataset graph
     * @param node       Node
     * @param direction  Direction of relationships
     * @param predicates Predicates, {@code null} or empty for any predicate
     * @return Stream of quads
     */
    protected Stream<Quad> stream(DatasetGraph dsg, Node node, EdgeDirection direction, Collection<Node> predicates) {
        Objects.requireNonNull(direction, "Direction cannot be null");
        Stream<Quad> quads = predicates == null || predicates.isEmpty() ? stream(dsg, node, Node.ANY, direction) :
                             predicates.stream().flatMap(p -> stream(dsg, node, p, direction));
        return switch (direction) {
            case OUT -> quads.filter(q -> isNode(q.getObject()));
            case IN -> quads.filter(q -> isNode(q.getSubject()));
        };
    }

    private static Stream<Quad> stream(DatasetGraph dsg, Node node, Node predicate, EdgeDirection direction) {
        return direction == EdgeDirection.OUT ? dsg.stream(Node.ANY, node, predicate, Node.ANY) :
               dsg.stream(Node.ANY, Node.ANY, predicate, node);
    }

    private static boolean isNode(Node n) {
        return n.isURI() || n.isBlank();
    }

    /**
     * Removes quads that differ only by graph, keeping the first quad seen for each triple
     *
     * @param quads Quads
     * @return Stream with one quad per distinct triple
     */
    private static Stream<Quad> distinct(Stream<Quad> quads) {
        Set<Triple> seen = new HashSet<>();
        return quads.sequential().filter(q -> seen.add(q.asTriple()));
    }
}
