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
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Abstract class for filters
 */
public abstract class AbstractFilter {
    /**
     * The filter mode
     */
    protected final FilterMode mode;
    /**
     * The filter values
     */
    protected final Set<Node> values = new LinkedHashSet<>();

    /**
     * Creates a new abstract filter
     *
     * @param mode   Filter Mode
     * @param values Values to filter by
     */
    public AbstractFilter(FilterMode mode, Collection<Node> values) {
        this.mode = mode;
        this.values.addAll(Objects.requireNonNull(values));
    }

    /**
     * Gets the filter mode
     *
     * @return Filter mode
     */
    public FilterMode mode() {
        return this.mode;
    }

    /**
     * Gets the filter values
     *
     * @return Values to filter by
     */
    public Set<Node> values() {
        return this.values;
    }

    /**
     * Applies the filter
     *
     * @param stream Input stream
     * @param dsg    Dataset graph
     * @return Filtered stream
     */
    public abstract Stream<Quad> filter(Stream<Quad> stream, DatasetGraph dsg);
}
