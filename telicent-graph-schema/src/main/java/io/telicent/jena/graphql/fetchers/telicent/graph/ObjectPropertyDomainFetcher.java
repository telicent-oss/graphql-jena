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
import io.telicent.jena.graphql.schemas.telicent.graph.models.ObjectPropertyDefinition;
import io.telicent.jena.graphql.schemas.telicent.graph.models.RdfsClass;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.system.Txn;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import java.util.Optional;

/**
 * Fetches the domain class for an object property.
 */
public class ObjectPropertyDomainFetcher implements DataFetcher<RdfsClass> {

    /**
     * Creates a new fetcher.
     */
    public ObjectPropertyDomainFetcher() {
    }

    @Override
    public RdfsClass get(DataFetchingEnvironment environment) {
        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        ObjectPropertyDefinition property = environment.getSource();
        Node predicate = property.getPredicate();
        Node graphNode = property.getGraphNode();

        return Txn.calculateRead(dsg, () -> {
            Optional<Node> domain = dsg.stream(graphNode, predicate, RDFS.domain.asNode(), Node.ANY)
                                       .map(Quad::getObject)
                                       .findFirst();
            boolean owlClass = domain.filter(node -> dsg.contains(graphNode, node, RDF.type.asNode(),
                                                                 OWL.Class.asNode()))
                                     .isPresent();
            return domain.map(node -> new RdfsClass(node, dsg.prefixes(), owlClass, graphNode))
                         .orElse(null);
        });
    }
}
