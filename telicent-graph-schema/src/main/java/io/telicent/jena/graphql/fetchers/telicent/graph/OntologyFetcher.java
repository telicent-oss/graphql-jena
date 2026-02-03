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
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import io.telicent.jena.graphql.schemas.telicent.graph.models.OntologyPlaceholder;

/**
 * Returns a placeholder instance for the Ontology root field.
 */
public class OntologyFetcher implements DataFetcher<OntologyPlaceholder> {

    /**
     * Creates a new fetcher.
     */
    public OntologyFetcher() {
    }

    @Override
    public OntologyPlaceholder get(DataFetchingEnvironment environment) {
        String graph = environment.getArgument(TelicentGraphSchema.ARGUMENT_GRAPH);
        return new OntologyPlaceholder(OntologySupport.parseGraphNode(graph));
    }
}
