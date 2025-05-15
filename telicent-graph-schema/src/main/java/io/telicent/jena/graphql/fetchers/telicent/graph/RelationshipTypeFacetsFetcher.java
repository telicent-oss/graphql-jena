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
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.telicent.graph.models.FacetInfo;
import io.telicent.jena.graphql.schemas.telicent.graph.models.FacetInfoPlaceholder;
import io.telicent.jena.graphql.schemas.telicent.graph.models.NodePlaceholder;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A GraphQL {@link DataFetcher} that finds the type facets of incoming/outgoing relationships for a node
 */
public class RelationshipTypeFacetsFetcher extends AbstractRelationshipsFetcher<List<FacetInfo>> {

    /**
     * Creates a new relationship counts fetcher
     *
     * @param direction Direction of relationships to count
     */
    public RelationshipTypeFacetsFetcher(EdgeDirection direction) {
        super(direction);
    }

    @Override
    protected TelicentGraphNode getSource(DataFetchingEnvironment environment) {
        FacetInfoPlaceholder placeholder = environment.getSource();
        return placeholder.node();
    }

    @Override
    protected List<FacetInfo> map(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node,
                                  Stream<Quad> input) {
        return input.flatMap(q -> dsg.stream(Node.ANY, this.direction == EdgeDirection.IN ? q.getSubject() : q.getObject(), RDF.type.asNode(),
                                             Node.ANY))
                    .collect(Collectors.groupingBy(Quad::getObject, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .map(e -> new FacetInfo(e.getKey(), dsg.prefixes(), e.getValue().intValue()))
                    .toList();
    }

    @Override
    protected Stream<Quad> applyLimitAndOffset(DataFetchingEnvironment environment, Stream<Quad> stream) {
        // Want the full facets information so don't apply paging
        return stream;
    }
}
