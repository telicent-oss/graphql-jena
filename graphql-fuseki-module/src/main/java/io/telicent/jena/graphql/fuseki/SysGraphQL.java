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

import io.telicent.jena.graphql.execution.DatasetExecutor;
import org.apache.jena.fuseki.Fuseki;
import org.apache.jena.fuseki.server.Operation;
import org.apache.jena.fuseki.server.OperationRegistry;
import org.apache.jena.fuseki.servlets.ActionService;
import org.apache.jena.sparql.core.DatasetGraphFactory;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Provides global registration of the GraphQL operation within Fuseki
 */
public class SysGraphQL {

    private SysGraphQL() {}

    private static final AtomicBoolean INITIALIZED = new AtomicBoolean(false);

    /**
     * The GraphQL operation that may be associated with Fuseki endpoints
     */
    public static final Operation
            OP_GRAPHQL =
            Operation.alloc(VocabGraphQL.OPERATION, "graphql",
                            "GraphQL Query using a configurable GraphQLExecutor");

    /**
     * Ensures that the GraphQL module is properly initialised
     */
    public static void init() {
        boolean initialized = INITIALIZED.getAndSet(true);
        if (initialized) {
            return;
        }
        // GraphQL Actions for Fuseki
        try {
            ActionService graphQL = new ActionGraphQL(new DatasetExecutor(DatasetGraphFactory.empty()));
            OperationRegistry operationRegistry = OperationRegistry.get();
            operationRegistry.register(OP_GRAPHQL, graphQL);
        } catch (IOException e) {
            Fuseki.configLog.warn("Failed to register Fuseki GraphQL Operation");
        }
    }
}
