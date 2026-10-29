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
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Verifies that queries are executed on the calling thread, inside the read transaction that
 * {@link AbstractDatasetExecutor} opens for them, whether or not the query is already in the parsed query cache
 */
public class TestExecutionThread {

    /**
     * A minimal executor whose fields report the thread they were resolved on, and whether the dataset was in a
     * transaction on that thread
     */
    private static final class ThreadReportingExecutor extends AbstractDatasetExecutor {

        ThreadReportingExecutor(DatasetGraph dsg) throws IOException {
            super(dsg);
        }

        @Override
        protected TypeDefinitionRegistry loadRawSchema() {
            return new SchemaParser().parse("type Query { thread: String  inTransaction: Boolean }");
        }

        @Override
        protected RuntimeWiring.Builder buildRuntimeWiring() {
            return RuntimeWiring.newRuntimeWiring()
                                .type("Query", t -> t.dataFetcher("thread", env -> Thread.currentThread().getName())
                                                     .dataFetcher("inTransaction", env -> dsg.isInTransaction()));
        }

        @Override
        protected boolean extendsCoreSchema() {
            return false;
        }
    }

    private static Map<String, Object> verifyNoErrors(ExecutionResult result) {
        Assert.assertTrue(result.getErrors().isEmpty(), "Unexpected errors: " + result.getErrors());
        return result.getData();
    }

    @Test
    public void givenNewQueryText_whenExecuted_thenRunsOnCallingThreadInsideTransaction() throws IOException {
        // Given
        ThreadReportingExecutor executor = new ThreadReportingExecutor(DatasetGraphFactory.createTxnMem());
        String query = "{ thread inTransaction }";

        // When the query is first seen, and when it is already cached
        for (String when : new String[] { "first execution", "cached execution" }) {
            Map<String, Object> data = verifyNoErrors(executor.execute(query));

            // Then
            Assert.assertEquals(data.get("thread"), Thread.currentThread().getName(), when);
            Assert.assertEquals(data.get("inTransaction"), Boolean.TRUE, when);
        }
    }

    @Test
    public void givenManyDistinctQueryTexts_whenExecuted_thenEveryOneRunsInsideTransaction() throws IOException {
        // Given
        ThreadReportingExecutor executor = new ThreadReportingExecutor(DatasetGraphFactory.createTxnMem());

        for (int i = 0; i < 20; i++) {
            // When each query text is new to the cache
            Map<String, Object> data = verifyNoErrors(executor.execute("query Q" + i + " { inTransaction }"));

            // Then
            Assert.assertEquals(data.get("inTransaction"), Boolean.TRUE, "query Q" + i);
        }
    }
}
