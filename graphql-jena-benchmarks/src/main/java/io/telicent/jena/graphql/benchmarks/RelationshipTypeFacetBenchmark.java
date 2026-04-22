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
import io.telicent.jena.graphql.fetchers.telicent.graph.RelationshipTypeFacetsFetcher;
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.telicent.graph.models.FacetInfo;
import io.telicent.jena.graphql.schemas.telicent.graph.models.FacetInfoPlaceholder;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
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
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Benchmarks the relationship type facets fetcher before and after deduplicating related-node type lookups.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class RelationshipTypeFacetBenchmark {

    /**
     * Creates a relationship type facet benchmark.
     */
    public RelationshipTypeFacetBenchmark() {
    }

    /**
     * Shared benchmark state.
     */
    @State(Scope.Benchmark)
    public static class RelationshipTypeFacetState {

        /**
         * Total number of outbound relationships from the benchmark subject.
         */
        @Param({"100", "1000", "5000"})
        public int relationshipCount;

        private DatasetGraph dsg;
        private DataFetchingEnvironment environment;
        private RelationshipTypeFacetsFetcher optimizedFetcher;
        private RelationshipTypeFacetsFetcher legacyFetcher;

        /**
         * Creates a benchmark state container.
         */
        public RelationshipTypeFacetState() {
        }

        /**
         * Builds a synthetic dataset and fetchers for the benchmark.
         */
        @Setup
        public void setup() {
            this.dsg = DatasetGraphFactory.create();
            Node source = NodeFactory.createURI("https://example.org/source");
            generateDataset(this.dsg, source, this.relationshipCount);

            TelicentExecutionContext context = new TelicentExecutionContext(this.dsg, "");
            this.environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                         .localContext(context)
                                                         .source(new FacetInfoPlaceholder(new TelicentGraphNode(source,
                                                                                                                null),
                                                                                          null, null))
                                                         .arguments(Map.of())
                                                         .build();
            this.optimizedFetcher = new RelationshipTypeFacetsFetcher(EdgeDirection.OUT);
            this.legacyFetcher = new LegacyRelationshipTypeFacetsFetcher();
        }

        private static void generateDataset(DatasetGraph dsg, Node source, int relationshipCount) {
            Node graph = NodeFactory.createURI("https://example.org/graph");
            int uniqueObjects = Math.max(1, relationshipCount / 10);
            for (int i = 0; i < relationshipCount; i++) {
                Node predicate = NodeFactory.createURI("https://example.org/predicate/" + (i % 12));
                Node object = NodeFactory.createURI("https://example.org/object/" + (i % uniqueObjects));
                dsg.add(new Quad(graph, source, predicate, object));
            }
            for (int i = 0; i < uniqueObjects; i++) {
                Node object = NodeFactory.createURI("https://example.org/object/" + i);
                dsg.add(new Quad(graph, object, RDF.type.asNode(),
                                 NodeFactory.createURI("https://example.org/type/" + (i % 8))));
                dsg.add(new Quad(graph, object, RDF.type.asNode(),
                                 NodeFactory.createURI("https://example.org/type/" + ((i + 3) % 8))));
            }
        }
    }

    /**
     * Measures the optimized type facets fetcher.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole.
     * @throws Exception If fetcher execution fails.
     */
    @Benchmark
    public void optimizedTypeFacets(RelationshipTypeFacetState state, Blackhole blackhole) throws Exception {
        List<FacetInfo> facets = state.optimizedFetcher.get(state.environment);
        blackhole.consume(facets);
    }

    /**
     * Measures the old behavior where types are looked up once per relationship.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole.
     * @throws Exception If fetcher execution fails.
     */
    @Benchmark
    public void legacyTypeFacets(RelationshipTypeFacetState state, Blackhole blackhole) throws Exception {
        List<FacetInfo> facets = state.legacyFetcher.get(state.environment);
        blackhole.consume(facets);
    }

    private static final class LegacyRelationshipTypeFacetsFetcher extends RelationshipTypeFacetsFetcher {

        private LegacyRelationshipTypeFacetsFetcher() {
            super(EdgeDirection.OUT);
        }

        @Override
        protected List<FacetInfo> map(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node,
                                      Stream<Quad> input) {
            return input.flatMap(q -> streamTypes(dsg, getRelatedNode(q)))
                        .collect(Collectors.groupingBy(Quad::getObject, Collectors.counting()))
                        .entrySet()
                        .stream()
                        .map(e -> new FacetInfo(e.getKey(), dsg.prefixes(), e.getValue().intValue()))
                        .toList();
        }
    }
}
