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

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import graphql.*;
import graphql.execution.preparsed.PreparsedDocumentEntry;
import graphql.execution.preparsed.PreparsedDocumentProvider;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.telicent.jena.graphql.fetchers.NodeFetcher;
import io.telicent.jena.graphql.schemas.CoreSchema;
import io.telicent.jena.graphql.server.model.GraphQLRequest;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.system.Txn;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * Abstract GraphQL Executor that operates over a Jena {@link DatasetGraph}
 */
public abstract class AbstractDatasetExecutor implements GraphQLExecutor, GraphQLOverDatasetExecutor {

    /**
     * The Dataset over which queries are executed by default
     */
    protected final DatasetGraph dsg;
    /**
     * The GraphQL schema used for the queries
     */
    protected final GraphQLSchema schema;
    /**
     * The configured GraphQL instance for executing the queries
     */
    protected final GraphQL graphQL;

    /**
     * Creates a new execution
     *
     * @param dsg Default Dataset Graph over which queries will execute
     * @throws IOException Thrown if there is a problem reading in the underlying GraphQL schema
     */
    public AbstractDatasetExecutor(DatasetGraph dsg) throws IOException {
        this.dsg = dsg;
        Objects.requireNonNull(dsg, "DatasetGraph to execute over cannot be null");

        TypeDefinitionRegistry rawSchema = this.loadRawSchema();
        RuntimeWiring wiring = buildCoreRuntimeWiring(buildRuntimeWiring()).build();
        SchemaGenerator generator = new SchemaGenerator();
        this.schema = generator.makeExecutableSchema(rawSchema, wiring);

        // Add Query Cache here
        // TODO Eventually should make the cache size configurable here
        Cache<String, CompletableFuture<PreparsedDocumentEntry>> cache =
                Caffeine.newBuilder().maximumSize(1_000).build();
        PreparsedDocumentProvider preparsedCache =
                (executionInput, computeFunction) -> cache.get(executionInput.getQuery(),
                                                               key -> CompletableFuture.supplyAsync(
                                                                       () -> computeFunction.apply(executionInput)));

        //@formatter:off
        this.graphQL
                = GraphQL.newGraphQL(this.schema)
                         .preparsedDocumentProvider(preparsedCache)
                         .build();
        //@formatter:on
    }

    /**
     * Loads in the raw GraphQL schema that this execution supports
     *
     * @return Raw GraphQL Schema
     * @throws IOException Thrown if the schema cannot be loaded
     */
    protected abstract TypeDefinitionRegistry loadRawSchema() throws IOException;

    /**
     * Builds the runtime wiring necessary to execute the GraphQL schema that this execution supports
     *
     * @return Runtime Wiring builder
     */
    protected abstract RuntimeWiring.Builder buildRuntimeWiring();

    /**
     * Builds the runtime wiring necessary for our base types from the Core schema to function
     *
     * @param builder Runtime Wiring builder
     * @return Runtime Wiring builder
     */
    private RuntimeWiring.Builder buildCoreRuntimeWiring(RuntimeWiring.Builder builder) {
        if (extendsCoreSchema()) {
            NodeFetcher nodeFetcher = new NodeFetcher();
            //@formatter:off
            return builder.type(CoreSchema.QUAD_TYPE,
                                t -> t.defaultDataFetcher(nodeFetcher))
                          .type(CoreSchema.TRIPLE_TYPE,
                                t -> t.defaultDataFetcher(nodeFetcher));
            //@formatter:on
        } else {
            return builder;
        }
    }

    /**
     * Gets whether the schema used for this execution extends our {@link CoreSchema}, if {@code true} then the runtime
     * wiring built for executing queries will automatically add support for the {@code Quad} and {@code Triple} types
     * from that schema.
     *
     * @return True if this execution uses a schema that extends the Core schema, false otherwise
     */
    protected boolean extendsCoreSchema() {
        return true;
    }

    @Override
    public final ExecutionResult execute(String query) {
        return execute(query, Collections.emptyMap());
    }

    @Override
    public final ExecutionResult execute(String query, Map<String, Object> variables) {
        return execute(query, null, variables, Collections.emptyMap());
    }

    @Override
    public final ExecutionResult execute(GraphQLRequest request) {
        return execute(request.getQuery(), request.getOperationName(), request.getVariables(), request.getExtensions());
    }

    @Override
    public final ExecutionResult execute(String query, String operationName, Map<String, Object> variables,
                                         Map<String, Object> extensions) {
        return execute(this.dsg, query, operationName, variables, extensions);
    }

    /**
     * Executes a GraphQL request against a specific {@link DatasetGraph} instance
     *
     * @param dsg     Dataset Graph
     * @param request GraphQL Request
     * @return GraphQL Results
     */
    @Override
    public final ExecutionResult execute(DatasetGraph dsg, GraphQLRequest request) {
        return execute(dsg, request.getQuery(), request.getOperationName(), request.getVariables(),
                       request.getExtensions());
    }

    /**
     * Executes a GraphQL request against a specific {@link DatasetGraph} instance
     * <p>
     * Most of the other {@code execute()} overloads on this class will call this method using the {@link DatasetGraph}
     * instance that this executor instance was created with.  However callers can choose to provide an alternative
     * instance if they want to execute queries within this schema over different {@link DatasetGraph} instances.
     * </p>
     *
     * @param dsg           DatasetGraph
     * @param query         GraphQL Query
     * @param operationName Operation name
     * @param variables     Variables
     * @param extensions    Extensions
     * @return GraphQL Results
     */
    @Override
    public final ExecutionResult execute(DatasetGraph dsg, String query, String operationName,
                                         Map<String, Object> variables, Map<String, Object> extensions) {
        Objects.requireNonNull(dsg, "DatasetGraph to execute over cannot be null");
        ExecutionInput input = ExecutionInput.newExecutionInput(query)
                                             .localContext(createLocalContext(dsg, extensions))
                                             .operationName(operationName)
                                             .variables(variables)
                                             .extensions(extensions)
                                             .build();

        // Ensure we execute the GraphQL query inside a read transaction on the Dataset.  This gives proper transaction
        // isolation for the entire query which could include many requests against the dataset
        return Txn.calculateRead(dsg, () -> this.graphQL.execute(input));
    }

    /**
     * Creates the local context object passed to the GraphQL {@link ExecutionInput}
     * <p>
     * By default this is simply the {@link DatasetGraph} associated with this executor but derived implementations may
     * wish to use a more complex context object in which case they can override this method and supply one.
     * </p>
     *
     * @param dsg        The Dataset Graph the query will operate over
     * @param extensions The extensions for the query (if any)
     * @return Local context object
     */
    protected Object createLocalContext(DatasetGraph dsg, Map<String, Object> extensions) {
        return dsg;
    }


    /**
     * Validate the given Graph QL query
     * @param query         Query to validate
     * @param operationName Operation name indicating an operation within the query document to execute
     * @param variables     Variables to make available to the query
     * @param extensions    Vendor extensions to make available to the query
     * @return Parsing results
     */
    @Override
    public ParseAndValidateResult validate(String query, String operationName, Map<String, Object> variables,
                                    Map<String, Object> extensions){
        Objects.requireNonNull(dsg, "DatasetGraph to validate against cannot be null");
        ExecutionInput input =
                ExecutionInput.newExecutionInput(query)
                              .localContext(createLocalContext(dsg, extensions))
                              .operationName(operationName)
                              .variables(variables)
                              .extensions(extensions)
                              .build();
        return ParseAndValidate.parseAndValidate(schema, input);
    }
}
