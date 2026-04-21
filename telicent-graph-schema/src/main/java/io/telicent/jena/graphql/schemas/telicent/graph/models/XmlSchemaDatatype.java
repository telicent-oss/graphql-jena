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

import java.util.Objects;

/**
 * Represents an XML Schema datatype.
 */
public class XmlSchemaDatatype {

    private final Node node;

    /**
     * Creates a new datatype wrapper.
     *
     * @param node Datatype node
     */
    public XmlSchemaDatatype(Node node) {
        this.node = Objects.requireNonNull(node, "node");
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
}
