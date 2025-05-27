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

import static io.telicent.jena.graphql.utils.UtilConstants.RANDOM_ID;
import static java.util.Collections.emptyMap;
import static org.apache.jena.graph.NodeFactory.*;

import java.util.Map;

import org.apache.jena.graph.*;
import org.apache.jena.shared.AddDeniedException;
import org.apache.jena.shared.DeleteDeniedException;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.sparql.sse.SSE;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestWrappedNode {
    @Test(dataProvider = "invalidNodeTypes", expectedExceptions = IllegalArgumentException.class)
    public void test_WrappedNode_illegalMode(Node node) {
        // given
        // when
        // then
        new WrappedNode(node);
    }

    @Test(dataProvider = "validNodeTypes")
    public void test_WrappedNode_validNodes(Node node, String expectedValue) {
        // given
        // when
        WrappedNode wrappedNode = new WrappedNode(node);
        // then
        Assert.assertNotNull(wrappedNode);
        Assert.assertEquals(wrappedNode.getValue(), expectedValue);
        Map<String, Object> map = wrappedNode.toMap();
        Assert.assertNotNull(map);
    }

    @Test(dataProvider = "validLiteralNodeTypes")
    public void test_WrappedNode_validLiteralNodes(Node node, String expectedValue) {
        // given
        // when
        WrappedNode wrappedNode = new WrappedNode(node);
        // then
        Assert.assertNotNull(wrappedNode);
        Assert.assertEquals(wrappedNode.getValue(), expectedValue);
        Map<String, Object> map = wrappedNode.toMap();
        Assert.assertNotNull(map);
    }


    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "invalidMaps")
    public void test_WrapperNode_invalidMaps(Map<String, Object> map) {
        // given
        // when
        // then
        new WrappedNode(map);
    }

    @Test(dataProvider = "validMaps")
    public void test_WrapperNode_mapValid(Map<String, Object> map) {
        // given
        // when
        // then
        WrappedNode wrappedNode = new WrappedNode(map);
        Assert.assertNotNull(wrappedNode);
        Assert.assertNotNull(wrappedNode.getValue());
        Assert.assertNotNull(wrappedNode.toMap());
        Assert.assertNull(wrappedNode.getTriple());
    }


    @Test
    public void test_WrapperNode_mapTripleValid() {
        // given
        Map<String,Object> map = Map.of("kind", "TRIPLE", "triple", Map.of("subject", Map.of("kind", "BLANK", "value", "value"), "predicate", Map.of("kind", "BLANK", "value", "value"), "object", Map.of("kind", "BLANK", "value", "value")));
        // when
        // then
        WrappedNode wrappedNode = new WrappedNode(map);
        Assert.assertNotNull(wrappedNode);
        Assert.assertNull(wrappedNode.getValue());
        Assert.assertNotNull(wrappedNode.toMap());
        Assert.assertNotNull(wrappedNode.getTriple());
    }

    @Test
    public void test_WrapperNode_mapNullTriple() {
        // given
        Triple triple = SSE.parseTriple("(:s :p :o)");
        Node n = createTripleTerm(triple);
        // when
        WrappedNode wrappedNode = new WrappedNode(n);
        // then
        Assert.assertNotNull(wrappedNode.toMap());
    }

    @DataProvider(name = "validMaps")
    private static Object[] validMaps() {
        return new Object[] {
                Map.of("kind", "URI", "value", "value"),
                Map.of("kind", "BLANK", "value", "value"),
                Map.of("kind", "PLAIN_LITERAL", "value", "value"),
                Map.of("kind", "LANGUAGE_LITERAL", "value", "value"),
                Map.of("kind", "TYPED_LITERAL", "value", "value"),
                Map.of("kind", "VARIABLE", "value", "value"),
                };
    }

    @DataProvider(name = "invalidMaps")
    private static Object[] invalidMaps() {
        return new Object[] {
                emptyMap(),
                Map.of("kind", "unrecognised"),
                Map.of("kind", "TRIPLE"),
                Map.of("kind", "TRIPLE", "value", "of-no-value"),
                Map.of("kind", "TRIPLE", "triple", emptyMap()),
                Map.of("kind", "TRIPLE", "triple", Map.of("subject", emptyMap())),
                Map.of("kind", "TRIPLE", "triple", Map.of("predicate", emptyMap())),
                Map.of("kind", "TRIPLE", "triple", Map.of("object", emptyMap())),
                Map.of("kind", "TRIPLE", "triple", Map.of("subject", emptyMap())),
                Map.of("kind", "TRIPLE", "triple", Map.of("subject", emptyMap(), "predicate", emptyMap())),
                Map.of("kind", "TRIPLE", "triple", Map.of("subject", emptyMap(), "object", emptyMap())),
                Map.of("kind", "TRIPLE", "triple", Map.of("predicate", emptyMap(), "object", emptyMap())),
                Map.of("kind", "TRIPLE", "triple", Map.of("subject", emptyMap(), "predicate", emptyMap(), "object", emptyMap())),
                };
    }

    @DataProvider(name = "validLiteralNodeTypes")
    private static Object[][] validLiteralNodeTypes() {
        return new Object[][] {
                { createLiteralString(RANDOM_ID), RANDOM_ID },
                { createLiteralString(""), "" },
                { createLiteralLang(RANDOM_ID, "lang"), RANDOM_ID},
                { createLiteralString(RANDOM_ID), RANDOM_ID},
        };
    }

    @DataProvider(name = "validNodeTypes")
    private static Object[][] validNodeTypes() {
        return new Object[][] {
                { createBlankNode(RANDOM_ID), RANDOM_ID },
                { createLiteralString(RANDOM_ID), RANDOM_ID },
                { createURI(RANDOM_ID), RANDOM_ID },
                { createVariable(RANDOM_ID), RANDOM_ID },
                { createTripleTerm(Triple.create(createBlankNode(), createBlankNode(), createBlankNode())), null },
                };
    }

    @DataProvider(name = "invalidNodeTypes")
    private static Object[] invalidNodeTypes() {
        return new Object[] {
                createExt("ext"),
                createGraphNode(new TestGraph())
        };
    }

    private static class TestGraph implements Graph {

        @Override
        public boolean dependsOn(Graph graph) {
            return false;
        }

        @Override
        public TransactionHandler getTransactionHandler() {
            return null;
        }

        @Override
        public Capabilities getCapabilities() {
            return null;
        }

        @Override
        public GraphEventManager getEventManager() {
            return null;
        }

        @Override
        public PrefixMapping getPrefixMapping() {
            return null;
        }

        @Override
        public void add(Triple triple) throws AddDeniedException {

        }

        @Override
        public void delete(Triple triple) throws DeleteDeniedException {

        }

        @Override
        public ExtendedIterator<Triple> find(Triple triple) {
            return null;
        }

        @Override
        public ExtendedIterator<Triple> find(Node node, Node node1, Node node2) {
            return null;
        }

        @Override
        public boolean isIsomorphicWith(Graph graph) {
            return false;
        }

        @Override
        public boolean contains(Node node, Node node1, Node node2) {
            return false;
        }

        @Override
        public boolean contains(Triple triple) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public void remove(Node node, Node node1, Node node2) {

        }

        @Override
        public void close() {

        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isClosed() {
            return false;
        }
    }
}
