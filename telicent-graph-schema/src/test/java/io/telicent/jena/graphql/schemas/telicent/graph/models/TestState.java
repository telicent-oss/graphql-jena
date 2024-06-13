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
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.telicent.jena.graphql.utils.UtilConstants.RANDOM_ID;
import static org.apache.jena.graph.NodeFactory.createBlankNode;
import static org.apache.jena.graph.NodeFactory.createURI;

public class TestState {
    private static final String BLANK_NODE_PREFIX = "_:";

    @Test
    public void test_getURI_happy() {
        // given
        State state = new State(createURI(RANDOM_ID), Node.ANY, Node.ANY);
        // when
        String actual = state.getUri();
        // then
        Assert.assertEquals(actual, RANDOM_ID);
    }

    @Test
    public void test_getURI_blankState() {
        // given
        State state = new State(createBlankNode(RANDOM_ID), Node.ANY, Node.ANY);
        // when
        String actual = state.getUri();
        // then
        Assert.assertEquals(actual, TelicentGraphSchema.BLANK_NODE_PREFIX +RANDOM_ID);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void test_getURI_error() {
        // given
        State state = new State(Node.ANY, Node.ANY, Node.ANY);
        // when
        // then
        state.getUri();
    }
}
