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
import io.telicent.jena.graphql.schemas.telicent.graph.models.FacetInfo;
import io.telicent.jena.graphql.schemas.telicent.graph.models.FacetInfoPlaceholder;

import java.util.List;

/**
 * A facet fetcher that delegates to another fetcher
 */
public class FacetsFetcher implements DataFetcher<List<FacetInfo>> {

    /**
     * Creates a new fetcher
     */
    public FacetsFetcher() {

    }

    @Override
    public List<FacetInfo> get(DataFetchingEnvironment environment) throws Exception {
        FacetInfoPlaceholder placeholder = environment.getSource();
        return switch (environment.getField().getName()) {
            case TelicentGraphSchema.FIELD_TYPES -> placeholder.typesFetcher().get(environment);
            case TelicentGraphSchema.FIELD_PREDICATES -> placeholder.predicatesFetcher().get(environment);
            default -> throw new IllegalArgumentException("Unsupported field: " + environment.getField().getName());
        };
    }
}
