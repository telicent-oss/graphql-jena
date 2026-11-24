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
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import io.telicent.jena.graphql.schemas.telicent.graph.models.Relationship;
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.Filter;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * A GraphQL {@link DataFetcher} that finds the incoming/outgoing relationships for a node
 * <p>
 * If the dataset provides a {@link GraphAccess}, the request's filters can be expressed as a
 * {@link GraphAccess#relationships(DatasetGraph, Node, EdgeDirection, Collection, long, long)} call, and the request
 * has not already built the full list of these relationships for another field, then only the requested page is
 * obtained from the {@link GraphAccess}, rather than building the full list and paging that.
 * </p>
 */
public class RelationshipsFetcher
        extends AbstractRelationshipsFetcher<List<Relationship>> {

    /**
     * Creates a new relationship fetcher
     *
     * @param direction Edge direction
     */
    public RelationshipsFetcher(EdgeDirection direction) {
        super(direction);
    }

    @Override
    protected Optional<List<Relationship>> fetchDirectly(DataFetchingEnvironment environment, DatasetGraph dsg,
                                                         TelicentGraphNode node, List<Filter> filters) {
        GraphAccess access = GraphAccess.get(dsg);
        if (access == null || isSelected(environment, node)) {
            return Optional.empty();
        }
        Optional<Collection<Node>> predicates = graphAccessPredicates(filters);
        if (predicates.isEmpty()) {
            return Optional.empty();
        }
        Stream<Quad> page = access.relationships(dsg, node.getNode(), this.direction, predicates.get(),
                                                 getSkip(environment), getLimit(environment));
        return Optional.of(map(environment, dsg, node, page));
    }

    @Override
    protected List<Relationship> map(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode source,
                                     Stream<Quad> input) {
        return input.map(q -> new Relationship(new TelicentGraphNode(q.getSubject(), dsg.prefixes()),
                                               new TelicentGraphNode(q.getPredicate(), dsg.prefixes()),
                                               new TelicentGraphNode(q.getObject(), dsg.prefixes())))
                    .toList();
    }

}
