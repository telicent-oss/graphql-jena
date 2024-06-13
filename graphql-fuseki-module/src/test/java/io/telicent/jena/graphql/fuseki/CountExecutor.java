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

import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.telicent.jena.graphql.execution.AbstractDatasetExecutor;
import io.telicent.jena.graphql.schemas.GraphQLJenaSchemas;
import org.apache.jena.sparql.core.DatasetGraph;

import java.io.IOException;

public class CountExecutor extends AbstractDatasetExecutor {
    /**
     * Creates a new execution
     *
     * @param dsg Default Dataset Graph over which queries will execute
     * @throws IOException Thrown if there is a problem reading in the underlying GraphQL schema
     */
    public CountExecutor(DatasetGraph dsg) throws IOException {
        super(dsg);
    }

    @Override
    protected TypeDefinitionRegistry loadRawSchema() throws IOException {
        return GraphQLJenaSchemas.loadSchema("/count.graphqls");
    }

    @Override
    protected RuntimeWiring.Builder buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring().type("Count", t -> t.dataFetcher("count", new CountFetcher()));
    }

    @Override
    protected boolean extendsCoreSchema() {
        return false;
    }
}
