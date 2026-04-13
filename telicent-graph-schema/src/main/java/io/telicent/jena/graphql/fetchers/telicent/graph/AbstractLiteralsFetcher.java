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
import io.telicent.jena.graphql.execution.telicent.graph.TelicentExecutionContext;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.Filter;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;

import java.util.List;
import java.util.stream.Stream;

/**
 * Abstract data fetcher that finds literals directly attached to nodes
 * @param <TOutput> Output type
 */
public abstract class AbstractLiteralsFetcher<TOutput>
        extends AbstractPagingFetcher<TelicentGraphNode, Quad, TOutput> {

    /**
     * Default constructor
     */
    protected AbstractLiteralsFetcher() {
        super();
    }

    @Override
    protected Stream<Quad> select(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node, List<Filter> filters) {
        // NB - Filtering not enabled for literals
        return getLiteralProperties(environment, dsg, node).stream();
    }

    /**
     * Gets the literal properties for a node, reusing request-scoped cached values where available
     *
     * @param environment Data fetching environment
     * @param dsg         Dataset graph
     * @param node        Node
     * @return Literal properties
     */
    protected List<Quad> getLiteralProperties(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node) {
        TelicentExecutionContext context = environment.getLocalContext();
        return context.getOrCompute(new NodeCacheKey(NodeKind.LITERALS, node.getNode()),
                                    () -> loadLiteralProperties(dsg, node));
    }

    /**
     * Loads the literal properties for a node from the dataset
     *
     * @param dsg  Dataset graph
     * @param node Node
     * @return Literal properties
     */
    protected List<Quad> loadLiteralProperties(DatasetGraph dsg, TelicentGraphNode node) {
        return dsg.stream(Node.ANY, node.getNode(), Node.ANY, Node.ANY)
                  .filter(q -> q.getObject().isLiteral())
                  .toList();
    }
}
