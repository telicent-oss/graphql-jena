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
 * Represents a literal property
 */
public class LiteralProperty {

    /*
    type Property { #A literal property
    predicate: String!
    shortPredicate: String!
    value: String!
    datatype: String
    language: String
}
     */

    private final Node predicate, literal;
    private final PrefixMap prefixes;

    /**
     * Creates a new literal property
     *
     * @param predicate Predicate
     * @param literal   Literal
     * @param prefixes  Prefix map, used to provide a shortened form of the predicate if possible
     */
    public LiteralProperty(Node predicate, Node literal, PrefixMap prefixes) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(literal);
        if (!literal.isLiteral()) {
            throw new IllegalArgumentException("literal is not a literal!");
        }
        this.predicate = predicate;
        this.literal = literal;
        this.prefixes = prefixes;
    }

    /**
     * Gets the predicate
     *
     * @return Predicate
     */
    public String getPredicate() {
        if (this.predicate.isURI()) {
            return this.predicate.getURI();
        } else if (this.predicate.isBlank()) {
            return TelicentGraphSchema.BLANK_NODE_PREFIX + this.predicate.getBlankNodeLabel();
        } else {
            throw new IllegalStateException("Not a predicate with a URI");
        }
    }

    /**
     * Gets the shortened form of the predicate
     *
     * @return Shortened URI if possible, otherwise the full URI
     */
    public String getShortPredicate() {
        if (this.prefixes == null) {
            return this.getPredicate();
        } else if (!this.predicate.isURI()) {
            return this.getPredicate();
        } else {
            String shortened = this.prefixes.abbreviate(this.getPredicate());
            return shortened != null ? shortened : this.getPredicate();
        }
    }

    /**
     * Gets the literal value
     *
     * @return Value
     */
    public String getValue() {
        return this.literal.getLiteralLexicalForm();
    }

    /**
     * Gets the literal datatype (if any)
     *
     * @return Datatype
     */
    public String getDatatype() {
        return this.literal.getLiteralDatatypeURI();
    }

    /**
     * Gets the literal language tag (if any)
     *
     * @return Language tag
     */
    public String getLanguage() {
        return this.literal.getLiteralLanguage();
    }
}
