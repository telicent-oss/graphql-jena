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

import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import org.apache.jena.graph.Node;

import java.util.Objects;

/**
 * Cache key for a materialised relationship selection within a single GraphQL request
 */
final class RelationshipSelectionCacheKey {
    private final Node source;
    private final EdgeDirection direction;
    private final Object predicateFilter;
    private final Object nodeFilter;
    private final Object typeFilter;

    RelationshipSelectionCacheKey(Node source, EdgeDirection direction, Object predicateFilter, Object nodeFilter,
                                  Object typeFilter) {
        this.source = source;
        this.direction = direction;
        this.predicateFilter = predicateFilter;
        this.nodeFilter = nodeFilter;
        this.typeFilter = typeFilter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelationshipSelectionCacheKey that)) return false;
        return Objects.equals(this.source, that.source) && this.direction == that.direction && Objects.equals(
                this.predicateFilter, that.predicateFilter) && Objects.equals(this.nodeFilter, that.nodeFilter)
               && Objects.equals(this.typeFilter, that.typeFilter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.source, this.direction, this.predicateFilter, this.nodeFilter, this.typeFilter);
    }
}
