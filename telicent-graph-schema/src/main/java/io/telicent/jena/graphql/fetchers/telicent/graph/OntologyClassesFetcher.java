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
import io.telicent.jena.graphql.schemas.telicent.graph.models.OntologyPlaceholder;
import io.telicent.jena.graphql.schemas.telicent.graph.models.RdfsClass;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.system.Txn;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Fetches classes from the ontology.
 */
public class OntologyClassesFetcher implements DataFetcher<List<RdfsClass>> {

    /**
     * Creates a new fetcher.
     */
    public OntologyClassesFetcher() {
    }

    @Override
    public List<RdfsClass> get(DataFetchingEnvironment environment) {
        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        Object source = environment.getSource();
        Node graphNode = source instanceof OntologyPlaceholder ? ((OntologyPlaceholder) source).getGraphNode() : Node.ANY;
        UriFilterSpec uriFilter = OntologySupport.parseUriFilter(environment, TelicentGraphSchema.ARGUMENT_URI_FILTER);
        UriFilterSpec typeFilter = OntologySupport.parseUriFilter(environment, TelicentGraphSchema.ARGUMENT_TYPE_FILTER);
        String labelFilter = environment.getArgument(TelicentGraphSchema.ARGUMENT_LABEL_FILTER);

        return Txn.calculateRead(dsg, () -> {
            Stream<Node> stream = OntologySupport.streamClasses(dsg, graphNode)
                                                .filter(node -> OntologySupport.matchesUriFilter(uriFilter, node))
                                                .filter(node -> OntologySupport.matchesLabelFilter(dsg, graphNode, node, labelFilter))
                                                .filter(node -> OntologySupport.matchesTypeFilter(dsg, graphNode, node, typeFilter));
            stream = AbstractPagingFetcher.applyLimitAndOffset(environment, stream,
                                                               TelicentGraphSchema.DEFAULT_LIMIT,
                                                               TelicentGraphSchema.MAX_LIMIT);
            return stream.map(node -> new RdfsClass(node, dsg.prefixes(),
                                                    OntologySupport.isOwlClass(dsg, graphNode, node), graphNode))
                         .collect(Collectors.toList());
        });
    }
}
