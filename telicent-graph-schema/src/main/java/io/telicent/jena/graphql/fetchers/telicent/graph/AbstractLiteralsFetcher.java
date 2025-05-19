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
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.AbstractFilter;
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
    protected Stream<Quad> select(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node, List<AbstractFilter> filters) {
        // NB - Filtering not enabled for literals
        return dsg.stream(Node.ANY, node.getNode(), Node.ANY, Node.ANY).filter(q -> q.getObject().isLiteral());
    }
}
