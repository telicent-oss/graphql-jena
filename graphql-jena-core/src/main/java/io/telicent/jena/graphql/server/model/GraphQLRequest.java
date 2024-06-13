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
package io.telicent.jena.graphql.server.model;

import java.util.HashMap;
import java.util.Map;

/**
 * A GraphQL Request as submitted via an HTTP POST per the <a
 * href="https://github.com/graphql/graphql-over-http/blob/main/spec/GraphQLOverHTTP.md#post">GraphQL over HTTP</a>
 * specification.
 */
public class GraphQLRequest {

    private String query, operationName;
    private Map<String, Object> variables = new HashMap<>(), extensions = new HashMap<>();

    /**
     * Creates an empty GraphQL Request that may be populated by its setter methods
     */
    public GraphQLRequest() {

    }

    /**
     * Gets the GraphQL query string
     *
     * @return Query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets the GraphQL query string
     *
     * @param query Query
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Gets the GraphQL Operation name
     *
     * @return Operation name
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * Sets the GraphQL Operation name
     *
     * @param operationName Operation name
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    /**
     * Gets the GraphQL query variables (if any)
     *
     * @return Query variables
     */
    public Map<String, Object> getVariables() {
        return variables;
    }

    /**
     * Sets the GraphQL query variables (if any)
     *
     * @param variables Query variables
     */
    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    /**
     * Gets the GraphQL query extensions (if any)
     *
     * @return Query extensions
     */
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    /**
     * Sets the GraphQL query extensions (if any)
     *
     * @param extensions Query extensions
     */
    public void setExtensions(Map<String, Object> extensions) {
        this.extensions = extensions;
    }
}
