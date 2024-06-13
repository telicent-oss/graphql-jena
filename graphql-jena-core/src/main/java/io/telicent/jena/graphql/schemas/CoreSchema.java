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
package io.telicent.jena.graphql.schemas;

/**
 * Constants related to the Core GraphQL Schema
 */
public class CoreSchema {

    /**
     * Class provides static constants so no need for instantiation
     */
    CoreSchema() {

    }

    /**
     * Quad type
     */
    public static final String QUAD_TYPE = "Quad";
    /**
     * Triple type
     */
    public static final String TRIPLE_TYPE = "Triple";
    /**
     * Node type
     */
    public static final String Node_TYPE = "Node";
    /**
     * Node kind field
     */
    public static final String KIND_FIELD = "kind";
    /**
     * Node value field
     */
    public static final String VALUE_FIELD = "value";
    /**
     * Node language field
     */
    public static final String LANGUAGE_FIELD = "language";
    /**
     * Node datatype field
     */
    public static final String DATATYPE_FIELD = "datatype";
    /**
     * Subject field
     */
    public static final String SUBJECT_FIELD = "subject";
    /**
     * Predicate field
     */
    public static final String PREDICATE_FIELD = "predicate";
    /**
     * Object field
     */
    public static final String OBJECT_FIELD = "object";
    /**
     * Graph field
     */
    public static final String GRAPH_FIELD = "graph";
    /**
     * Triple field
     */
    public static final String TRIPLE_FIELD = "triple";
}
