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
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.telicent.graph.models.Relationship;
import io.telicent.jena.graphql.schemas.telicent.graph.models.RelationshipCounts;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.system.Txn;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A GraphQL {@link DataFetcher} that finds the counts of incoming/outgoing relationships for a node
 */
public class RelationshipCountsFetcher implements DataFetcher<RelationshipCounts> {

    /**
     * Creates a new relationship counts fetcher
     */
    public RelationshipCountsFetcher() {}

    @Override
    public RelationshipCounts get(DataFetchingEnvironment environment) {
        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        TelicentGraphNode target = environment.getSource();

        return Txn.calculateRead(dsg, () -> countRelationships(dsg, target));
    }

    private RelationshipCounts countRelationships(DatasetGraph dsg, TelicentGraphNode target) {
        return new RelationshipCounts(stream(dsg, target, EdgeDirection.IN), stream(dsg, target, EdgeDirection.OUT));
    }

    private int stream(DatasetGraph dsg, TelicentGraphNode target, EdgeDirection direction) {
        return Math.toIntExact(streamTargets(dsg, target, direction).filter(n -> n.isURI() || n.isBlank()).count());
    }

    private static Stream<Node> streamTargets(DatasetGraph dsg, TelicentGraphNode target, EdgeDirection direction) {
        return switch (direction) {
            case OUT -> dsg.stream(Node.ANY, target.getNode(), Node.ANY, Node.ANY).map(Quad::getObject);
            case IN -> dsg.stream(Node.ANY, Node.ANY, Node.ANY, target.getNode()).map(Quad::getSubject);
        };
    }
}
