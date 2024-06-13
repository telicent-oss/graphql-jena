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
package io.telicent.jena.graphql.fetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.telicent.jena.graphql.schemas.CoreSchema;
import io.telicent.jena.graphql.schemas.DatasetSchema;
import io.telicent.jena.graphql.schemas.models.WrappedNode;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;
import org.apache.jena.sparql.core.Quad;

import java.util.Map;

/**
 * A GraphQL {@link DataFetcher} that accesses a specific Node from a {@link Triple}, {@link Quad} or {@link Map}
 */
public class NodeFetcher implements DataFetcher<WrappedNode> {

    /**
     * Creates a new node fetcher that extracts the individual nodes (or RDF terms) from a larger data structure
     */
    public NodeFetcher() {

    }

    @Override
    @SuppressWarnings("unchecked")
    public WrappedNode get(DataFetchingEnvironment environment) {
        Object parent = environment.getSource();
        if (parent instanceof Triple) {
            return get(environment, (Triple) parent);
        } else if (parent instanceof Quad) {
            return get(environment, (Quad) parent);
        } else if (parent instanceof Map<?, ?>) {
            return get(environment, (Map<String, Object>) parent);
        } else {
            throw new IllegalArgumentException("Cannot fetch Node for a parent type that is not Triple/Quad");
        }
    }

    private WrappedNode get(DataFetchingEnvironment environment, Map<String, Object> parent) {
        return switch (environment.getField().getName()) {
            case CoreSchema.SUBJECT_FIELD -> wrap(parent.get(CoreSchema.SUBJECT_FIELD));
            case CoreSchema.PREDICATE_FIELD -> wrap(parent.get(CoreSchema.PREDICATE_FIELD));
            case CoreSchema.OBJECT_FIELD -> wrap(parent.get(CoreSchema.OBJECT_FIELD));
            case DatasetSchema.QUADS_FIELD -> wrap(parent.get(DatasetSchema.QUADS_FIELD));
            default -> throw new IllegalArgumentException("Unrecognised field " + environment.getField().getName());
        };
    }

    private WrappedNode get(DataFetchingEnvironment environment, Quad quad) {
        return switch (environment.getField().getName()) {
            case CoreSchema.SUBJECT_FIELD -> wrap(quad.getSubject());
            case CoreSchema.PREDICATE_FIELD -> wrap(quad.getPredicate());
            case CoreSchema.OBJECT_FIELD -> wrap(quad.getObject());
            case CoreSchema.GRAPH_FIELD -> wrap(quad.getGraph());
            default -> throw new IllegalArgumentException(
                    "Cannot fetch field " + environment.getField().getName() + " for a Quad");
        };
    }

    private WrappedNode get(DataFetchingEnvironment environment, Triple triple) {
        return switch (environment.getField().getName()) {
            case CoreSchema.SUBJECT_FIELD -> wrap(triple.getSubject());
            case CoreSchema.PREDICATE_FIELD -> wrap(triple.getPredicate());
            case CoreSchema.OBJECT_FIELD -> wrap(triple.getObject());
            default -> throw new IllegalArgumentException(
                    "Cannot fetch field " + environment.getField().getName() + " for a Quad");
        };
    }

    private WrappedNode wrap(Object o) {
        if (o instanceof Node) {
            return wrap((Node) o);
        } else if (o instanceof WrappedNode) {
            return (WrappedNode) o;
        } else {
            throw new IllegalArgumentException("Cannot fetch Node for field type that is not Node/WrappedNode");
        }
    }

    private WrappedNode wrap(Node n) {
        return new WrappedNode(n);
    }
}
