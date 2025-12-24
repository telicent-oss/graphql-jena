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
package io.telicent.jena.graphql.benchmarks;

import graphql.ExecutionResult;
import io.telicent.jena.graphql.execution.DatasetExecutor;
import org.apache.jena.sparql.core.DatasetGraph;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Benchmarks dataset query execution with varying dataset sizes.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class DatasetQueryBenchmark {

    /**
     * Creates a benchmark instance.
     */
    public DatasetQueryBenchmark() {
    }

    /**
     * Shared benchmark state for dataset benchmarks.
     */
    @State(Scope.Benchmark)
    public static class DatasetState {

        /**
         * Dataset size parameter used to scale the generated quads.
         */
        @Param({"10", "100", "1000"})
        public int size;

        private DatasetExecutor executor;
        private String simpleQuery;
        private String filteredQuery;

        /**
         * Creates a dataset state container.
         */
        public DatasetState() {
        }

        /**
         * Builds the dataset and loads queries for the benchmark.
         *
         * @throws IOException If schema loading fails.
         */
        @Setup
        public void setup() throws IOException {
            DatasetGraph dsg = BenchmarkResources.generateDataset(size);
            executor = new DatasetExecutor(dsg);
            simpleQuery = BenchmarkResources.loadResource("/queries/dataset/simple-quads.graphql");
            filteredQuery = BenchmarkResources.loadResource("/queries/dataset/filtered-quads.graphql");
        }
    }

    /**
     * Executes a simple dataset query.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole for consuming results.
     */
    @Benchmark
    public void executeSimple(DatasetState state, Blackhole blackhole) {
        ExecutionResult result = state.executor.execute(state.simpleQuery);
        blackhole.consume(result.getData());
        blackhole.consume(result.getErrors());
    }

    /**
     * Executes a filtered dataset query.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole for consuming results.
     */
    @Benchmark
    public void executeFiltered(DatasetState state, Blackhole blackhole) {
        ExecutionResult result = state.executor.execute(state.filteredQuery);
        blackhole.consume(result.getData());
        blackhole.consume(result.getErrors());
    }
}
