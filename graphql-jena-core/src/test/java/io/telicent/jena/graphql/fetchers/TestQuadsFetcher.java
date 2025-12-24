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
package io.telicent.jena.graphql.fetchers;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentImpl;
import graphql.schema.DataFetchingFieldSelectionSet;
import graphql.schema.SelectedField;
import io.telicent.jena.graphql.schemas.CoreSchema;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;

public class TestQuadsFetcher {

    @Test
    public void test_1() {
        // given
        TestDataFetchingFieldSelectionSet selectionSet = new TestDataFetchingFieldSelectionSet();
        DatasetGraph dsg = DatasetGraphFactory.empty();
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .selectionSet(selectionSet)
                .localContext(dsg)
                .build();
        QuadsFetcher quadsFetcher = new QuadsFetcher();
        // when
        List<Object> result = quadsFetcher.get(environment);
        // then
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void test_2() {
        // given
        TestDataFetchingFieldSelectionSet selectionSet = new TestDataFetchingFieldSelectionSet();
        DatasetGraph dsg = DatasetGraphFactory.empty();
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .selectionSet(selectionSet)
                .localContext(dsg)
                .build();
        QuadsFetcher quadsFetcher = new QuadsFetcher();
        // when
        List<Object> result = quadsFetcher.get(environment);
        // then
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    @DataProvider(name = "mapFieldSelection")
    private static Object[] mapFieldSelection() {
        return new Object[] {
                Map.of("subject/**", "anything"),
                Map.of("predicate/**", "something"),
                Map.of("object/**", "ignore"),
                Map.of("graph/**", "relevance"),
                Map.of("subject/**", "", "object/**", ""),
                Map.of("subject/**", "", "predicate/**", ""),
                Map.of("predicate/**", "", "object/**", ""),
                Map.of("subject/**", "", "object/**", "","graph/**", ""),
                Map.of("predicate/**", "", "object/**", "","graph/**", ""),
                Map.of("subject/**", "","graph/**", ""),
                Map.of("object/**", "","graph/**", ""),
                Map.of("predicate/**", "", "graph/**", ""),
                Map.of("subject/**", "", "predicate/**", "", "object/**", ""),
                Map.of("subject/**", "", "predicate/**", "", "object/**", "", "graph/**", ""),
                emptyMap()
        };
    }

    @Test(dataProvider = "mapFieldSelection")
    public void test_get_includeOptions(Map<String,String> map) {
        TestDataFetchingFieldSelectionSet selectionSet = new TestDataFetchingFieldSelectionSet(map);
        DatasetGraph dsg = DatasetGraphFactory.createGeneral();
        dsg.add(Quad.ANY);
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .selectionSet(selectionSet)
                .localContext(dsg)
                .build();
        QuadsFetcher quadsFetcher = new QuadsFetcher();
        // when
        List<Object> result = quadsFetcher.get(environment);
        // then
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void test_graphFieldUsesGraphNode() {
        TestDataFetchingFieldSelectionSet selectionSet =
                new TestDataFetchingFieldSelectionSet(Map.of("graph/**", ""));
        DatasetGraph dsg = DatasetGraphFactory.createGeneral();
        Node graph = NodeFactory.createURI("https://example.org/graph");
        Node subject = NodeFactory.createURI("https://example.org/subject");
        Node predicate = NodeFactory.createURI("https://example.org/predicate");
        Node object = NodeFactory.createURI("https://example.org/object");
        dsg.add(new Quad(graph, subject, predicate, object));

        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .selectionSet(selectionSet)
                .localContext(dsg)
                .build();
        QuadsFetcher quadsFetcher = new QuadsFetcher();

        List<Object> result = quadsFetcher.get(environment);

        Assert.assertEquals(result.size(), 1);
        @SuppressWarnings("unchecked")
        Map<String, Object> quadMap = (Map<String, Object>) result.get(0);
        Assert.assertEquals(quadMap.get(CoreSchema.GRAPH_FIELD), graph);
        Assert.assertNotEquals(quadMap.get(CoreSchema.GRAPH_FIELD), object);
    }

    private static class TestDataFetchingFieldSelectionSet
            implements DataFetchingFieldSelectionSet {

        Map<String,String> underlyingMap = new HashMap<>();
        public TestDataFetchingFieldSelectionSet() {
        }

        public TestDataFetchingFieldSelectionSet(Map<String,String> map) {
            underlyingMap = map;
        }

        @Override
        public boolean contains(String fieldGlobPattern) {
            return underlyingMap.containsKey(fieldGlobPattern);
        }

        @Override
        public boolean containsAnyOf(String fieldGlobPattern, String... fieldGlobPatterns) {
            return false;
        }

        @Override
        public boolean containsAllOf(String fieldGlobPattern, String... fieldGlobPatterns) {
            return false;
        }

        @Override
        public List<SelectedField> getFields() {
            return null;
        }

        @Override
        public List<SelectedField> getImmediateFields() {
            return null;
        }

        @Override
        public List<SelectedField> getFields(String fieldGlobPattern, String... fieldGlobPatterns) {
            return null;
        }

        @Override
        public Map<String, List<SelectedField>> getFieldsGroupedByResultKey() {
            return null;
        }

        @Override
        public Map<String, List<SelectedField>> getFieldsGroupedByResultKey(String fieldGlobPattern,
                                                                            String... fieldGlobPatterns) {
            return null;
        }
    }
}
