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
import io.telicent.jena.graphql.fetchers.telicent.graph.NodeTypesFetcher;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Benchmarks the relationship type facets fetcher: legacy (one type lookup per relationship), previous (one per unique
 * related node) and optimized (one per unique related node, shared with the request's other fields). Every invocation
 * is a new request with its own execution context, so nothing is cached between invocations.
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
        private FacetInfoPlaceholder source;
        private final List<Node> relatedNodes = new ArrayList<>();
        private RelationshipTypeFacetsFetcher optimizedFetcher;
        private RelationshipTypeFacetsFetcher previousFetcher;
        private RelationshipTypeFacetsFetcher legacyFetcher;
        private final NodeTypesFetcher typesFetcher = new NodeTypesFetcher();

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
            this.relatedNodes.clear();
            this.dsg.stream(Node.ANY, source, Node.ANY, Node.ANY).map(Quad::getObject).distinct()
                    .forEach(this.relatedNodes::add);

            this.source = new FacetInfoPlaceholder(new TelicentGraphNode(source, null), null, null);
            this.optimizedFetcher = new RelationshipTypeFacetsFetcher(EdgeDirection.OUT);
            this.previousFetcher = new PreviousRelationshipTypeFacetsFetcher();
            this.legacyFetcher = new LegacyRelationshipTypeFacetsFetcher();
        }

        /**
         * Creates the environment for one GraphQL request. Each invocation gets a fresh execution context, as each real
         * request does, so neither fetcher can reuse relationships or types cached by an earlier invocation.
         *
         * @return Data fetching environment for a new request
         */
        private DataFetchingEnvironment newRequest() {
            return DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                              .localContext(new TelicentExecutionContext(this.dsg, ""))
                                              .source(this.source)
                                              .arguments(Map.of())
                                              .build();
        }

        /**
         * Runs one request that fetches the type facets with the given fetcher, then the types of every related node
         * (as {@code relFacets { outRels { types } } outRels { range { types } }} does)
         *
         * @param facetsFetcher Type facets fetcher
         * @param blackhole     JMH blackhole
         * @throws Exception If fetcher execution fails
         */
        private void facetsAndRelatedTypes(RelationshipTypeFacetsFetcher facetsFetcher, Blackhole blackhole)
                throws Exception {
            DataFetchingEnvironment request = newRequest();
            blackhole.consume(facetsFetcher.get(request));
            for (Node related : this.relatedNodes) {
                blackhole.consume(this.typesFetcher.get(
                        DataFetchingEnvironmentImpl.newDataFetchingEnvironment(request)
                                                   .source(new TelicentGraphNode(related, null))
                                                   .build()));
            }
        }

        private static void generateDataset(DatasetGraph dsg, Node source, int relationshipCount) {
            Node graph = NodeFactory.createURI("https://example.org/graph");
            int uniqueObjects = Math.max(1, relationshipCount / 10);
            for (int i = 0; i < relationshipCount; i++) {
                Node predicate = NodeFactory.createURI("https://example.org/predicate/" + (i % 12));
                Node object = NodeFactory.createURI("https://example.org/object/" + (i % uniqueObjects));
                dsg.add(Quad.create(graph, source, predicate, object));
            }
            for (int i = 0; i < uniqueObjects; i++) {
                Node object = NodeFactory.createURI("https://example.org/object/" + i);
                dsg.add(Quad.create(graph, object, RDF.type.asNode(),
                                 NodeFactory.createURI("https://example.org/type/" + (i % 8))));
                dsg.add(Quad.create(graph, object, RDF.type.asNode(),
                                 NodeFactory.createURI("https://example.org/type/" + ((i + 3) % 8))));
            }
        }
    }

    /**
     * Measures the type facets as they were before request-scoped type re-use (one lookup per unique related node,
     * not shared with other fields), for comparison with {@link #optimizedTypeFacets}.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole.
     * @throws Exception If fetcher execution fails.
     */
    @Benchmark
    public void previousTypeFacets(RelationshipTypeFacetState state, Blackhole blackhole) throws Exception {
        blackhole.consume(state.previousFetcher.get(state.newRequest()));
    }

    /**
     * Measures one request that fetches the type facets and then each related node's types, before type re-use: every
     * related node's types are looked up twice, once by the facets and once by the {@code types} field.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole.
     * @throws Exception If fetcher execution fails.
     */
    @Benchmark
    public void previousFacetsAndRelatedTypes(RelationshipTypeFacetState state, Blackhole blackhole) throws Exception {
        state.facetsAndRelatedTypes(state.previousFetcher, blackhole);
    }

    /**
     * Measures the same request with type re-use: the {@code types} fields reuse the types the facets loaded.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole.
     * @throws Exception If fetcher execution fails.
     */
    @Benchmark
    public void optimizedFacetsAndRelatedTypes(RelationshipTypeFacetState state, Blackhole blackhole)
            throws Exception {
        state.facetsAndRelatedTypes(state.optimizedFetcher, blackhole);
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
        List<FacetInfo> facets = state.optimizedFetcher.get(state.newRequest());
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
        List<FacetInfo> facets = state.legacyFetcher.get(state.newRequest());
        blackhole.consume(facets);
    }

    private static final class PreviousRelationshipTypeFacetsFetcher extends RelationshipTypeFacetsFetcher {

        private PreviousRelationshipTypeFacetsFetcher() {
            super(EdgeDirection.OUT);
        }

        @Override
        protected List<FacetInfo> map(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node,
                                      Stream<Quad> input) {
            // The type facets before request-scoped type re-use: each unique related node's types are streamed from
            // the dataset (deduplicated across graphs), without consulting or populating the request cache
            Map<Node, Integer> relatedNodeCounts = new HashMap<>();
            input.forEach(q -> relatedNodeCounts.merge(getRelatedNode(q), 1, Integer::sum));
            Map<Node, Integer> typeFacetCounts = new HashMap<>();
            for (Map.Entry<Node, Integer> entry : relatedNodeCounts.entrySet()) {
                dsg.stream(Node.ANY, entry.getKey(), RDF.type.asNode(), Node.ANY)
                   .map(Quad::asTriple)
                   .distinct()
                   .forEach(t -> typeFacetCounts.merge(t.getObject(), entry.getValue(), Integer::sum));
            }
            return typeFacetCounts.entrySet()
                                  .stream()
                                  .map(e -> new FacetInfo(e.getKey(), dsg.prefixes(), e.getValue()))
                                  .toList();
        }
    }

    private static final class LegacyRelationshipTypeFacetsFetcher extends RelationshipTypeFacetsFetcher {

        private LegacyRelationshipTypeFacetsFetcher() {
            super(EdgeDirection.OUT);
        }

        private Stream<Quad> streamTypes(DatasetGraph dsg, Node relatedNode) {
            // Preserve the legacy uncached lookup and union-graph deduplication for each relationship.
            return dsg.stream(Node.ANY, relatedNode, RDF.type.asNode(), Node.ANY)
                      .map(Quad::asTriple)
                      .distinct()
                      .map(triple -> Quad.create(Quad.defaultGraphNodeGenerated, triple));
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
