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

import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import org.apache.jena.graph.Node;
import org.apache.jena.riot.system.PrefixMap;

import java.util.Objects;

/**
 * Represents an RDFS/OWL class.
 */
public class RdfsClass {

    private final Node node;
    private final PrefixMap prefixes;
    private final boolean owlClass;
    private final Node graphNode;

    /**
     * Creates a new class wrapper.
     *
     * @param node      Node for the class
     * @param prefixes  Prefix map for shortening URIs
     * @param owlClass  Whether the class is an owl:Class
     */
    public RdfsClass(Node node, PrefixMap prefixes, boolean owlClass) {
        this(node, prefixes, owlClass, Node.ANY);
    }

    /**
     * Creates a new class wrapper with a graph scope.
     *
     * @param node      Node for the class
     * @param prefixes  Prefix map for shortening URIs
     * @param owlClass  Whether the class is an owl:Class
     * @param graphNode Graph node scope
     */
    public RdfsClass(Node node, PrefixMap prefixes, boolean owlClass, Node graphNode) {
        this.node = Objects.requireNonNull(node, "node");
        this.prefixes = prefixes;
        this.owlClass = owlClass;
        this.graphNode = graphNode != null ? graphNode : Node.ANY;
    }

    /**
     * Gets the underlying node.
     *
     * @return Jena node
     */
    public Node getNode() {
        return node;
    }

    /**
     * Gets the graph node scope.
     *
     * @return Graph node
     */
    public Node getGraphNode() {
        return graphNode;
    }

    /**
     * Gets the GraphQL ID.
     *
     * @return ID value
     */
    public String getId() {
        return getUri();
    }

    /**
     * Gets the URI.
     *
     * @return URI value
     */
    public String getUri() {
        if (this.node.isURI()) {
            return this.node.getURI();
        } else if (this.node.isBlank()) {
            return TelicentGraphSchema.BLANK_NODE_PREFIX + this.node.getBlankNodeLabel();
        } else {
            throw new IllegalStateException("Not a node with a URI");
        }
    }

    /**
     * Gets the shortened URI if available.
     *
     * @return Short URI value
     */
    public String getShortUri() {
        if (this.prefixes == null) {
            return getUri();
        } else if (!this.node.isURI()) {
            return getUri();
        } else {
            String shortened = this.prefixes.abbreviate(getUri());
            return shortened != null ? shortened : getUri();
        }
    }

    /**
     * Indicates whether this is an owl:Class.
     *
     * @return True if owl:Class
     */
    public boolean isOwlClass() {
        return owlClass;
    }
}
