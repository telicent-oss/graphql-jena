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

import org.apache.jena.graph.Triple;
import org.apache.jena.sparql.core.Quad;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Stream utilities for presenting named-graph data as an RDF union view.
 */
final class QuadStreams {

    private QuadStreams() {
    }

    /**
     * Removes quads that differ only by graph while preserving lazy stream processing.
     * <p>
     * The retained quad is only a carrier for its subject, predicate and object. Its graph is deliberately not
     * meaningful because GraphQL does not currently expose named-graph provenance.
     * </p>
     *
     * @param quads Quad stream
     * @return Stream containing one quad for each distinct RDF triple
     */
    static Stream<Quad> distinctByTriple(Stream<Quad> quads) {
        Set<Triple> seen = new HashSet<>();
        return quads.sequential().filter(quad -> seen.add(quad.asTriple()));
    }
}
