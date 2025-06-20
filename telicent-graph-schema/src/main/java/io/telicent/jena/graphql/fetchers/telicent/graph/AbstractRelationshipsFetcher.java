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
import org.apache.jena.atlas.lib.tuple.Tuple4;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Abstract data fetcher for finding relationships going in/out of a node
 *
 * @param <TOutput> Output type
 */
public abstract class AbstractRelationshipsFetcher<TOutput>
        extends AbstractPagingFetcher<TelicentGraphNode, Quad, TOutput> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRelationshipsFetcher.class);

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
    protected Filter createTypeFilter(FilterMode mode, Collection<Node> values) {
        return switch (this.direction) {
            case IN -> new InboundTypeFilter(mode, values);
            case OUT -> new OutboundTypeFilter(mode, values);
        };
    }

    @Override
    protected Filter createNodeFilter(FilterMode mode, List<Node> values) {
        return this.direction == EdgeDirection.OUT ? super.createNodeFilter(mode, values) :
               new SubjectFilter(mode, values);
    }

    @Override
    protected Stream<Quad> select(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node,
                                  List<Filter> filters) {
        // If a Predicate INCLUDE filter can do a more targeted initial stream
        List<Tuple4<Node>> quadPatterns = getPreFilter(filters, node);
        Stream<Quad> quads = quadPatterns != null ? streamPreFiltered(dsg, node, quadPatterns) : stream(dsg, node);
        for (Filter filter : filters) {
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
     * Gets the pre-filter (if any)
     *
     * @param filters Filters that apply
     * @return Pre-filter quad patterns, or {@code null} if no eligible filters
     */
    private List<Tuple4<Node>> getPreFilter(List<Filter> filters, TelicentGraphNode node) {
        if (CollectionUtils.isEmpty(filters)) {
            return null;
        }
        List<Filter> copy = new ArrayList<>(filters);
        List<Tuple4<Node>> quadPatterns = null;
        for (Filter filter : copy) {
            if (filter instanceof QuadPatternFilter quadPatternFilter) {
                List<Tuple4<Node>> patterns = quadPatternFilter.getQuadPatterns(Node.ANY,
                                                                                this.direction == EdgeDirection.OUT ?
                                                                                node.getNode() : Node.ANY, Node.ANY,
                                                                                this.direction == EdgeDirection.IN ?
                                                                                node.getNode() : Node.ANY);
                if (CollectionUtils.isNotEmpty(patterns)) {
                    // Remove from original list of filters as we're applying it as a pre-filter so no need to apply
                    // again as a post-filter
                    filters.remove(filter);

                    // Combine with existing pre-filters (if any)
                    if (quadPatterns == null) {
                        quadPatterns = patterns;
                    } else {
                        quadPatterns = QuadPatternFilter.combinePatterns(quadPatterns, patterns);
                    }
                }
            }
        }
        return quadPatterns;
    }

    /**
     * Streams all relationships that lead to another node using specific quad patterns
     * <p>
     * This is called only if one/more {@link QuadPatternFilter}'s are being used in which case it is more efficient to
     * directly query for those specific quad patterns than it is to query for all quads and then filter afterward. This
     * is especially true for nodes in the graph that have lots of relationships.
     * </p>
     *
     * @param dsg          Dataset Graph
     * @param node         Starting Node
     * @param quadPatterns Quad patterns to use
     * @return Stream of quads representing relationships
     */
    private Stream<Quad> streamPreFiltered(DatasetGraph dsg, TelicentGraphNode node, List<Tuple4<Node>> quadPatterns) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Translated pre-filter eligible filters into following {} Quad Patterns:\n  {}",
                         quadPatterns.size(),
                         quadPatterns.stream().map(Object::toString).collect(Collectors.joining("\n  ")));
        }

        Stream<Quad> stream = quadPatterns.stream().flatMap(p -> dsg.stream(p.get(0), p.get(1), p.get(2), p.get(3)));
        return switch (this.direction) {
            case OUT -> stream.filter(q -> q.getObject().isURI() || q.getObject().isBlank());
            case IN -> stream.filter(q -> q.getSubject().isURI() || q.getSubject().isBlank());
        };
    }
}
