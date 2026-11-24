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
package io.telicent.jena.graphql.fetchers.telicent.graph;

import graphql.schema.DataFetchingEnvironment;
import io.telicent.jena.graphql.access.DefaultGraphAccess;
import io.telicent.jena.graphql.access.GraphAccess;
import io.telicent.jena.graphql.execution.telicent.graph.TelicentExecutionContext;
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import io.telicent.jena.graphql.schemas.telicent.graph.models.FacetInfoPlaceholder;
import io.telicent.jena.graphql.schemas.telicent.graph.models.NodePlaceholder;
import io.telicent.jena.graphql.schemas.telicent.graph.models.Relationship;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.Filter;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.FilterMode;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.InboundTypeFilter;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.PredicateFilter;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.DatasetGraphWrapper;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.apache.jena.graph.NodeFactory.createURI;

/**
 * How the relationship fetchers use a {@link GraphAccess} when the dataset has one
 */
public class TestRelationshipsGraphAccess extends AbstractFetcherTests {

    private static final Node GRAPH = createURI("graph");
    private static final Node SUBJECT = createURI("subject");
    private static final Node PREDICATE = createURI("predicate");
    private static final Node TYPE = createURI("type");

    /**
     * A dataset that counts how often its quads are read
     */
    private static final class RecordingDataset extends DatasetGraphWrapper {
        private final AtomicInteger reads = new AtomicInteger();

        private RecordingDataset(DatasetGraph dsg) {
            super(dsg);
        }

        @Override
        public Iterator<Quad> find(Node g, Node s, Node p, Node o) {
            this.reads.incrementAndGet();
            return super.find(g, s, p, o);
        }

        @Override
        public Stream<Quad> stream(Node g, Node s, Node p, Node o) {
            this.reads.incrementAndGet();
            return super.stream(g, s, p, o);
        }
    }

    /**
     * A graph access that answers every count with a fixed number, recording the predicates it was asked about
     */
    private static final class FixedCountAccess extends DefaultGraphAccess {
        private final List<Collection<Node>> countCalls = new ArrayList<>();

        @Override
        public long[] countRelationships(DatasetGraph dsg, List<Node> nodes, EdgeDirection direction,
                                         Collection<Node> predicates) {
            this.countCalls.add(predicates);
            long[] counts = new long[nodes.size()];
            Arrays.fill(counts, 42);
            return counts;
        }
    }

    private static RecordingDataset datasetWith(GraphAccess access) {
        DatasetGraph base = DatasetGraphFactory.create();
        for (int i = 0; i < 100; i++) {
            base.add(GRAPH, SUBJECT, PREDICATE, createURI("object" + i));
            base.add(GRAPH, createURI("subject" + i), PREDICATE, SUBJECT);
        }
        RecordingDataset dsg = new RecordingDataset(base);
        if (access != null) {
            dsg.getContext().set(GraphAccess.SYMBOL, access);
        }
        return dsg;
    }

    private static DataFetchingEnvironment countEnvironment(DatasetGraph dsg, Map<String, Object> arguments) {
        return prepareFetchingEnvironment(new TelicentExecutionContext(dsg, ""),
                                          new NodePlaceholder(new TelicentGraphNode(SUBJECT, null)), arguments);
    }

    private static Map<String, Object> filter(String argument, String mode, Node... values) {
        return Map.of(argument, Map.of(TelicentGraphSchema.ARGUMENT_MODE, mode, TelicentGraphSchema.ARGUMENT_VALUES,
                                       Stream.of(values).map(Node::getURI).toList()));
    }

    @Test
    public void givenGraphAccess_whenCountingRelationships_thenCountComesFromGraphAccessAndNoQuadsAreRead()
            throws Exception {
        // Given
        FixedCountAccess access = new FixedCountAccess();
        RecordingDataset dsg = datasetWith(access);
        AtomicInteger listsBuilt = new AtomicInteger();
        RelationshipCountsFetcher fetcher = new RelationshipCountsFetcher(EdgeDirection.IN) {
            @Override
            protected List<Quad> generateRelationships(DatasetGraph dsg, TelicentGraphNode node,
                                                       List<Filter> filters) {
                listsBuilt.incrementAndGet();
                return super.generateRelationships(dsg, node, filters);
            }
        };

        // When
        Integer count = fetcher.get(countEnvironment(dsg, Map.of()));

        // Then
        Assert.assertEquals(count, 42);
        Assert.assertEquals(listsBuilt.get(), 0);
        Assert.assertEquals(dsg.reads.get(), 0);
        Assert.assertEquals(access.countCalls, List.of(List.of()));
    }

