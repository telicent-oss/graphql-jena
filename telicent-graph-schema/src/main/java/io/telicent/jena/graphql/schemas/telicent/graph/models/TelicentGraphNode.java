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
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.jena.graph.Node;
import org.apache.jena.riot.system.PrefixMap;

import java.util.Objects;

/**
 * A wrapper around a Jena {@link Node} that maps it into the basic data structure used for the Telicent GraphQL Schema
 */
public class TelicentGraphNode {

    /*
    Simple GraphQL schema fields that we implement directly on this POJO

    id: ID! #Used if you want client-side caching, but will return the same string as uri
    uri: String! #The uri of the resource
    shortUri: String! #If a shortened (namespace prefixed) form of the uri is available, otherwise returns full uri
     */

    private final Node node;
    private final PrefixMap prefixes;

    /**
     * Creates a new node
     *
     * @param node          Node
     * @param prefixMapping Prefix mapping, used to provide the shortened URI if possible
     */
    public TelicentGraphNode(Node node, PrefixMap prefixMapping) {
        Objects.requireNonNull(node, "Node cannot be null");
        this.node = node;
        this.prefixes = prefixMapping;
    }

    /**
     * Gets the underlying Jena node
     *
     * @return Jena Node
     */
    public Node getNode() {
        return this.node;
    }

    /**
     * Gets the URI of the node
     *
     * @return URI of the node
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
     * Gets the SHA1 hash of the URI
     *
     * @return URI hash
     */
    public String getUriHash() {
        return DigestUtils.sha1Hex(this.getUri());
    }

    /**
     * Gets the ID of the node, this is usually the same as the URI
     *
     * @return ID
     */
    public String getId() {
        return this.getUri();
    }

    /**
     * Gets the short URI of the node
     *
     * @return Short URI if available, otherwise the full URI
     */
    public String getShortUri() {
        if (this.prefixes == null) {
            return this.getUri();
        } else if (!this.node.isURI()) {
            return this.getUri();
        } else {
            String shortened = this.prefixes.abbreviate(this.getUri());
            return shortened != null ? shortened : this.getUri();
        }
    }
}
