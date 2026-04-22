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
import io.telicent.jena.graphql.fetchers.telicent.graph.RelationshipPredicateFacetsFetcher;
import io.telicent.jena.graphql.fetchers.telicent.graph.RelationshipTypeFacetsFetcher;
import io.telicent.jena.graphql.fetchers.telicent.graph.RelationshipsFetcher;
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import io.telicent.jena.graphql.schemas.telicent.graph.models.FacetInfo;
import io.telicent.jena.graphql.schemas.telicent.graph.models.FacetInfoPlaceholder;
import io.telicent.jena.graphql.schemas.telicent.graph.models.NodePlaceholder;
import io.telicent.jena.graphql.schemas.telicent.graph.models.Relationship;
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

/**
 * Benchmarks request-scoped relationship traversal reuse in the Telicent graph fetchers.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class RelationshipFetcherReuseBenchmark {

    /**
     * Creates a relationship fetcher reuse benchmark.
     */
    public RelationshipFetcherReuseBenchmark() {
    }

    /**
     * Shared benchmark state.
     */
    @State(Scope.Benchmark)
    public static class RelationshipState {

        /**
         * Number of outbound relationships from the benchmark subject.
         */
        @Param({"100", "1000", "5000"})
        public int relationshipCount;

        private DatasetGraph dsg;
        private TelicentGraphNode sourceNode;
        private RelationshipsFetcher relationshipsFetcher;
        private RelationshipCountsFetcher countsFetcher;
        private RelationshipPredicateFacetsFetcher predicateFacetsFetcher;
        private RelationshipTypeFacetsFetcher typeFacetsFetcher;
        private Map<String, Object> arguments;

        /**
         * Creates a benchmark state container.
         */
        public RelationshipState() {
        }

        /**
         * Builds a synthetic dataset and fetchers for the benchmark.
         */
        @Setup
        public void setup() {
            this.dsg = DatasetGraphFactory.create();
            this.sourceNode = new TelicentGraphNode(NodeFactory.createURI("https://example.org/source"), null);
            generateRelationshipDataset(this.dsg, this.sourceNode.getNode(), this.relationshipCount);

            this.relationshipsFetcher = new RelationshipsFetcher(EdgeDirection.OUT);
            this.countsFetcher = new RelationshipCountsFetcher(EdgeDirection.OUT);
            this.predicateFacetsFetcher = new RelationshipPredicateFacetsFetcher(EdgeDirection.OUT);
            this.typeFacetsFetcher = new RelationshipTypeFacetsFetcher(EdgeDirection.OUT);
            this.arguments = Map.of(
                    TelicentGraphSchema.ARGUMENT_PREDICATE_FILTER,
                    Map.of(TelicentGraphSchema.ARGUMENT_MODE, "EXCLUDE",
                           TelicentGraphSchema.ARGUMENT_VALUES, List.of(RDF.type.getURI())),
                    TelicentGraphSchema.ARGUMENT_LIMIT,
                    (int) TelicentGraphSchema.DEFAULT_LIMIT,
                    TelicentGraphSchema.ARGUMENT_OFFSET,
                    1
            );
        }

        private static void generateRelationshipDataset(DatasetGraph dsg, Node sourceNode, int relationshipCount) {
            Node graph = NodeFactory.createURI("https://example.org/graph");
            for (int i = 0; i < relationshipCount; i++) {
                Node predicate = NodeFactory.createURI("https://example.org/predicate/" + (i % 12));
                Node object = NodeFactory.createURI("https://example.org/object/" + i);
                Node type = NodeFactory.createURI("https://example.org/type/" + (i % 8));
                dsg.add(new Quad(graph, sourceNode, predicate, object));
                dsg.add(new Quad(graph, object, RDF.type.asNode(), type));
            }
        }

        private DataFetchingEnvironment relationshipsEnvironment(TelicentExecutionContext context) {
            return DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                             .localContext(context)
                                             .source(this.sourceNode)
                                             .arguments(this.arguments)
                                             .build();
        }

        private DataFetchingEnvironment countsEnvironment(TelicentExecutionContext context) {
            return DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                             .localContext(context)
                                             .source(new NodePlaceholder(this.sourceNode))
                                             .arguments(this.arguments)
                                             .build();
        }

        private DataFetchingEnvironment facetsEnvironment(TelicentExecutionContext context) {
            return DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                             .localContext(context)
                                             .source(new FacetInfoPlaceholder(this.sourceNode, null, null))
                                             .arguments(this.arguments)
                                             .build();
        }
    }

    /**
     * Simulates the old behavior where rels, counts and predicate facets each do their own traversal.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole.
     * @throws Exception If fetcher execution fails.
     */
    @Benchmark
    public void outRelsCountsAndPredicateFacets_uncached(RelationshipState state, Blackhole blackhole) throws Exception {
        List<Relationship> relationships = state.relationshipsFetcher.get(
                state.relationshipsEnvironment(new TelicentExecutionContext(state.dsg, "")));
        Integer counts = state.countsFetcher.get(state.countsEnvironment(new TelicentExecutionContext(state.dsg, "")));
        List<FacetInfo> predicateFacets = state.predicateFacetsFetcher.get(
                state.facetsEnvironment(new TelicentExecutionContext(state.dsg, "")));

        blackhole.consume(relationships);
        blackhole.consume(counts);
        blackhole.consume(predicateFacets);
    }

    /**
     * Measures the same work with a shared request context so the relationship selection is reused.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole.
     * @throws Exception If fetcher execution fails.
     */
    @Benchmark
    public void outRelsCountsAndPredicateFacets_cached(RelationshipState state, Blackhole blackhole) throws Exception {
        TelicentExecutionContext context = new TelicentExecutionContext(state.dsg, "");
        List<Relationship> relationships = state.relationshipsFetcher.get(state.relationshipsEnvironment(context));
        Integer counts = state.countsFetcher.get(state.countsEnvironment(context));
        List<FacetInfo> predicateFacets = state.predicateFacetsFetcher.get(state.facetsEnvironment(context));

        blackhole.consume(relationships);
        blackhole.consume(counts);
        blackhole.consume(predicateFacets);
    }

    /**
     * Simulates the old behavior for the heavier relation panel path that also includes type facets.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole.
     * @throws Exception If fetcher execution fails.
     */
    @Benchmark
    public void outRelsCountsAndAllFacets_uncached(RelationshipState state, Blackhole blackhole) throws Exception {
        List<Relationship> relationships = state.relationshipsFetcher.get(
                state.relationshipsEnvironment(new TelicentExecutionContext(state.dsg, "")));
        Integer counts = state.countsFetcher.get(state.countsEnvironment(new TelicentExecutionContext(state.dsg, "")));
        List<FacetInfo> predicateFacets = state.predicateFacetsFetcher.get(
                state.facetsEnvironment(new TelicentExecutionContext(state.dsg, "")));
        List<FacetInfo> typeFacets = state.typeFacetsFetcher.get(
                state.facetsEnvironment(new TelicentExecutionContext(state.dsg, "")));

        blackhole.consume(relationships);
        blackhole.consume(counts);
        blackhole.consume(predicateFacets);
        blackhole.consume(typeFacets);
    }

    /**
     * Measures the heavier relation panel path with a shared request context.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole.
     * @throws Exception If fetcher execution fails.
     */
    @Benchmark
    public void outRelsCountsAndAllFacets_cached(RelationshipState state, Blackhole blackhole) throws Exception {
        TelicentExecutionContext context = new TelicentExecutionContext(state.dsg, "");
        List<Relationship> relationships = state.relationshipsFetcher.get(state.relationshipsEnvironment(context));
        Integer counts = state.countsFetcher.get(state.countsEnvironment(context));
        List<FacetInfo> predicateFacets = state.predicateFacetsFetcher.get(state.facetsEnvironment(context));
        List<FacetInfo> typeFacets = state.typeFacetsFetcher.get(state.facetsEnvironment(context));

        blackhole.consume(relationships);
        blackhole.consume(counts);
        blackhole.consume(predicateFacets);
        blackhole.consume(typeFacets);
    }
}
