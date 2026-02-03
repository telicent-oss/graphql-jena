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
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;

/**
 * Fetches a specific class by URI.
 */
public class OntologyClassFetcher implements DataFetcher<RdfsClass> {

    /**
     * Creates a new fetcher.
     */
    public OntologyClassFetcher() {
    }

    @Override
    public RdfsClass get(DataFetchingEnvironment environment) {
        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        Object source = environment.getSource();
        Node graphNode = source instanceof OntologyPlaceholder ? ((OntologyPlaceholder) source).getGraphNode() : Node.ANY;
        String uri = environment.getArgument(TelicentGraphSchema.ARGUMENT_URI);
        Node classNode = OntologySupport.parseUriNode(uri);

        return Txn.calculateRead(dsg, () -> {
            if (!dsg.contains(graphNode, classNode, Node.ANY, Node.ANY)) {
                return null;
            }
            boolean owlClass = dsg.contains(graphNode, classNode, RDF.type.asNode(), OWL.Class.asNode());
            return new RdfsClass(classNode, dsg.prefixes(), owlClass, graphNode);
        });
    }
}
