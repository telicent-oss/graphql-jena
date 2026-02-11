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
package io.telicent.jena.graphql.benchmarks;

import graphql.ExecutionResult;
import io.telicent.jena.graphql.execution.telicent.graph.TelicentGraphExecutor;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Benchmarks ontology queries executed via the Telicent schema.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class OntologyQueryBenchmark {

    /**
     * Creates a benchmark instance.
     */
    public OntologyQueryBenchmark() {
    }

    /**
     * Shared benchmark state for ontology benchmarks.
     */
    @State(Scope.Benchmark)
    public static class OntologyState {

        /**
         * Number of classes to generate.
         */
        @Param({"10", "100", "1000"})
        public int size;

        private TelicentGraphExecutor executor;
        private String classesQuery;
        private String propertiesQuery;

        /**
         * Creates an ontology state container.
         */
        public OntologyState() {
        }

        /**
         * Builds the dataset and loads queries for the benchmark.
         *
         * @throws IOException If schema loading fails.
         */
        @Setup
        public void setup() throws IOException {
            DatasetGraph dsg = DatasetGraphFactory.create();
            populateOntology(dsg, size);
            executor = new TelicentGraphExecutor(dsg);
            classesQuery = BenchmarkResources.loadResource("/queries/ontology/classes.graphql");
            propertiesQuery = BenchmarkResources.loadResource("/queries/ontology/properties.graphql");
        }
    }

    /**
     * Executes an ontology classes query.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole for consuming results.
     */
    @Benchmark
    public void executeClasses(OntologyState state, Blackhole blackhole) {
        ExecutionResult result = state.executor.execute(state.classesQuery);
        blackhole.consume(result.getData());
        blackhole.consume(result.getErrors());
    }

    /**
     * Executes an ontology properties query.
     *
     * @param state Shared benchmark state.
     * @param blackhole JMH blackhole for consuming results.
     */
    @Benchmark
    public void executeProperties(OntologyState state, Blackhole blackhole) {
        ExecutionResult result = state.executor.execute(state.propertiesQuery);
        blackhole.consume(result.getData());
        blackhole.consume(result.getErrors());
    }

    private static void populateOntology(DatasetGraph dsg, int size) {
        Node graph = NodeFactory.createURI("graph");
        Node styleShape = NodeFactory.createURI("http://example.com/style#shape");
        Node styleLineColor = NodeFactory.createURI("http://example.com/style#line_color");
        for (int i = 0; i < size; i++) {
            Node klass = NodeFactory.createURI("http://example.com/Class" + i);
            dsg.add(graph, klass, RDF.type.asNode(), RDFS.Class.asNode());
            if (i % 2 == 0) {
                dsg.add(graph, klass, RDF.type.asNode(), OWL.Class.asNode());
            }
            dsg.add(graph, klass, RDFS.label.asNode(), NodeFactory.createLiteral("Class " + i));
            dsg.add(graph, klass, styleShape, NodeFactory.createLiteral("CIRCLE"));
            dsg.add(graph, klass, styleLineColor, NodeFactory.createLiteral("#123456"));

            Node objectProperty = NodeFactory.createURI("http://example.com/prop/object/" + i);
            Node datatypeProperty = NodeFactory.createURI("http://example.com/prop/datatype/" + i);
            dsg.add(graph, objectProperty, RDF.type.asNode(), OWL.ObjectProperty.asNode());
            dsg.add(graph, objectProperty, RDFS.domain.asNode(), klass);
            dsg.add(graph, objectProperty, RDFS.range.asNode(), klass);
            dsg.add(graph, objectProperty, RDFS.label.asNode(), NodeFactory.createLiteral("Object " + i));

            dsg.add(graph, datatypeProperty, RDF.type.asNode(), OWL.DatatypeProperty.asNode());
            dsg.add(graph, datatypeProperty, RDFS.domain.asNode(), klass);
            dsg.add(graph, datatypeProperty, RDFS.range.asNode(), XSD.xstring.asNode());
            dsg.add(graph, datatypeProperty, RDFS.label.asNode(), NodeFactory.createLiteral("Datatype " + i));
        }
    }
}
