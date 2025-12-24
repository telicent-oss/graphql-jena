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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Objects;

import graphql.ExecutionResult;
import io.telicent.jena.graphql.execution.GraphQLOverDatasetExecutor;
import io.telicent.jena.graphql.server.model.GraphQLOverHttp;
import io.telicent.jena.graphql.server.model.GraphQLRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.apache.jena.atlas.logging.FmtLog;
import org.apache.jena.fuseki.servlets.ActionLib;
import org.apache.jena.fuseki.servlets.ActionService;
import org.apache.jena.fuseki.servlets.HttpAction;
import org.apache.jena.fuseki.servlets.ServletOps;
import org.apache.jena.riot.WebContent;
import org.apache.jena.sparql.core.DatasetGraph;

/**
 * A Fuseki action that evaluates GraphQL Requests using a configured Jena GraphQL {@link GraphQLOverDatasetExecutor}
 */
public class ActionGraphQL extends ActionService {

    /**
     * Function that extracts a request parameter from a Fuseki HTTP Action
     *
     * @param req   Fuseki HTTP Action
     * @param param Request parameter
     * @return Request parameter value
     */
    protected static String getRequestParameter(HttpAction req, String param) {
        return req.getRequestParameter(param);
    }

    /**
     * Function that extracts the request body from a Fuseki HTTP Action
     *
     * @param req Fuseki HTTP Action
     * @return Request body
     */
    protected static InputStream getRequestBody(HttpAction req) {
        try {
            return req.getRequestInputStream();
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse POST request body as a valid GraphQL Request", e);
        }
    }

    private final GraphQLOverDatasetExecutor executor;

    /**
     * Creates a new GraphQL Action
     *
     * @param executor GraphQL Executor to use
     */
    public ActionGraphQL(GraphQLOverDatasetExecutor executor) {
        this.executor = Objects.requireNonNull(executor, "GraphQL Executor cannot be null");
    }

    @Override
    public void execGet(HttpAction action) {
        this.executeLifecycle(action);
    }

    @Override
    public void execPost(HttpAction action) {
        this.executeLifecycle(action);
    }

    @Override
    public void execOptions(HttpAction action) {
        ActionLib.doOptionsGetPost(action);
        ServletOps.success(action);
    }

    @Override
    public final void validate(HttpAction httpAction) {
        String method = httpAction.getRequestMethod().toUpperCase(Locale.ROOT);

        if (Strings.CS.equals(method, "GET")) {
            if (StringUtils.isBlank(httpAction.getRequestParameter(GraphQLOverHttp.PARAMETER_QUERY))) {
                ServletOps.errorBadRequest(
                        "GET requests to GraphQL endpoints MUST have a non-empty query parameter");
            }
        } else {
            // Allow only Content-Type: application/json if Content-Type is present, if missing we assume JSON body
            // and will fail parsing in the execute() implementation if it isn't valid JSON
            if (StringUtils.isNotBlank(httpAction.getRequestContentType())
                    && !Strings.CS.startsWith(httpAction.getRequestContentType(),
                                               WebContent.contentTypeJSON)) {
                ServletOps.errorBadRequest(
                        "POST requests to GraphQL endpoints MUST use Content-Type: application/json");
            }
        }
    }

    @Override
    public final void execute(HttpAction httpAction) {
        // Parse in the GraphQL Request which may have arrived as either a GET or a POST
        String method = httpAction.getRequestMethod().toUpperCase(Locale.ROOT);

        GraphQLRequest request = null;
        try {
            request = GraphQLOverHttp.parseRequest(httpAction, method, ActionGraphQL::getRequestParameter,
                                                   ActionGraphQL::getRequestBody);
        } catch (Throwable e) {
            ServletOps.errorBadRequest(e.getMessage());
        }

        httpAction.beginRead();
        try {
            DatasetGraph dsg = httpAction.getActiveDSG();
            dsg = prepare(httpAction, request, dsg);

            FmtLog.info(httpAction.log, "[%d] GraphQL Query = \n%s", httpAction.id, request.getQuery());
            ExecutionResult result = this.executor.execute(dsg, request);

            httpAction.setResponseHeader("Content-Type", GraphQLOverHttp.CONTENT_TYPE_GRAPHQL_RESPONSE_JSON);
            httpAction.setResponseStatus(GraphQLOverHttp.selectHttpStatus(result));
            try (OutputStream output = httpAction.getResponseOutputStream()) {
                GraphQLOverHttp.write(result, output);
            } catch (IOException e) {
                ServletOps.warning(httpAction, "Failed to serialize GraphQL Results", e);
            }
        } finally {
            httpAction.endRead();
        }
    }

    /**
     * Performs any additional preparation required before executing the GraphQL Request
     * <p>
     * This is intended as an extension point for developers, for example you might want to inject some additional
     * information/configuration into the GraphQL Request via extensions or modify the {@link DatasetGraph} instance to
     * be used.
     * </p>
     * <p>
     * The default implementation here does nothing and merely returns the dataset unchanged.
     * </p>
     *
     * @param action  The HTTP Action for this request
     * @param request The parsed GraphQL Request
     * @param dsg     The DatasetGraph for this request
     * @return The DatasetGraph to execute the GraphQL Request against
     */
    protected DatasetGraph prepare(HttpAction action, GraphQLRequest request, DatasetGraph dsg) {
        return dsg;
    }
}
