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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentImpl;
import io.telicent.jena.graphql.execution.telicent.graph.TelicentExecutionContext;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentSearchResults;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.util.Collections.emptyMap;
import static org.apache.jena.graph.NodeFactory.*;

public class TestStartingSearchFetcher {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static WireMockServer WIRE_MOCK_SERVER;

    @BeforeClass
    public static void setupClass() {
        WIRE_MOCK_SERVER = new WireMockServer(wireMockConfig().port(8181));
        WIRE_MOCK_SERVER.start();
    }

    @AfterTest
    public void tidy() {
        WIRE_MOCK_SERVER.resetAll();
    }

    @AfterClass
    public static void cleanUp() {
        WIRE_MOCK_SERVER.stop();
    }


    // NOTE: All other fetchers use specific exceptions, worth a revisit?
    @Test(expectedExceptions = RuntimeException.class)
    public void givenNoSearchTerm_whenUsingSearchFetcher_thenErrorIsThrown() {
        // given
        StartingSearchFetcher fetcher = new StartingSearchFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createURI("object")));
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment =
                DataFetchingEnvironmentImpl.newDataFetchingEnvironment().localContext(context).build();
        // when
        // then
        fetcher.get(environment);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void givenBadSearchResponse_whenUsingSearchFetcher_thenErrorIsThrown() {
        // given
        WIRE_MOCK_SERVER.stubFor(get(urlEqualTo("/documents?query=test")).willReturn(notFound()));

        StartingSearchFetcher fetcher = new StartingSearchFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createURI("object")));
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "authtoken");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                                         .localContext(context)
                                                                         .arguments(Map.of("searchTerm", "test"))
                                                                         .build();
        // when
        // then
        fetcher.get(environment);
    }

    @Test
    public void givenEmptySearchResponse_whenUsingSearchFetcher_thenSuccess() {
        // given
        System.setProperty("SEARCH_API_URL", "http://localhost:8181/");
        WIRE_MOCK_SERVER.stubFor(get(urlEqualTo("/documents?query=test")).willReturn(ok().withBody("{}")));

        StartingSearchFetcher fetcher = new StartingSearchFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(),
                         createURI("object")));
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                                         .localContext(context)
                                                                         .arguments(Map.of("searchTerm", "test"))
                                                                         .build();
        // when
        List<TelicentGraphNode> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertTrue(actualList.isEmpty());
        WIRE_MOCK_SERVER.verify(getRequestedFor(urlEqualTo("/documents?query=test")));
    }

    @Test
    public void givenSearchResponse_whenUsingSearchFetcher_thenSuccess() throws IOException {
        // given
        Map<String, List<Map<String, Object>>> returnedData = Map.of("results", List.of(Map.of("document", Map.of("uri",
                                                                                                                  "subject")),
                                                                                        emptyMap(), Map.of("document",
                                                                                                           Map.of("something",
                                                                                                                  "else"))));
        WIRE_MOCK_SERVER.stubFor(get(urlEqualTo("/documents?query=test")).willReturn(
                ok().withBody(MAPPER.writeValueAsString(returnedData))));

        StartingSearchFetcher fetcher = new StartingSearchFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(new Quad(createURI("graph"), createURI("subject"), RDF.type.asNode(), createLiteralString("object1")));
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                                         .localContext(context)
                                                                         .arguments(
                                                                                 Map.of("graph", "graph", "searchTerm",
                                                                                        "test"))
                                                                         .build();
        // when
        List<TelicentGraphNode> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
        WIRE_MOCK_SERVER.verify(getRequestedFor(urlEqualTo("/documents?query=test")));
    }

    private static List<Map<?, ?>> createPagedResultDocuments() {
        return List.of(Map.of("document", Map.of("uri", "subject")), emptyMap(),
                       Map.of("document", Map.of("uri", "subject2", "something", "else")));
    }

    private static Map<String, Object> createSearchResults(List<Map<?, ?>> resultItems, int offset, int limit) {
        //@formatter:off
        return Map.of("results",
                        offset - 1 >= resultItems.size() ?
                                 Collections.emptyList() :
                                 resultItems.subList(offset - 1, Math.min(offset + limit, resultItems.size())),
                      "query", "test",
                      "type", "query",
                      "limit", limit,
                      "offset", offset,
                      "maybeMore", offset + limit - 1 < resultItems.size() - 1);
        //@formatter:on
    }

    public static DatasetGraph createPagedSearchTestDataset() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(new Quad(createURI("graph"), createURI("subject"), RDF.type.asNode(), createLiteralString("object1")));
        dsg.add(new Quad(createURI("graph"), createURI("subject2"), RDF.type.asNode(), createLiteralString("object2")));
        return dsg;
    }

    @Test
    public void givenPagedSearchResponse_whenUsingSearchFetcherWithLimit_thenSuccess_andCorrectResults() throws
            IOException {
        // given
        List<Map<?, ?>> resultItems = createPagedResultDocuments();
        Map<String, Object> pagedData = createSearchResults(resultItems, 1, 1);
        WIRE_MOCK_SERVER.stubFor(get(urlEqualTo("/documents?query=test&limit=1")).willReturn(
                ok().withBody(MAPPER.writeValueAsString(pagedData))));

        StartingSearchFetcher fetcher = new StartingSearchFetcher();
        DatasetGraph dsg = createPagedSearchTestDataset();
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                                         .localContext(context)
                                                                         .arguments(
                                                                                 Map.of("graph", "graph", "searchTerm",
                                                                                        "test", "limit", 1))
                                                                         .build();
        // when
        List<TelicentGraphNode> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
        WIRE_MOCK_SERVER.verify(getRequestedFor(urlEqualTo("/documents?query=test&limit=1")));
        // and
        Assert.assertTrue(actualList.stream().anyMatch(n -> n.getUri().equals("subject")));
    }

    @Test
    public void givenPagedSearchResponse_whenUsingSearchWithMetadataFetcherWithLimit_thenSuccess_andCorrectMetadataAndResults() throws
            IOException {
        // given
        List<Map<?, ?>> resultItems = createPagedResultDocuments();
        Map<String, Object> pagedData = createSearchResults(resultItems, 1, 1);
        WIRE_MOCK_SERVER.stubFor(get(urlEqualTo("/documents?query=test&limit=1")).willReturn(
                ok().withBody(MAPPER.writeValueAsString(pagedData))));

        StartingSearchWithMetadataFetcher fetcher = new StartingSearchWithMetadataFetcher();
        DatasetGraph dsg = createPagedSearchTestDataset();
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                                         .localContext(context)
                                                                         .arguments(
                                                                                 Map.of("graph", "graph", "searchTerm",
                                                                                        "test", "limit", 1))
                                                                         .build();
        // when
        TelicentSearchResults results = fetcher.get(environment);
        // then
        Assert.assertNotNull(results);
        Assert.assertFalse(results.getNodes().isEmpty());
        WIRE_MOCK_SERVER.verify(getRequestedFor(urlEqualTo("/documents?query=test&limit=1")));
        // and
        Assert.assertEquals(results.getLimit(), 1);
        Assert.assertEquals(results.getOffset(), 1);
        Assert.assertTrue(results.isMaybeMore());
        Assert.assertTrue(results.getNodes().stream().anyMatch(n -> n.getUri().equals("subject")));
    }

    @Test
    public void givenPagedSearchResponse_whenUsingSearchFetcherWithOffset_thenSuccess_andCorrectResults() throws
            IOException {
        // given
        List<Map<?, ?>> resultItems = createPagedResultDocuments();
        Map<String, Object> pagedData = createSearchResults(resultItems, 2, resultItems.size());
        WIRE_MOCK_SERVER.stubFor(get(urlEqualTo("/documents?query=test&offset=2")).willReturn(
                ok().withBody(MAPPER.writeValueAsString(pagedData))));

        StartingSearchFetcher fetcher = new StartingSearchFetcher();
        DatasetGraph dsg = createPagedSearchTestDataset();
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                                         .localContext(context)
                                                                         .arguments(
                                                                                 Map.of("graph", "graph", "searchTerm",
                                                                                        "test", "offset", 2))
                                                                         .build();
        // when
        List<TelicentGraphNode> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
        WIRE_MOCK_SERVER.verify(getRequestedFor(urlEqualTo("/documents?query=test&offset=2")));
        // and
        Assert.assertTrue(actualList.stream().anyMatch(n -> n.getUri().equals("subject2")));
    }

    @Test
    public void givenPagedSearchResponse_whenUsingSearchWithMetadataFetcherWithOffset_thenSuccess_andCorrectMetadataAndResults() throws
            IOException {
        // given
        List<Map<?, ?>> resultItems = createPagedResultDocuments();
        Map<String, Object> pagedData = createSearchResults(resultItems, 2, resultItems.size());
        WIRE_MOCK_SERVER.stubFor(get(urlEqualTo("/documents?query=test&offset=2")).willReturn(
                ok().withBody(MAPPER.writeValueAsString(pagedData))));

        StartingSearchWithMetadataFetcher fetcher = new StartingSearchWithMetadataFetcher();
        DatasetGraph dsg = createPagedSearchTestDataset();
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                                         .localContext(context)
                                                                         .arguments(
                                                                                 Map.of("graph", "graph", "searchTerm",
                                                                                        "test", "offset", 2))
                                                                         .build();
        // when
        TelicentSearchResults results = fetcher.get(environment);
        // then
        Assert.assertNotNull(results);
        Assert.assertFalse(results.getNodes().isEmpty());
        WIRE_MOCK_SERVER.verify(getRequestedFor(urlEqualTo("/documents?query=test&offset=2")));
        // and
        Assert.assertEquals(results.getLimit(), 3);
        Assert.assertEquals(results.getOffset(), 2);
        Assert.assertFalse(results.isMaybeMore());
        Assert.assertTrue(results.getNodes().stream().anyMatch(n -> n.getUri().equals("subject2")));
    }

    @Test
    public void givenPagedSearchResponse_whenUsingSearchFetcherWithLimitAndOffset_thenNoResults() throws IOException {
        // given
        List<Map<?, ?>> resultItems = createPagedResultDocuments();
        Map<String, Object> pagedData = createSearchResults(resultItems, 4, 1);
        WIRE_MOCK_SERVER.stubFor(get(urlEqualTo("/documents?query=test&limit=1&offset=4")).willReturn(
                ok().withBody(MAPPER.writeValueAsString(pagedData))));

        StartingSearchFetcher fetcher = new StartingSearchFetcher();
        DatasetGraph dsg = createPagedSearchTestDataset();
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                                         .localContext(context)
                                                                         .arguments(
                                                                                 Map.of("graph", "graph", "searchTerm",
                                                                                        "test", "offset", 4, "limit",
                                                                                        1))
                                                                         .build();
        // when
        List<TelicentGraphNode> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertTrue(actualList.isEmpty());
        WIRE_MOCK_SERVER.verify(getRequestedFor(urlEqualTo("/documents?query=test&limit=1&offset=4")));
    }

    @Test
    public void givenPagedSearchResponse_whenUsingSearchWithMetadataFetcherWithLimitAndOffset_thenMetadataAndNoResults() throws
            IOException {
        // given
        List<Map<?, ?>> resultItems = createPagedResultDocuments();
        Map<String, Object> pagedData = createSearchResults(resultItems, 4, 1);
        WIRE_MOCK_SERVER.stubFor(get(urlEqualTo("/documents?query=test&limit=1&offset=4")).willReturn(
                ok().withBody(MAPPER.writeValueAsString(pagedData))));

        StartingSearchWithMetadataFetcher fetcher = new StartingSearchWithMetadataFetcher();
        DatasetGraph dsg = createPagedSearchTestDataset();
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                                         .localContext(context)
                                                                         .arguments(
                                                                                 Map.of("graph", "graph", "searchTerm",
                                                                                        "test", "offset", 4, "limit",
                                                                                        1))
                                                                         .build();
        // when
        TelicentSearchResults results = fetcher.get(environment);
        // then
        Assert.assertNotNull(results);
        Assert.assertEquals(results.getLimit(), 1);
        Assert.assertEquals(results.getOffset(), 4);
        Assert.assertFalse(results.isMaybeMore());
        Assert.assertTrue(results.getNodes().isEmpty());
        WIRE_MOCK_SERVER.verify(getRequestedFor(urlEqualTo("/documents?query=test&limit=1&offset=4")));
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void givenInvalidLimit_whenUsingSearchFetcher_thenErrorIsThrown() {
        // given
        StartingSearchFetcher fetcher = new StartingSearchFetcher();
        DatasetGraph dsg = createPagedSearchTestDataset();
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                                         .localContext(context)
                                                                         .arguments(
                                                                                 Map.of("searchTerm", "test", "limit",
                                                                                        "foo"))
                                                                         .build();
        // when
        // then
        fetcher.get(environment);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void givenInvalidOffset_whenUsingSearchFetcher_thenErrorIsThrown() {
        // given
        StartingSearchFetcher fetcher = new StartingSearchFetcher();
        DatasetGraph dsg = createPagedSearchTestDataset();
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                                         .localContext(context)
                                                                         .arguments(
                                                                                 Map.of("searchTerm", "test", "offset",
                                                                                        "foo"))
                                                                         .build();
        // when
        // then
        fetcher.get(environment);
    }

}
