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
import io.telicent.jena.graphql.schemas.telicent.graph.models.FacetInfo;
import io.telicent.jena.graphql.schemas.telicent.graph.models.FacetInfoPlaceholder;
import io.telicent.jena.graphql.schemas.telicent.graph.models.NodePlaceholder;

import java.util.List;
import java.util.Objects;

/**
 * A data fetcher that simply injects a placeholder object that will be consumed by other data fetchers
 * <p>
 * See {@link NodePlaceholderFetcher} for discussion of why fetchers like this are needed.
 * </p>
 */
public class FacetPlaceholderFetcher implements DataFetcher<FacetInfoPlaceholder> {
    private final DataFetcher<List<FacetInfo>> predicatesFetcher, typesFetcher;

    /**
     * Creates a new placeholder fetcher
     *
     * @param predicatesFetcher Predicates fetcher
     * @param typesFetcher      Types fetcher
     */
    public FacetPlaceholderFetcher(DataFetcher<List<FacetInfo>> predicatesFetcher,
                                   DataFetcher<List<FacetInfo>> typesFetcher) {
        this.predicatesFetcher = Objects.requireNonNull(predicatesFetcher);
        this.typesFetcher = Objects.requireNonNull(typesFetcher);
    }

    @Override
    public FacetInfoPlaceholder get(DataFetchingEnvironment environment) throws Exception {
        NodePlaceholder node = environment.getSource();
        return new FacetInfoPlaceholder(node.parent(), this.predicatesFetcher, this.typesFetcher);
    }
}
