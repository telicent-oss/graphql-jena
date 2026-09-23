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

import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.apache.jena.graph.NodeFactory.createLiteralString;
import static org.apache.jena.graph.NodeFactory.createURI;

/**
 * Characterises duplication of triples that are asserted in more than one named graph.
 * <p>
 * The fetchers select quads with {@link Node#ANY} in the graph position and never deduplicate:
 * </p>
 * <ul>
 *     <li>{@code AbstractNodeTypesFetcher.loadNodeTypes} - {@code dsg.stream(Node.ANY, subject, RDF.type, Node.ANY)}</li>
 *     <li>{@code AbstractLiteralsFetcher.loadLiteralProperties} - {@code dsg.stream(Node.ANY, subject, Node.ANY, Node.ANY)}</li>
 *     <li>{@code AbstractRelationshipsFetcher.stream} - {@code dsg.stream(Node.ANY, subject, Node.ANY, Node.ANY)}</li>
 * </ul>
 * <p>
 * RDF is set based, so a triple asserted in N named graphs is still a single triple in a union view, but these return
 * it N times.  In deployment the named graphs correspond to distributions, so an entity described identically by
 * several distributions has each of its types, properties and relationships reported once per distribution and
 * clients render apparent duplicates.
 * </p>
 * <p>
 * The assertions below deliberately pin the <strong>current</strong> behaviour.  When the fetchers deduplicate, each
 * count marked DEFECT becomes 1 and these assertions must be updated.
 * </p>
 */
public class TestFetcherNamedGraphDuplication {

    private static final Node SUBJECT = createURI("https://example.org/entity#shared");
    private static final Node TYPE = createURI("https://example.org/ontology#Thing");
    private static final Node LABEL_PREDICATE = createURI("http://www.w3.org/2000/01/rdf-schema#label");
    private static final Node LABEL = createLiteralString("Shared Entity");
    private static final Node TARGET = createURI("https://example.org/entity#target");
    private static final Node RELATIONSHIP = createURI("https://example.org/ontology#relatedTo");

    /**
     * The named graphs an identical triple is asserted in, standing in for the distributions that each supplied the
     * same statement
     */
    private static final List<String> DISTRIBUTION_GRAPHS = List.of("https://example.org/distribution/1",
                                                                    "https://example.org/distribution/2",
                                                                    "https://example.org/distribution/3");

    /**
     * Builds a dataset in which one subject is described identically in every graph in
     * {@link #DISTRIBUTION_GRAPHS}, i.e. the graph holds exactly three distinct triples however many graphs assert
     * them
     *
     * @return Dataset graph
     */
    private static DatasetGraph datasetWithSubjectRepeatedAcrossGraphs() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        for (String graph : DISTRIBUTION_GRAPHS) {
            Node g = createURI(graph);
            dsg.add(new Quad(g, SUBJECT, RDF.type.asNode(), TYPE));
            dsg.add(new Quad(g, SUBJECT, LABEL_PREDICATE, LABEL));
            dsg.add(new Quad(g, SUBJECT, RELATIONSHIP, TARGET));
        }
        return dsg;
    }

    private static TelicentGraphNode subjectNode() {
        return new TelicentGraphNode(SUBJECT, null);
    }

    @Test
    public void givenOneTypeAssertedInSeveralGraphs_whenLoadingNodeTypes_thenItIsReturnedOncePerGraph() {
        // Given
        DatasetGraph dsg = datasetWithSubjectRepeatedAcrossGraphs();
        NodeTypesFetcher fetcher = new NodeTypesFetcher();

        // When
        List<Node> types = fetcher.loadNodeTypes(dsg, subjectNode());

        // Then
        // CORE-1552 BUG - one distinct type is asserted, but it is returned once per named graph asserting it
        Assert.assertEquals(types.size(), DISTRIBUTION_GRAPHS.size(),
                            "Expected one entry per named graph; a size of 1 means the fetcher now deduplicates and this test should be updated - see this class' Javadoc");
        Assert.assertEquals(types.stream().distinct().count(), 1L,
                            "Every entry should be the same type");
        Assert.assertEquals(types.getFirst(), TYPE);
    }

    @Test
    public void givenOneLiteralAssertedInSeveralGraphs_whenLoadingLiteralProperties_thenItIsReturnedOncePerGraph() {
        // Given
        DatasetGraph dsg = datasetWithSubjectRepeatedAcrossGraphs();
        LiteralPropertiesFetcher fetcher = new LiteralPropertiesFetcher();

        // When
        List<Quad> properties = fetcher.loadLiteralProperties(dsg, subjectNode());

        // Then
        // CORE-1552 BUG - one distinct literal property is asserted, but it is returned once per named graph asserting it
        Assert.assertEquals(properties.size(), DISTRIBUTION_GRAPHS.size(),
                            "Expected one entry per named graph; a size of 1 means the fetcher now deduplicates and this test should be updated - see this class' Javadoc");
        Assert.assertEquals(properties.stream().map(Quad::getObject).distinct().count(), 1L,
                            "Every entry should be the same literal value");
        Assert.assertEquals(properties.stream().map(Quad::asTriple).distinct().count(), 1L,
                            "Every entry should be the same triple, differing only by graph");
    }

    @Test
    public void givenOneRelationshipAssertedInSeveralGraphs_whenGeneratingRelationships_thenItIsReturnedOncePerGraph() {
        // Given
        DatasetGraph dsg = datasetWithSubjectRepeatedAcrossGraphs();
        RelationshipsFetcher fetcher = new RelationshipsFetcher(EdgeDirection.OUT);

        // When
        List<Quad> relationships = fetcher.generateRelationships(dsg, subjectNode(), List.of());

        // Then
        // CORE-1552 BUG - two distinct outbound relationships are asserted (rdf:type and relatedTo, the literal is excluded),
        //          but each is returned once per named graph asserting it
        Assert.assertEquals(relationships.size(), DISTRIBUTION_GRAPHS.size() * 2,
                            "Expected each relationship once per named graph; a smaller size means the fetcher now deduplicates and this test should be updated - see this class' Javadoc");
        Assert.assertEquals(relationships.stream().map(Quad::asTriple).distinct().count(), 2L,
                            "Only two distinct relationship triples are asserted");
    }
}
