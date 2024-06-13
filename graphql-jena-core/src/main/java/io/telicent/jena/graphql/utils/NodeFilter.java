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
package io.telicent.jena.graphql.utils;

import io.telicent.jena.graphql.schemas.models.NodeKind;
import io.telicent.jena.graphql.schemas.models.WrappedNode;
import org.apache.jena.graph.Node;

import java.util.*;

/**
 * Utilities around node filters
 */
public class NodeFilter {

    /**
     * Private constructor prevents instantiation
     */
    private NodeFilter() {
    }

    /**
     * Parses a node filter
     *
     * @param rawFilter Raw filter object
     * @return Node filter, will be {@link Node#ANY} if no filter was supplied
     */
    public static Node parse(Map<String, Object> rawFilter) {
        if (rawFilter == null || rawFilter.isEmpty()) {
            return Node.ANY;
        }
        return new WrappedNode(rawFilter).getNode();

    }

    /**
     * Makes a Node filter from the given Node
     *
     * @param n Node
     * @return Node filter
     */
    public static Map<String, Object> make(Node n) {
        if (n == null) {
            return null;
        }
        return new WrappedNode(n).toMap();
    }

    /**
     * Parses a list of Node filters
     *
     * @param argument Raw filter object
     * @return A list of node filters
     */
    @SuppressWarnings("unchecked")
    public static List<Node> parseList(Object argument) {
        if (argument == null) {
            return List.of(Node.ANY);
        } else if (argument instanceof Map<?, ?>) {
            return List.of(parse((Map<String, Object>) argument));
        } else if (argument instanceof List<?>) {
            List<Node> filters = new ArrayList<>();
            for (Object filter : ((List<Object>) argument)) {
                if (filter instanceof Map<?, ?>) {
                    filters.add(parse((Map<String, Object>) filter));
                } else {
                    throw new IllegalArgumentException("Unsupported item type in list of Node Filters");
                }
            }
            return filters;
        } else if (argument instanceof Node) {
            return List.of((Node) argument);
        } else {
            throw new IllegalArgumentException("Unsupported type to parse a list of Node Filters from");
        }
    }

    /**
     * Parses a set of node kinds
     *
     * @param argument Raw kinds argument
     * @return Set of node kinds
     */
    @SuppressWarnings("unchecked")
    public static EnumSet<NodeKind> parseKinds(Object argument) {
        if (argument == null) {
            return EnumSet.allOf(NodeKind.class);
        }

        if (argument instanceof List<?>) {
            return EnumSet.copyOf(((List<String>) argument).stream().map(NodeKind::valueOf).toList());
        } else {
            throw new IllegalArgumentException("Unsupported type to parse a list of Node kinds from");
        }
    }
}
