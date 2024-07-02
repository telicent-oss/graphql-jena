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
package io.telicent.jena.graphql.execution;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import graphql.ErrorType;
import graphql.ExecutionResult;
import graphql.GraphQLError;
import graphql.ParseAndValidateResult;
import io.telicent.jena.graphql.schemas.models.NodeKind;
import io.telicent.jena.graphql.schemas.models.WrappedNode;
import io.telicent.jena.graphql.server.model.GraphQLRequest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDFS;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class AbstractExecutionTests {

    private static final ObjectMapper JSON = new JsonMapper();

    protected static String loadQuery(String queryBase, String queryResource) {
        try {
            return IOUtils.resourceToString(queryBase + queryResource, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static GraphQLRequest loadRequest(String requestBase, String requestResource) {
        try (InputStream input = AbstractExecutionTests.class.getResourceAsStream(requestBase + requestResource)) {
            if (input == null) {
                throw new RuntimeException(requestBase + requestResource + " is not a valid Classpath resource");
            }
            return JSON.readValue(input, GraphQLRequest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifies that the given query can be executed without errors and returns data
     *
     * @param execution Execution to run the query against
     * @param query     Raw GraphQL Query
     * @return Execution result
     */
    protected static ExecutionResult verifyExecution(GraphQLExecutor execution, String query) {
        ExecutionResult result = execution.execute(query);
        Assert.assertTrue(result.getErrors().isEmpty());
        Assert.assertTrue(result.isDataPresent());
        return result;
    }

    /**
     * Verifies that the given query can be executed without errors and returns data
     *
     * @param execution Execution to run the query against
     * @param query     Raw GraphQL Query
     * @param variables Variables for use by the query
     * @return Execution result
     */
    protected static ExecutionResult verifyExecution(GraphQLExecutor execution, String query,
                                                     Map<String, Object> variables) {
        ExecutionResult result = execution.execute(query, variables);
        Assert.assertTrue(result.getErrors().isEmpty());
        Assert.assertTrue(result.isDataPresent());
        return result;
    }

    /**
     * Verifies that the given query request can be executed without errors and returns data
     *
     * @param execution Execution to run the query against
     * @param request   Query request
     * @return Execution result
     */
    protected static ExecutionResult verifyExecution(GraphQLExecutor execution, GraphQLRequest request) {
        ExecutionResult result = execution.execute(request);
        Assert.assertTrue(result.getErrors().isEmpty());
        Assert.assertTrue(result.isDataPresent());
        return result;
    }

    /**
     * Verifies that the given query is valid
     *
     * @param execution Execution to validate the query against
     * @param query     Raw GraphQL Query
     * @return Validation result
     */
    protected static ParseAndValidateResult verifyValidationSuccess(GraphQLExecutor execution, String query) {
        return verifyValidationSuccess(execution, query, Collections.emptyMap());
    }

    /**
     * Verifies that the given query is valid
     *
     * @param execution Execution to validate the query against
     * @param query     Raw GraphQL Query
     * @param variables Variables for use by the query
     * @return Validation result
     */
    protected static ParseAndValidateResult verifyValidationSuccess(GraphQLExecutor execution, String query,
                                                     Map<String, Object> variables) {
        return verifyValidationSuccess(execution, query, variables, Collections.emptyMap());
    }

    /**
     * Verifies that the given query is valid
     *
     * @param execution  Execution to validate the query against
     * @param query      Raw GraphQL Query
     * @param variables  Variables for use by the query
     * @param extensions Extensions for use by the query
     * @return Validation result
     */
    protected static ParseAndValidateResult verifyValidationSuccess(GraphQLExecutor execution, String query,
                                                                    Map<String, Object> variables, Map<String,Object> extensions) {
        ParseAndValidateResult result = execution.validate(query, "test", variables, extensions);
        Assert.assertFalse(result.isFailure());
        Assert.assertTrue(result.getErrors().isEmpty());
        return result;
    }

    /**
     * Verifies that the given query is invalid
     *
     * @param execution Execution to validate the query against
     * @param query     Raw GraphQL Query
     * @return Validation result
     */
    protected static ParseAndValidateResult verifyValidationFailure(GraphQLExecutor execution, String query) {
        return verifyValidationFailure(execution, query, Collections.emptyMap());
    }

    /**
     * Verifies that the given query is invalid
     *
     * @param execution Execution to validate the query against
     * @param query     Raw GraphQL Query
     * @param variables Variables for use by the query
     * @return Validation result
     */
    protected static ParseAndValidateResult verifyValidationFailure(GraphQLExecutor execution, String query,
                                                                    Map<String, Object> variables) {
        return verifyValidationFailure(execution, query, variables, Collections.emptyMap());
    }
    /**
     * Verifies that the given query is invalid
     *
     * @param execution  Execution to validate the query against
     * @param query      Raw GraphQL Query
     * @param variables  Variables for use by the query
     * @param extensions Extensions for use by the query
     * @return Validation result
     */
    protected static ParseAndValidateResult verifyValidationFailure(GraphQLExecutor execution, String query,
                                                                    Map<String, Object> variables, Map<String,Object> extensions) {
        ParseAndValidateResult result = execution.validate(query, "test", variables, extensions);
        Assert.assertTrue(result.isFailure());
        Assert.assertFalse(result.getErrors().isEmpty());
        List<GraphQLError> errors = result.getErrors();
        Assert.assertEquals(errors.size(), 1);
        GraphQLError error = errors.get(0);
        Assert.assertEquals(error.getErrorType(), ErrorType.InvalidSyntax);
        return result;
    }


    protected static void verifyNode(Map<String, Object> quad, String field, NodeKind kind, String expectedValue,
                                     String expectedLang, String expectedDatatype) {
        @SuppressWarnings("unchecked")
        WrappedNode node = new WrappedNode((Map<String, Object>) quad.get(field));
        Assert.assertEquals(node.getKind(), kind);
        if (StringUtils.isNotBlank(expectedValue)) {
            Assert.assertEquals(node.getValue(), expectedValue);
        }
        // Intentionally no else here, we're either not selecting the value OR we don't know the expected value here
        // e.g. a blank node

        if (StringUtils.isNotBlank(expectedLang)) {
            Assert.assertEquals(node.getLanguage(), expectedLang);
        } else if (kind == NodeKind.LANGUAGE_LITERAL) {
            // Not expected a language field because we weren't selecting it BUT Jena coerces a null language to the
            // empty language
            Assert.assertNotNull(node.getLanguage());
            Assert.assertTrue(StringUtils.isBlank(node.getLanguage()));
        } else {
            Assert.assertNull(node.getLanguage());
        }
        if (StringUtils.isNotBlank(expectedDatatype)) {
            Assert.assertEquals(node.getDatatype(), expectedDatatype);
        } else {
            Assert.assertNull(node.getDatatype());
        }
    }

    @SuppressWarnings("unchecked")
    protected static void verifyNodes(List<Map<String, Object>> quads, String field, List<WrappedNode> expected) {
        Set<WrappedNode> expectedNodes = new HashSet<>(expected);
        Set<WrappedNode> actualNodes = new HashSet<>();
        for (Map<String, Object> quad : quads) {
            actualNodes.add(new WrappedNode((Map<String, Object>) quad.get(field)));
        }

        Assert.assertEquals(actualNodes.size(), expectedNodes.size());

        for (WrappedNode actual : actualNodes) {
            Assert.assertTrue(expectedNodes.remove(actual), "Found a Node " + actual + " that was not expected");
        }
    }

    protected static void verifyFields(Map<String, Object> map, String[] expectedFields, String[] unexpectedFields) {
        for (String field : expectedFields) {
            Assert.assertTrue(map.containsKey(field), "Expected field " + field + " missing");
        }
        for (String field : unexpectedFields) {
            Assert.assertFalse(map.containsKey(field), "Unexpected field " + field + " present");
        }
    }

    protected static void generateDummyQuads(DatasetGraph dsg, int size) {
        for (int i = 1; i <= size; i++) {
            dsg.add(Quad.defaultGraphIRI, NodeFactory.createURI("https://example.org/" + i), RDFS.comment.asNode(),
                    NodeFactory.createLiteralLang("foo", "en-gb"));
        }
    }

    protected static List<WrappedNode> getDummyExpected(int size) {
        List<WrappedNode> nodes = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            nodes.add(new WrappedNode(NodeFactory.createURI("https://example.org/" + i)));
        }
        return nodes;
    }
}
