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
import io.telicent.jena.graphql.execution.TraversalExecutor;
import org.apache.jena.sparql.core.DatasetGraph;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Benchmarks traversal query execution over a small graph dataset.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class TraversalQueryBenchmark {

    /**
     * Creates a benchmark instance.
     */
    public TraversalQueryBenchmark() {
    }

    /**
     * Shared benchmark state for traversal benchmarks.
     */
    @State(Scope.Benchmark)
    public static class TraversalState {

        private TraversalExecutor executor;
        private String simpleQuery;
        private String friendsQuery;

        /**
         * Creates a traversal state container.
         */
        public TraversalState() {
        }

        /**
         * Builds the dataset and loads queries for the benchmark.
         *
         * @throws IOException If schema loading fails.
         */
        @Setup
        public void setup() throws IOException {
            DatasetGraph dsg = BenchmarkResources.loadDatasetFromTrig("/data/traversals.trig");
            executor = new TraversalExecutor(dsg);
            simpleQuery = BenchmarkResources.loadResource("/queries/traversal/simple-traversal.graphql");
            friendsQuery = BenchmarkResources.loadResource("/queries/traversal/friends.graphql");
        }
    }

    /**
     * Executes a traversal query that returns outgoing edges.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole for consuming results.
     */
    @Benchmark
    public void executeSimple(TraversalState state, Blackhole blackhole) {
        ExecutionResult result = state.executor.execute(state.simpleQuery);
        blackhole.consume(result.getData());
        blackhole.consume(result.getErrors());
    }

    /**
     * Executes a traversal query that fetches incoming and outgoing edges.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole for consuming results.
     */
    @Benchmark
    public void executeFriends(TraversalState state, Blackhole blackhole) {
        ExecutionResult result = state.executor.execute(state.friendsQuery);
        blackhole.consume(result.getData());
        blackhole.consume(result.getErrors());
    }
}
