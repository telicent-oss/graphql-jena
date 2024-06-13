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
package io.telicent.jena.graphql.fuseki;

import static org.apache.jena.fuseki.system.ActionCategory.ACTION;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertThrows;
import static org.testng.Assert.expectThrows;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.telicent.jena.graphql.execution.DatasetExecutor;
import io.telicent.jena.graphql.server.model.GraphQLRequest;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.jena.fuseki.server.DataService;
import org.apache.jena.fuseki.servlets.ActionErrorException;
import org.apache.jena.fuseki.servlets.HttpAction;
import org.apache.jena.riot.WebContent;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.testng.annotations.*;

public class TestActionGraphQL {

    private static ActionGraphQL ACTION_GRAPH_QL;

    private static final ServletContextHandler SERVLET_CONTEXT_HANDLER = new ServletContextHandler();
    private static final ServletContext SERVLET_CONTEXT = SERVLET_CONTEXT_HANDLER.getServletContext();
    private static final HttpServletRequest MOCK_REQUEST = mock(HttpServletRequest.class);
    private static final HttpServletResponse MOCK_RESPONSE = mock(HttpServletResponse.class);
    private static final Logger LOGGER = mock(Logger.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final String SAMPLE_QUERY = """
            query {
                quads {
                    subject {
                        kind
                        value
                    }
                    predicate {
                        kind
                        value
                    }
                    object {
                        kind
                        value
                    }
                }
            }""";


    @BeforeSuite
    public static void setUpClass() throws IOException {
        ACTION_GRAPH_QL = new ActionGraphQL(new DatasetExecutor(DatasetGraphFactory.empty()));
        when(MOCK_REQUEST.getServletContext()).thenReturn(SERVLET_CONTEXT);
    }

    @BeforeMethod
    public void setupTest() {
        when(MOCK_REQUEST.getServletContext()).thenReturn(SERVLET_CONTEXT);
    }

    @AfterMethod
    public void teardown() {
        reset(MOCK_REQUEST, MOCK_RESPONSE, LOGGER);
    }

    @Test
    public void test_validate_badGetRequest_noQueryParameter() {
        // given
        when(MOCK_REQUEST.getMethod()).thenReturn("GET");
        // when
        // then
        expectThrows("Reject GET request w/ no query parameters", ActionErrorException.class,
                     () -> ACTION_GRAPH_QL.process(getHttpAction()));
    }

    @Test
    public void test_validate_badPostRequest_wrongContentType() {
        // given
        when(MOCK_REQUEST.getMethod()).thenReturn("POST");
        when(MOCK_REQUEST.getContentType()).thenReturn(WebContent.contentTypeHTMLForm);
        // when
        // then
        expectThrows("Reject POST request w/ wrong content type", ActionErrorException.class,
                     () -> ACTION_GRAPH_QL.process(getHttpAction()));
    }

    @Test
    public void test_validate_badPostRequest_emptyContentType() {
        // given
        when(MOCK_REQUEST.getMethod()).thenReturn("POST");
        when(MOCK_REQUEST.getContentType()).thenReturn("");
        // when
        // then
        expectThrows("Reject POST request w/ wrong content type", ActionErrorException.class,
                     () -> ACTION_GRAPH_QL.process(getHttpAction()));
    }


    @Test(dataProvider = "invalidRequestMethods")
    public void test_process_invalidRequestMethods(String requestMethod) {
        // given
        when(MOCK_REQUEST.getMethod()).thenReturn(requestMethod);
        // when
        // then
        assertThrows(ActionErrorException.class, () -> ACTION_GRAPH_QL.process(getHttpAction()));
    }

    @DataProvider(name = "invalidRequestMethods")
    private static Object[] invalidRequestMethods() {
        return new Object[] { "PATCH", "PUT", "DELETE", "HEAD", "TRACE" };
    }

    @Test
    public void test_process_invalidInputStream() throws IOException {
        // given
        when(MOCK_REQUEST.getMethod()).thenReturn("POST");
        when(MOCK_REQUEST.getInputStream()).thenThrow(IOException.class);
        // when
        // then
        assertThrows(ActionErrorException.class, () -> ACTION_GRAPH_QL.process(getHttpAction()));
    }

    @Test
    public void test_process_invalidOutputStream_noExceptionThrown() throws IOException {
        // given
        when(MOCK_REQUEST.getMethod()).thenReturn("POST");
        GraphQLRequest request = new GraphQLRequest();
        request.setQuery("");
        String json = OBJECT_MAPPER.writeValueAsString(request);
        TestServletInputStream inputStream = new TestServletInputStream(new ByteArrayInputStream(json.getBytes()));
        when(MOCK_REQUEST.getInputStream()).thenReturn(inputStream);

        ServletOutputStream outputStream = mock(ServletOutputStream.class);
        doThrow(IOException.class).when(outputStream).close();
        when(MOCK_RESPONSE.getOutputStream()).thenReturn(outputStream);

        DataService mockDataService = mock(DataService.class);
        when(mockDataService.getDataset()).thenReturn(DatasetGraphFactory.empty());

        HttpAction action = getHttpAction();
        action.setRequest(null, mockDataService);

        // when
        boolean exceptionThrown = false;
        try {
            ACTION_GRAPH_QL.process(action);
        } catch (Exception e) {
            exceptionThrown = true;
        }
        // then
        assertFalse(exceptionThrown, "No exception should be thrown");
        verify(LOGGER).warn(eq("Failed to serialize GraphQL Results"), any(IOException.class));
    }

    @Test
    public void test_process_happyPath_POST() throws IOException {
        // given
        when(MOCK_REQUEST.getMethod()).thenReturn("POST");

        GraphQLRequest request = new GraphQLRequest();
        request.setQuery(SAMPLE_QUERY);
        String json = OBJECT_MAPPER.writeValueAsString(request);
        TestServletInputStream inputStream = new TestServletInputStream(new ByteArrayInputStream(json.getBytes()));
        when(MOCK_REQUEST.getInputStream()).thenReturn(inputStream);

        ServletOutputStream outputStream = mock(ServletOutputStream.class);
        when(MOCK_RESPONSE.getOutputStream()).thenReturn(outputStream);

        DataService mockDataService = mock(DataService.class);
        when(mockDataService.getDataset()).thenReturn(DatasetGraphFactory.empty());

        HttpAction action = getHttpAction();
        action.setRequest(null, mockDataService);

        // when
        boolean exceptionThrown = false;
        try {
            ACTION_GRAPH_QL.process(action);
        } catch (Exception e) {
            exceptionThrown = true;
        }
        // then
        assertFalse(exceptionThrown, "No exception should be thrown");
        verify(LOGGER, never()).warn(eq("Failed to serialize GraphQL Results"), any(IOException.class));
    }


    @Test
    public void test_process_happyPath_GET() throws IOException {
        // given
        when(MOCK_REQUEST.getMethod()).thenReturn("GET");
        when(MOCK_REQUEST.getParameter("query")).thenReturn(SAMPLE_QUERY);
        ServletOutputStream outputStream = mock(ServletOutputStream.class);
        when(MOCK_RESPONSE.getOutputStream()).thenReturn(outputStream);

        DataService mockDataService = mock(DataService.class);
        when(mockDataService.getDataset()).thenReturn(DatasetGraphFactory.empty());
        HttpAction action = getHttpAction();

        action.setRequest(null, mockDataService);

        // when
        boolean exceptionThrown = false;
        String exceptionMessage = "";
        try {
            ACTION_GRAPH_QL.process(action);
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
            exceptionThrown = true;
        }
        // then
        assertFalse(exceptionThrown, exceptionMessage);
        verify(LOGGER, never()).warn(eq("Failed to serialize GraphQL Results"), any(IOException.class));
    }

    private HttpAction getHttpAction() {
        return new HttpAction(1L, LOGGER, ACTION, MOCK_REQUEST, MOCK_RESPONSE);
    }

    private static class TestServletInputStream extends ServletInputStream {

        private final InputStream inputStream;

        public TestServletInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public int read() throws IOException {
            return this.inputStream.read();
        }

        @Override
        public void close() throws IOException {
            super.close();
            this.inputStream.close();
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
        }
    }
}
