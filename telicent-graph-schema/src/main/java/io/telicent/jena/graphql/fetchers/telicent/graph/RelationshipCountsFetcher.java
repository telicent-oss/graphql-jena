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
import io.telicent.jena.graphql.access.GraphAccess;
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.telicent.graph.models.NodePlaceholder;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.Filter;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * A GraphQL {@link DataFetcher} that finds the counts of incoming/outgoing relationships for a node
 * <p>
 * If the dataset provides a {@link GraphAccess}, and the request's filters can be expressed as a
 * {@link GraphAccess#countRelationships(DatasetGraph, List, EdgeDirection, Collection)} call, then the count is
 * obtained from that rather than by building the list of relationships.
 * </p>
 */
public class RelationshipCountsFetcher extends AbstractRelationshipsFetcher<Integer> {

    /**
     * Creates a new relationship counts fetcher
     * @param direction Direction of relationships to count
     */
    public RelationshipCountsFetcher(EdgeDirection direction) {
        super(direction);
    }

    @Override
    @SuppressWarnings("java:S2259")
    protected TelicentGraphNode getSource(DataFetchingEnvironment environment) {
        NodePlaceholder counts = environment.getSource();
        return counts.parent();
    }

    @Override
    protected Optional<Integer> fetchDirectly(DataFetchingEnvironment environment, DatasetGraph dsg,
                                              TelicentGraphNode node, List<Filter> filters) {
        GraphAccess access = GraphAccess.get(dsg);
        if (access == null) {
            return Optional.empty();
        }
        Optional<Collection<Node>> predicates = graphAccessPredicates(filters);
        if (predicates.isEmpty()) {
            return Optional.empty();
        }
        long[] counts = access.countRelationships(dsg, List.of(node.getNode()), this.direction, predicates.get());
        return Optional.of(Math.toIntExact(counts[0]));
    }

    @Override
    protected Integer map(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node,
                          Stream<Quad> input) {
        return Math.toIntExact(input.count());
    }

    @Override
    protected Stream<Quad> applyLimitAndOffset(DataFetchingEnvironment environment, Stream<Quad> stream) {
        // Want the full count so don't apply paging
        return stream;
    }
}
