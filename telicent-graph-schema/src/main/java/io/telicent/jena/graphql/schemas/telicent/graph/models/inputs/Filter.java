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

import java.util.Set;
import java.util.stream.Stream;

/**
 * Interface for GraphQL query filters
 */
public interface Filter {
    /**
     * Filter mode
     *
     * @return Mode
     */
    FilterMode mode();

    /**
     * Values to filter by
     *
     * @return Values
     */
    Set<Node> values();

    /**
     * Applies the filter to the given stream
     *
     * @param stream Input stream
     * @param dsg    Dataset graph
     * @return Filtered stream
     */
    Stream<Quad> filter(Stream<Quad> stream, DatasetGraph dsg);
}
