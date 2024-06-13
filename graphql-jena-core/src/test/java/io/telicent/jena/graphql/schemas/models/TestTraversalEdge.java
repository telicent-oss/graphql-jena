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
package io.telicent.jena.graphql.schemas.models;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.apache.jena.graph.NodeFactory.createBlankNode;

public class TestTraversalEdge {

    @Test
    public void test_of_happyPath() {
        // given
        WrappedNode wrappedNodeEdge = new WrappedNode(createBlankNode());
        WrappedNode wrappedNodeTarget = new WrappedNode(createBlankNode());
        // when
        TraversalEdge traversalEdge = TraversalEdge.of(wrappedNodeEdge, null, wrappedNodeTarget);
        // then
        Assert.assertNull(traversalEdge.getDirection());
    }
}
