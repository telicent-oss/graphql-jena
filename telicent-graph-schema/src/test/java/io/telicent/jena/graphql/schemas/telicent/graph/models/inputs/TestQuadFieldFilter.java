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

import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.apache.jena.graph.NodeFactory.createURI;

public class TestQuadFieldFilter {

    private static final class TestFilter extends QuadFieldFilter {
        private TestFilter(FilterMode mode, Set<Node> values) {
            super(mode, values, Quad::getSubject);
        }
    }

    @Test
    public void testIncludeFilter() {
        Node subject = createURI("http://example.com/a");
        Node other = createURI("http://example.com/b");
        Node graph = Quad.defaultGraphIRI;
        Quad quad1 = new Quad(graph, subject, createURI("http://example.com/p"), other);
        Quad quad2 = new Quad(graph, other, createURI("http://example.com/p"), other);

        DatasetGraph dsg = DatasetGraphFactory.create();
        TestFilter filter = new TestFilter(FilterMode.INCLUDE, Set.of(subject));
        List<Quad> results = filter.filter(Stream.of(quad1, quad2), dsg).toList();

        Assert.assertEquals(results.size(), 1);
        Assert.assertEquals(results.get(0).getSubject(), subject);
    }

    @Test
    public void testExcludeFilter() {
        Node subject = createURI("http://example.com/a");
        Node other = createURI("http://example.com/b");
        Node graph = Quad.defaultGraphIRI;
        Quad quad1 = new Quad(graph, subject, createURI("http://example.com/p"), other);
        Quad quad2 = new Quad(graph, other, createURI("http://example.com/p"), other);

        DatasetGraph dsg = DatasetGraphFactory.create();
        TestFilter filter = new TestFilter(FilterMode.EXCLUDE, Set.of(subject));
        List<Quad> results = filter.filter(Stream.of(quad1, quad2), dsg).toList();

        Assert.assertEquals(results.size(), 1);
        Assert.assertEquals(results.get(0).getSubject(), other);
    }
}
