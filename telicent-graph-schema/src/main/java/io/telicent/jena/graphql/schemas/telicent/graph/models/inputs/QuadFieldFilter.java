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
package io.telicent.jena.graphql.schemas.telicent.graph.models.inputs;

import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * A filter that filters on a single field of a quad
 */
public class QuadFieldFilter extends AbstractFilter {

    private final Function<Quad, Node> fieldSelector;

    /**
     * Creates a new quad field filter
     *
     * @param mode          Filter Mode
     * @param values        Values to filter by
     * @param fieldSelector Function that selects the field of the quad to compare against the filter values
     */
    protected QuadFieldFilter(FilterMode mode, Collection<Node> values, Function<Quad, Node> fieldSelector) {
        super(mode, values);
        this.fieldSelector = Objects.requireNonNull(fieldSelector);
    }

    @Override
    public Stream<Quad> filter(Stream<Quad> stream, DatasetGraph dsg) {
        return switch (mode) {
            case INCLUDE -> stream.filter(q -> this.values.contains(fieldSelector.apply(q)));
            case EXCLUDE -> stream.filter(q -> !this.values.contains(fieldSelector.apply(q)));
        };
    }
}
