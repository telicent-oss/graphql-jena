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
package io.telicent.jena.graphql.schemas;

/**
 * Provides constants relating to the Traversal GraphQL Schema
 */
public class TraversalSchema extends CoreSchema {

    /**
     * Class contains static constants so no instantiation is needed
     */
    TraversalSchema() {
        super();
    }

    /**
     * Traversal Node type
     */
    public static final String TRAVERSAL_NODE_TYPE = "TraversalNode";

    /**
     * Traversal Edge type
     */
    public static final String TRAVERSAL_EDGE_TYPE = "TraversalEdge";

    /**
     * Traversal Query type
     */
    public static final String TRAVERSAL_QUERY_TYPE = "Traversal";

    /**
     * Nodes field
     */
    public static final String NODES_FIELD = "nodes";

    /**
     * Node field
     */
    public static final String NODE_FIELD = "node";

    /**
     * Target field
     */
    public static final String TARGET_FIELD = "target";

    /**
     * The starts argument used to define the starting nodes for a traversal
     */
    public static final String STARTS_ARGUMENT = "starts";

    /**
     * The kinds argument used to define the kinds of the target node for the traversal
     */
    public static final String KINDS_ARGUMENT = "kinds";

    /**
     * Outgoing field
     */
    public static final String OUTGOING_FIELD = "outgoing";

    /**
     * Incoming field
     */
    public static final String INCOMING_FIELD = "incoming";
}
