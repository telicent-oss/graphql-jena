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

import io.telicent.smart.cache.server.jaxrs.applications.Server;
import io.telicent.smart.cache.server.jaxrs.applications.ServerBuilder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AbstractResourceTests {

    protected final Client client = ClientBuilder.newClient(new ClientConfig());

    protected Server server;

    @BeforeClass
    public void setUpServer() throws IOException {
        GraphQLEntrypoint graphQLEntrypoint = new GraphQLEntrypoint();
        ServerBuilder builder = graphQLEntrypoint.buildServer();
        server = builder.port(65534).build();
        server.start();
    }

    @AfterClass
    public void shutdownServer() {
        server.shutdownNow();
    }

    protected WebTarget getTargetForEndpoint(String path) {
        return this.client.target(server.getBaseUri()).path(path);
    }

    protected void verifyResponse(Response response, Response.Status expectedStatus) {
        Assert.assertEquals(response.getStatus(), expectedStatus.getStatusCode());
        response.close();
    }

    protected void verifyFailureWithErrorAndMessage(Response response, Response.Status expectedStatus, String expectedErrorType, String expectedMessage) {
        Assert.assertEquals(response.getStatus(), expectedStatus.getStatusCode());
        List<Map<String,String>> errors = response.readEntity(List.class);
        Assert.assertFalse(errors.isEmpty());
        Map<String,String> errorMap = errors.get(0);
        Assert.assertTrue(errorMap.getOrDefault("message", "").contains(expectedMessage));
        Assert.assertEquals(errorMap.getOrDefault("errorType", ""), expectedErrorType);
        response.close();
    }
}
