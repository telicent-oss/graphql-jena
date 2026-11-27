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
import io.telicent.jena.graphql.fetchers.telicent.graph.RelationshipCountsFetcher;
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.telicent.graph.models.NodePlaceholder;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.system.Txn;
import org.apache.jena.tdb2.DatabaseMgr;
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

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Benchmarks counting the inbound relationships of a hub node, as the Telicent graph schema's {@code relCounts} field
 * does for every node on a page of relationships, with no {@link io.telicent.jena.graphql.access.GraphAccess}.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class RelationshipCountBenchmark {

    /**
     * Creates a relationship count benchmark.
     */
    public RelationshipCountBenchmark() {
    }

    /**
     * Shared benchmark state.
     */
    @State(Scope.Benchmark)
    public static class CountState {

        /**
         * Number of distinct inbound relationships of the hub, each present in two named graphs.
         */
        @Param({"1000", "100000"})
        public int relationshipCount;

        /**
         * The dataset implementation: {@code mem} for Jena's in-memory dataset, {@code tdb2} for TDB2.
         */
        @Param({"mem", "tdb2"})
        public String store;

        private DatasetGraph dsg;
        private TelicentGraphNode hub;
        private RelationshipCountsFetcher fetcher;

        /**
         * Creates a benchmark state container.
         */
        public CountState() {
        }

        /**
         * Builds a hub with the configured number of inbound relationships.
         */
        @Setup
        public void setup() {
            this.dsg = "tdb2".equals(this.store) ? DatabaseMgr.createDatasetGraph() :
                       DatasetGraphFactory.createTxnMem();
            Node hubNode = NodeFactory.createURI("https://example.org/hub");
            this.hub = new TelicentGraphNode(hubNode, null);
            Node predicate = NodeFactory.createURI("https://example.org/predicate");
            Node g1 = NodeFactory.createURI("https://example.org/graph/1");
            Node g2 = NodeFactory.createURI("https://example.org/graph/2");
            Txn.executeWrite(this.dsg, () -> {
                for (int i = 0; i < this.relationshipCount; i++) {
                    Node subject = NodeFactory.createURI("https://example.org/subject/" + i);
                    this.dsg.add(Quad.create(g1, subject, predicate, hubNode));
                    this.dsg.add(Quad.create(g2, subject, predicate, hubNode));
                }
            });
            this.fetcher = new RelationshipCountsFetcher(EdgeDirection.IN);
        }

        private DataFetchingEnvironment environment() {
            return DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                             .localContext(new TelicentExecutionContext(this.dsg, ""))
                                             .source(new NodePlaceholder(this.hub))
                                             .arguments(Map.of())
                                             .build();
        }
    }

    /**
     * Counts the hub's inbound relationships in a request of its own.
     *
     * @param state     Shared benchmark state.
     * @param blackhole JMH blackhole.
     * @throws Exception If fetcher execution fails.
     */
    @Benchmark
    public void inboundCount(CountState state, Blackhole blackhole) throws Exception {
        blackhole.consume(state.fetcher.get(state.environment()));
    }
}