    @Test
    public void givenGraphAccessAndPredicateIncludeFilter_whenCounting_thenPredicatesArePassedOn() throws Exception {
        // Given
        FixedCountAccess access = new FixedCountAccess();
        RecordingDataset dsg = datasetWith(access);

        // When
        Integer count = new RelationshipCountsFetcher(EdgeDirection.OUT).get(
                countEnvironment(dsg, filter(TelicentGraphSchema.ARGUMENT_PREDICATE_FILTER, "INCLUDE", PREDICATE)));

        // Then
        Assert.assertEquals(count, 42);
        Assert.assertEquals(access.countCalls.size(), 1);
        Assert.assertEquals(Set.copyOf(access.countCalls.get(0)), Set.of(PREDICATE));
        Assert.assertEquals(dsg.reads.get(), 0);
    }

    @Test
    public void givenGraphAccessAndFilterItCannotExpress_whenCounting_thenQuadsAreReadAsBefore() throws Exception {
        for (Map<String, Object> arguments : List.of(
                filter(TelicentGraphSchema.ARGUMENT_PREDICATE_FILTER, "EXCLUDE", RDF.type.asNode()),
                filter(TelicentGraphSchema.ARGUMENT_TYPE_FILTER, "INCLUDE", TYPE),
                filter(TelicentGraphSchema.ARGUMENT_NODE_FILTER, "INCLUDE", createURI("object1")))) {
            // Given
            FixedCountAccess access = new FixedCountAccess();
            RecordingDataset dsg = datasetWith(access);
            RecordingDataset without = datasetWith(null);

            // When
            Integer count = new RelationshipCountsFetcher(EdgeDirection.OUT).get(countEnvironment(dsg, arguments));
            Integer expected =
                    new RelationshipCountsFetcher(EdgeDirection.OUT).get(countEnvironment(without, arguments));

            // Then
            Assert.assertEquals(count, expected);
            Assert.assertTrue(access.countCalls.isEmpty());
            Assert.assertTrue(dsg.reads.get() > 0);
        }
    }

    @Test
    public void givenNoGraphAccess_whenCounting_thenQuadsAreRead() throws Exception {
        // Given
        RecordingDataset dsg = datasetWith(null);

        // When
        Integer count = new RelationshipCountsFetcher(EdgeDirection.OUT).get(countEnvironment(dsg, Map.of()));

        // Then
        Assert.assertEquals(count, 100);
        Assert.assertTrue(dsg.reads.get() > 0);
    }

    @Test
    public void givenNoGraphAccess_whenCounting_thenNoListIsBuiltOrHeldAndTriplesAreCountedOnce() throws Exception {
        for (EdgeDirection direction : EdgeDirection.values()) {
            // Given
            RecordingDataset dsg = datasetWith(null);
            // The same relationships again in a second graph
            Node other = createURI("graph2");
            for (int i = 0; i < 100; i++) {
                dsg.add(other, SUBJECT, PREDICATE, createURI("object" + i));
                dsg.add(other, createURI("subject" + i), PREDICATE, SUBJECT);
            }
            TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
            AtomicInteger listsBuilt = new AtomicInteger();
            RelationshipCountsFetcher counts = new RelationshipCountsFetcher(direction) {
                @Override
                protected List<Quad> generateRelationships(DatasetGraph dsg, TelicentGraphNode node,
                                                           List<Filter> filters) {
                    listsBuilt.incrementAndGet();
                    return super.generateRelationships(dsg, node, filters);
                }
            };
            RelationshipsFetcher page = new RelationshipsFetcher(direction) {
                @Override
                protected List<Quad> generateRelationships(DatasetGraph dsg, TelicentGraphNode node,
                                                           List<Filter> filters) {
                    listsBuilt.incrementAndGet();
                    return super.generateRelationships(dsg, node, filters);
                }
            };

            // When
            Integer count = counts.get(prepareFetchingEnvironment(context, new NodePlaceholder(
                    new TelicentGraphNode(SUBJECT, null)), Map.of()));
            boolean heldAfterCount = page.isSelected(pageEnvironment(context, Map.of()),
                                                     new TelicentGraphNode(SUBJECT, null));

            // Then
            Assert.assertEquals(count, 100);
            Assert.assertEquals(listsBuilt.get(), 0);
            Assert.assertFalse(heldAfterCount);
        }
    }

