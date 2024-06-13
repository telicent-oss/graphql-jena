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

import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.io.IOException;
import java.io.InputStream;

/**
 * Provides helpers around accessing our provided GraphQL schemas
 */
public class GraphQLJenaSchemas {

    private GraphQLJenaSchemas() {}

    /**
     * The classpath resource containing our Core GraphQL Schema
     */
    public static final String CORE = "/io/telicent/jena/graphql/schemas/core.graphqls";
    /**
     * The classpath resource containing our Dataset GraphQL Schema
     */
    public static final String DATASET = "/io/telicent/jena/graphql/schemas/dataset.graphqls";
    /**
     * The classpath resource containing our Traversal GraphQL Schema
     */
    public static final String TRAVERSAL = "/io/telicent/jena/graphql/schemas/traversal.graphqls";

    /**
     * Loads a schema by combining all the given schema files into a single Schema
     *
     * @param schemas Schema files
     * @return Combined schema
     * @throws IOException Thrown if any of the given schema files is missing
     */
    public static TypeDefinitionRegistry loadSchema(String... schemas) throws IOException {
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry registry = new TypeDefinitionRegistry();
        for (String schema : schemas) {
            try (InputStream input = GraphQLJenaSchemas.class.getResourceAsStream(schema)) {
                if (input == null) {
                    throw new IllegalArgumentException("Schema " + schema + " not found as a Classpath resource");
                }
                registry.merge(schemaParser.parse(input));
            }
        }
        return registry;
    }

    /**
     * Loads the dataset schema
     *
     * @return Dataset schema
     * @throws IllegalArgumentException                If the schema is not present on the Classpath
     * @throws IOException                             If the schema cannot be read
     * @throws graphql.schema.idl.errors.SchemaProblem If the schema is invalid
     */
    public static TypeDefinitionRegistry loadDatasetSchema() throws IOException {
        return loadSchema(CORE, DATASET);
    }

    /**
     * Loads the traversal schema
     *
     * @return Traversal schema
     * @throws IllegalArgumentException                If the schema is not present on the Classpath
     * @throws IOException                             If the schema cannot be read
     * @throws graphql.schema.idl.errors.SchemaProblem If the schema is invalid
     */
    public static TypeDefinitionRegistry loadTraversalSchema() throws IOException {
        return loadSchema(CORE, TRAVERSAL);
    }
}
