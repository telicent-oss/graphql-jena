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
package io.telicent.jena.graphql.execution;

import graphql.ExecutionResult;
import graphql.ParseAndValidateResult;
import io.telicent.jena.graphql.server.model.GraphQLRequest;

import java.util.Map;

/**
 * Provides the ability to execute GraphQL queries
 */
public interface GraphQLExecutor {
    /**
     * Executes the provided query
     *
     * @param query Query
     * @return Execution Result
     */
    ExecutionResult execute(String query);

    /**
     * Executes the provided query
     *
     * @param query     Query
     * @param variables Variables to make available to the query
     * @return Execution Result
     */
    ExecutionResult execute(String query, Map<String, Object> variables);

    /**
     * Executes the provided query
     *
     * @param query         Query
     * @param operationName Operation name indicating an operation within the query document to execute
     * @param variables     Variables to make available to the query
     * @param extensions    Vendor extensions to make available to the query
     * @return Execution Result
     */
    ExecutionResult execute(String query, String operationName, Map<String, Object> variables,
                            Map<String, Object> extensions);

    /**
     * Executes the provided request
     *
     * @param request Request
     * @return Execution result
     */
    ExecutionResult execute(GraphQLRequest request);

    /**
     * Validates the provided request
     * @param query Query
     * @param operationName Operation name indicating an operation within the query document to execute
     * @param variables     Variables to make available to the query
     * @param extensions    Vendor extensions to make available to the query
     * @return Validation Result
     */
    ParseAndValidateResult validate(String query, String operationName, Map<String, Object> variables,
                            Map<String, Object> extensions);
}
