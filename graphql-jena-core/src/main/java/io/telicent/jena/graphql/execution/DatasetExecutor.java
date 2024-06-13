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
import io.telicent.jena.graphql.fetchers.QuadsFetcher;
import io.telicent.jena.graphql.schemas.DatasetSchema;
import io.telicent.jena.graphql.schemas.GraphQLJenaSchemas;
import io.telicent.jena.graphql.schemas.models.NodeKind;
import org.apache.jena.sparql.core.DatasetGraph;

import java.io.IOException;

/**
 * Provides the ability to execute GraphQL queries using our simple Dataset schema
 */
public class DatasetExecutor extends AbstractDatasetExecutor {

    /**
     * Creates a new execution engine using the Dataset schema and the given {@link DatasetGraph} as the underlying data
     * source
     *
     * @param dsg Dataset Graph
     * @throws IOException Thrown if the schema cannot be loaded
     */
    public DatasetExecutor(DatasetGraph dsg) throws IOException {
        super(dsg);
    }

    @Override
    protected TypeDefinitionRegistry loadRawSchema() throws IOException {
        return GraphQLJenaSchemas.loadDatasetSchema();
    }

    @Override
    protected RuntimeWiring.Builder buildRuntimeWiring() {
        //@formatter:off
        NaturalEnumValuesProvider<NodeKind> nodeKinds = new NaturalEnumValuesProvider<>(NodeKind.class);
        return RuntimeWiring.newRuntimeWiring()
                               .type(DatasetSchema.QUADS_QUERY_TYPE,
                                     t -> t.dataFetcher(DatasetSchema.QUADS_FIELD, new QuadsFetcher()).enumValues(nodeKinds));
        //@formatter:on
    }

}
