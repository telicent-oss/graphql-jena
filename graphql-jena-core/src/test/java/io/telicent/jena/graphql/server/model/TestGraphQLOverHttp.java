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
package io.telicent.jena.graphql.server.model;

import graphql.ExecutionResult;
import graphql.ExecutionResultImpl;
import graphql.GraphQLError;
import graphql.execution.AbortExecutionException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.telicent.jena.graphql.server.model.GraphQLOverHttp.*;

public class TestGraphQLOverHttp {

    @Test
    public void test_selectHttpStatus_happy() {
        // given
        ExecutionResult result = new ExecutionResultImpl(Collections.emptyList());
        // when
        int actual = selectHttpStatus(result);
        // then
        Assert.assertEquals(actual, 200);
    }

    @Test
    public void test_selectHttpStatus_error() {
        // given
        GraphQLError error = new AbortExecutionException();
        ExecutionResult result = new ExecutionResultImpl(error);
        // when
        int actual = selectHttpStatus(result);
        // then
        Assert.assertEquals(actual, 400);
    }

    @Test
    public void test_selectHttpStatus_errorDataPresent() {
        // given
        GraphQLError error = new AbortExecutionException();
        ExecutionResult result = new ExecutionResultImpl(new Object(), List.of(error));
        // when
        int actual = selectHttpStatus(result);
        // then
        Assert.assertEquals(actual, 200);
    }

    @Test
    public void test_parseMap_empty() throws IOException  {
        // given
        // when
        Map<String,Object> map = parseMap("");
        // then
        Assert.assertNotNull(map);
        Assert.assertTrue(map.isEmpty());
    }

    @Test
    public void test_parseMap_happy() throws IOException  {
        // given
        // when
        Map<String,Object> map = parseMap("{}");
        // then
        Assert.assertNotNull(map);
        Assert.assertTrue(map.isEmpty());
    }

    @Test
    public void test_parseRequest() throws IOException {
        // given
        String testInput = "{}";
        InputStream testInputStream = new ByteArrayInputStream(testInput.getBytes());
        // when
        GraphQLRequest request = parseRequest(testInputStream);
        //then
        Assert.assertNotNull(request);
    }

    @DataProvider(name = "parseRequestCases")
    private static Object[][] parseRequestCases() {
        //@formatter:off
        return new Object[][] {
                // method, request body, parameter value, expects failure
                { "POST", "{}", "{}",        false },
                { "POST", "{",  "something", true  },
                { "GET",  "{}", "invalid",   true  },
                { "GET",  "{}", "{}",        false }
        };
        //@formatter:on
    }

    @Test(dataProvider = "parseRequestCases")
    public void test_parseRequest(String method, String body, String parameterValue, boolean expectsFailure) {
        // given
        InputStream mockInputStream = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
        // when and then
        if (expectsFailure) {
            Assert.assertThrows(RuntimeException.class,
                                () -> parseRequest("", method, (a, b) -> parameterValue, a -> mockInputStream));
        } else {
            Assert.assertNotNull(parseRequest("", method, (a, b) -> parameterValue, a -> mockInputStream));
        }
    }


    @Test
    public void test_write() throws IOException {
        // given
        ExecutionResult executionResult = new ExecutionResultImpl(Collections.emptyList());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // when
        write(executionResult, outputStream);
        //then
        String result = outputStream.toString(StandardCharsets.UTF_8); // Specify the encoding used in the conversion
        Assert.assertNotNull(result);
    }
}
