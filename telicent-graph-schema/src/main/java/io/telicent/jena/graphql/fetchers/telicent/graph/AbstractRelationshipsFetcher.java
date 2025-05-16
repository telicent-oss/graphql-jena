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
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.AbstractFilter;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.FilterMode;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.InboundTypeFilter;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.OutboundTypeFilter;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Abstract data fetcher for finding relationships going in/out of a node
 *
 * @param <TOutput> Output type
 */
public abstract class AbstractRelationshipsFetcher<TOutput>
        extends AbstractPagingFetcher<TelicentGraphNode, Quad, TOutput> {
    /**
     * The direction of relationships we're configured to fetch
     */
    protected final EdgeDirection direction;

    /**
     * Creates a new relationships fetcher
     *
     * @param direction Direction of relationships to fetch
     */
    protected AbstractRelationshipsFetcher(EdgeDirection direction) {
        this.direction = direction;
    }

    /**
     * Indicates that filtering is enabled for relationships
     * @return Always true
     */
    @Override
    protected boolean enableFilters() {
        return true;
    }

    @Override
    protected AbstractFilter createTypeFilter(FilterMode mode, Collection<Node> values) {
        return switch (this.direction) {
            case IN -> new InboundTypeFilter(mode, values);
            case OUT -> new OutboundTypeFilter(mode, values);
        };
    }

    @Override
    protected Stream<Quad> select(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node,
                                  List<AbstractFilter> filters) {
        Stream<Quad> quads = stream(dsg, node);
        for (AbstractFilter filter : filters) {
            quads = filter.filter(quads, dsg);
        }
        return quads;
    }

    private Stream<Quad> stream(DatasetGraph dsg, TelicentGraphNode node) {
        // NB - We filter() because we only care about relationships to other nodes, not literals, the filter is
        //      different depending on the edge direction as it's the target node we care about, which is either the
        //      object for OUT relationships, or the subject for IN relationships
        return switch (this.direction) {
            case OUT -> dsg.stream(Node.ANY, node.getNode(), Node.ANY, Node.ANY)
                           .filter(q -> q.getObject().isURI() || q.getObject().isBlank());
            case IN -> dsg.stream(Node.ANY, Node.ANY, Node.ANY, node.getNode())
                          .filter(q -> q.getSubject().isURI() || q.getSubject().isBlank());
        };
    }
}
