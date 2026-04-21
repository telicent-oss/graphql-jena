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
package io.telicent.jena.graphql.schemas.telicent.graph.models;

import org.apache.jena.graph.Node;
import org.apache.jena.riot.system.PrefixMap;

import java.util.Objects;

/**
 * Represents a datatype property definition.
 */
public class DatatypePropertyDefinition {

    private final Node predicate;
    private final PrefixMap prefixes;
    private final Node graphNode;

    /**
     * Creates a new datatype property definition.
     *
     * @param predicate Property node
     * @param prefixes  Prefix map
     */
    public DatatypePropertyDefinition(Node predicate, PrefixMap prefixes) {
        this(predicate, prefixes, Node.ANY);
    }

    /**
     * Creates a new datatype property definition with a graph scope.
     *
     * @param predicate Property node
     * @param prefixes  Prefix map
     * @param graphNode Graph node scope
     */
    public DatatypePropertyDefinition(Node predicate, PrefixMap prefixes, Node graphNode) {
        this.predicate = Objects.requireNonNull(predicate, "predicate");
        this.prefixes = prefixes;
        this.graphNode = graphNode != null ? graphNode : Node.ANY;
    }

    /**
     * Gets the property node.
     *
     * @return Property node
     */
    public Node getPredicate() {
        return predicate;
    }

    /**
     * Gets the prefix map.
     *
     * @return Prefix map
     */
    public PrefixMap getPrefixes() {
        return prefixes;
    }

    /**
     * Gets the graph node scope.
     *
     * @return Graph node
     */
    public Node getGraphNode() {
        return graphNode;
    }
}
