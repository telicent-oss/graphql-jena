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
import org.apache.jena.sys.JenaSystem;

import java.util.Map;

public class AbstractFetcherTests {

    static {
        JenaSystem.init();
    }

    protected static <T> DataFetchingEnvironment prepareFetchingEnvironment(DatasetGraph dsg, T source) {
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        return DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .source(source)
                .build();
    }

    protected static <T> DataFetchingEnvironment prepareFetchingEnvironment(DatasetGraph dsg, T source, Map<String, Object> arguments) {
        TelicentExecutionContext context = new TelicentExecutionContext(dsg, "");
        return DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(context)
                .source(source)
                .arguments(arguments)
                .build();
    }
}
