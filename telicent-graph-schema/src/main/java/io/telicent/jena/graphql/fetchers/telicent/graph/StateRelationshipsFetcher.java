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

import graphql.com.google.common.collect.Streams;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.telicent.jena.graphql.execution.telicent.graph.TelicentExecutionContext;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import io.telicent.jena.graphql.schemas.telicent.graph.models.NonDirectionalRelationship;
import io.telicent.jena.graphql.schemas.telicent.graph.models.State;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.system.Txn;
import org.apache.jena.vocabulary.RDF;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A GraphQL {@link DataFetcher} that finds the relationships for a state
 */
public class StateRelationshipsFetcher implements DataFetcher<List<NonDirectionalRelationship>> {

    /**
     * Creates a new fetcher that finds the relationships involving states
     */
    public StateRelationshipsFetcher() {

    }

    @Override
    public List<NonDirectionalRelationship> get(DataFetchingEnvironment environment) {
        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        State target = environment.getSource();

        return Txn.calculateRead(dsg, () -> Streams.concat(outboundRelationships(dsg, target),
                                                           inboundRelationships(dsg, target))
                                                   .collect(Collectors.toList()));
    }

    private static Stream<NonDirectionalRelationship> inboundRelationships(DatasetGraph dsg, State target) {
        return inbound(dsg, target).filter(q -> q.getSubject().isURI() || q.getSubject().isBlank())
                                   .map(q -> new NonDirectionalRelationship(
                                           new TelicentGraphNode(q.getPredicate(), dsg.prefixes()),
                                           new TelicentGraphNode(q.getSubject(), dsg.prefixes())));
    }

    private static Stream<NonDirectionalRelationship> outboundRelationships(DatasetGraph dsg, State target) {
        return outbound(dsg, target).filter(q -> q.getObject().isURI() || q.getObject().isBlank())
                                    .map(q -> new NonDirectionalRelationship(
                                            new TelicentGraphNode(q.getPredicate(), dsg.prefixes()),
                                            new TelicentGraphNode(q.getObject(), dsg.prefixes())));
    }

    private static Stream<Quad> outbound(DatasetGraph dsg, State target) {
        return dsg.stream(Node.ANY, target.getStateNode(), Node.ANY, Node.ANY)
                  .filter(q -> !q.getPredicate().equals(RDF.type.asNode()) && !q.getObject()
                                                                                .equals(target.getEntityNode()));
    }

    private static Stream<Quad> inbound(DatasetGraph dsg, State target) {
        return dsg.stream(Node.ANY, Node.ANY, Node.ANY, target.getStateNode())
                  .filter(q -> dsg.contains(Node.ANY, q.getSubject(), RDF.type.asNode(), Node.ANY));
    }

}
