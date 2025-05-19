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
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;

import java.util.Collection;
import java.util.List;
import java.util.Set;
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
     *
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
        // If a Predicate INCLUDE filter can do a more targeted initial stream
        Set<Node> predicates = getPreFilter(filters);
        Stream<Quad> quads = predicates != null ? streamPreFiltered(dsg, node, predicates) : stream(dsg, node);
        for (AbstractFilter filter : filters) {
            quads = filter.filter(quads, dsg);
        }
        return quads;
    }

    /**
     * Stream all relationships that lead to another node
     *
     * @param dsg  Dataset Graph
     * @param node Starting Node
     * @return Stream of quads representing relationships
     */
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

    /**
     * Gets the predicate pre-filter if any
     *
     * @param filters Filters that apply
     * @return Pre-filter predicates values, or {@code null} if no eligible filter
     */
    private Set<Node> getPreFilter(List<AbstractFilter> filters) {
        if (CollectionUtils.isEmpty(filters)) {
            return null;
        }
        if (filters.get(0) instanceof PredicateFilter predicateFilter && predicateFilter.mode() == FilterMode.INCLUDE) {
            filters.remove(0);
            return predicateFilter.values();
        }
        return null;
    }

    /**
     * Streams all relationships that lead to another node using specific predicates
     *
     * @param dsg        Dataset Graph
     * @param node       Starting Node
     * @param predicates Predicates
     * @return Stream of quads representing relationships
     */
    private Stream<Quad> streamPreFiltered(DatasetGraph dsg, TelicentGraphNode node, Set<Node> predicates) {
        // If we're only including specific predicates it's more efficient to only select quads involving those
        // predicates than it is to select all quads and then filter
        return switch (this.direction) {
            case OUT -> predicates.stream()
                                  .flatMap(p -> dsg.stream(Node.ANY, node.getNode(), p, Node.ANY)
                                                   .filter(q -> q.getObject().isURI() || q.getObject().isBlank()));
            case IN -> predicates.stream()
                                 .flatMap(p -> dsg.stream(Node.ANY, Node.ANY, p, node.getNode())
                                                  .filter(q -> q.getSubject().isURI() || q.getSubject().isBlank()));
        };
    }
}
