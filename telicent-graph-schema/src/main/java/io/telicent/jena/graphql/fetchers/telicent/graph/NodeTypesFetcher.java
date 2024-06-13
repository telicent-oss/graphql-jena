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
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A GraphQL {@link DataFetcher} that fetches the defined {@code rdf:type}'s of a node
 */
public class NodeTypesFetcher implements DataFetcher<List<TelicentGraphNode>> {

    /**
     * Creates a fetcher that finds the types for nodes
     */
    public NodeTypesFetcher() {

    }
    @Override
    public List<TelicentGraphNode> get(DataFetchingEnvironment environment) {
        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        TelicentGraphNode node = environment.getSource();

        return dsg.stream(Node.ANY, node.getNode(), RDF.type.asNode(), Node.ANY)
                  .map(Quad::getObject)
                  .filter(t -> t.isURI() || t.isBlank())
                  .map(t -> new TelicentGraphNode(t, dsg.prefixes()))
                  .collect(Collectors.toList());
    }
}
