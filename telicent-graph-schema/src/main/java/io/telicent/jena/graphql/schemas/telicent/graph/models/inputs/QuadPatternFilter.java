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

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for filters whose filter condition can be expressed as one/more simple quad patterns
 */
public interface QuadPatternFilter extends Filter {

    /**
     * Gets the quad patterns that this filter represents if applicable
     * <p>
     * Generally this is only applicable for filters that
     * </p>
     *
     * @param graph     Default graph filter term
     * @param subject   Default subject filter term
     * @param predicate Default predicate filter term
     * @param object    Default object filter term
     * @return Quad Patterns
     * @throws IllegalArgumentException If one of the given default terms conflicts with the filter, typically this
     *                                  happens if you supply something other than {@link Node#ANY} in a position that
     *                                  the filter wants to filter on.
     */
    List<Tuple4<Node>> getQuadPatterns(Node graph, Node subject, Node predicate, Node object);

    /**
     * Combines two sets of patterns together to yield all the pattern combinations
     *
     * @param a First set of patterns
     * @param b Second set of patterns
     * @return Combined patterns
     */
    static List<Tuple4<Node>> combinePatterns(List<Tuple4<Node>> a, List<Tuple4<Node>> b) {
        List<Tuple4<Node>> combined = new ArrayList<>();
        for (Tuple4<Node> left : a) {
            for (Tuple4<Node> right : b) {
                combined.add(
                        TupleFactory.create4(combine(left, right, 0), combine(left, right, 1), combine(left, right, 2),
                                             combine(left, right, 3)));
            }
        }
        return combined;
    }

    private static Node combine(Tuple4<Node> left, Tuple4<Node> right, int index) {
        Node leftNode = left.get(index);
        Node rightNode = right.get(index);
        if (leftNode == Node.ANY) {
            return rightNode;
        } else if (rightNode == Node.ANY) {
            return leftNode;
        } else if (leftNode.equals(rightNode)) {
            return leftNode;
        } else {
            throw new IllegalArgumentException(
                    "Both patterns have conflicting concrete values in the " + position(index) + " position");
        }
    }

    private static String position(int i) {
        return switch (i) {
            case 0 -> "Graph";
            case 1 -> "Subject";
            case 2 -> "Predicate";
            case 3 -> "Object";
            default -> "Unknown";
        };
    }

}
