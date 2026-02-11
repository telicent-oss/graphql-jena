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
package io.telicent.jena.graphql.fetchers.telicent.graph;

import graphql.schema.DataFetchingEnvironment;
import io.telicent.jena.graphql.schemas.telicent.graph.models.RdfsClass;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.apache.jena.graph.NodeFactory.createBlankNode;
import static org.apache.jena.graph.NodeFactory.createLiteralString;
import static org.apache.jena.graph.NodeFactory.createURI;

public class TestRdfsClassInstanceAndTypeFetchers extends AbstractFetcherTests {

    @Test
    public void testInstancesFiltersLiteralSubjects() throws Exception {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node classNode = createURI("http://example.com/Class");
        Node graph = createURI("graph");

        dsg.add(new Quad(graph, createURI("http://example.com/subject"), RDF.type.asNode(), classNode));
        dsg.add(new Quad(graph, createBlankNode("b1"), RDF.type.asNode(), classNode));
        dsg.add(new Quad(graph, createLiteralString("literalSubject"), RDF.type.asNode(), classNode));

        RdfsClass rdfsClass = new RdfsClass(classNode, dsg.prefixes(), false);
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, rdfsClass);

        List<TelicentGraphNode> instances = new RdfsClassInstancesFetcher().get(environment);

        Assert.assertEquals(instances.size(), 2);
    }

    @Test
    public void testTypesFiltersLiteralObjects() throws Exception {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node classNode = createURI("http://example.com/Class");
        Node graph = createURI("graph");

        dsg.add(new Quad(graph, classNode, RDF.type.asNode(), createURI("http://example.com/Type1")));
        dsg.add(new Quad(graph, classNode, RDF.type.asNode(), createBlankNode("b2")));
        dsg.add(new Quad(graph, classNode, RDF.type.asNode(), createLiteralString("literalType")));

        RdfsClass rdfsClass = new RdfsClass(classNode, dsg.prefixes(), false);
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, rdfsClass);

        List<TelicentGraphNode> types = new RdfsClassTypesFetcher().get(environment);

        Assert.assertEquals(types.size(), 2);
    }
}
