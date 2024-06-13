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

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestTelicentExecutionContext {

    private static final String RANDOM_STRING = RandomStringUtils.random(6);

    @Test
    public void test_nullAuthToken() {
        // given
        TelicentExecutionContext context = new TelicentExecutionContext(DatasetGraphFactory.empty(), null);
        // when
        boolean actual = context.hasAuthToken();
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void test_emptyAuthToken() {
        // given
        TelicentExecutionContext context = new TelicentExecutionContext(DatasetGraphFactory.empty(), "");
        // when
        boolean actual = context.hasAuthToken();
        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void test_nonNullAuthToken() {
        // given
        TelicentExecutionContext context = new TelicentExecutionContext(DatasetGraphFactory.empty(), RANDOM_STRING);
        // when
        boolean actual = context.hasAuthToken();
        // then
        Assert.assertTrue(actual);
        Assert.assertEquals(RANDOM_STRING, context.getAuthToken());
    }
}
