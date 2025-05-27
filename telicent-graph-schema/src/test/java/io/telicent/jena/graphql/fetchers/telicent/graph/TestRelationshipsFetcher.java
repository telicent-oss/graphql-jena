/**
 * Copyright (C) Telicent Ltd
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package io.telicent.jena.graphql.fetchers.telicent.graph;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import io.telicent.jena.graphql.schemas.telicent.graph.models.Relationship;
import io.telicent.jena.graphql.schemas.telicent.graph.models.NodePlaceholder;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.apache.jena.graph.NodeFactory.*;

public class TestRelationshipsFetcher extends AbstractFetcherTests {

    private static final Node GRAPH = createURI("graph");

    @Test
    public void test_get_blankNode() throws Exception {
        // given
        RelationshipsFetcher fetcher = new RelationshipsFetcher(EdgeDirection.IN);
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(new Quad(GRAPH, createURI("subject1"), createURI("predicate1"), createLiteralString("object")));
        dsg.add(new Quad(GRAPH, createURI("subject2"), createURI("predicate2"), createBlankNode("object")));
        dsg.add(new Quad(GRAPH, createURI("subject3"), createURI("predicate3"), createURI("object")));
        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new TelicentGraphNode(createBlankNode("object"), null));

        // when
        List<Relationship> actualList = fetcher.get(environment);

        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }

    @Test
    public void test_get_uriNode() throws Exception {
        // given
        RelationshipsFetcher fetcher = new RelationshipsFetcher(EdgeDirection.IN);
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(new Quad(GRAPH, createURI("subject1"), createURI("predicate1"), createLiteralString("object")));
        dsg.add(new Quad(GRAPH, createURI("subject2"), createURI("predicate2"), createBlankNode("object")));
        dsg.add(new Quad(GRAPH, createURI("subject3"), createURI("predicate3"), createURI("object")));
        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new TelicentGraphNode(createURI("object"), null));

        // when
        List<Relationship> actualList = fetcher.get(environment);

        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
    }

    @Test
    public void givenSimpleGraph_whenFetchingRelationshipsWithPredicateIncludeFilter_thenOnlyRelevantRelationshipsFetched() throws
            Exception {
        // given
        RelationshipsFetcher fetcher = new RelationshipsFetcher(EdgeDirection.OUT);
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node subject = createURI("subject");
        dsg.add(new Quad(GRAPH, subject, createURI("predicate1"), createURI("object1")));
        dsg.add(new Quad(GRAPH, subject, createURI("predicate2"), createBlankNode("object2")));
        dsg.add(new Quad(GRAPH, subject, createURI("predicate3"), createURI("object3")));

        for (Quad q : dsg.stream().toList()) {
            // Given
            DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new TelicentGraphNode(subject, null),
                                                                             Map.of(TelicentGraphSchema.ARGUMENT_PREDICATE_FILTER,
                                                                                    Map.of(TelicentGraphSchema.ARGUMENT_MODE,
                                                                                           "INCLUDE",
                                                                                           TelicentGraphSchema.ARGUMENT_VALUES,
                                                                                           List.of(q.getPredicate()
                                                                                                    .getURI()))));

            // When
            List<Relationship> actualList = fetcher.get(environment);

            // then
            Assert.assertNotNull(actualList);
            Assert.assertFalse(actualList.isEmpty());
            Assert.assertEquals(actualList.size(), 1);
            Relationship relationship = actualList.get(0);
            Assert.assertEquals(relationship.getPredicate(), q.getPredicate().getURI());
            Assert.assertEquals(relationship.getRange().getNode(), q.getObject());
        }
    }

    @Test
    public void givenSimpleGraph_whenFetchingRelationshipsWithPredicateExcludeFilter_thenOnlyOtherRelationshipsFetched() throws
            Exception {
        // given
        RelationshipsFetcher fetcher = new RelationshipsFetcher(EdgeDirection.OUT);
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node subject = createURI("subject");
        dsg.add(new Quad(GRAPH, subject, createURI("predicate1"), createURI("object1")));
        dsg.add(new Quad(GRAPH, subject, createURI("predicate2"), createBlankNode("object2")));
        dsg.add(new Quad(GRAPH, subject, createURI("predicate3"), createURI("object3")));

        for (Quad q : dsg.stream().toList()) {
            // Given
            DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new TelicentGraphNode(subject, null),
                                                                             Map.of(TelicentGraphSchema.ARGUMENT_PREDICATE_FILTER,
                                                                                    Map.of(TelicentGraphSchema.ARGUMENT_MODE,
                                                                                           "EXCLUDE",
                                                                                           TelicentGraphSchema.ARGUMENT_VALUES,
                                                                                           List.of(q.getPredicate()
                                                                                                    .getURI()))));

            // When
            List<Relationship> actualList = fetcher.get(environment);

            // then
            Assert.assertNotNull(actualList);
            Assert.assertFalse(actualList.isEmpty());
            Assert.assertEquals(actualList.size(), 2);
            Assert.assertTrue(actualList.stream().allMatch(r -> !r.getPredicate().equals(q.getPredicate())));
        }
    }

    private static void createTypedGraph(DatasetGraph dsg, Node subject) {
        Node rdfType = RDF.type.asNode();

        // Subject is related via predicate 1 to object 1 which has types 1 and 2
        Node object1 = createURI("object1");
        dsg.add(new Quad(GRAPH, subject, createURI("predicate1"), object1));
        dsg.add(new Quad(GRAPH, object1, createURI("predicate1"), subject));
        dsg.add(new Quad(GRAPH, object1, rdfType, createURI("type1")));
        dsg.add(new Quad(GRAPH, object1, rdfType, createURI("type2")));

        // Subject is related via predicate 2 to object 2 which has types 1 and 3
        Node object2 = createURI("object2");
        dsg.add(new Quad(GRAPH, subject, createURI("predicate2"), object2));
        dsg.add(new Quad(GRAPH, object2, createURI("predicate2"), subject));
        dsg.add(new Quad(GRAPH, object2, rdfType, createURI("type1")));
        dsg.add(new Quad(GRAPH, object2, rdfType, createURI("type3")));

        // Subject is related via predicate 3 to object 3 which has no types
        Node object3 = createURI("object3");
        dsg.add(new Quad(GRAPH, subject, createURI("predicate3"), object3));
        dsg.add(new Quad(GRAPH, object3, createURI("predicate3"), subject));
    }

    @DataProvider(name = "outboundTypeFilters")
    private Object[][] outboundTypeFilters() {
        return new Object[][] {
                // Both 1 and 2 have type 1
                { "INCLUDE", List.of("type1"), List.of("object1", "object2") },
                // Only 3 has no type so can't ever be excluded by a type filter
                { "EXCLUDE", List.of("type1"), List.of("object3") },
                // Only 1 has type 2
                { "INCLUDE", List.of("type2"), List.of("object1") },
                { "EXCLUDE", List.of("type2"), List.of("object2", "object3") },
                // Only 2 has type 3
                { "INCLUDE", List.of("type3"), List.of("object2") },
                { "EXCLUDE", List.of("type3"), List.of("object1", "object3") },
                // noSuchType not in graph
                { "INCLUDE", List.of("noSuchType"), Collections.emptyList() },
                { "EXCLUDE", List.of("noSuchType"), List.of("object1", "object2", "object3") },
                // Only 1 and 2 have types
                { "INCLUDE", List.of("type1", "type2", "type3"), List.of("object1", "object2") },
                // 3 has no types
                { "EXCLUDE", List.of("type1", "type2", "type3"), List.of("object3") },
                };
    }

    @DataProvider(name = "inboundTypeFilters")
    private Object[][] inboundTypeFilters() {
        return new Object[][] {
                // Only subject has type 0
                { "INCLUDE", List.of("type1"), List.of("object1", "object2") },
                // No inbound relationships with
                { "EXCLUDE", List.of("type1"), List.of("object3") },
                };
    }

    @DataProvider(name = "rangeFilters")
    private Object[][] rangeFilters() {
        return new Object[][] {
                { "INCLUDE", List.of("object1"), List.of("object1") },
                { "EXCLUDE", List.of("object1"), List.of("object2", "object3") },
                };
    }

    @DataProvider(name = "domainFilters")
    private Object[][] domainFilters() {
        return new Object[][] {
                { "INCLUDE", List.of("object1", "object3"), List.of("object1", "object3") },
                { "INCLUDE", List.of("object4"), Collections.emptyList() },
                { "EXCLUDE", List.of("object3"), List.of("object1", "object2") },
                };
    }

    @DataProvider(name = "outboundTypeAndPredicateFilters")
    private Object[][] outboundTypeAndPredicateFilters() {
        return new Object[][] {
                // Both 1 and 2 have type 1, but only 1 uses predicate 1
                { "INCLUDE", List.of("predicate1"), "INCLUDE", List.of("type1"), List.of("object1") },
                // Only 3 has no type so can't ever be excluded by a type filter, but is uses predicate 3
                { "EXCLUDE", List.of("predicate1"), "EXCLUDE", List.of("type1"), List.of("object3") },
                { "EXCLUDE", List.of("predicate3"), "EXCLUDE", List.of("type1"), Collections.emptyList() },
                // Only 1 has type 2 and predicate 1
                { "INCLUDE", List.of("predicate1"), "INCLUDE", List.of("type2"), List.of("object1") },
                { "INCLUDE", List.of("predicate2"), "INCLUDE", List.of("type2"), Collections.emptyList() },
                // Excluding by type 2 leave 2 and 3, but only 2 uses predicate 2
                { "INCLUDE", List.of("predicate2"), "EXCLUDE", List.of("type2"), List.of("object2") },
                // Only 2 has type 3
                { "INCLUDE", List.of("predicate2"), "INCLUDE", List.of("type3"), List.of("object2") },
                // But it doesn't use predicate 1
                { "INCLUDE", List.of("predicate1"), "INCLUDE", List.of("type3"), Collections.emptyList() },
                { "INCLUDE", List.of("predicate3"), "EXCLUDE", List.of("type3"), List.of("object3") },
                // noSuchType not in graph
                { "INCLUDE", List.of("predicate1"), "INCLUDE", List.of("noSuchType"), Collections.emptyList() },
                {
                        "INCLUDE",
                        List.of("predicate1", "predicate3"),
                        "EXCLUDE",
                        List.of("noSuchType"),
                        List.of("object1", "object3")
                },
                // Only 1 and 2 have types, but since those triples are not directly from our subject returns nothing
                {
                        "INCLUDE",
                        List.of(RDF.type.getURI()),
                        "INCLUDE",
                        List.of("type1", "type2", "type3"),
                        Collections.emptyList()
                },
                // 3 has no types
                {
                        "INCLUDE",
                        List.of(RDF.type.getURI()),
                        "EXCLUDE",
                        List.of("type1", "type2", "type3"),
                        Collections.emptyList()
                },
                };
    }

    @DataProvider(name = "predicateAndRangeFilters")
    private Object[][] predicateAndRangeFilters() {
        return new Object[][] {
                // Includes only predicate 1 and object 1 so only object 1 returned
                { "INCLUDE", List.of("predicate1"), "INCLUDE", List.of("object1"), List.of("object1") },
                // Includes any predicate and objects 1 or 2 so those objects are returned
                {
                        "INCLUDE",
                        List.of("predicate1", "predicate2", "predicate3"),
                        "INCLUDE",
                        List.of("object1", "object2"),
                        List.of("object1", "object2")
                },
                // Object 3 is linked via predicate 3 so combining these filters yields nothing
                { "INCLUDE", List.of("predicate1"), "INCLUDE", List.of("object3"), Collections.emptyList() },
                };
    }

    @Test(dataProvider = "outboundTypeFilters")
    public void givenGraphWithTypes_whenFetchingRelationshipsWithOutboundTypeFilter_thenOnlyRelationshipsWithRelevantTypedObjectsFetched(
            String filterMode, List<String> filterValues, List<String> expectedResults) throws Exception {
        verifyFetchedRelationships(filterMode, filterValues, expectedResults, EdgeDirection.OUT,
                                   TelicentGraphSchema.ARGUMENT_TYPE_FILTER, r -> r.getRange().getUri());
    }

    private static void verifyFetchedRelationships(String filterMode, List<String> filterValues,
                                                   List<String> expectedResults, EdgeDirection edgeDirection,
                                                   String filterArgument,
                                                   Function<Relationship, String> fetchedUriExtractor) throws Exception {
        // given
        RelationshipsFetcher fetcher = new RelationshipsFetcher(edgeDirection);
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node subject = createURI("subject");
        createTypedGraph(dsg, subject);

        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new TelicentGraphNode(subject, null),
                                                                         Map.of(filterArgument,
                                                                                Map.of(TelicentGraphSchema.ARGUMENT_MODE,
                                                                                       filterMode,
                                                                                       TelicentGraphSchema.ARGUMENT_VALUES,
                                                                                       filterValues)));

        // When
        List<Relationship> actualList = fetcher.get(environment);

        // then
        Assert.assertNotNull(actualList);
        Assert.assertEquals(actualList.size(), expectedResults.size());
        for (String expected : expectedResults) {
            Assert.assertTrue(
                    actualList.stream().map(fetchedUriExtractor).anyMatch(uri -> StringUtils.equals(uri, expected)));
        }
    }

    @Test(dataProvider = "inboundTypeFilters")
    public void givenGraphWithTypes_whenFetchingRelationshipsWithInboundTypeFilter_thenOnlyRelationshipsWithRelevantTypedObjectsFetched(
            String filterMode, List<String> filterValues, List<String> expectedResults) throws Exception {
        verifyFetchedRelationships(filterMode, filterValues, expectedResults, EdgeDirection.IN,
                                   TelicentGraphSchema.ARGUMENT_TYPE_FILTER, r -> r.getDomain().getUri());
    }

    @Test(dataProvider = "rangeFilters")
    public void givenGraphWithTypes_whenFetchingRelationshipsWithRangeFilter_thenOnlyRelationshipsWithRelevantObjectsFetched(
            String filterMode, List<String> filterValues, List<String> expectedResults) throws Exception {
        // given
        verifyFetchedRelationships(filterMode, filterValues, expectedResults, EdgeDirection.OUT,
                                   TelicentGraphSchema.ARGUMENT_RANGE_FILTER, r -> r.getRange().getUri());
    }

    @Test(dataProvider = "domainFilters")
    public void givenGraphWithTypes_whenFetchingRelationshipsWithDomainFilter_thenOnlyRelationshipsWithRelevantSubjectsFetched(
            String filterMode, List<String> filterValues, List<String> expectedResults) throws Exception {
        // given
        verifyFetchedRelationships(filterMode, filterValues, expectedResults, EdgeDirection.IN,
                                   TelicentGraphSchema.ARGUMENT_DOMAIN_FILTER, r -> r.getDomain().getUri());
    }

    @Test(dataProvider = "outboundTypeAndPredicateFilters")
    public void givenGraphWithTypes_whenFetchingRelationshipsWithOutboundPredicateAndTypeFilter_thenOnlyRelationshipsWithRelevantPredicatesAndTypedObjectsFetched(
            String predicateFilterMode, List<String> predicateFilters, String typeFilterMode, List<String> typeFilters,
            List<String> expectedResults) throws Exception {
        verifyDualFilterFetchedRelationships(predicateFilterMode, predicateFilters,
                                             TelicentGraphSchema.ARGUMENT_PREDICATE_FILTER, typeFilterMode, typeFilters,
                                             TelicentGraphSchema.ARGUMENT_TYPE_FILTER, expectedResults
        );
    }

    private static void verifyDualFilterFetchedRelationships(String filter1Mode, List<String> filter1Values,
                                                             String filter1Argument, String filter2Mode,
                                                             List<String> filter2Values, String filter2Argument,
                                                             List<String> expectedResults) throws Exception {
        // given
        RelationshipsFetcher fetcher = new RelationshipsFetcher(EdgeDirection.OUT);
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node subject = createURI("subject");
        createTypedGraph(dsg, subject);

        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new TelicentGraphNode(subject, null),
                                                                         Map.of(filter1Argument,
                                                                                Map.of(TelicentGraphSchema.ARGUMENT_MODE,
                                                                                       filter1Mode,
                                                                                       TelicentGraphSchema.ARGUMENT_VALUES,
                                                                                       filter1Values),
                                                                                filter2Argument,
                                                                                Map.of(TelicentGraphSchema.ARGUMENT_MODE,
                                                                                       filter2Mode,
                                                                                       TelicentGraphSchema.ARGUMENT_VALUES,
                                                                                       filter2Values)));

        // When
        List<Relationship> actualList = fetcher.get(environment);

        // then
        Assert.assertNotNull(actualList);
        Assert.assertEquals(actualList.size(), expectedResults.size());
        for (String expected : expectedResults) {
            Assert.assertTrue(actualList.stream().anyMatch(r -> StringUtils.equals(r.getRange().getUri(), expected)));
        }
    }

    @Test(dataProvider = "predicateAndRangeFilters")
    public void givenGraphWithTypes_whenFetchingRelationshipsWithPredicateAndRangeFilter_thenOnlyRelationshipsWithRelevantPredicatesAndObjectsFetched(
            String predicateFilterMode, List<String> predicateFilters, String rangeFilterMode, List<String> rangeFilters,
            List<String> expectedResults) throws Exception {
        verifyDualFilterFetchedRelationships(predicateFilterMode, predicateFilters,
                                             TelicentGraphSchema.ARGUMENT_PREDICATE_FILTER, rangeFilterMode, rangeFilters,
                                             TelicentGraphSchema.ARGUMENT_RANGE_FILTER, expectedResults
        );
    }

    @Test
    public void givenGraphWithManyRelationships_whenFetchingNodeTypes_thenPagingIsApplied() throws Exception {
        // Given
        RelationshipsFetcher inFetcher = new RelationshipsFetcher(EdgeDirection.IN);
        RelationshipsFetcher outFetcher = new RelationshipsFetcher(EdgeDirection.OUT);
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graph = GRAPH;
        Node subject = createURI("subject");
        generateManyRelationships(dsg, graph, subject);
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new TelicentGraphNode(subject, null));

        // When
        List<Relationship> inRels = inFetcher.get(environment);
        List<Relationship> outRels = outFetcher.get(environment);

        // Then
        Assert.assertNotNull(inRels);
        Assert.assertEquals(inRels.size(), TelicentGraphSchema.DEFAULT_LIMIT);
        Assert.assertNotNull(outRels);
        Assert.assertEquals(outRels.size(), TelicentGraphSchema.DEFAULT_LIMIT);
    }

    @Test
    public void givenGraphWithManyRelationships_whenCountingNodeTypes_thenCountIsCorrect() throws Exception {
        // Given
        DataFetcher<Integer> inFetcher = new RelationshipCountsFetcher(EdgeDirection.IN);
        DataFetcher<Integer> outFetcher = new RelationshipCountsFetcher(EdgeDirection.OUT);
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graph = GRAPH;
        Node subject = createURI("subject");
        generateManyRelationships(dsg, graph, subject);
        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new NodePlaceholder(new TelicentGraphNode(subject, null)));

        // When
        Integer inCount = inFetcher.get(environment);
        Integer outCount = outFetcher.get(environment);

        // Then
        Assert.assertNotNull(inCount);
        Assert.assertEquals(inCount, 1_000);
        Assert.assertNotNull(outCount);
        Assert.assertEquals(outCount, 1_000);
    }

    private static void generateManyRelationships(DatasetGraph dsg, Node graph, Node subject) {
        Node predicate = createURI("predicate");
        for (int i = 0; i < 1_000; i++) {
            dsg.add(graph, subject, predicate, createURI("object" + i));
            dsg.add(graph, createURI("subject" + i), predicate, subject);
        }
    }
}
