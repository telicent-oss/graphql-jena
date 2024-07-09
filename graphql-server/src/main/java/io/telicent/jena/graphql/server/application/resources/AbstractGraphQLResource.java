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
package io.telicent.jena.graphql.server.application.resources;

import com.fasterxml.jackson.core.JsonProcessingException;

import graphql.ExecutionResult;
import graphql.ParseAndValidateResult;
import io.telicent.jena.graphql.execution.GraphQLExecutor;
import io.telicent.jena.graphql.server.model.GraphQLOverHttp;
import io.telicent.smart.cache.server.jaxrs.model.Problem;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.riot.web.HttpNames;
import org.apache.jena.web.HttpSC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;

/**
 * Abstract JAX-RS resource for handling GraphQL requests
 */
public class AbstractGraphQLResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatasetResource.class);

    /**
     * Creates an abstract resource for handling GraphQL requests
     */
    public AbstractGraphQLResource() {

    }

    /**
     * Creates a 400 Bad Request response to a request
     *
     * @param e         JSON processing error
     * @param title     Error title
     * @param parameter Bad request parameter
     * @return 400 Bad Request response
     */
    protected static Response badRequest(JsonProcessingException e, String title, String parameter) {
        //@formatter:off
        return new Problem("BadRequest",
                           title,
                           HttpSC.BAD_REQUEST_400,
                           "Failed to parse GraphQL " + parameter + " as a valid JSON string: " + e.getMessage(),
                           null).toResponse();
        //@formatter:on
    }

    /**
     * Validate or execute the given Graph QL query (and associate variables/extensions).
     *
     * @param query          query to validate
     * @param operationName  operation Name
     * @param variables      variables to make available to the query
     * @param extensions     query extensions
     * @param servletContext for communicating with surrounding container (session management etc..)
     * @param executorType   relevant class to execute the operation
     * @param validate       flag indicating execution (false) or validation (true)
     * @return either a successful response (200) or the error(s) (400).
     */
    protected final Response executeOrValidateGraphQL(String query, String operationName, String variables, String extensions,
                                            ServletContext servletContext, Class<?> executorType, boolean validate) {
        boolean variable = true;
        try {
            Map<String, Object> parsedVariables = parseJSONStringIntoMap(variables);
            variable = false;
            Map<String, Object> parsedExtensions = parseJSONStringIntoMap(extensions);
            return executeOrValidateGraphQL(query, operationName, parsedVariables, parsedExtensions, servletContext, executorType, validate);
        } catch (JsonProcessingException exception) {
            if (variable) {
                return badRequest(exception, "Invalid GraphQL Variables", GraphQLOverHttp.PARAMETER_VARIABLES);
            } else {
                return badRequest(exception, "Invalid GraphQL Extensions", GraphQLOverHttp.PARAMETER_EXTENSIONS);
            }
        }
    }

    /**
     * Validate or execute the given Graph QL query (and associate variables/extensions).
     *
     * @param query          query to validate
     * @param operationName  operation Name
     * @param variables      variables to make available to the query
     * @param extensions     query extensions
     * @param servletContext for communicating with surrounding container (session management etc..)
     * @param executorType   relevant class to execute the operation
     * @param validate       flag indicating execution (false) or validation (true)
     * @return either a successful response (200) or the error(s) (400).
     */
    protected final Response executeOrValidateGraphQL(String query, String operationName, Map<String, Object> variables,
                                               Map<String, Object> extensions, ServletContext servletContext,
                                               Class<?> executorType, boolean validate) {
        if (variables == null) {
            variables = Collections.emptyMap();
        }
        if (extensions == null) {
            extensions = Collections.emptyMap();
        }

        GraphQLExecutor executor = (GraphQLExecutor) servletContext.getAttribute(executorType.getCanonicalName());
        if (executor == null) {
            //@formatter:off
            return new Problem("ServiceUnavailable",
                               "No " + executorType.getSimpleName() + " Configured",
                               HttpSC.INTERNAL_SERVER_ERROR_500,
                               "No GraphQL Executor configured for this API",
                               null).toResponse();
            //@formatter:on
        }

        if (validate) {
            ParseAndValidateResult result = executor.validate(query, operationName, variables, extensions);
            if (result.isFailure()) {
                return Response.status(400).entity(result.getErrors()).build();
            } else {
                return Response.status(200).entity("Query is valid").build();
            }
        } else {
            LOGGER.info("Starting GraphQL Query with executor {}...", executor.getClass().getSimpleName());
            ExecutionResult result = executor.execute(query, operationName, variables, extensions);
            Map<String, Object> specResponse = result.toSpecification();
            int status = GraphQLOverHttp.selectHttpStatus(result);
            LOGGER.info("Finished GraphQL Query with executor {}, returning status {}", executor.getClass().getSimpleName(),
                        status);
            return Response.status(status)
                           .entity(specResponse)
                           .header(HttpNames.hContentType, GraphQLOverHttp.CONTENT_TYPE_GRAPHQL_RESPONSE_JSON)
                           .build();
        }
    }

    /**
     * Converts a string into a map, throwing an exception if there's a problem or empty map if empty/null string.
     *
     * @param mapIsString JSON representation of map
     * @return a mapping of values
     * @throws JsonProcessingException if the JSON is invalid
     */
    private Map<String, Object> parseJSONStringIntoMap(String mapIsString) throws JsonProcessingException {
        if (StringUtils.isNotBlank(mapIsString)) {
            return GraphQLOverHttp.parseMap(mapIsString);
        } else {
            return Collections.emptyMap();
        }
    }
}
