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
import org.apache.jena.sys.JenaSystem;
import org.apache.jena.vocabulary.RDF;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * A filter that filters on the types of outbound relationships
 */
public class OutboundTypeFilter extends AbstractFilter {

    static {
        JenaSystem.init();
    }

    private static final Node RDF_TYPE = RDF.type.asNode();

    /**
     * Creates a new outbound type filter
     *
     * @param mode   Filter Mode
     * @param values Values to filter by
     */
    public OutboundTypeFilter(FilterMode mode, Collection<Node> values) {
        super(mode, values);
    }

    @Override
    public Stream<Quad> filter(Stream<Quad> stream, DatasetGraph dsg) {
        if (this.mode == FilterMode.INCLUDE) {
            return stream.filter(
                    q -> this.values.stream().anyMatch(t -> dsg.contains(Node.ANY, q.getObject(), RDF_TYPE, t)));
        } else {
            return stream.filter(
                    q -> this.values.stream().noneMatch(t -> dsg.contains(Node.ANY, q.getObject(), RDF_TYPE, t)));
        }
    }
}
