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
import org.apache.jena.vocabulary.RDF;

import java.util.List;
import java.util.stream.Stream;

/**
 * Abstract data fetcher that finds the declared {@code rdf:type}'s for a node
 *
 * @param <TOutput> Output type
 */
public abstract class AbstractNodeTypesFetcher<TOutput>
        extends AbstractPagingFetcher<TelicentGraphNode, Node, TOutput> {

    /**
     * Default constructor
     */
    protected AbstractNodeTypesFetcher() {
        super();
    }

    @Override
    protected Stream<Node> select(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node,
                                  List<Filter> filters) {
        // NB - Filters not enabled for node types
        return getNodeTypes(environment, dsg, node).stream();
    }

    /**
     * Gets the declared types for a node, reusing request-scoped cached values where available
     *
     * @param environment Data fetching environment
     * @param dsg         Dataset graph
     * @param node        Node
     * @return Declared types
     */
    protected List<Node> getNodeTypes(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node) {
        TelicentExecutionContext context = environment.getLocalContext();
        return context.getOrCompute(new NodeCacheKey(NodeKind.TYPES, node.getNode()),
                                    () -> loadNodeTypes(dsg, node));
    }

    /**
     * Loads the declared types for a node from the dataset
     *
     * @param dsg  Dataset graph
     * @param node Node
     * @return Declared types
     */
    protected List<Node> loadNodeTypes(DatasetGraph dsg, TelicentGraphNode node) {
        return dsg.stream(Node.ANY, node.getNode(), RDF.type.asNode(), Node.ANY)
                  .map(Quad::getObject)
                  .filter(t -> t.isURI() || t.isBlank())
                  .toList();
    }
}
