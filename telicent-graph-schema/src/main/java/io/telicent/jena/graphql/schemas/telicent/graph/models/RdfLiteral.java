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

import java.util.Objects;

/**
 * Represents a literal value with optional language/datatype.
 */
public class RdfLiteral {

    private final Node literal;

    /**
     * Creates a new RDF literal wrapper.
     *
     * @param literal Literal node
     */
    public RdfLiteral(Node literal) {
        this.literal = Objects.requireNonNull(literal, "literal");
        if (!literal.isLiteral()) {
            throw new IllegalArgumentException("literal is not a literal");
        }
    }

    /**
     * Gets the literal value.
     *
     * @return Lexical form
     */
    public String getValue() {
        return this.literal.getLiteralLexicalForm();
    }

    /**
     * Gets the language tag if any.
     *
     * @return Language tag
     */
    public String getLanguage() {
        return this.literal.getLiteralLanguage();
    }

    /**
     * Gets the datatype URI if any.
     *
     * @return Datatype URI
     */
    public String getDatatype() {
        return this.literal.getLiteralDatatypeURI();
    }
}
