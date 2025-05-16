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
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
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
 * A GraphQL {@link DataFetcher} that finds the starting states for a states query
 */
public class StartingStatesFetcher implements DataFetcher<List<State>> {

    /**
     * Creates a fetcher that finds the states associated with a given URI
     */
    public StartingStatesFetcher() {

    }

    @Override
    public List<State> get(DataFetchingEnvironment environment) {
        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        Node node = StartingNodesFetcher.parseStart(environment.getArgument(TelicentGraphSchema.ARGUMENT_URI));

        return Txn.calculateRead(dsg, () -> AbstractPagingFetcher.applyLimitAndOffset(environment,
                                                                                      findStates(dsg, node),
                                                                                      TelicentGraphSchema.DEFAULT_LIMIT,
                                                                                      TelicentGraphSchema.MAX_LIMIT)
                                                                 .collect(Collectors.toList()));
    }

    private static Stream<State> findStates(DatasetGraph dsg, Node node) {
        return IesFetchers.STATE_PREDICATES.stream()
                                           .flatMap(p -> dsg.stream(Node.ANY, Node.ANY, p,
                                                                    node)
                                                            .filter(q -> (q.getSubject()
                                                                           .isURI() || q.getSubject()
                                                                                        .isBlank()) && dsg.contains(
                                                                    Node.ANY,
                                                                    q.getSubject(),
                                                                    RDF.type.asNode(),
                                                                    Node.ANY))
                                                            .map(Quad::getSubject)
                                                            .distinct()
                                                            .map(n -> new State(n, p,
                                                                                node)));
    }
}
