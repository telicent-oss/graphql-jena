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
package io.telicent.jena.graphql.execution.telicent.graph;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.core.DatasetGraph;

import java.util.Objects;


/**
 * An execution context for GraphQL queries with the Telicent GraphQL schema
 */
public class TelicentExecutionContext {

    private final DatasetGraph dsg;
    private final String authToken;

    /**
     * Creates a new execution context
     *
     * @param dsg       Dataset Graph the query executes over
     * @param authToken The users authentication token for passing onwards to other Telicent Core services where needed
     */
    public TelicentExecutionContext(DatasetGraph dsg, String authToken) {
        Objects.requireNonNull(dsg, "DatasetGraph cannot be null");
        this.dsg = dsg;
        this.authToken = authToken;
    }

    /**
     * Gets the dataset graph the query is executing over
     *
     * @return Dataset graph
     */
    public DatasetGraph getDatasetGraph() {
        return this.dsg;
    }

    /**
     * Gets the authentication token for the user (if any)
     *
     * @return Authentication token
     */
    public String getAuthToken() {
        return this.authToken;
    }

    /**
     * Gets whether an authentication token is present
     *
     * @return True if an authentication token is present, false otherwise
     */
    public boolean hasAuthToken() {
        return StringUtils.isNotBlank(this.authToken);
    }
}
