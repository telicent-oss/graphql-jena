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
import org.apache.jena.atlas.lib.tuple.TupleFactory;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.sys.JenaSystem;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestQuadPatternFilter {

    static {
        JenaSystem.init();
    }

    @DataProvider(name = "patterns")
    private Object[][] patterns() {
        // Concrete node values
        Node s1 = NodeFactory.createURI("https://example.com/s1");
        Node s2 = NodeFactory.createURI("https://example.com/s2");
        Node rdfType = RDF.type.asNode();
        Node rdfsLabel = RDFS.label.asNode();
        Node rdfsComment = RDFS.comment.asNode();
        List<Node> predicates = generatePredicates();

        // Filter patterns
        List<Tuple4<Node>> rdfTypePredicate = List.of(TupleFactory.create4(Node.ANY, Node.ANY, rdfType, Node.ANY));
        List<Tuple4<Node>> rdfsPredicates = List.of(TupleFactory.create4(Node.ANY, Node.ANY, rdfsLabel, Node.ANY),
                                                    TupleFactory.create4(Node.ANY, Node.ANY, rdfsComment, Node.ANY));
        List<Tuple4<Node>> manyPredicates =
                new PredicateFilter(FilterMode.INCLUDE, predicates).getQuadPatterns(Node.ANY, Node.ANY, Node.ANY,
                                                                                    Node.ANY);
        List<Tuple4<Node>> subject1Or2 = List.of(TupleFactory.create4(Node.ANY, s1, Node.ANY, Node.ANY),
                                                 TupleFactory.create4(Node.ANY, s2, Node.ANY, Node.ANY));

        // Expected Combined Patterns
        List<Tuple4<Node>> subjectsPlusRdfType = List.of(TupleFactory.create4(Node.ANY, s1, rdfType, Node.ANY),
                                                         TupleFactory.create4(Node.ANY, s2, rdfType, Node.ANY));
        List<Tuple4<Node>> subjectsPlusRdfsPredicates = List.of(TupleFactory.create4(Node.ANY, s1, rdfsLabel, Node.ANY),
                                                                TupleFactory.create4(Node.ANY, s1, rdfsComment,
                                                                                     Node.ANY),
                                                                TupleFactory.create4(Node.ANY, s2, rdfsLabel, Node.ANY),
                                                                TupleFactory.create4(Node.ANY, s2, rdfsComment,
                                                                                     Node.ANY));
        List<Tuple4<Node>> subjectsPlusManyPredicates = new ArrayList<>();
        populateCombinedPatterns(s1, predicates, subjectsPlusManyPredicates);
        populateCombinedPatterns(s2, predicates, subjectsPlusManyPredicates);

        return new Object[][] {
                { rdfTypePredicate, subject1Or2, subjectsPlusRdfType },
                { subject1Or2, rdfTypePredicate, subjectsPlusRdfType },
                { rdfsPredicates, subject1Or2, subjectsPlusRdfsPredicates },
                { subject1Or2, rdfsPredicates, subjectsPlusRdfsPredicates },
                { manyPredicates, subject1Or2, subjectsPlusManyPredicates }
        };
    }

    private void populateCombinedPatterns(Node subject, List<Node> predicates,
                                          List<Tuple4<Node>> combined) {
        for (Node predicate : predicates) {
            combined.add(TupleFactory.create4(Node.ANY, subject, predicate, Node.ANY));
        }
    }

    private List<Node> generatePredicates() {
        List<Node> predicates = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            predicates.add(NodeFactory.createURI("https://example.com/predicate" + i));
        }
        return predicates;
    }

    @Test(dataProvider = "patterns")
    public void givenPatterns_whenCombining_thenCorrectCombinations(List<Tuple4<Node>> left, List<Tuple4<Node>> right,
                                                                    List<Tuple4<Node>> expected) {
        // Given and When
        List<Tuple4<Node>> combined = QuadPatternFilter.combinePatterns(left, right);

        // Then
        Assert.assertEquals(combined.size(), expected.size());
        Assert.assertTrue(combined.containsAll(expected));
    }

    @DataProvider(name = "singlePattern")
    private Object[][] singlePattern() {
        return new Object[][] {
                { List.of(TupleFactory.create4(NodeFactory.createURI("g1"), Node.ANY, Node.ANY, Node.ANY)) },
                { List.of(TupleFactory.create4(Node.ANY, NodeFactory.createURI("s1"), Node.ANY, Node.ANY)) },
                { List.of(TupleFactory.create4(Node.ANY, Node.ANY, NodeFactory.createURI("p1"), Node.ANY)) },
                { List.of(TupleFactory.create4(Node.ANY, Node.ANY, Node.ANY, NodeFactory.createURI("o1"))) },
                };
    }

    @Test(dataProvider = "singlePattern")
    public void givenSinglePattern_whenCombiningWithItself_thenOk(
            List<Tuple4<Node>> pattern) {
        // Given and When
        List<Tuple4<Node>> combined = QuadPatternFilter.combinePatterns(pattern, pattern);

        // Then
        Assert.assertEquals(combined.size(), pattern.size());
    }

    @Test(dataProvider = "singlePattern", expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*conflicting concrete values.*")
    public void givenSinglePattern_whenCombiningWithConcretePattern_thenConflict(
            List<Tuple4<Node>> pattern) {
        // Given
        List<Tuple4<Node>> conflicting =
                List.of(TupleFactory.create4(NodeFactory.createURI("g"), NodeFactory.createURI("s"),
                                             NodeFactory.createURI("p"), NodeFactory.createURI("o")));

        // When and Then
        QuadPatternFilter.combinePatterns(pattern, conflicting);
    }
}
