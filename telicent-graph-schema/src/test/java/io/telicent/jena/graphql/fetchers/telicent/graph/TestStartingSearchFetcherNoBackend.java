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
package io.telicent.jena.graphql.fetchers.telicent.graph;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentImpl;
import io.telicent.jena.graphql.execution.telicent.graph.TelicentExecutionContext;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.testng.annotations.Test;

import java.util.Map;

public class TestStartingSearchFetcherNoBackend {

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = ".*Failed to make query.*")
    public void givenNoSearchBackend_whenUsingSearchFetcher_thenErrorIsThrown() {
        // given
        StartingSearchFetcher fetcher = new StartingSearchFetcher();
        DatasetGraph dsg  = DatasetGraphFactory.empty();
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "authtoken");
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .arguments(Map.of("searchTerm","test"))
                .build();
        // when
        // then
        fetcher.get(environment);
    }
}
