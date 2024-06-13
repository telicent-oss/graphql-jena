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
import graphql.Assert;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentImpl;
import io.telicent.jena.graphql.execution.telicent.graph.TelicentExecutionContext;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.util.Collections.emptyMap;
import static org.apache.jena.graph.NodeFactory.*;

public class TestStartingSearchFetcher {
    private static ObjectMapper MAPPER = new ObjectMapper();

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
    public void test_get_noSearchTerm()  {
        // given
        StartingSearchFetcher fetcher = new StartingSearchFetcher();
        DatasetGraph dsg  = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(), createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(), createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(), createURI("object")));
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .build();
        // when
        // then
        fetcher.get(environment);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void test_get_failedQuery() {
        // given
        WIRE_MOCK_SERVER.stubFor(get(urlEqualTo("/documents?query=test"))
                                         .willReturn(notFound()));

        StartingSearchFetcher fetcher = new StartingSearchFetcher();
        DatasetGraph dsg  = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(), createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(), createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(), createURI("object")));
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "authtoken");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .arguments(Map.of("searchTerm","test"))
                .build();
        // when
        // then
        fetcher.get(environment);
    }

    @Test
    public void test_get_emptyMapResult() {
        // given
        System.setProperty("SEARCH_API_URL", "http://localhost:8181/");
        WIRE_MOCK_SERVER.stubFor(get(urlEqualTo("/documents?query=test"))
                                         .willReturn(ok().withBody("{}")
                                         ));

        StartingSearchFetcher fetcher = new StartingSearchFetcher();
        DatasetGraph dsg  = DatasetGraphFactory.create();
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(), createLiteralString("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(), createBlankNode("object")));
        dsg.add(new Quad(createLiteralString("graph"), createBlankNode("subject"), RDF.type.asNode(), createURI("object")));
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .arguments(Map.of("searchTerm","test"))
                .build();
        // when
        List<TelicentGraphNode> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertTrue(actualList.isEmpty());
        WIRE_MOCK_SERVER.verify(getRequestedFor(urlEqualTo("/documents?query=test")));
    }

    @Test
    public void test_get_() throws IOException  {
        // given
        Map<String, List<Map<String,Object>>> returnedData = Map.of("results", List.of(Map.of("document", Map.of("uri", "subject")), emptyMap(), Map.of("document", Map.of("something", "else"))));
        WIRE_MOCK_SERVER.stubFor(get(urlEqualTo("/documents?query=test"))
                                         .willReturn(ok().withBody(MAPPER.writeValueAsString(returnedData))
                                         ));

        StartingSearchFetcher fetcher = new StartingSearchFetcher();
        DatasetGraph dsg  = DatasetGraphFactory.create();
        dsg.add(new Quad(createURI("graph"), createURI("subject"), RDF.type.asNode(), createLiteralString("object1")));
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .arguments(Map.of("graph", "graph", "searchTerm","test"))
                .build();
        // when
        List<TelicentGraphNode> actualList = fetcher.get(environment);
        // then
        Assert.assertNotNull(actualList);
        Assert.assertFalse(actualList.isEmpty());
        WIRE_MOCK_SERVER.verify(getRequestedFor(urlEqualTo("/documents?query=test")));
    }
}
