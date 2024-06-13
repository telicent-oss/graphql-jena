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

import io.telicent.jena.graphql.execution.DatasetExecutor;
import io.telicent.jena.graphql.execution.TraversalExecutor;
import io.telicent.jena.graphql.execution.telicent.graph.TelicentGraphExecutor;
import io.telicent.jena.graphql.server.model.GraphQLOverHttp;
import io.telicent.jena.graphql.server.model.GraphQLRequest;
import jakarta.servlet.ServletContext;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * A JAX-RS resource that provides access to GraphQL queries using the
 * {@link io.telicent.jena.graphql.schemas.DatasetSchema} and {@link io.telicent.jena.graphql.schemas.TraversalSchema}
 */
@Path("/dataset")
public class DatasetResource extends AbstractGraphQLResource {

    /**
     * Creates a resource that can answer GraphQL queries using the basic GraphQL schemas we've defined
     */
    public DatasetResource() {

    }

    /**
     * GET requests using the {@link io.telicent.jena.graphql.schemas.DatasetSchema}
     *
     * @param query          GraphQL Query
     * @param operationName  GraphQL Operation name
     * @param variables      GraphQL variables
     * @param extensions     GraphQL extensions
     * @param servletContext Servlet context
     * @return Response
     */
    @Path("/graphql")
    @GET
    @Produces({ GraphQLOverHttp.CONTENT_TYPE_GRAPHQL_RESPONSE_JSON, "application/problem+json" })
    public Response quads(@QueryParam(GraphQLOverHttp.PARAMETER_QUERY) @NotNull String query,
                          @QueryParam(GraphQLOverHttp.PARAMETER_OPERATION_NAME) String operationName,
                          @QueryParam(GraphQLOverHttp.PARAMETER_VARIABLES) String variables,
                          @QueryParam(GraphQLOverHttp.PARAMETER_EXTENSIONS) String extensions,
                          @Context ServletContext servletContext) {
        return executeGraphQL(query, operationName, variables, extensions, servletContext, DatasetExecutor.class);
    }

    /**
     * POST requests using the {@link io.telicent.jena.graphql.schemas.DatasetSchema}
     *
     * @param request        GraphQL Request
     * @param servletContext Servlet context
     * @return Response
     */
    @Path("/graphql")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ GraphQLOverHttp.CONTENT_TYPE_GRAPHQL_RESPONSE_JSON, "application/problem+json" })
    public Response postQuads(GraphQLRequest request, @Context ServletContext servletContext) {
        return executeGraphQL(request.getQuery(), request.getOperationName(), request.getVariables(),
                              request.getExtensions(), servletContext, DatasetExecutor.class);
    }

    /**
     * GET requests using the {@link io.telicent.jena.graphql.schemas.TraversalSchema}
     *
     * @param query          GraphQL Query
     * @param operationName  GraphQL Operation name
     * @param variables      GraphQL variables
     * @param extensions     GraphQL extensions
     * @param servletContext Servlet context
     * @return Response
     */
    @Path("/traversal/graphql")
    @GET
    @Produces({ GraphQLOverHttp.CONTENT_TYPE_GRAPHQL_RESPONSE_JSON, "application/problem+json" })
    public Response traverse(@QueryParam(GraphQLOverHttp.PARAMETER_QUERY) @NotNull String query,
                             @QueryParam(GraphQLOverHttp.PARAMETER_OPERATION_NAME) String operationName,
                             @QueryParam(GraphQLOverHttp.PARAMETER_VARIABLES) String variables,
                             @QueryParam(GraphQLOverHttp.PARAMETER_EXTENSIONS) String extensions,
                             @Context ServletContext servletContext) {
        return executeGraphQL(query, operationName, variables, extensions, servletContext, TraversalExecutor.class);
    }

    /**
     * POST requests using the {@link io.telicent.jena.graphql.schemas.TraversalSchema}
     *
     * @param request        GraphQL Request
     * @param servletContext Servlet context
     * @return Response
     */
    @Path("/traversal/graphql")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ GraphQLOverHttp.CONTENT_TYPE_GRAPHQL_RESPONSE_JSON, "application/problem+json" })
    public Response postTraversal(GraphQLRequest request, @Context ServletContext servletContext) {
        return executeGraphQL(request.getQuery(), request.getOperationName(), request.getVariables(),
                              request.getExtensions(), servletContext, TraversalExecutor.class);
    }


    /**
     * GET requests using the {@link io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema}
     *
     * @param query          GraphQL Query
     * @param operationName  GraphQL Operation name
     * @param variables      GraphQL variables
     * @param extensions     GraphQL extensions
     * @param servletContext Servlet context
     * @return Response
     */
    @Path("/telicent/graphql")
    @GET
    @Produces({ GraphQLOverHttp.CONTENT_TYPE_GRAPHQL_RESPONSE_JSON, "application/problem+json" })
    public Response telicent(@QueryParam(GraphQLOverHttp.PARAMETER_QUERY) @NotNull String query,
                          @QueryParam(GraphQLOverHttp.PARAMETER_OPERATION_NAME) String operationName,
                          @QueryParam(GraphQLOverHttp.PARAMETER_VARIABLES) String variables,
                          @QueryParam(GraphQLOverHttp.PARAMETER_EXTENSIONS) String extensions,
                          @Context ServletContext servletContext) {
        return executeGraphQL(query, operationName, variables, extensions, servletContext, TelicentGraphExecutor.class);
    }

    /**
     * POST requests using the {@link io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema}
     *
     * @param request        GraphQL Request
     * @param servletContext Servlet context
     * @return Response
     */
    @Path("/telicent/graphql")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ GraphQLOverHttp.CONTENT_TYPE_GRAPHQL_RESPONSE_JSON, "application/problem+json" })
    public Response postTelicent(GraphQLRequest request, @Context ServletContext servletContext) {
        return executeGraphQL(request.getQuery(), request.getOperationName(), request.getVariables(),
                              request.getExtensions(), servletContext, TelicentGraphExecutor.class);
    }
}
