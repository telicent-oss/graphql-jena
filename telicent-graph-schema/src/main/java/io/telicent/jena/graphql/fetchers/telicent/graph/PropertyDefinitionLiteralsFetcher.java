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
import io.telicent.jena.graphql.schemas.telicent.graph.models.DatatypePropertyDefinition;
import io.telicent.jena.graphql.schemas.telicent.graph.models.ObjectPropertyDefinition;
import io.telicent.jena.graphql.schemas.telicent.graph.models.RdfLiteral;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.system.Txn;

import java.util.List;

/**
 * Fetches literal values for a property definition.
 */
public class PropertyDefinitionLiteralsFetcher implements DataFetcher<List<RdfLiteral>> {

    private final Node predicate;

    /**
     * Creates a new literal fetcher for property definitions.
     *
     * @param predicate Predicate to query
     */
    public PropertyDefinitionLiteralsFetcher(Node predicate) {
        this.predicate = predicate;
    }

    @Override
    public List<RdfLiteral> get(DataFetchingEnvironment environment) {
        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        Object source = environment.getSource();
        Node propertyNode = extractPropertyNode(source);
        Node graphNode = extractGraphNode(source);

        return Txn.calculateRead(dsg, () -> OntologySupport.literalsFor(dsg, graphNode, propertyNode, predicate));
    }

    private static Node extractPropertyNode(Object source) {
        if (source instanceof ObjectPropertyDefinition objectProperty) {
            return objectProperty.getPredicate();
        }
        if (source instanceof DatatypePropertyDefinition datatypeProperty) {
            return datatypeProperty.getPredicate();
        }
        throw new IllegalArgumentException("Unsupported property definition source type: " + source.getClass());
    }

    private static Node extractGraphNode(Object source) {
        if (source instanceof ObjectPropertyDefinition objectProperty) {
            return objectProperty.getGraphNode();
        }
        if (source instanceof DatatypePropertyDefinition datatypeProperty) {
            return datatypeProperty.getGraphNode();
        }
        return Node.ANY;
    }
}
