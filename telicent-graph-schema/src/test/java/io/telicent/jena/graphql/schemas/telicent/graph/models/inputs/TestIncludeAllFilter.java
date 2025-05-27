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
package io.telicent.jena.graphql.schemas.telicent.graph.models.inputs;

import org.apache.jena.graph.NodeFactory;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.stream.Stream;

public class TestIncludeAllFilter {

    @Test
    public void givenIncludeAllFilter_whenInspectingConfig_thenAsExpected() {
        // Given
        Filter filter = IncludeAllFilter.INSTANCE;

        // When and Then
        Assert.assertEquals(filter.mode(), FilterMode.INCLUDE);
        Assert.assertTrue(filter.values().isEmpty());
    }

    @Test
    public void givenIncludeAllFilter_whenApplying_thenNoOp() {
        // Given
        Filter filter = IncludeAllFilter.INSTANCE;
        Stream<Quad> stream = Stream.of(
                new Quad(Quad.defaultGraphIRI, NodeFactory.createURI("subject"), NodeFactory.createURI("predicate"),
                         NodeFactory.createLiteralString("object")));

        // When
        Stream<Quad> filtered = filter.filter(stream, DatasetGraphFactory.empty());

        // Then
        Assert.assertSame(stream, filtered);
    }
}
