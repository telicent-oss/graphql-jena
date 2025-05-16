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
import io.telicent.jena.graphql.schemas.telicent.graph.models.RelationshipCounts;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;

/**
 * A placeholder fetcher that returns a simple record class with a reference back to the parent node
 * <p>
 * This is needed because GraphQL Java doesn't allow a non-scalar typed field to have an empty value (unless explicitly
 * nullable), and we need to have a placeholder object present so that the fetchers that actually compute the counts
 * can get access to the parent {@link TelicentGraphNode} they need to find the things to count.
 * </p>
 */
public class RelationshipCountsPlaceholderFetcher implements DataFetcher<RelationshipCounts> {

    /**
     * Default constructor
     */
    public RelationshipCountsPlaceholderFetcher() {
        super();
    }

    @Override
    public RelationshipCounts get(DataFetchingEnvironment environment) throws Exception {
        TelicentGraphNode source = environment.getSource();
        return new RelationshipCounts(source);
    }
}
