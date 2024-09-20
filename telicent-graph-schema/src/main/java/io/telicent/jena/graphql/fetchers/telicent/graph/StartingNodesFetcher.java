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
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.system.Txn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A GraphQL {@link DataFetcher} that finds the starting nodes for a query
 */
public class StartingNodesFetcher implements DataFetcher<Object> {

    private final boolean multiSelect;

    /**
     * Creates a new fetcher
     *
     * @param multiSelect Whether this fetcher should support selecting multiple starting nodes
     */
    public StartingNodesFetcher(boolean multiSelect) {
        this.multiSelect = multiSelect;
    }

    @Override
    public Object get(DataFetchingEnvironment environment) {
        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        String rawGraph = environment.getArgument(TelicentGraphSchema.ARGUMENT_GRAPH);
        Node graphFilter = StringUtils.isNotBlank(rawGraph) ? parseStart(rawGraph) : Node.ANY;
        List<Node> startFilters = parseStarts(multiSelect ? environment.getArgument(TelicentGraphSchema.ARGUMENT_URIS) :
                                              environment.getArgument(TelicentGraphSchema.ARGUMENT_URI));

        return Txn.calculateRead(dsg, () -> {
            List<TelicentGraphNode> nodes = startFilters.stream()
                                                        .distinct()
                                                        .filter(n -> usedAsSubjectOrObject(n, dsg, graphFilter))
                                                        .map(n -> new TelicentGraphNode(n, dsg.prefixes()))
                                                        .collect(Collectors.toList());
            return multiSelect ? nodes : (!nodes.isEmpty() ? nodes.get(0) : null);
        });
    }

    /**
     * Given a node checks whether it is used as either the subject/object of any quads in the dataset
     * @param n Node
     * @param dsg Dataset
     * @param graphFilter Graph node
     * @return True if used as a subject/object, false otherwise
     */
    public static boolean usedAsSubjectOrObject(Node n, DatasetGraph dsg, Node graphFilter) {
        return dsg.contains(graphFilter, n, Node.ANY, Node.ANY) || dsg.contains(graphFilter, Node.ANY, Node.ANY, n);
    }

    @SuppressWarnings("unchecked")
    private List<Node> parseStarts(Object rawStart) {
        if (rawStart == null) {
            throw new IllegalArgumentException(
                    "Required argument" + (this.multiSelect ? TelicentGraphSchema.ARGUMENT_URIS :
                                           TelicentGraphSchema.ARGUMENT_URI) + " missing");
        }
        if (this.multiSelect) {
            if (rawStart instanceof List<?>) {
                List<Node> starts = new ArrayList<>();
                for (String uri : (List<String>) rawStart) {
                    starts.add(parseStart(uri));
                }
                return starts;
            } else {
                throw new IllegalArgumentException(
                        "Argument " + TelicentGraphSchema.ARGUMENT_URIS + " received as wrong type, expected List but got " + rawStart.getClass()
                                                                                                                                      .getCanonicalName());
            }
        } else {
            if (rawStart instanceof String) {
                return List.of(parseStart((String) rawStart));
            } else {
                throw new IllegalArgumentException(
                        "Argument " + TelicentGraphSchema.ARGUMENT_URI + " received as wrong type, expected String but got " + rawStart.getClass()
                                                                                                                                       .getCanonicalName());
            }
        }
    }

    static Node parseStart(String uri) {
        if (uri.startsWith(TelicentGraphSchema.BLANK_NODE_PREFIX)) {
            return NodeFactory.createBlankNode(uri.substring(2));
        } else {
            return NodeFactory.createURI(uri);
        }
    }
}
