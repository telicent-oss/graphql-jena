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

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A GraphQL {@link DataFetcher} that finds instances of a type
 */
public class InstancesFetcher
        extends AbstractInstancesFetcher<List<TelicentGraphNode>> {

    /**
     * Creates a fetcher that finds all instances of a type
     */
    @SuppressWarnings("java:S1186")
    public InstancesFetcher() {

    }

    @Override
    protected List<TelicentGraphNode> map(DataFetchingEnvironment environment, DatasetGraph dsg,
                                          TelicentGraphNode source, Stream<Node> input) {
        return input.map(n -> new TelicentGraphNode(n, dsg.prefixes())).collect(Collectors.toList());
    }
}
