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
package io.telicent.jena.graphql.fuseki;

import org.apache.jena.sparql.util.Symbol;

/**
 * Constants related to the GraphQL URIs and symbols needed for this module
 */
public class VocabGraphQL {
    private VocabGraphQL() {}
    /**
     * The namespace URI for this modules configuration data
     */
    public static final String NS = "https://telicent.io/fuseki/modules/graphql#";

    /**
     * The URI used to identify the GraphQL operation when defining Fuseki endpoints in a configuration file
     */
    public static final String OPERATION = NS + "graphql";

    /**
     * Context symbol used to define the {@link io.telicent.jena.graphql.execution.GraphQLOverDatasetExecutor}
     * implementation to use for a GraphQL endpoint
     */
    public static final Symbol EXECUTOR = Symbol.create("graphql:executor");
}
