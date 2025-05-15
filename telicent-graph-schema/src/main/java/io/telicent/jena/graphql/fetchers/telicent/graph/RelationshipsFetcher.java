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
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import io.telicent.jena.graphql.schemas.telicent.graph.models.Relationship;
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A GraphQL {@link DataFetcher} that finds the incoming/outgoing relationships for a node
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
    protected List<Relationship> map(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode source,
                                     Stream<Quad> input) {
        return input.map(q -> new Relationship(new TelicentGraphNode(q.getSubject(), dsg.prefixes()),
                                               new TelicentGraphNode(q.getPredicate(), dsg.prefixes()),
                                               new TelicentGraphNode(q.getObject(), dsg.prefixes())))
                    .collect(Collectors.toList());
    }

}
