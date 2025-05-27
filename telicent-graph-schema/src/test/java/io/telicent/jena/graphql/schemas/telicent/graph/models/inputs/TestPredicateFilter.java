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
import org.apache.jena.vocabulary.RDFS;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class TestPredicateFilter {

    static {
        JenaSystem.init();
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = "mode.*null")
    public void givenNullFilterMode_whenCreatingFilter_thenNPE() {
        // Given, When and Then
        new PredicateFilter(null, List.of(RDF.type.asNode()));
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = "Values.*null")
    public void givenNullValues_whenCreatingFilter_thenNPE() {
        // Given, When and Then
        new PredicateFilter(FilterMode.INCLUDE, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Values.*empty")
    public void givenNoValues_whenCreatingFilter_thenIllegalArgument() {
        // Given, When and Then
        new PredicateFilter(FilterMode.INCLUDE, Collections.emptyList());
    }

    @Test
    public void givenValuesForInclusion_whenCreatingFilter_thenCorrect_andCanSupplyQuadPatterns() {
        // Given
        List<Node> values = List.of(RDF.type.asNode(), RDFS.comment.asNode(), RDFS.label.asNode());

        // When
        PredicateFilter filter = new PredicateFilter(FilterMode.INCLUDE, values);

        // Then
        Assert.assertEquals(filter.mode(), FilterMode.INCLUDE);
        Assert.assertTrue(filter.values().containsAll(values));

        // And
        List<Tuple4<Node>> quadPatterns = filter.getQuadPatterns(Node.ANY, Node.ANY, Node.ANY, Node.ANY);
        Assert.assertFalse(quadPatterns.isEmpty());
        Assert.assertEquals(quadPatterns.size(), 3);
    }

    @Test
    public void givenValuesForExclusion_whenCreatingFilter_thenCorrect_andNoQuadPatterns() {
        // Given
        List<Node> values = List.of(RDF.type.asNode(), RDFS.comment.asNode(), RDFS.label.asNode());

        // When
        PredicateFilter filter = new PredicateFilter(FilterMode.EXCLUDE, values);

        // Then
        Assert.assertEquals(filter.mode(), FilterMode.EXCLUDE);
        Assert.assertTrue(filter.values().containsAll(values));

        // And
        List<Tuple4<Node>> quadPatterns = filter.getQuadPatterns(Node.ANY, Node.ANY, Node.ANY, Node.ANY);
        Assert.assertTrue(quadPatterns.isEmpty());
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*MUST be ANY")
    public void givenFilter_whenCreatingQuadPatternsWithConcretePredicate_thenIllegalArgument() {
        // Given
        PredicateFilter filter = new PredicateFilter(FilterMode.INCLUDE, List.of(RDF.type.asNode()));

        // When and Then
        Node predicate = NodeFactory.createURI("predicate");
        filter.getQuadPatterns(Node.ANY, Node.ANY, predicate, Node.ANY);;
    }
}
