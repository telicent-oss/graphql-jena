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
package io.telicent.jena.graphql.execution.telicent.graph;

import graphql.ExecutionResult;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sys.JenaSystem;
import org.apache.jena.system.Txn;
import org.apache.jena.tdb2.DatabaseMgr;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Executes queries over a TDB2 dataset, whose prefixes are held in transactional storage, so resolving {@code shortUri}
 * and {@code shortPredicate} needs the request's read transaction
 */
@SuppressWarnings("unchecked")
public class TestTelicentGraphExecutionTdb2 {

    static {
        JenaSystem.init();
    }

    private static final String OBI_WAN_KENOBI = TestTelicentGraphExecution.OBI_WAN_KENOBI;

    private static final String SHORT_URIS_QUERY = """
            query($uri: String!) {
              node(uri: $uri) {
                uri
                shortUri
                properties { shortPredicate }
                outRels { range { shortUri } }
              }
            }""";

    private static TelicentGraphExecutor createStarwarsTdb2() throws IOException {
        DatasetGraph dsg = DatabaseMgr.createDatasetGraph();
        Txn.executeWrite(dsg, () -> RDFDataMgr.read(dsg, TestTelicentGraphExecutionTdb2.class.getResourceAsStream(
                "/data/starwars.ttl"), Lang.TURTLE));
        return new TelicentGraphExecutor(dsg);
    }

    private static Map<String, Object> verifyNode(ExecutionResult result, String when) {
        Assert.assertTrue(result.getErrors().isEmpty(), when + ": unexpected errors " + result.getErrors());
        Map<String, Object> node = (Map<String, Object>) ((Map<String, Object>) result.getData()).get("node");
        Assert.assertNotNull(node, when);
        Assert.assertEquals(node.get("shortUri"), "starwars:person_Obi-WanKenobi", when);
        Assert.assertFalse(((List<Object>) node.get("properties")).isEmpty(), when);
        Assert.assertFalse(((List<Object>) node.get("outRels")).isEmpty(), when);
        return node;
    }

    @Test
    public void givenTdb2Dataset_whenNewQueryTextAsksForShortUris_thenFirstExecutionSucceeds() throws IOException {
        // Given
        TelicentGraphExecutor executor = createStarwarsTdb2();

        // When
        ExecutionResult first = executor.execute(SHORT_URIS_QUERY, Map.of("uri", OBI_WAN_KENOBI));

        // Then
        verifyNode(first, "first execution");
    }

    @Test
    public void givenTdb2Dataset_whenQueryRepeated_thenFirstAndCachedExecutionsAgree() throws IOException {
        // Given
        TelicentGraphExecutor executor = createStarwarsTdb2();

        // When
        ExecutionResult first = executor.execute(SHORT_URIS_QUERY, Map.of("uri", OBI_WAN_KENOBI));
        ExecutionResult cached = executor.execute(SHORT_URIS_QUERY, Map.of("uri", OBI_WAN_KENOBI));

        // Then
        Assert.assertEquals(verifyNode(first, "first execution"), verifyNode(cached, "cached execution"));
    }
}
