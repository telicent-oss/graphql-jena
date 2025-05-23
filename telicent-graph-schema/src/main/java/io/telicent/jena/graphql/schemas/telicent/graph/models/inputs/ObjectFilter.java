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

import org.apache.jena.atlas.lib.tuple.Tuple4;
import org.apache.jena.atlas.lib.tuple.TupleFactory;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.Quad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A filter that includes/excludes only quads with specific objects
 */
public class ObjectFilter extends QuadFieldFilter implements QuadPatternFilter {
    /**
     * Creates a new object filter
     *
     * @param mode   Filter Mode
     * @param values Objects to filter by
     */
    public ObjectFilter(FilterMode mode, Collection<Node> values) {
        super(mode, values, Quad::getObject);
    }

    @Override
    public List<Tuple4<Node>> getQuadPatterns(Node graph, Node subject, Node predicate, Node object) {
        if (this.mode == FilterMode.EXCLUDE) return List.of();

        if (object != Node.ANY) {
            throw new IllegalArgumentException("object term MUST be ANY");
        }

        List<Tuple4<Node>> patterns = new ArrayList<>();
        for (Node objectFilter : this.values) {
            patterns.add(TupleFactory.create4(graph, subject, predicate, objectFilter));
        }
        return patterns;
    }
}
