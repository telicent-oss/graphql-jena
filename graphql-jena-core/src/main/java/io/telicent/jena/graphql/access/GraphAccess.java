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
package io.telicent.jena.graphql.access;

import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.sparql.util.Context;
import org.apache.jena.sparql.util.Symbol;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Optional, store-neutral access to common graph questions that a storage engine may be able to answer more cheaply
 * than by streaming quads through Jena's {@link DatasetGraph} API
 * <p>
 * A store that can answer these questions in its own engine places an implementation in the dataset's
 * {@link Context} under {@link #SYMBOL}.  Fetchers look it up with {@link #get(DatasetGraph)} and, when there is none,
 * fall back to streaming quads from the dataset exactly as they would without this interface.  Since the lookup goes
 * through the dataset's context, it works even when the store is hidden beneath dataset wrappers.
 * </p>
 * <p>
 * Every method takes the dataset the caller is working with, inside the caller's transaction, and must answer
 * exactly as {@link DefaultGraphAccess} does over that dataset, i.e. as if by
 * {@link DatasetGraph#stream(Node, Node, Node, Node)} over every graph, so that any access control or visibility
 * applied by the dataset applies equally to answers given by an implementation.
 * </p>
 * <p>
 * The questions are plain graph questions with no knowledge of any GraphQL schema or ontology.  Further questions may
 * be added in future versions.
 * </p>
 */
public interface GraphAccess {

    /**
     * Context symbol under which a dataset's {@link GraphAccess} implementation, if any, is held
     */
    Symbol SYMBOL = Symbol.create("urn:x-graphql-jena:graphAccess");

    /**
     * Counts the relationships of each of the given nodes
     * <p>
     * A relationship is a distinct {@code (subject, predicate, object)} triple, in any graph of the dataset (default or
     * named), counted once however many graphs contain it, that goes from the node ({@link EdgeDirection#OUT}, the
     * node is the subject) or to the node ({@link EdgeDirection#IN}, the node is the object) and whose other end is a
     * URI or a blank node.
     * </p>
     *
     * @param dsg        Dataset graph, in the caller's transaction
     * @param nodes      Nodes to count relationships for
     * @param direction  Direction of the relationships
     * @param predicates Predicates to count relationships for, {@code null} or empty to count relationships with any
     *                   predicate
     * @return One count per node, in the same order as {@code nodes}
     */
    long[] countRelationships(DatasetGraph dsg, List<Node> nodes, EdgeDirection direction,
                              Collection<Node> predicates);

    /**
     * Gets a page of the relationships of a node, as counted by
     * {@link #countRelationships(DatasetGraph, List, EdgeDirection, Collection)}
     * <p>
     * Each distinct triple is returned once, as a quad whose graph carries no meaning.  Relationships are returned in
     * an order that is stable for the same arguments while the dataset is unchanged, so consecutive pages neither
     * repeat nor miss relationships.
     * </p>
     *
     * @param dsg        Dataset graph, in the caller's transaction
     * @param node       Node
     * @param direction  Direction of the relationships
     * @param predicates Predicates to return relationships for, {@code null} or empty for relationships with any
     *                   predicate
     * @param offset     Number of relationships to skip, {@code 0} to start from the first relationship
     * @param limit      Maximum number of relationships to return
     * @return Stream of relationships
     * @throws IllegalArgumentException Thrown if {@code offset} or {@code limit} is negative
     */
    Stream<Quad> relationships(DatasetGraph dsg, Node node, EdgeDirection direction, Collection<Node> predicates,
                               long offset, long limit);

    /**
     * Gets the {@link GraphAccess} implementation for a dataset, if one has been placed in its context
     *
     * @param dsg Dataset graph
     * @return Graph access implementation, or {@code null} if the dataset has none and callers should use the
     * {@link DatasetGraph} API directly
     */
    static GraphAccess get(DatasetGraph dsg) {
        if (dsg == null) {
            return null;
        }
        Context context = dsg.getContext();
        if (context == null) {
            return null;
        }
        return context.get(SYMBOL) instanceof GraphAccess access ? access : null;
    }
}