    @Test
    public void givenDefaultGraphAccess_whenCounting_thenSameCountsAsWithout() throws Exception {
        for (EdgeDirection direction : EdgeDirection.values()) {
            // Given
            RecordingDataset with = datasetWith(new DefaultGraphAccess());
            RecordingDataset without = datasetWith(null);

            // When
            Integer a = new RelationshipCountsFetcher(direction).get(countEnvironment(with, Map.of()));
            Integer b = new RelationshipCountsFetcher(direction).get(countEnvironment(without, Map.of()));

            // Then
            Assert.assertEquals(a, b);
            Assert.assertEquals(a, 100);
        }
    }

    /**
     * A graph access that records the pages it is asked for and answers them from the default implementation
     */
    private static final class RecordingPageAccess extends DefaultGraphAccess {
        private final List<long[]> pageCalls = new ArrayList<>();
        private final List<Collection<Node>> pagePredicates = new ArrayList<>();

        @Override
        public Stream<Quad> relationships(DatasetGraph dsg, Node node, EdgeDirection direction,
                                          Collection<Node> predicates, long offset, long limit) {
            this.pageCalls.add(new long[] { offset, limit });
            this.pagePredicates.add(predicates);
            return super.relationships(dsg, node, direction, predicates, offset, limit);
        }
    }

    private static DataFetchingEnvironment pageEnvironment(TelicentExecutionContext context,
                                                           Map<String, Object> arguments) {
        return prepareFetchingEnvironment(context, new TelicentGraphNode(SUBJECT, null), arguments);
    }

    private static List<String> objects(List<Relationship> relationships) {
        return relationships.stream().map(r -> r.getDomain().getUri() + " " + r.getRange().getUri()).toList();
    }

    @Test
    public void givenGraphAccess_whenFetchingAPage_thenOnlyThatPageIsAskedForWithAZeroBasedOffset() throws Exception {
        // Given
        RecordingPageAccess access = new RecordingPageAccess();
        RecordingDataset dsg = datasetWith(access);
        AtomicInteger listsBuilt = new AtomicInteger();
        RelationshipsFetcher fetcher = new RelationshipsFetcher(EdgeDirection.OUT) {
            @Override
            protected List<Quad> generateRelationships(DatasetGraph dsg, TelicentGraphNode node,
                                                       List<Filter> filters) {
                listsBuilt.incrementAndGet();
                return super.generateRelationships(dsg, node, filters);
            }
        };
        Map<String, Object> arguments = Map.of(TelicentGraphSchema.ARGUMENT_OFFSET, 3, TelicentGraphSchema.ARGUMENT_LIMIT, 5);

        // When
        List<Relationship> page = fetcher.get(pageEnvironment(new TelicentExecutionContext(dsg, ""), arguments));
        List<Relationship> expected = new RelationshipsFetcher(EdgeDirection.OUT).get(
                pageEnvironment(new TelicentExecutionContext(datasetWith(null), ""), arguments));

        // Then
        Assert.assertEquals(objects(page), objects(expected));
        Assert.assertEquals(page.size(), 5);
        Assert.assertEquals(listsBuilt.get(), 0);
        Assert.assertEquals(access.pageCalls.size(), 1);
        Assert.assertEquals(access.pageCalls.get(0), new long[] { 2, 5 });
        Assert.assertEquals(access.pagePredicates.get(0), List.of());
    }

