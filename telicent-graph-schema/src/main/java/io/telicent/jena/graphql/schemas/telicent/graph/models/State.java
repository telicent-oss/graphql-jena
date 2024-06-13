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
package io.telicent.jena.graphql.schemas.telicent.graph.models;

import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import org.apache.jena.graph.Node;

import java.util.Objects;

/**
 * Represents an entity state
 */
public class State {

    /*
     type State {
        uri: String!
        type: String!
        start: String
        end: String
        period: String
        relations: [NonDirectionalRel]!
     }
     */

    private final Node state, predicate, entity;

    private Node period;

    /**
     * Creates a new state
     *
     * @param state     State node
     * @param predicate Predicate by which this state is connected to the entity
     * @param entity    Entity that this state is a state of
     */
    public State(Node state, Node predicate, Node entity) {
        Objects.requireNonNull(state);
        Objects.requireNonNull(entity);
        Objects.requireNonNull(predicate);
        this.state = state;
        this.predicate = predicate;
        this.entity = entity;
    }

    /**
     * Gets the state URI
     *
     * @return State URI
     */
    public String getUri() {
        if (this.state.isURI()) {
            return this.state.getURI();
        } else if (this.state.isBlank()) {
            return TelicentGraphSchema.BLANK_NODE_PREFIX + this.state.getBlankNodeLabel();
        } else {
            throw new IllegalStateException("Not a Node with a URI");
        }
    }

    /**
     * Gets the state node
     *
     * @return State node
     */
    public Node getStateNode() {
        return this.state;
    }

    /**
     * Gets the node representing the entity that this state is a state of
     *
     * @return Entity
     */
    public Node getEntityNode() {
        return this.entity;
    }

    /**
     * Gets the predicate node by which this state is connected to its entity
     *
     * @return Predicate node
     */
    public Node getPredicateNode() {
        return this.predicate;
    }

    /**
     * Sets the period for this state
     *
     * @param period Period
     */
    public void setPeriod(Node period) {
        this.period = period;
    }

    /**
     * Gets the period for this state
     *
     * @return Period
     */
    public Node getPeriod() {
        return this.period;
    }

    /**
     * Gets whether the period for this state has been fetched
     *
     * @return True if period present, false otherwise
     */
    public boolean hasPeriod() {
        return this.period != null;
    }
}
