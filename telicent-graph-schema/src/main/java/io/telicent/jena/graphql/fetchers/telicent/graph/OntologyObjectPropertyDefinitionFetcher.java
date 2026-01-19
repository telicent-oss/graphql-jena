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
import io.telicent.jena.graphql.schemas.telicent.graph.models.ObjectPropertyDefinition;
import io.telicent.jena.graphql.schemas.telicent.graph.models.OntologyPlaceholder;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.system.Txn;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;

/**
 * Fetches a single object property definition by URI.
 */
public class OntologyObjectPropertyDefinitionFetcher implements DataFetcher<ObjectPropertyDefinition> {

    /**
     * Creates a new fetcher.
     */
    public OntologyObjectPropertyDefinitionFetcher() {
    }

    @Override
    public ObjectPropertyDefinition get(DataFetchingEnvironment environment) {
        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        Object source = environment.getSource();
        Node graphNode = source instanceof OntologyPlaceholder ? ((OntologyPlaceholder) source).getGraphNode() : Node.ANY;
        String uri = environment.getArgument(TelicentGraphSchema.ARGUMENT_URI);
        Node propertyNode = OntologySupport.parseUriNode(uri);

        return Txn.calculateRead(dsg, () -> {
            if (!dsg.contains(graphNode, propertyNode, RDF.type.asNode(), OWL.ObjectProperty.asNode())) {
                return null;
            }
            return new ObjectPropertyDefinition(propertyNode, dsg.prefixes(), graphNode);
        });
    }
}
