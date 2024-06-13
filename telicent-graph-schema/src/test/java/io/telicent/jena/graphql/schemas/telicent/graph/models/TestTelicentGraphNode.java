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
import org.apache.jena.riot.system.PrefixMap;
import org.apache.jena.riot.system.PrefixMapFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


import static io.telicent.jena.graphql.utils.UtilConstants.RANDOM_ID;
import static org.apache.jena.graph.NodeFactory.*;

public class TestTelicentGraphNode {

    @Test
    public void test_getURI_blankNode() {
        // given
        TelicentGraphNode node = new TelicentGraphNode(createBlankNode(RANDOM_ID), null);
        // when
        String actualUri = node.getUri();
        // then
        Assert.assertEquals(actualUri, TelicentGraphSchema.BLANK_NODE_PREFIX + RANDOM_ID);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void test_getURI_nonURINode() {
        // given
        TelicentGraphNode node = new TelicentGraphNode(Node.ANY, null);
        // when
        // then
        node.getUri();
    }

    @Test
    public void test_getShortURI_nullPrefix() {
        // given
        TelicentGraphNode node = new TelicentGraphNode(createBlankNode(RANDOM_ID), null);
        // when
        String actualUri = node.getShortUri();
        // then
        Assert.assertEquals(actualUri, TelicentGraphSchema.BLANK_NODE_PREFIX + RANDOM_ID);
    }

    @Test
    public void test_getShortURI_notURINode() {
        // given
        TelicentGraphNode node = new TelicentGraphNode(createBlankNode(RANDOM_ID), PrefixMapFactory.create());
        // when
        String actualUri = node.getShortUri();
        // then
        Assert.assertEquals(actualUri, TelicentGraphSchema.BLANK_NODE_PREFIX + RANDOM_ID);
    }

    @Test
    public void test_getShortURI_URINode_noPrefix() {
        // given
        TelicentGraphNode node = new TelicentGraphNode(createURI(RANDOM_ID), PrefixMapFactory.create());
        // when
        String actualUri = node.getShortUri();
        // then
        Assert.assertEquals(actualUri, RANDOM_ID);
    }

    @Test
    public void test_getShortURI_URINode_withPrefix() {
        // given
        PrefixMap prefixMap = PrefixMapFactory.create();
        prefixMap.add(RANDOM_ID, "test/");
        TelicentGraphNode node = new TelicentGraphNode(createURI("test/" + RANDOM_ID), prefixMap);
        // when
        String actualUri = node.getShortUri();
        // then
        Assert.assertEquals(actualUri, RANDOM_ID + ":" + RANDOM_ID);
    }

    @Test
    public void test_getUriHash() {
        // given
        TelicentGraphNode node = new TelicentGraphNode(createURI(RANDOM_ID), null);
        // when
        String actual = node.getUriHash();
        // then
        Assert.assertNotNull(actual);
        Assert.assertNotEquals(actual, RANDOM_ID);
    }
}
