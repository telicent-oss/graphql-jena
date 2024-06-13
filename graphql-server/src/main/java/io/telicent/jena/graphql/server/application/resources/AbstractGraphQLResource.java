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

import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import graphql.ExecutionResult;
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
     * Executes a GraphQL Query
     *
     * @param query          Query
     * @param operationName  Operation name
     * @param variables      Raw variables
     * @param extensions     Raw extensions
     * @param servletContext Servlet context
     * @param executorType   Executor class, used as a key to servlet context attributes to retrieve the configured
     *                       instance of {@link GraphQLExecutor} used to execute this query
     * @return HTTP Response
     */
    protected final Response executeGraphQL(String query, String operationName, String variables, String extensions,
                                            ServletContext servletContext, Class<?> executorType) {
        // Parse any query variables and extensions
        Map<String, Object> parsedVariables;
        if (StringUtils.isNotBlank(variables)) {
            try {
                parsedVariables = GraphQLOverHttp.parseMap(variables);
            } catch (JsonProcessingException e) {
                return badRequest(e, "Invalid GraphQL Variables", GraphQLOverHttp.PARAMETER_VARIABLES);
            }
        } else {
            parsedVariables = Collections.emptyMap();
        }
        Map<String, Object> parsedExtensions;
        if (StringUtils.isNotBlank(extensions)) {
            try {
                parsedExtensions = GraphQLOverHttp.parseMap(extensions);
            } catch (JsonProcessingException e) {
                return badRequest(e, "Invalid GraphQL Extensions", GraphQLOverHttp.PARAMETER_EXTENSIONS);
            }
        } else {
            parsedExtensions = Collections.emptyMap();
        }

        return executeGraphQL(query, operationName, parsedVariables, parsedExtensions, servletContext, executorType);
    }

    /**
     * Executes a GraphQL Query
     *
     * @param query            Query
     * @param operationName    Operation name
     * @param parsedVariables  Parsed variables
     * @param parsedExtensions Parsed extensions
     * @param servletContext   Servlet context
     * @param executorType     Executor class, used as a key to servlet context attributes to retrieve the configured
     *                         instance of {@link GraphQLExecutor} used to execute this query
     * @return HTTP Response
     */
    protected final Response executeGraphQL(String query, String operationName, Map<String, Object> parsedVariables,
                                            Map<String, Object> parsedExtensions,
                                            ServletContext servletContext, Class<?> executorType) {
        if (parsedVariables == null) {
            parsedVariables = Collections.emptyMap();
        }
        if (parsedExtensions == null) {
            parsedExtensions = Collections.emptyMap();
        }

        // Get the executor from somewhere
        GraphQLExecutor executor =
                (GraphQLExecutor) servletContext.getAttribute(executorType.getCanonicalName());
        if (executor == null) {
            //@formatter:off
            return new Problem("ServiceUnavailable",
                               "No " + executorType.getSimpleName() + " Configured",
                               HttpSC.INTERNAL_SERVER_ERROR_500,
                               "No GraphQL Executor configured for this API",
                               null).toResponse();
            //@formatter:on
        }

        // Run the actual query and produce the response
        LOGGER.info("Starting GraphQL Query with executor {}...", executor.getClass().getSimpleName());
        ExecutionResult result = executor.execute(query, operationName, parsedVariables, parsedExtensions);
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
