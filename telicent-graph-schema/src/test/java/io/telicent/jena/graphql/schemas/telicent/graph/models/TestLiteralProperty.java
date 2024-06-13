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

public class TestLiteralProperty {

    private static final String BLANK_NODE_PREFIX = "_:";

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void test_literalProperty_noLiteral() {
        // given
        // when
        // then
        new LiteralProperty(Node.ANY, Node.ANY, null);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void test_getPredicate_illegal() {
        // given
        LiteralProperty literalProperty = new LiteralProperty(Node.ANY, createLiteralString(RANDOM_ID), null);
        // when
        // then
        literalProperty.getPredicate();
    }

    @Test
    public void test_getPredicate_isBlank() {
        // given
        LiteralProperty literalProperty = new LiteralProperty(createBlankNode(RANDOM_ID), createLiteralString(RANDOM_ID), null);
        // when
        String actualPredicate = literalProperty.getPredicate();
        // then
        Assert.assertEquals(actualPredicate, TelicentGraphSchema.BLANK_NODE_PREFIX +RANDOM_ID);
    }

    @Test
    public void test_getShortPredicate_nullPrefix() {
        // given
        LiteralProperty literalProperty = new LiteralProperty(createBlankNode(RANDOM_ID), createLiteralString(RANDOM_ID), null);
        // when
        String actualPredicate = literalProperty.getShortPredicate();
        // then
        Assert.assertEquals(actualPredicate, TelicentGraphSchema.BLANK_NODE_PREFIX+RANDOM_ID);
    }

    @Test
    public void test_getShortPredicate_notURIPredicate() {
        // given
        LiteralProperty literalProperty = new LiteralProperty(createBlankNode(RANDOM_ID), createLiteralString(RANDOM_ID),
                                                              PrefixMapFactory.create());
        // when
        String actualPredicate = literalProperty.getShortPredicate();
        // then
        Assert.assertEquals(actualPredicate, TelicentGraphSchema.BLANK_NODE_PREFIX+RANDOM_ID);
    }

    @Test
    public void test_getShortPredicate_uriPredicate_noPrefix() {
        // given
        LiteralProperty literalProperty = new LiteralProperty(createURI(RANDOM_ID), createLiteralString(RANDOM_ID),
                                                              PrefixMapFactory.create());
        // when
        String actualPredicate = literalProperty.getShortPredicate();
        // then
        Assert.assertEquals(actualPredicate, RANDOM_ID);
    }

    @Test
    public void test_getShortPredicate_uriPredicate_prefix() {
        // given
        PrefixMap prefixMap = PrefixMapFactory.create();
        prefixMap.add(RANDOM_ID, "test/");
        LiteralProperty literalProperty = new LiteralProperty(createURI("test/" + RANDOM_ID), createLiteralString(RANDOM_ID), prefixMap);
        // when
        String actualPredicate = literalProperty.getShortPredicate();
        // then
        Assert.assertEquals(actualPredicate, RANDOM_ID + ":" + RANDOM_ID);
    }

}
