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
 * A wrapper around a {@link WrappedNode} to map it into a format suitable for use with our Traversal GraphQL Schema
 */
public class TraversalNode {

    private final WrappedNode node;

    /**
     * Wraps a Jena {@link Node} as a traversal node
     *
     * @param n Node
     * @return Traversal node
     */
    public static TraversalNode of(Node n) {
        return new TraversalNode(new WrappedNode(n));
    }

    /**
     * Wraps a {@link WrappedNode} as a traversal node
     *
     * @param n Wrapped node
     * @return Traversal node
     */
    public static TraversalNode of(WrappedNode n) {
        return new TraversalNode(n);
    }

    /**
     * Creates a new traversal node
     *
     * @param n Wrapped node
     */
    private TraversalNode(WrappedNode n) {
        Objects.requireNonNull(n, "Node cannot be null");
        this.node = n;
    }

    /**
     * Gets the wrapped node
     *
     * @return Wrapped node
     */
    public WrappedNode getNode() {
        return this.node;
    }
}
