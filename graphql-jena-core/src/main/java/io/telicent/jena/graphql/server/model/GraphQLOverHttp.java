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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import graphql.ExecutionResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.web.HttpSC;

/**
 * Provides constants and helper methods relating to the <a
 * href="https://github.com/graphql/graphql-over-http/blob/main/spec/GraphQLOverHTTP.md">GraphQL over HTTP</a>
 * specification, e.g. parameter names, content types etc.
 */
public class GraphQLOverHttp {

    private GraphQLOverHttp() {}
    /**
     * The MIME Content Type for JSON based GraphQL responses
     */
    public static final String CONTENT_TYPE_GRAPHQL_RESPONSE_JSON = "application/graphql-response+json";
    /**
     * The {@code variables} parameter to GraphQL endpoints
     */
    public static final String PARAMETER_VARIABLES = "variables";
    /**
     * The {@code extensions} parameter to GraphQL endpoints
     */
    public static final String PARAMETER_EXTENSIONS = "extensions";
    /**
     * The {@code query} parameter to GraphQL endpoints
     */
    public static final String PARAMETER_QUERY = "query";
    /**
     * The {@code operationName} parameter to GraphQL endpoints
     */
    public static final String PARAMETER_OPERATION_NAME = "operationName";

    private static final ObjectMapper JSON = new JsonMapper();

    /**
     * A Jackson type reference for a generic map of String to Object which gets used extensively within the GraphQL
     * Java APIs, particularly in conveying GraphQL results back over HTTP
     */
    public static final TypeReference<Map<String, Object>> GENERIC_MAP_TYPE = new TypeReference<>() {
    };

    /**
     * Selects the HTTP Status Code to return with a GraphQL Response
     * <p>
     * The <a
     * href="https://github.com/graphql/graphql-over-http/blob/main/spec/GraphQLOverHTTP.md#applicationgraphql-responsejson">specification</a>
     * says that if any data is present we <strong>MUST</strong> return a 200 OK even if errors are also present.
     * Regardless of the status code the response will always encode the errors (if any) and the data (which might be
     * incomplete when errors have occurred).
     * </p>
     *
     * @param result GraphQL Execution Result
     * @return Either 200 OK or 400 Bad Request depending on the provided {@link ExecutionResult}
     */
    public static int selectHttpStatus(ExecutionResult result) {
        int status = HttpSC.OK_200;
        if (!result.getErrors().isEmpty() && !result.isDataPresent()) {
            status = HttpSC.BAD_REQUEST_400;
        }
        return status;
    }

    /**
     * Tries to parse in a raw input string that is a JSON encoded object representing an arbitrary map.
     * <p>
     * This is used by the {@code graphql-java} library for both query variables and vendor extensions
     * </p>
     *
     * @param rawInput Raw input, expected to be a valid JSON string encoding an object
     * @return Parsed map
     * @throws JsonProcessingException Thrown if the JSON is invalid
     */
    public static Map<String, Object> parseMap(String rawInput) throws JsonProcessingException {
        // Empty input generates an empty map, intentionally returning a mutable map in case other code wants to add
        // additional values from other sources
        if (StringUtils.isBlank(rawInput)) {
            return new HashMap<>();
        }
        return JSON.readValue(rawInput, GENERIC_MAP_TYPE);
    }

    /**
     * Parses a GraphQL Request from some arbitrary HTTP Request type, this type is intentionally parameterised to make
     * this method reusable across many different HTTP Application frameworks
     *
     * @param httpRequest    HTTP Request
     * @param method         HTTP Request Method
     * @param getParameter   A function that allows retrieving request parameters from the HTTP Request
     * @param getRequestBody A function that allows retrieving the request body from the HTTP Request
     * @param <T>            HTTP Request type
     * @return GraphQL Request
     * @throws RuntimeException If the HTTP Request cannot be parsed successfully
     */
    public static <T> GraphQLRequest parseRequest(T httpRequest, String method,
                                                  BiFunction<T, String, String> getParameter,
                                                  Function<T, InputStream> getRequestBody) {
        method = method.toUpperCase(Locale.ROOT);

        if (StringUtils.equals(method, "POST")) {
            // Read the whole body as a GraphQL Request
            try {
                return GraphQLOverHttp.parseRequest(getRequestBody.apply(httpRequest));
            } catch (IOException e) {
                throw new RuntimeException("Failed to parse POST request body as a valid GraphQL Request");
            }
        } else {
            // Read individual request parameters
            String query = getParameter.apply(httpRequest, GraphQLOverHttp.PARAMETER_QUERY);
            String opName = getParameter.apply(httpRequest, GraphQLOverHttp.PARAMETER_OPERATION_NAME);
            GraphQLRequest request = new GraphQLRequest();
            request.setQuery(query);
            request.setOperationName(opName);
            try {
                request.setVariables(
                        GraphQLOverHttp.parseMap(getParameter.apply(httpRequest, GraphQLOverHttp.PARAMETER_VARIABLES)));
                request.setExtensions(
                        GraphQLOverHttp.parseMap(
                                getParameter.apply(httpRequest, GraphQLOverHttp.PARAMETER_EXTENSIONS)));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to parse provided variables/extensions for the GraphQL Request");
            }
            return request;
        }

    }

    /**
     * Parses a GraphQL Request that is JSON encoded from the provided {@link InputStream}
     *
     * @param input Input Stream
     * @return GraphQL Request
     * @throws IOException Thrown if the input cannot be successfully parsed as a GraphQL Request
     */
    public static GraphQLRequest parseRequest(InputStream input) throws IOException {
        Objects.requireNonNull(input, "Cannot parse a GraphQL Request from a null input stream");
        return JSON.readValue(input, GraphQLRequest.class);
    }

    /**
     * Writes a GraphQL result as JSON to the provided {@link OutputStream}
     *
     * @param result GraphQL Result
     * @param output Output stream to write to
     * @throws IOException Thrown if the output cannot be successfully written
     */
    public static void write(ExecutionResult result, OutputStream output) throws IOException {
        Objects.requireNonNull(result, "Cannot write a null GraphQL Result");
        Objects.requireNonNull(output, "Cannot write a GraphQL Response to a null output stream");
        Map<String, Object> specResponse = result.toSpecification();
        JSON.writeValue(output, specResponse);
    }
}
