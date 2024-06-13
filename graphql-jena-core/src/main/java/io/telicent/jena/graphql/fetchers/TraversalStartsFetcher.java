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
package io.telicent.jena.graphql.fetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.telicent.jena.graphql.schemas.TraversalSchema;
import io.telicent.jena.graphql.schemas.models.TraversalNode;
import io.telicent.jena.graphql.utils.NodeFilter;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A {@link DataFetcher} that generates the starting point of a Traversal GraphQL schema query
 */
public class TraversalStartsFetcher implements DataFetcher<List<TraversalNode>> {

    /**
     * Creates a traversal starts fetcher that fetches the traversal nodes from which a traversal begins
     */
    public TraversalStartsFetcher() {

    }

    @Override
    public List<TraversalNode> get(DataFetchingEnvironment environment) {
        DatasetGraph dsg = environment.getLocalContext();
        List<Node> startFilters = NodeFilter.parseList(environment.getArgument(TraversalSchema.STARTS_ARGUMENT));

        return startFilters.stream()
                           .distinct()
                           .flatMap(n -> dsg.stream(Node.ANY, n, Node.ANY, Node.ANY))
                           .map(Quad::getSubject)
                           .distinct()
                           .map(TraversalNode::of)
                           .collect(Collectors.toList());
    }
}