    @Test
    public void givenGraphAccess_whenFetchingPagesWithDefaultLimitAndPredicates_thenSameAsWithout() throws Exception {
        for (EdgeDirection direction : EdgeDirection.values()) {
            for (Map<String, Object> arguments : List.of(Map.<String, Object>of(),
                                                         Map.<String, Object>of(TelicentGraphSchema.ARGUMENT_OFFSET, 90),
                                                         filter(TelicentGraphSchema.ARGUMENT_PREDICATE_FILTER,
                                                                "INCLUDE", PREDICATE))) {
                // Given
                RecordingPageAccess access = new RecordingPageAccess();

                // When
                List<Relationship> with = new RelationshipsFetcher(direction).get(
                        pageEnvironment(new TelicentExecutionContext(datasetWith(access), ""), arguments));
                List<Relationship> without = new RelationshipsFetcher(direction).get(
                        pageEnvironment(new TelicentExecutionContext(datasetWith(null), ""), arguments));

                // Then
                Assert.assertEquals(access.pageCalls.size(), 1);
                Assert.assertEquals(objects(with), objects(without));
            }
        }
    }

    @Test
    public void givenListAlreadyBuiltForTheRequest_whenFetchingAPageOrCount_thenTheListIsUsed() throws Exception {
        // Given
        RecordingPageAccess pageAccess = new RecordingPageAccess();
        FixedCountAccess countAccess = new FixedCountAccess();
        RecordingDataset dsg = datasetWith(pageAccess);
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        // Facets need the whole list, so build it for this request first
        new RelationshipPredicateFacetsFetcher(EdgeDirection.OUT).get(
                prepareFetchingEnvironment(context, new FacetInfoPlaceholder(
                        new TelicentGraphNode(SUBJECT, null), null, null), Map.of()));

        // When
        List<Relationship> page = new RelationshipsFetcher(EdgeDirection.OUT).get(pageEnvironment(context, Map.of()));
        dsg.getContext().set(GraphAccess.SYMBOL, countAccess);
        Integer count = new RelationshipCountsFetcher(EdgeDirection.OUT).get(
                prepareFetchingEnvironment(context, new NodePlaceholder(new TelicentGraphNode(SUBJECT, null)),
                                           Map.of()));

        // Then
        Assert.assertEquals(page.size(), TelicentGraphSchema.DEFAULT_LIMIT);
        Assert.assertTrue(pageAccess.pageCalls.isEmpty());
        Assert.assertEquals(count, 100);
        Assert.assertTrue(countAccess.countCalls.isEmpty());
    }

    @Test
    public void givenFilters_whenTranslatingToGraphAccessPredicates_thenOnlyPredicateIncludesAreExpressible() {
        Node a = createURI("a");
        Node b = createURI("b");
        Node c = createURI("c");

        Assert.assertEquals(AbstractRelationshipsFetcher.graphAccessPredicates(List.of()), Optional.of(List.of()));
        Assert.assertEquals(AbstractRelationshipsFetcher.graphAccessPredicates(
                List.of(new PredicateFilter(FilterMode.INCLUDE, List.of(a, b)))).map(Set::copyOf), Optional.of(Set.of(a, b)));
        Assert.assertEquals(AbstractRelationshipsFetcher.graphAccessPredicates(
                                    List.of(new PredicateFilter(FilterMode.INCLUDE, List.of(a, b)),
                                            new PredicateFilter(FilterMode.INCLUDE, List.of(b, c))))
                                                        .map(Set::copyOf), Optional.of(Set.of(b)));
        Assert.assertEquals(AbstractRelationshipsFetcher.graphAccessPredicates(
                List.of(new PredicateFilter(FilterMode.INCLUDE, List.of(a)),
                        new PredicateFilter(FilterMode.INCLUDE, List.of(c)))), Optional.empty());
        Assert.assertEquals(AbstractRelationshipsFetcher.graphAccessPredicates(
                List.of(new PredicateFilter(FilterMode.EXCLUDE, List.of(a)))), Optional.empty());
        Assert.assertEquals(AbstractRelationshipsFetcher.graphAccessPredicates(
                List.of(new InboundTypeFilter(FilterMode.INCLUDE, List.of(a)))), Optional.empty());
    }
}
