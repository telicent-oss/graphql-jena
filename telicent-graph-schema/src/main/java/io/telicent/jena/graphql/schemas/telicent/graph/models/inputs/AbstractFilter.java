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

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Abstract implementation of a query filter
 */
public abstract class AbstractFilter implements Filter {
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
        this.mode = Objects.requireNonNull(mode, "mode cannot be null");
        this.values.addAll(Objects.requireNonNull(values, "Values to filter by cannot be null"));
        if (this.values.isEmpty()) {
            throw new IllegalArgumentException("Values to filter by cannot be empty");
        }
    }

    @Override
    public FilterMode mode() {
        return this.mode;
    }

    @Override
    public Set<Node> values() {
        return this.values;
    }
}
