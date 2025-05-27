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

import org.apache.jena.atlas.lib.tuple.Tuple4;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.sys.JenaSystem;
import org.apache.jena.vocabulary.RDF;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class TestObjectFilter {

    private static final Node OBJECT_1 = NodeFactory.createURI("objectUri");
    private static final Node OBJECT_2 = NodeFactory.createLiteralString("objectLiteral");

    static {
        JenaSystem.init();
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = "mode.*null")
    public void givenNullFilterMode_whenCreatingFilter_thenNPE() {
        // Given, When and Then
        new ObjectFilter(null, List.of(RDF.type.asNode()));
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = "Values.*null")
    public void givenNullValues_whenCreatingFilter_thenNPE() {
        // Given, When and Then
        new ObjectFilter(FilterMode.INCLUDE, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Values.*empty")
    public void givenNoValues_whenCreatingFilter_thenIllegalArgument() {
        // Given, When and Then
        new ObjectFilter(FilterMode.INCLUDE, Collections.emptyList());
    }

    @Test
    public void givenValuesForInclusion_whenCreatingFilter_thenCorrect_andCanSupplyQuadPatterns() {
        // Given
        List<Node> values = List.of(OBJECT_1, OBJECT_2);

        // When
        QuadPatternFilter filter = new ObjectFilter(FilterMode.INCLUDE, values);

        // Then
        Assert.assertEquals(filter.mode(), FilterMode.INCLUDE);
        Assert.assertTrue(filter.values().containsAll(values));

        // And
        List<Tuple4<Node>> quadPatterns = filter.getQuadPatterns(Node.ANY, Node.ANY, Node.ANY, Node.ANY);
        Assert.assertFalse(quadPatterns.isEmpty());
        Assert.assertEquals(quadPatterns.size(), 2);
    }

    @Test
    public void givenValuesForExclusion_whenCreatingFilter_thenCorrect_andNoQuadPatterns() {
        // Given
        List<Node> values = List.of(OBJECT_1, OBJECT_2);

        // When
        QuadPatternFilter filter = new ObjectFilter(FilterMode.EXCLUDE, values);

        // Then
        Assert.assertEquals(filter.mode(), FilterMode.EXCLUDE);
        Assert.assertTrue(filter.values().containsAll(values));

        // And
        List<Tuple4<Node>> quadPatterns = filter.getQuadPatterns(Node.ANY, Node.ANY, Node.ANY, Node.ANY);
        Assert.assertTrue(quadPatterns.isEmpty());
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*MUST be ANY")
    public void givenFilter_whenCreatingQuadPatternsWithConcreteSubject_thenIllegalArgument() {
        // Given
        QuadPatternFilter filter = new ObjectFilter(FilterMode.INCLUDE, List.of(OBJECT_1));

        // When and Then
        Node object = NodeFactory.createURI("object");
        filter.getQuadPatterns(Node.ANY, Node.ANY, Node.ANY, object);
    }
}
