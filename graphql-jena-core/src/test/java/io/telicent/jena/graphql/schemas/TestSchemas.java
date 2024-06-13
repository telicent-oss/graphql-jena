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
package io.telicent.jena.graphql.schemas;

import graphql.schema.idl.TypeDefinitionRegistry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestSchemas {

    @Test
    public void dataset_schema() throws IOException {
        TypeDefinitionRegistry registry = GraphQLJenaSchemas.loadDatasetSchema();
        Assert.assertNotNull(registry);
    }

    @Test
    public void traversal_schema() throws IOException {
        TypeDefinitionRegistry registry = GraphQLJenaSchemas.loadTraversalSchema();
        Assert.assertNotNull(registry);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void test_GraphQLJenaSchemas_load_failure() throws IOException {
        // given
        // when
        // then
        GraphQLJenaSchemas.loadSchema("Fail");
    }

    @Test
    public void test_CoreSchema_ctor() {
        // given
        // when
        CoreSchema schema = new CoreSchema();
        // then
        Assert.assertNotNull(schema);
    }

    @Test
    public void test_DatasetSchema_ctor() {
        // given
        // when
        DatasetSchema schema = new DatasetSchema();
        // then
        Assert.assertNotNull(schema);
    }

    @Test
    public void test_TraversalSchema_ctor() {
        // given
        // when
        TraversalSchema schema = new TraversalSchema();
        // then
        Assert.assertNotNull(schema);
    }
}
