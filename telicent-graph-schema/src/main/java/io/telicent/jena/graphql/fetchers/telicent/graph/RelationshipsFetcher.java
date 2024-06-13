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
import io.telicent.jena.graphql.execution.telicent.graph.TelicentExecutionContext;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import io.telicent.jena.graphql.schemas.telicent.graph.models.Relationship;
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A GraphQL {@link DataFetcher} that finds the incoming/outgoing relationships for a node
 */
public class RelationshipsFetcher implements DataFetcher<List<Relationship>> {

    private final EdgeDirection direction;

    /**
     * Creates a new relationship fetcher
     *
     * @param direction Edge direction
     */
    public RelationshipsFetcher(EdgeDirection direction) {
        this.direction = direction;
    }

    @Override
    public List<Relationship> get(DataFetchingEnvironment environment) {
        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        TelicentGraphNode target = environment.getSource();

        return stream(dsg, target)
                .filter(q -> q.getObject().isURI() || q.getObject().isBlank())
                .map(q -> new Relationship(new TelicentGraphNode(q.getSubject(), dsg.prefixes()),
                                           new TelicentGraphNode(q.getPredicate(), dsg.prefixes()),
                                           new TelicentGraphNode(q.getObject(), dsg.prefixes())))
                .collect(Collectors.toList());
    }

    private Stream<Quad> stream(DatasetGraph dsg, TelicentGraphNode target) {
        return switch (this.direction) {
            case OUT -> dsg.stream(Node.ANY, target.getNode(), Node.ANY, Node.ANY);
            case IN -> dsg.stream(Node.ANY, Node.ANY, Node.ANY, target.getNode());
        };
    }
}
