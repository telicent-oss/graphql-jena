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

import graphql.ExecutionResult;
import io.telicent.jena.graphql.execution.GraphQLOverDatasetExecutor;
import io.telicent.jena.graphql.server.model.GraphQLRequest;
import org.apache.jena.sparql.core.DatasetGraph;

import java.util.Map;

public abstract class AbstractBadExecutor implements GraphQLOverDatasetExecutor {

    public AbstractBadExecutor(DatasetGraph dsg) {

    }

    @Override
    public ExecutionResult execute(DatasetGraph dsg, GraphQLRequest request) {
        return null;
    }

    @Override
    public ExecutionResult execute(DatasetGraph dsg, String query, String operationName, Map<String, Object> variables,
                                   Map<String, Object> extensions) {
        return null;
    }
}
