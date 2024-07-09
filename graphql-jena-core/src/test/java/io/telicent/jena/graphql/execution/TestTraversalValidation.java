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
package io.telicent.jena.graphql.execution;

import graphql.ExecutionResult;
import io.telicent.jena.graphql.schemas.TraversalSchema;
import io.telicent.jena.graphql.schemas.models.NodeKind;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFParserBuilder;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class TestTraversalValidation extends AbstractExecutionTests {

    private static final String QUERY_BASE = "/queries/traversal/";
    public static final String ADAM_ID = "https://example.org/Adam";
    public static final String BENJAMIN_ID = "https://example.org/Benjamin";
    public static final String CHARLIE_ID = "https://example.org/Charlie";
    public static final String DANIEL_ID = "https://example.org/Daniel";
    public static final String EVE_ID = "https://example.org/Eve";

    private static String loadQuery(String queryResource) {
        return loadQuery(QUERY_BASE, queryResource);
    }
    private static final String SIMPLE_TRAVERSAL = loadQuery("simple-traversal.graphql");

    private static final String NAMES_TRAVERSAL = loadQuery("names.graphql");

    private static final String FRIENDS_TRAVERSAL = loadQuery("friends.graphql");

    private static final String LITERALS_TRAVERSAL = loadQuery("literals.graphql");

    private static final String BLANKS_TRAVERSAL = loadQuery("blanks.graphql");

    private static void verifyTargets(List<Object> edges, List<String> expected) {
        for (Object edge : edges) {
            Assert.assertTrue(edge instanceof Map<?, ?>);
            Map<String, Object> traversalEdge = (Map<String, Object>) edge;
            Assert.assertTrue(traversalEdge.containsKey(TraversalSchema.TARGET_FIELD));
            Map<String, Object> target = (Map<String, Object>) traversalEdge.get(TraversalSchema.TARGET_FIELD);
            Assert.assertTrue(target.containsKey(TraversalSchema.NODE_FIELD));
            Map<String, Object> node = (Map<String, Object>) target.get(TraversalSchema.NODE_FIELD);
            Assert.assertTrue(node.containsKey(TraversalSchema.VALUE_FIELD));
            String value = (String) node.get(TraversalSchema.VALUE_FIELD);
            Assert.assertTrue(expected.contains(value));
        }
    }

    private final TraversalExecutor executor;

    public TestTraversalValidation() throws IOException {
        DatasetGraph dsg = RDFParserBuilder.create()
                                           .lang(Lang.TRIG)
                                           .source(TestTraversalValidation.class.getResourceAsStream(
                                                   "/data/traversals.trig"))
                                           .toDatasetGraph();
        this.executor = new TraversalExecutor(dsg);
    }

    @DataProvider(name = "validQueries")
    private static Object[] validQueryList() {
        return new Object[] {
                SIMPLE_TRAVERSAL,
                NAMES_TRAVERSAL,
                FRIENDS_TRAVERSAL,
                LITERALS_TRAVERSAL,
                BLANKS_TRAVERSAL
        };
    }

    @Test(dataProvider = "validQueries")
    public void traversal_valid_query(String query) throws IOException {
        TraversalExecutor execution = new TraversalExecutor(DatasetGraphFactory.empty());
        verifyValidationSuccess(execution, query);
    }

    @DataProvider(name = "invalidQueries")
    private static Object[] invalidQueryList() {
        return new Object[] {
                "",
                "random",
                "query={}",
                "query={",
                "query=query($subject: NodeFilter, $predicate: NodeFilter, $object: NodeFilter, $graph: NodeFilter) {}"
        };
    }

    @Test(dataProvider = "invalidQueries")
    public void dataset_invalid_queries(String invalidQuery) throws IOException {
        TraversalExecutor execution = new TraversalExecutor(DatasetGraphFactory.empty());

        verifyValidationFailure(execution, invalidQuery);
    }
}
