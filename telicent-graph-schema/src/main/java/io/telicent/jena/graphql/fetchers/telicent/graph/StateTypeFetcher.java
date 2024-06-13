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
import org.apache.jena.vocabulary.RDF;

import java.util.List;

/**
 * A GraphQL {@link DataFetcher} that finds the {@code rdf:type}'s for a state
 */
public class StateTypeFetcher implements DataFetcher<String> {
    /**
     * Creates a new fetcher that finds the types for states
     */
    public StateTypeFetcher() {

    }

    @Override
    public String get(DataFetchingEnvironment environment) {
        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        State state = environment.getSource();

        List<Node> types = dsg.stream(Node.ANY, state.getStateNode(), RDF.type.asNode(), Node.ANY)
                              .map(Quad::getObject)
                              .filter(t -> t.isURI() || t.isBlank())
                              .toList();
        if (types.isEmpty()) {
            throw new IllegalStateException("No types available for state " + state.getUri());
        }
        Node primaryType = types.get(0);
        if (primaryType.isURI()) {
            return primaryType.getURI();
        } else {
            return TelicentGraphSchema.BLANK_NODE_PREFIX + primaryType.getBlankNodeLabel();
        }
    }
}
