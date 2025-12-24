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

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentImpl;
import graphql.schema.DataFetchingFieldSelectionSet;
import graphql.schema.SelectedField;
import io.telicent.jena.graphql.fetchers.QuadsFetcher;
import io.telicent.jena.graphql.schemas.CoreSchema;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Benchmarks QuadsFetcher mapping costs for different selection shapes.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class QuadsFetcherMappingBenchmark {

    /**
     * Creates a benchmark instance.
     */
    public QuadsFetcherMappingBenchmark() {
    }

    /**
     * Shared benchmark state for mapping benchmarks.
     */
    @State(Scope.Benchmark)
    public static class QuadsState {

        /**
         * Dataset size parameter used to scale the generated quads.
         */
        @Param({"10", "100", "1000"})
        public int size;

        /**
         * Selection shape for GraphQL fields.
         */
        @Param({"subject-only", "triple", "all-fields", "graph-only"})
        public String selection;

        private QuadsFetcher fetcher;
        private DataFetchingEnvironment environment;

        /**
         * Creates a quads state container.
         */
        public QuadsState() {
        }

        /**
         * Builds the dataset and environment for the benchmark.
         */
        @Setup
        public void setup() {
            DatasetGraph dsg = BenchmarkResources.generateDataset(size);
            fetcher = new QuadsFetcher();

            TestSelectionSet selectionSet = new TestSelectionSet(selection);
            environment = DataFetchingEnvironmentImpl
                    .newDataFetchingEnvironment()
                    .selectionSet(selectionSet)
                    .localContext(dsg)
                    .build();
        }
    }

    /**
     * Measures QuadsFetcher mapping work for the given selection shape.
     * @param state n/a
     * @param blackhole n/a
     */
    @Benchmark
    public void mapQuads(QuadsState state, Blackhole blackhole) {
        blackhole.consume(state.fetcher.get(state.environment));
    }

    private static final class TestSelectionSet implements DataFetchingFieldSelectionSet {

        private final Map<String, String> selected = new HashMap<>();

        private TestSelectionSet(String selection) {
            switch (selection) {
                case "subject-only" -> selected.put(CoreSchema.SUBJECT_FIELD + "/**", "");
                case "triple" -> {
                    selected.put(CoreSchema.SUBJECT_FIELD + "/**", "");
                    selected.put(CoreSchema.PREDICATE_FIELD + "/**", "");
                    selected.put(CoreSchema.OBJECT_FIELD + "/**", "");
                }
                case "all-fields" -> {
                    selected.put(CoreSchema.SUBJECT_FIELD + "/**", "");
                    selected.put(CoreSchema.PREDICATE_FIELD + "/**", "");
                    selected.put(CoreSchema.OBJECT_FIELD + "/**", "");
                    selected.put(CoreSchema.GRAPH_FIELD + "/**", "");
                }
                case "graph-only" -> selected.put(CoreSchema.GRAPH_FIELD + "/**", "");
                default -> throw new IllegalArgumentException("Unknown selection: " + selection);
            }
        }

        @Override
        public boolean contains(String fieldGlobPattern) {
            return selected.containsKey(fieldGlobPattern);
        }

        @Override
        public boolean containsAnyOf(String fieldGlobPattern, String... fieldGlobPatterns) {
            return false;
        }

        @Override
        public boolean containsAllOf(String fieldGlobPattern, String... fieldGlobPatterns) {
            return false;
        }

        @Override
        public List<SelectedField> getFields() {
            return null;
        }

        @Override
        public List<SelectedField> getImmediateFields() {
            return null;
        }

        @Override
        public List<SelectedField> getFields(String fieldGlobPattern, String... fieldGlobPatterns) {
            return null;
        }

        @Override
        public Map<String, List<SelectedField>> getFieldsGroupedByResultKey() {
            return null;
        }

        @Override
        public Map<String, List<SelectedField>> getFieldsGroupedByResultKey(String fieldGlobPattern,
                                                                            String... fieldGlobPatterns) {
            return null;
        }
    }
}
