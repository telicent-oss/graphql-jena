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
package io.telicent.jena.graphql.execution;

import graphql.ExecutionResult;
import graphql.schema.idl.NaturalEnumValuesProvider;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.telicent.jena.graphql.fetchers.QuadsFetcher;
import io.telicent.jena.graphql.schemas.CoreSchema;
import io.telicent.jena.graphql.schemas.DatasetSchema;
import io.telicent.jena.graphql.schemas.GraphQLJenaSchemas;
import io.telicent.jena.graphql.schemas.models.NodeKind;
import io.telicent.jena.graphql.server.model.GraphQLRequest;
import io.telicent.jena.graphql.utils.NodeFilter;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class TestDatasetExecution extends AbstractExecutionTests {

    private static final String QUERY_BASE = "/queries/dataset/";

    private static String loadQuery(String queryResource) {
        return loadQuery(QUERY_BASE, queryResource);
    }

    public static final String SIMPLE_QUADS_QUERY = loadQuery("simple-quads.graphql");


    public static final String FILTERED_QUADS_QUERY = loadQuery("filtered-quads.graphql");

    public static final String VARIABLE_QUADS_QUERY = loadQuery("variable-quads.graphql");

    public static final String ALIASED_QUADS_QUERY = loadQuery("aliased-quads.graphql");

    public static final String FRAGMENT_QUADS_QUERY = loadQuery("fragment-quads.graphql");

    public static final String DETAILED_QUADS_QUERY = loadQuery("detailed-quads.graphql");

    /**
     * Verifies that the query results include the expected number of quads in the given field
     *
     * @param result        Execution result
     * @param expectedQuads Expected number of quads
     * @param field         Field that should contain the quads
     * @return List of quads for further inspection and verification
     */
    private static List<Object> verifyQuads(ExecutionResult result, int expectedQuads, String field) {
        Map<String, Object> data = result.getData();
        Assert.assertTrue(data.containsKey(field));
        List<Object> quads = (List<Object>) data.get(field);
        if (expectedQuads > 0) {
            Assert.assertFalse(quads.isEmpty());
        } else {
            Assert.assertTrue(quads.isEmpty());
        }
        Assert.assertEquals(quads.size(), expectedQuads);
        return quads;
    }

    @Test
    public void dataset_empty_01() throws IOException {
        DatasetGraph dsg = DatasetGraphFactory.empty();
        DatasetExecutor execution = new DatasetExecutor(dsg);

        ExecutionResult result = verifyExecution(execution, SIMPLE_QUADS_QUERY);

        List<Quad> quads = (List<Quad>) ((Map<String, Object>) result.getData()).get("quads");
        Assert.assertTrue(quads.isEmpty());
    }

    @Test
    public void dataset_01() throws IOException {
        DatasetGraph dsg = DatasetGraphFactory.create();
        dsg.add(Quad.defaultGraphIRI, NodeFactory.createBlankNode(), RDFS.comment.asNode(),
                NodeFactory.createLiteralLang("foo", "en-gb"));
        DatasetExecutor execution = new DatasetExecutor(dsg);

        ExecutionResult result = verifyExecution(execution, SIMPLE_QUADS_QUERY);

        List<Object> quads = verifyQuads(result, 1, DatasetSchema.QUADS_FIELD);

        Map<String, Object> quad = (Map<String, Object>) quads.get(0);
        Assert.assertNotNull(quad);
        verifyNode(quad, CoreSchema.SUBJECT_FIELD, NodeKind.BLANK, null, null, null);
        verifyNode(quad, CoreSchema.PREDICATE_FIELD, NodeKind.URI, RDFS.comment.getURI(), null, null);
        verifyNode(quad, CoreSchema.OBJECT_FIELD, NodeKind.LANGUAGE_LITERAL, "foo", null, RDF.langString.getURI());
    }

    @Test
    public void dataset_02() throws IOException {
        DatasetGraph dsg = DatasetGraphFactory.create();
        generateDummyQuads(dsg, 100);
        DatasetExecutor execution = new DatasetExecutor(dsg);

        ExecutionResult result = verifyExecution(execution, FILTERED_QUADS_QUERY);

        List<Object> quads = verifyQuads(result, 1, DatasetSchema.QUADS_FIELD);
        verifyNode((Map<String, Object>) quads.get(0), CoreSchema.SUBJECT_FIELD, NodeKind.URI,
                   "https://example.org/13", null, null);
    }

    @Test
    public void dataset_03() throws IOException {
        DatasetGraph dsg = DatasetGraphFactory.create();
        generateDummyQuads(dsg, 100);
        DatasetExecutor execution = new DatasetExecutor(dsg);

        Map<String, Object> variables = Map.of(CoreSchema.PREDICATE_FIELD, NodeFilter.make(RDFS.comment.asNode()));
        ExecutionResult result = verifyExecution(execution, VARIABLE_QUADS_QUERY, variables);
        List<Object> quads = verifyQuads(result, 100, DatasetSchema.QUADS_FIELD);
        quads.stream().map(q -> (Map<String, Object>) q).forEach(q -> {
            verifyNode(q, CoreSchema.PREDICATE_FIELD, NodeKind.URI, RDFS.comment.getURI(), null, null);
            verifyNode(q, CoreSchema.OBJECT_FIELD, NodeKind.LANGUAGE_LITERAL, "foo", "en-GB",
                       RDF.langString.getURI());
        });

        variables = Map.of(CoreSchema.PREDICATE_FIELD, NodeFilter.make(RDF.type.asNode()));
        result = verifyExecution(execution, VARIABLE_QUADS_QUERY, variables);
        verifyQuads(result, 0, DatasetSchema.QUADS_FIELD);

        variables =
                Map.of(CoreSchema.SUBJECT_FIELD, NodeFilter.make(NodeFactory.createURI("https://example.org/57")));
        result = verifyExecution(execution, VARIABLE_QUADS_QUERY, variables);
        verifyQuads(result, 1, DatasetSchema.QUADS_FIELD);
    }

    @Test
    public void dataset_04() throws IOException {
        DatasetGraph dsg = DatasetGraphFactory.create();
        // Declare a type and 10 instances thereof
        Node type = NodeFactory.createURI("https://example.org/my-type");
        dsg.add(Quad.defaultGraphIRI, type, RDF.type.asNode(), RDFS.Class.asNode());
        for (int i = 1; i <= 10; i++) {
            dsg.add(Quad.defaultGraphIRI, NodeFactory.createURI("https://example.org/instances/" + i),
                    RDF.type.asNode(), type);
        }
        DatasetExecutor execution = new DatasetExecutor(dsg);

        ExecutionResult result = verifyExecution(execution, ALIASED_QUADS_QUERY);

        String[] expectedFields = new String[] { CoreSchema.SUBJECT_FIELD };
        String[] unexpectedFields = new String[] {
                CoreSchema.PREDICATE_FIELD,
                CoreSchema.OBJECT_FIELD,
                CoreSchema.GRAPH_FIELD
        };
        List<Object> types = verifyQuads(result, 1, "types");
        types.stream()
             .map(t -> (Map<String, Object>) t)
             .forEach(t -> verifyFields(t, expectedFields, unexpectedFields));
        // NB: Instances returns 11 because the type definition is treated as an instance of a type (which it is of
        // rdfs:Class)!
        List<Object> instances = verifyQuads(result, 11, "instances");
        instances.stream()
                 .map(i -> (Map<String, Object>) i)
                 .forEach(i -> verifyFields(i, expectedFields, unexpectedFields));
    }

    @Test
    public void dataset_05() throws IOException {
        DatasetGraph dsg = DatasetGraphFactory.create();
        generateDummyQuads(dsg, 100);
        DatasetExecutor execution = new DatasetExecutor(dsg);

        ExecutionResult result = verifyExecution(execution, FRAGMENT_QUADS_QUERY);

        List<Object> quads = verifyQuads(result, 100, DatasetSchema.QUADS_FIELD);
        quads.stream().map(q -> (Map<String, Object>) q).forEach(q -> {
            verifyNode(q, CoreSchema.SUBJECT_FIELD, NodeKind.URI, null, null, null);
            verifyNode(q, CoreSchema.PREDICATE_FIELD, NodeKind.URI, RDFS.comment.getURI(), null, null);
            verifyNode(q, CoreSchema.OBJECT_FIELD, NodeKind.LANGUAGE_LITERAL, "foo", null, RDF.langString.getURI());
        });
    }

    @Test
    public void dataset_06_graph_field() throws IOException {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graph = NodeFactory.createURI("https://example.org/graph");
        Node subject = NodeFactory.createURI("https://example.org/subject");
        dsg.add(graph, subject, RDFS.comment.asNode(), NodeFactory.createLiteralLang("bar", "en-gb"));
        DatasetExecutor execution = new DatasetExecutor(dsg);

        ExecutionResult result = verifyExecution(execution, DETAILED_QUADS_QUERY);

        List<Object> quads = verifyQuads(result, 1, DatasetSchema.QUADS_FIELD);
        Map<String, Object> quad = (Map<String, Object>) quads.get(0);
        verifyNode(quad, CoreSchema.GRAPH_FIELD, NodeKind.URI, graph.getURI(), null, null);
        verifyNode(quad, CoreSchema.SUBJECT_FIELD, NodeKind.URI, subject.getURI(), null, null);
    }

    @Test
    public void casting_01() throws IOException {
        DatasetGraph dsg = DatasetGraphFactory.empty();
        DatasetExecutor executor = new DatasetExecutor(dsg);

        Assert.assertTrue(AbstractDatasetExecutor.class.isAssignableFrom(executor.getClass()));
    }

    @Test
    public void test_NonCoreSchemaExecutor() throws IOException {
        DatasetGraph dsg = DatasetGraphFactory.empty();
        NonCoreSchemaDatasetExecutor executor = new NonCoreSchemaDatasetExecutor(dsg);

        Assert.assertTrue(AbstractDatasetExecutor.class.isAssignableFrom(executor.getClass()));
    }

    @Test
    public void test_executeGraphQLResult() throws IOException {
        // given
        DatasetGraph dsg = DatasetGraphFactory.create();
        generateDummyQuads(dsg, 10);
        DatasetExecutor execution = new DatasetExecutor(dsg);

        GraphQLRequest request = new GraphQLRequest();
        request.setQuery(SIMPLE_QUADS_QUERY);

        // when
        ExecutionResult result = verifyExecution(execution, request);

        // then
        List<Object> quads = verifyQuads(result, 10, DatasetSchema.QUADS_FIELD);
        verifyNodes(quads.stream().map(q -> (Map<String, Object>) q).toList(), CoreSchema.SUBJECT_FIELD,
                    getDummyExpected(10));
    }

    @Test
    public void test_executeGraphQLResultWithDSG() throws IOException {
        // given
        DatasetGraph dsg = DatasetGraphFactory.create();
        generateDummyQuads(dsg, 10);
        DatasetExecutor execution = new DatasetExecutor(dsg);
        GraphQLRequest request = new GraphQLRequest();
        request.setQuery(SIMPLE_QUADS_QUERY);

        DatasetGraph alternateDSG = DatasetGraphFactory.create();
        generateDummyQuads(alternateDSG, 5);

        // when
        ExecutionResult result = execution.execute(alternateDSG, request);

        // then
        Assert.assertTrue(result.getErrors().isEmpty());
        Assert.assertTrue(result.isDataPresent());
        List<Object> quads = verifyQuads(result, 5, DatasetSchema.QUADS_FIELD);
        verifyNodes(quads.stream().map(q -> (Map<String, Object>) q).toList(), CoreSchema.SUBJECT_FIELD,
                    getDummyExpected(5));
    }

    private static class NonCoreSchemaDatasetExecutor extends AbstractDatasetExecutor {

        public NonCoreSchemaDatasetExecutor(DatasetGraph dsg) throws IOException {
            super(dsg);
        }

        @Override
        protected TypeDefinitionRegistry loadRawSchema() throws IOException {
            return GraphQLJenaSchemas.loadDatasetSchema();
        }

        @Override
        protected RuntimeWiring.Builder buildRuntimeWiring() {
            NaturalEnumValuesProvider<NodeKind> nodeKinds = new NaturalEnumValuesProvider<>(NodeKind.class);
            return RuntimeWiring.newRuntimeWiring()
                                .type(DatasetSchema.QUADS_QUERY_TYPE,
                                      t -> t.dataFetcher(DatasetSchema.QUADS_FIELD, new QuadsFetcher())
                                            .enumValues(nodeKinds));
        }

        @Override
        protected boolean extendsCoreSchema() {
            return false;
        }
    }

}
