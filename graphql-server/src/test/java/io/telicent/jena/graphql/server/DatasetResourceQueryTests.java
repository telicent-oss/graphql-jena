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
package io.telicent.jena.graphql.server;

import io.telicent.jena.graphql.server.model.GraphQLRequest;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.testng.annotations.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static io.telicent.jena.graphql.server.model.GraphQLOverHttp.CONTENT_TYPE_GRAPHQL_RESPONSE_JSON;

public class DatasetResourceQueryTests extends AbstractResourceTests{

    private static final String DATASET_ENDPOINT = "/dataset/graphql";
    private static final String TRAVERSAL_ENDPOINT = "/dataset/traversal/graphql";
    private static final String TELICENT_ENDPOINT = "/dataset/telicent/graphql";

    public static final String VALID_QUAD_QUERY ="query{quads{subject{kind}}}";

    public static final String VALID_TRAVERSAL_QUERY = """
            {
              nodes(starts: []) {
                node {
                  value
                }
              }
            }
            """;

    public static final String VALID_TELICENT_QUERY = """
            {
              getAllEntities(graph: "example") {
                id
                uri
                uriHash
                shortUri
                types {
                  id
                  uri
                  uriHash
                  shortUri
                  types {
                    id
                    uri
                    uriHash
                    shortUri
                  }
                  properties {
                    predicate
                    shortPredicate
                    value
                    datatype
                    language
                  }
                  outRels {
                    id
                    domain_id
                    predicate
                    range_id
                  }
                  inRels {
                    id
                    range_id
                    domain_id
                    predicate
                  }
                  instances {
                    id
                    uri
                    uriHash
                    shortUri
                  }
                }
                properties {
                  predicate
                  shortPredicate
                  value
                  datatype
                  language
                }
                outRels {
                  id
                  domain_id
                  predicate
                  range_id
                }
                inRels {
                  id
                  range_id
                  domain_id
                  predicate
                }
                instances {
                  id
                  uri
                  uriHash
                  shortUri
                }
              }
            }
            """;

    @Test
    public void test_invalidQuery() {
        WebTarget target = getTargetForEndpoint("/invalidendpoint");
        Response response = target.request().get();
        verifyResponse(response, Response.Status.NOT_FOUND);
    }

    @Test
    public void test_invalidVariables() {
        WebTarget target = getTargetForEndpoint(DATASET_ENDPOINT);
        Invocation.Builder invocation = target.queryParam("query", URLEncoder.encode(
                VALID_QUAD_QUERY, StandardCharsets.UTF_8)).queryParam("variables", "RUBBISH").request(CONTENT_TYPE_GRAPHQL_RESPONSE_JSON);
        Response response = invocation.get();
        verifyResponse(response, Response.Status.BAD_REQUEST);
    }

    @Test
    public void test_invalidExtensions() {
        WebTarget target = getTargetForEndpoint(DATASET_ENDPOINT);
        Invocation.Builder invocation = target.queryParam("query", URLEncoder.encode(
                VALID_QUAD_QUERY, StandardCharsets.UTF_8))
                                              .queryParam("variables", URLEncoder.encode(
                                                      "{\"name\":\"value\"}", StandardCharsets.UTF_8))
                                              .queryParam("extensions", "RUBBISH")
                                              .request(CONTENT_TYPE_GRAPHQL_RESPONSE_JSON);
        Response response = invocation.get();
        verifyResponse(response, Response.Status.BAD_REQUEST);
    }

    @Test
    public void test_invalidOperation() {
        WebTarget target = getTargetForEndpoint(DATASET_ENDPOINT);
        Invocation.Builder invocation = target.queryParam("query", URLEncoder.encode(
                VALID_QUAD_QUERY, StandardCharsets.UTF_8)).queryParam("operationName", "RUBBISH").request(CONTENT_TYPE_GRAPHQL_RESPONSE_JSON);
        Response response = invocation.get();
        verifyResponse(response, Response.Status.BAD_REQUEST);
    }


    @Test
    public void test_getDataset_success()  {
        WebTarget target = getTargetForEndpoint(DATASET_ENDPOINT);
        Invocation.Builder invocation = target.queryParam("query", URLEncoder.encode(
                VALID_QUAD_QUERY, StandardCharsets.UTF_8)).request(CONTENT_TYPE_GRAPHQL_RESPONSE_JSON);
        verifyResponse(invocation.get(), Response.Status.OK);
    }

    @Test
    public void test_postDataset_success() {
        WebTarget target = getTargetForEndpoint(DATASET_ENDPOINT);
        GraphQLRequest request = new GraphQLRequest();
        request.setQuery(VALID_QUAD_QUERY);
        request.setVariables(null);
        request.setExtensions(null);
        Response response = target.request().post(Entity.entity(request, MediaType.APPLICATION_JSON));
        verifyResponse(response, Response.Status.OK);
    }

    @Test
    public void test_getTraversalValidate_success() {
        WebTarget target = getTargetForEndpoint(TRAVERSAL_ENDPOINT);
        Invocation.Builder invocation = target.queryParam("query", URLEncoder.encode(
                VALID_TRAVERSAL_QUERY, StandardCharsets.UTF_8).replace("+", "%20")).request(CONTENT_TYPE_GRAPHQL_RESPONSE_JSON);
        Response response = invocation.get();
        verifyResponse(response, Response.Status.OK);
    }

    @Test
    public void test_postTraversal_success() {
        WebTarget target = getTargetForEndpoint(TRAVERSAL_ENDPOINT);
        GraphQLRequest request = new GraphQLRequest();
        request.setQuery(VALID_TRAVERSAL_QUERY);
        Response response = target.request().post(Entity.entity(request, MediaType.APPLICATION_JSON));
        verifyResponse(response, Response.Status.OK);
    }

    @Test
    public void test_getTelicent_success()  {
        WebTarget target = getTargetForEndpoint(TELICENT_ENDPOINT);
        Invocation.Builder invocation = target.queryParam("query", URLEncoder.encode(
                VALID_TELICENT_QUERY, StandardCharsets.UTF_8).replace("+", "%20")).request(CONTENT_TYPE_GRAPHQL_RESPONSE_JSON);
        verifyResponse(invocation.get(), Response.Status.OK);
    }

    @Test
    public void test_postTelicent_success() {
        WebTarget target = getTargetForEndpoint(TELICENT_ENDPOINT);
        GraphQLRequest request = new GraphQLRequest();
        request.setQuery(VALID_TELICENT_QUERY);
        Response response = target.request().post(Entity.entity(request, MediaType.APPLICATION_JSON));
        verifyResponse(response, Response.Status.OK);
    }

}
