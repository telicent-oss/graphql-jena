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

import graphql.schema.DataFetchingEnvironment;
import io.telicent.jena.graphql.execution.telicent.graph.TelicentExecutionContext;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Request-scoped lookup of a node's declared {@code rdf:type}s, shared by every fetcher that needs them.
 * <p>
 * Within one GraphQL execution, a node's types are looked up in the dataset at most once. Whichever fetcher asks first
 * (e.g. {@code types} on a relationship's domain/range, or the type facets of a relationship list) loads them, and
 * later fetchers reuse the cached list. Every lookup goes through the user's security-filtered dataset, and the cache
 * lives in the {@link TelicentExecutionContext}, so it never outlives the request or crosses users.
 * </p>
 */
final class NodeTypes {

    private NodeTypes() {
    }

    /**
     * Gets a node's declared types, loading them at most once per GraphQL execution
     *
     * @param environment Data fetching environment, whose local context is the request's execution context
     * @param node        Node
     * @param loader      Loads the types when they aren't already cached for this request
     * @return Declared types
     */
    static List<Node> get(DataFetchingEnvironment environment, Node node, Supplier<List<Node>> loader) {
        TelicentExecutionContext context = Objects.requireNonNull(environment.getLocalContext(),
                                                                 "TelicentExecutionContext is required for node type lookups");
        return context.getOrCompute(new NodeCacheKey(NodeKind.TYPES, node), loader);
    }

    /**
     * Loads a node's declared types from the dataset: distinct URI or blank node objects of {@code rdf:type}, across
     * all graphs the dataset exposes
     *
     * @param dsg  Dataset graph
     * @param node Node
     * @return Declared types
     */
    static List<Node> load(DatasetGraph dsg, Node node) {
        return dsg.stream(Node.ANY, node, RDF.type.asNode(), Node.ANY)
                  .map(Quad::getObject)
                  .filter(t -> t.isURI() || t.isBlank())
                  .distinct()
                  .toList();
    }
}
