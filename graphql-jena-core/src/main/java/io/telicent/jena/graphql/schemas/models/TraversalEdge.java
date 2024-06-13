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
package io.telicent.jena.graphql.schemas.models;

import org.apache.jena.graph.Node;

import java.util.Objects;

/**
 * Represents an edge in a Traversal
 */
public class TraversalEdge {

    private final EdgeDirection direction;
    private final WrappedNode edge;
    private final TraversalNode target;

    /**
     * Creates a new traversal edge
     *
     * @param edge      Edge node i.e. predicate
     * @param direction Edge direction
     * @param target    Target of the edge, either the subject or object depending on the edge direction
     * @return Traversal edge
     */
    public static TraversalEdge of(Node edge, EdgeDirection direction, Node target) {
        return new TraversalEdge(new WrappedNode(edge), direction, TraversalNode.of(target));
    }

    /**
     * Creates a new traversal edge
     *
     * @param edge      Edge node i.e. predicate
     * @param direction Edge direction
     * @param target    Target of the edge, either the subject or object depending on the edge direction
     * @return Traversal edge
     */
    public static TraversalEdge of(WrappedNode edge, EdgeDirection direction, WrappedNode target) {
        return new TraversalEdge(edge, direction, TraversalNode.of(target));
    }

    private TraversalEdge(WrappedNode edge, EdgeDirection direction, TraversalNode target) {
        Objects.requireNonNull(edge, "Edge cannot be null");
        Objects.requireNonNull(target, "Node cannot be null");
        this.edge = edge;
        this.direction = direction;
        this.target = target;
    }

    /**
     * Gets the edge node
     *
     * @return Edge node
     */
    public WrappedNode getEdge() {
        return this.edge;
    }

    /**
     * Gets the target node
     *
     * @return Target node
     */
    public TraversalNode getTarget() {
        return this.target;
    }

    /**
     * Gets the edge direction
     *
     * @return Direction
     */
    public EdgeDirection getDirection() {
        return this.direction;
    }
}
