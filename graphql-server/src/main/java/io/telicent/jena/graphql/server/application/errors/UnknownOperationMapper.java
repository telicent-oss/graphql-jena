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
package io.telicent.jena.graphql.server.application.errors;

import graphql.execution.UnknownOperationException;
import io.telicent.smart.cache.server.jaxrs.model.Problem;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.apache.jena.web.HttpSC;

/**
 * A JAX-RS operation mapper that handles the GraphQL {@link UnknownOperationException} translating it into an <a
 * href="https://www.rfc-editor.org/rfc/rfc7807.html">RFC 7807</a> problem response.
 */
@Provider
@SuppressWarnings("deprecation")
public class UnknownOperationMapper implements ExceptionMapper<UnknownOperationException> {

    /**
     * Creates a new exception mapper that handles GraphQL {@link UnknownOperationException}
     */
    public UnknownOperationMapper() {

    }
    @Override
    public Response toResponse(UnknownOperationException exception) {
        //@formatter:off
        return new Problem("BadRequest",
                           "Unknown GraphQL Operation",
                           HttpSC.BAD_REQUEST_400,
                           exception.getMessage(),
                           null).toResponse();
        //@formatter:on
    }
}
