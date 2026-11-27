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
import io.telicent.jena.graphql.access.GraphAccess;
import io.telicent.jena.graphql.execution.telicent.graph.TelicentExecutionContext;
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.jena.atlas.lib.tuple.Tuple4;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Abstract data fetcher for finding relationships going in/out of a node
 *
 * @param <TOutput> Output type
 */
@SuppressWarnings("java:S119")
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
    @SuppressWarnings("java:S2259")
    protected Stream<Quad> select(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node,
                                  List<Filter> filters) {
        TelicentExecutionContext executionContext = environment.getLocalContext();
        List<Quad> quads = executionContext.getOrCompute(cacheKey(environment, node),
                                                         () -> generateRelationships(dsg, node, filters));
        return quads.stream();
    }

    /**
     * Gets whether this request has already built the relationships list for the given node and the request's
     * filters, e.g. because another field of the request needed it
     *
     * @param environment Data Fetching environment
     * @param node        Source node
     * @return True if the list is already built
     */
    @SuppressWarnings("java:S2259")
    protected boolean isSelected(DataFetchingEnvironment environment, TelicentGraphNode node) {
        TelicentExecutionContext executionContext = environment.getLocalContext();
        return executionContext.getIfPresent(cacheKey(environment, node)) != null;
    }

    private RelationshipSelectionCacheKey cacheKey(DataFetchingEnvironment environment, TelicentGraphNode node) {
        return new RelationshipSelectionCacheKey(node.getNode(), this.direction,
                                                 environment.getArgument(TelicentGraphSchema.ARGUMENT_PREDICATE_FILTER),
                                                 environment.getArgument(TelicentGraphSchema.ARGUMENT_NODE_FILTER),
                                                 environment.getArgument(TelicentGraphSchema.ARGUMENT_TYPE_FILTER));
    }

    /**
     * Gets the predicates to which a {@link GraphAccess} call must be restricted to answer for the given filters
     * <p>
     * A {@link GraphAccess} call can only express a predicate {@link FilterMode#INCLUDE} filter, if any filter
     * cannot be expressed then this returns an empty optional and the fetcher must use the {@link DatasetGraph}
     * directly.
     * </p>
     *
     * @param filters Filters that apply
     * @return Predicates to restrict to, an empty collection meaning any predicate, or an empty optional if the filters
     * cannot be expressed as a {@link GraphAccess} call
     */
    protected static Optional<Collection<Node>> graphAccessPredicates(List<Filter> filters) {
        Set<Node> predicates = null;
        for (Filter filter : filters) {
            if (!(filter instanceof PredicateFilter) || filter.mode() != FilterMode.INCLUDE) {
                return Optional.empty();
            }
            if (predicates == null) {
                predicates = new LinkedHashSet<>(filter.values());
            } else {
                predicates.retainAll(filter.values());
                if (predicates.isEmpty()) {
                    // Filters that include no predicate in common include no relationships, which the GraphAccess
                    // calls can't express since an empty collection means any predicate
                    return Optional.empty();
                }
            }
        }
        return Optional.of(predicates != null ? predicates : List.of());
    }

    /**
     * Builds the filtered relationships for this fetcher
     *
     * @param dsg     Dataset graph
     * @param node    Source node
     * @param filters Filters to apply
     * @return Materialised list of relationships
     */
    protected List<Quad> generateRelationships(DatasetGraph dsg, TelicentGraphNode node, List<Filter> filters) {
        return streamRelationships(dsg, node, filters).toList();
    }

    /**
     * Streams the filtered relationships for this fetcher, one quad per distinct triple, without building a list of
     * them
     *
     * @param dsg     Dataset graph
     * @param node    Source node
     * @param filters Filters to apply
     * @return Stream of relationships
     */
    protected Stream<Quad> streamRelationships(DatasetGraph dsg, TelicentGraphNode node, List<Filter> filters) {
        // If a Predicate INCLUDE filter can do a more targeted initial stream
        List<Tuple4<Node>> quadPatterns = getPreFilter(filters, node);
        Stream<Quad> quads = quadPatterns != null ? streamPreFiltered(dsg, quadPatterns) : stream(dsg, node);
        for (Filter filter : filters) {
            quads = filter.filter(quads, dsg);
        }
        return QuadStreams.distinctByTriple(quads);
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
    @SuppressWarnings({ "java:S1168", "java:S3776" })
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
     * @param quadPatterns Quad patterns to use
     * @return Stream of quads representing relationships
     */
    private Stream<Quad> streamPreFiltered(DatasetGraph dsg, List<Tuple4<Node>> quadPatterns) {
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
