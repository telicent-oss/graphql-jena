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
package io.telicent.jena.graphql.schemas.telicent.graph.models;

import graphql.schema.DataFetcher;

import java.util.List;

/**
 * A placeholder for use in fetching Facet information
 *
 * @param node              Node for which we are computing facets
 * @param predicatesFetcher Fetcher used to compute predicates facet
 * @param typesFetcher      Fetcher used to compute types facet
 */
public record FacetInfoPlaceholder(TelicentGraphNode node, DataFetcher<List<FacetInfo>> predicatesFetcher,
                                   DataFetcher<List<FacetInfo>> typesFetcher) {
}
