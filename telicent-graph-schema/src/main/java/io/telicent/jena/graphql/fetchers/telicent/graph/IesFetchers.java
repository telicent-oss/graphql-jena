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

import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;

import java.util.List;

/**
 * Constants relating to the IES 4 ontology
 */
public class IesFetchers {

    private IesFetchers(){}
    /*
      ies:isStateOf, ies:isStartOf, ies:isEndOf, ies:owns, ies:isPartOf, ies:isParticipant
    */

    /**
     * The IES 4 namespace
     */
    public static final String IES_NAMESPACE = "http://ies.data.gov.uk/ontology/ies4#";
    /**
     * The type for ISO 8601 period representations
     */
    public static final Node PERIOD_REPRESENTATION =
            iesTerm("iso8601PeriodRepresentation");
    /**
     * The in period predicate
     */
    public static final Node IN_PERIOD = iesTerm("inPeriod");
    /**
     * The is start of predicate
     */
    public static final Node IS_START_OF = iesTerm("isStartOf");
    /**
     * The is end of predicate
     */
    public static final Node IS_END_OF = iesTerm("isEndOf");
    /**
     * The is state of predicate
     */
    public static final Node IS_STATE_OF = iesTerm("isStateOf");
    /**
     * Predicates that are used to connect a state to an entity
     */
    public static final List<Node> STATE_PREDICATES =
            List.of(IS_STATE_OF, IS_START_OF, IS_END_OF, iesTerm("owns"),
                    iesTerm("isPartOf"), iesTerm("isParticipant"));

    /**
     * Creates a Node for an IES term using the provided local name portion and the predefined {@link #IES_NAMESPACE}
     * constant
     *
     * @param localName Local name portion of the URI
     * @return Node for the IES term within the {@link #IES_NAMESPACE}
     */
    public static Node iesTerm(String localName) {
        return NodeFactory.createURI(IES_NAMESPACE + localName);
    }
}
