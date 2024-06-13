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

import graphql.schema.idl.NaturalEnumValuesProvider;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.telicent.jena.graphql.fetchers.TraversalEdgesFetcher;
import io.telicent.jena.graphql.fetchers.TraversalStartsFetcher;
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.GraphQLJenaSchemas;
import io.telicent.jena.graphql.schemas.TraversalSchema;
import org.apache.jena.sparql.core.DatasetGraph;

import java.io.IOException;

/**
 * A GraphQL executor using our {@link TraversalSchema}
 */
public class TraversalExecutor extends AbstractDatasetExecutor {

    /**
     * Creates a new executor over the given {@link DatasetGraph}
     *
     * @param dsg Dataset graph
     * @throws IOException Thrown if the schema cannot be loaded
     */
    public TraversalExecutor(DatasetGraph dsg) throws IOException {
        super(dsg);
    }

    @Override
    protected TypeDefinitionRegistry loadRawSchema() throws IOException {
        return GraphQLJenaSchemas.loadTraversalSchema();
    }

    @Override
    protected RuntimeWiring.Builder buildRuntimeWiring() {
        NaturalEnumValuesProvider<EdgeDirection> edgeDirections = new NaturalEnumValuesProvider<>(EdgeDirection.class);
        TraversalEdgesFetcher edgesFetcher = new TraversalEdgesFetcher();
        //@formatter:off
        return RuntimeWiring.newRuntimeWiring()
                .type(TraversalSchema.TRAVERSAL_QUERY_TYPE,
                      t -> t.dataFetcher(TraversalSchema.NODES_FIELD, new TraversalStartsFetcher())
                            .enumValues(edgeDirections))
                .type(TraversalSchema.TRAVERSAL_NODE_TYPE,
                      t -> t.dataFetcher(TraversalSchema.INCOMING_FIELD, edgesFetcher)
                            .dataFetcher(TraversalSchema.OUTGOING_FIELD, edgesFetcher));
        //@formatter:on
    }
}
