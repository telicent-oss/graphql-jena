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

import org.apache.jena.graph.Node;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRelationship {

    @Test
    public void test_getID() {
        // given
        TelicentGraphNode subject = new TelicentGraphNode(Node.ANY, null);
        TelicentGraphNode predicate = new TelicentGraphNode(Node.ANY, null);
        TelicentGraphNode object = new TelicentGraphNode(Node.ANY, null);
        Relationship relationship = new Relationship(subject, predicate, object);
        // when
        String firstID = relationship.getId();
        String secondID = relationship.getId();
        // then
        Assert.assertEquals(firstID, secondID);
    }
}
