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
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class TestTelicentSearchResults {

    @Test
    public void testFromMapParsesValues() {
        Map<String, Object> raw = Map.of(
                "query", "hello",
                "type", "term",
                TelicentGraphSchema.ARGUMENT_LIMIT, "5",
                TelicentGraphSchema.ARGUMENT_OFFSET, 2,
                "maybeMore", "true");

        TelicentSearchResults results = TelicentSearchResults.fromMap(raw);

        Assert.assertEquals(results.getSearchTerm(), "hello");
        Assert.assertEquals(results.getSearchType(), SearchType.TERM);
        Assert.assertEquals(results.getLimit(), 5);
        Assert.assertEquals(results.getOffset(), 2);
        Assert.assertTrue(results.isMaybeMore());
    }

    @Test
    public void testFromMapDefaultsAndSetNodes() {
        Map<String, Object> raw = Map.of("query", "default");
        TelicentSearchResults results = TelicentSearchResults.fromMap(raw);

        Assert.assertEquals(results.getSearchType(), SearchType.QUERY);
        Assert.assertEquals(results.getLimit(), -1);
        Assert.assertEquals(results.getOffset(), -1);
        Assert.assertFalse(results.isMaybeMore());

        results.setNodes(List.of());
        Assert.assertNotNull(results.getNodes());
    }

    @Test
    public void testFromMapInvalidValues() {
        Map<String, Object> raw = Map.of(
                "query", "bad",
                TelicentGraphSchema.ARGUMENT_LIMIT, "not-a-number",
                TelicentGraphSchema.ARGUMENT_OFFSET, new Object(),
                "maybeMore", 123);

        TelicentSearchResults results = TelicentSearchResults.fromMap(raw);

        Assert.assertEquals(results.getLimit(), -1);
        Assert.assertEquals(results.getOffset(), -1);
        Assert.assertFalse(results.isMaybeMore());
    }
}
