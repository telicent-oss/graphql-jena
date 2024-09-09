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
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentSearchResults;

/**
 * A GraphQL {@link DataFetcher} that finds the starting points for a query based upon search terms which are passed on
 * to the Telicent Search REST API to find the matching entities
 */
public class StartingSearchWithMetadataFetcher extends AbstractSearchFetcher<TelicentSearchResults> {

    /**
     * Creates a new fetcher that uses a search query to find nodes of interest
     */
    public StartingSearchWithMetadataFetcher() {

    }

    @Override
    @SuppressWarnings("unchecked")
    public TelicentSearchResults get(DataFetchingEnvironment environment) {
        return searchCommon(environment);
    }

}
