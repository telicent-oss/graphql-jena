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
import io.telicent.jena.graphql.execution.telicent.graph.TelicentExecutionContext;
import io.telicent.jena.graphql.fetchers.telicent.graph.AbstractLiteralsFetcher;
import io.telicent.jena.graphql.fetchers.telicent.graph.AbstractNodeTypesFetcher;
import io.telicent.jena.graphql.fetchers.telicent.graph.LiteralPropertiesFetcher;
import io.telicent.jena.graphql.fetchers.telicent.graph.LiteralsCountFetcher;
import io.telicent.jena.graphql.fetchers.telicent.graph.NodeTypeCountsFetcher;
import io.telicent.jena.graphql.fetchers.telicent.graph.NodeTypesFetcher;
import io.telicent.jena.graphql.schemas.telicent.graph.models.LiteralProperty;
import io.telicent.jena.graphql.schemas.telicent.graph.models.NodePlaceholder;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;
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

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Benchmarks request-scoped node fact reuse for node types and literal properties.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class NodeTypeCacheBenchmark {

    /**
     * Creates a benchmark instance.
     */
    public NodeTypeCacheBenchmark() {
    }

    /**
     * Shared benchmark state.
     */
    @State(Scope.Benchmark)
    public static class BenchmarkState {

        /**
         * Number of types and literals to generate for the benchmark node.
         */
        @Param({"100", "1000", "5000"})
        public int nodeCount;

        private DatasetGraph dsg;
        private TelicentGraphNode sourceNode;
        private NodePlaceholder sourcePlaceholder;

        private AbstractNodeTypesFetcher<List<TelicentGraphNode>> legacyTypesFetcher;
        private NodeTypesFetcher cachedTypesFetcher;
        private AbstractNodeTypesFetcher<Integer> legacyTypeCountFetcher;
        private NodeTypeCountsFetcher cachedTypeCountFetcher;

        private AbstractLiteralsFetcher<List<LiteralProperty>> legacyPropertiesFetcher;
        private LiteralPropertiesFetcher cachedPropertiesFetcher;
        private AbstractLiteralsFetcher<Integer> legacyPropertiesCountFetcher;
        private LiteralsCountFetcher cachedPropertiesCountFetcher;

        /**
         * Creates a benchmark state container.
         */
        public BenchmarkState() {
        }

        /**
         * Builds the dataset and fetchers for the benchmark.
         */
        @Setup
        public void setup() {
            this.dsg = DatasetGraphFactory.create();
            Node graph = NodeFactory.createURI("https://example.org/graph");
            Node subject = NodeFactory.createURI("https://example.org/node");
            generateTypes(this.dsg, graph, subject, this.nodeCount);
            generateLiteralProperties(this.dsg, graph, subject, this.nodeCount);

            this.sourceNode = new TelicentGraphNode(subject, this.dsg.prefixes());
            this.sourcePlaceholder = new NodePlaceholder(this.sourceNode);

            this.legacyTypesFetcher = new NodeTypesFetcher() {
                @Override
                protected List<Node> getNodeTypes(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node) {
                    return loadNodeTypes(dsg, node);
                }
            };
            this.cachedTypesFetcher = new NodeTypesFetcher();
            this.legacyTypeCountFetcher = new NodeTypeCountsFetcher() {
                @Override
                protected List<Node> getNodeTypes(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node) {
                    return loadNodeTypes(dsg, node);
                }
            };
            this.cachedTypeCountFetcher = new NodeTypeCountsFetcher();

            this.legacyPropertiesFetcher = new LiteralPropertiesFetcher() {
                @Override
                protected List<Quad> getLiteralProperties(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node) {
                    return loadLiteralProperties(dsg, node);
                }
            };
            this.cachedPropertiesFetcher = new LiteralPropertiesFetcher();
            this.legacyPropertiesCountFetcher = new LiteralsCountFetcher() {
                @Override
                protected List<Quad> getLiteralProperties(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node) {
                    return loadLiteralProperties(dsg, node);
                }
            };
            this.cachedPropertiesCountFetcher = new LiteralsCountFetcher();
        }

        private DataFetchingEnvironment nodeEnvironment(TelicentExecutionContext context) {
            return DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                              .localContext(context)
                                              .source(this.sourceNode)
                                              .build();
        }

        private DataFetchingEnvironment placeholderEnvironment(TelicentExecutionContext context) {
            return DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                              .localContext(context)
                                              .source(this.sourcePlaceholder)
                                              .build();
        }

        private static void generateTypes(DatasetGraph dsg, Node graph, Node subject, int factCount) {
            for (int i = 0; i < factCount; i++) {
                dsg.add(graph, subject, RDF.type.asNode(), NodeFactory.createURI("https://example.org/type/" + i));
            }
        }

        private static void generateLiteralProperties(DatasetGraph dsg, Node graph, Node subject, int factCount) {
            Node predicate = NodeFactory.createURI("https://example.org/property");
            for (int i = 0; i < factCount; i++) {
                dsg.add(graph, subject, predicate,
                        NodeFactory.createLiteralDT(Integer.toString(i), XSDDatatype.XSDinteger));
            }
        }
    }

    /**
     * Measures the old behavior for `types` plus `relCounts.types`, where node types are rescanned.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole for consuming results.
     * @throws Exception Thrown if a fetcher fails.
     */
    @Benchmark
    public void typesAndCount_uncached(BenchmarkState state, Blackhole blackhole) throws Exception {
        TelicentExecutionContext context = new TelicentExecutionContext(state.dsg, "");
        List<TelicentGraphNode> types = state.legacyTypesFetcher.get(state.nodeEnvironment(context));
        Integer count = state.legacyTypeCountFetcher.get(state.placeholderEnvironment(context));
        blackhole.consume(types);
        blackhole.consume(count);
    }

    /**
     * Measures the new cached behavior for `types` plus `relCounts.types`.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole for consuming results.
     * @throws Exception Thrown if a fetcher fails.
     */
    @Benchmark
    public void typesAndCount_cached(BenchmarkState state, Blackhole blackhole) throws Exception {
        TelicentExecutionContext context = new TelicentExecutionContext(state.dsg, "");
        List<TelicentGraphNode> types = state.cachedTypesFetcher.get(state.nodeEnvironment(context));
        Integer count = state.cachedTypeCountFetcher.get(state.placeholderEnvironment(context));
        blackhole.consume(types);
        blackhole.consume(count);
    }

    /**
     * Measures the old behavior for `properties` plus `relCounts.properties`, where literals are rescanned.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole for consuming results.
     * @throws Exception Thrown if a fetcher fails.
     */
    @Benchmark
    public void propertiesAndCount_uncached(BenchmarkState state, Blackhole blackhole) throws Exception {
        TelicentExecutionContext context = new TelicentExecutionContext(state.dsg, "");
        List<LiteralProperty> properties = state.legacyPropertiesFetcher.get(state.nodeEnvironment(context));
        Integer count = state.legacyPropertiesCountFetcher.get(state.placeholderEnvironment(context));
        blackhole.consume(properties);
        blackhole.consume(count);
    }

    /**
     * Measures the new cached behavior for `properties` plus `relCounts.properties`.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole for consuming results.
     * @throws Exception Thrown if a fetcher fails.
     */
    @Benchmark
    public void propertiesAndCount_cached(BenchmarkState state, Blackhole blackhole) throws Exception {
        TelicentExecutionContext context = new TelicentExecutionContext(state.dsg, "");
        List<LiteralProperty> properties = state.cachedPropertiesFetcher.get(state.nodeEnvironment(context));
        Integer count = state.cachedPropertiesCountFetcher.get(state.placeholderEnvironment(context));
        blackhole.consume(properties);
        blackhole.consume(count);
    }
}
