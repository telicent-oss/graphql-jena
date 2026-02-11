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

import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.FilterMode;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Parsed URI filter specification.
 */
public class UriFilterSpec {

    private final Set<String> values;
    private final FilterMode mode;

    /**
     * Creates a new URI filter specification.
     *
     * @param values Allowed or blocked URI values
     * @param mode   Filter mode
     */
    public UriFilterSpec(Set<String> values, FilterMode mode) {
        this.values = Collections.unmodifiableSet(Objects.requireNonNull(values, "values"));
        this.mode = Objects.requireNonNull(mode, "mode");
    }

    /**
     * Gets the filter mode.
     *
     * @return Filter mode
     */
    public FilterMode getMode() {
        return mode;
    }

    /**
     * Gets the filter values.
     *
     * @return Filter value set
     */
    public Set<String> getValues() {
        return values;
    }

    /**
     * Tests whether a URI matches this filter.
     *
     * @param uri URI to test
     * @return True if matched
     */
    public boolean matches(String uri) {
        if (values.isEmpty()) {
            return true;
        }
        boolean contains = values.contains(uri);
        return mode == FilterMode.INCLUDE ? contains : !contains;
    }
}
