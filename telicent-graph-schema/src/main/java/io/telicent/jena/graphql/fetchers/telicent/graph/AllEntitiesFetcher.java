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
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.system.Txn;
import org.apache.jena.vocabulary.RDF;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A GraphQL {@link DataFetcher} that finds all entities in the dataset
 */
public class AllEntitiesFetcher implements DataFetcher<List<TelicentGraphNode>> {

    /**
     * Creates a fetcher that finds all entity nodes (URI/Blank Node subjects) in a dataset
     */
    public AllEntitiesFetcher() {

    }

    @Override
    public List<TelicentGraphNode> get(DataFetchingEnvironment environment) {
        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        String rawGraph = environment.getArgument(TelicentGraphSchema.ARGUMENT_GRAPH);
        Node graphFilter = StringUtils.isNotBlank(rawGraph) ? StartingNodesFetcher.parseStart(rawGraph) : Node.ANY;

        return Txn.calculateRead(dsg, () -> findEntities(dsg, graphFilter));
    }

    private static List<TelicentGraphNode> findEntities(DatasetGraph dsg, Node graphFilter) {
        return dsg.stream(graphFilter, Node.ANY, RDF.type.asNode(), Node.ANY)
                  .filter(q -> q.getSubject().isURI() || q.getSubject().isBlank())
                  .map(Quad::getSubject)
                  .distinct()
                  .map(n -> new TelicentGraphNode(n, dsg.prefixes()))
                  .collect(Collectors.toList());
    }
}
