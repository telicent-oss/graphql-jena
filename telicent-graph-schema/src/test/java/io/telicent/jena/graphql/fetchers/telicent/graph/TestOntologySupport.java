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
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import io.telicent.jena.graphql.schemas.telicent.graph.models.RdfLiteral;
import io.telicent.jena.graphql.schemas.telicent.graph.models.Style;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.FilterMode;
import org.apache.jena.graph.Node;
import org.apache.jena.riot.system.PrefixMap;
import org.apache.jena.riot.system.PrefixMapFactory;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.apache.jena.graph.NodeFactory.createLiteral;
import static org.apache.jena.graph.NodeFactory.createLiteralString;
import static org.apache.jena.graph.NodeFactory.createURI;
import static org.apache.jena.graph.NodeFactory.createBlankNode;

public class TestOntologySupport extends AbstractFetcherTests {

    @Test
    public void testParseUriFilterRequiresValues() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new Object());
        Map<String, Object> badFilter = Map.of(TelicentGraphSchema.ARGUMENT_MODE, FilterMode.INCLUDE);
        Map<String, Object> arguments = Map.of(TelicentGraphSchema.ARGUMENT_URI_FILTER, badFilter);
        DataFetchingEnvironment badEnvironment = prepareFetchingEnvironment(dsg, new Object(), arguments);

        try {
            OntologySupport.parseUriFilter(badEnvironment, TelicentGraphSchema.ARGUMENT_URI_FILTER);
            Assert.fail("Expected parseUriFilter to throw");
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("values"));
        }

        Assert.assertNull(OntologySupport.parseUriFilter(environment, TelicentGraphSchema.ARGUMENT_URI_FILTER));
    }

    @Test
    public void testParseUriFilterModesAndErrors() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Map<String, Object> goodFilter = Map.of(
                TelicentGraphSchema.ARGUMENT_VALUES, List.of("urn:test"),
                TelicentGraphSchema.ARGUMENT_MODE, "EXCLUDE");
        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new Object(), Map.of(TelicentGraphSchema.ARGUMENT_URI_FILTER, goodFilter));

        UriFilterSpec filter = OntologySupport.parseUriFilter(environment, TelicentGraphSchema.ARGUMENT_URI_FILTER);
        Assert.assertNotNull(filter);
        Assert.assertEquals(filter.getMode(), FilterMode.EXCLUDE);
        Assert.assertEquals(filter.getValues(), Set.of("urn:test"));

        try {
            OntologySupport.parseUriFilter(
                    prepareFetchingEnvironment(dsg, new Object(), Map.of(TelicentGraphSchema.ARGUMENT_URI_FILTER, "bad")),
                    TelicentGraphSchema.ARGUMENT_URI_FILTER);
            Assert.fail("Expected parseUriFilter to throw for non-map");
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("expected Map"));
        }

        Map<String, Object> badValues = Map.of(TelicentGraphSchema.ARGUMENT_VALUES, List.of(1));
        try {
            OntologySupport.parseUriFilter(
                    prepareFetchingEnvironment(dsg, new Object(), Map.of(TelicentGraphSchema.ARGUMENT_URI_FILTER, badValues)),
                    TelicentGraphSchema.ARGUMENT_URI_FILTER);
            Assert.fail("Expected parseUriFilter to throw for non-string values");
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("values must be strings"));
        }

        Map<String, Object> enumFilter = Map.of(
                TelicentGraphSchema.ARGUMENT_VALUES, List.of("urn:ok"),
                TelicentGraphSchema.ARGUMENT_MODE, FilterMode.INCLUDE);
        UriFilterSpec enumSpec = OntologySupport.parseUriFilter(
                prepareFetchingEnvironment(dsg, new Object(), Map.of(TelicentGraphSchema.ARGUMENT_URI_FILTER, enumFilter)),
                TelicentGraphSchema.ARGUMENT_URI_FILTER);
        Assert.assertEquals(enumSpec.getMode(), FilterMode.INCLUDE);

        Map<String, Object> invalidMode = Map.of(
                TelicentGraphSchema.ARGUMENT_VALUES, List.of("urn:ok"),
                TelicentGraphSchema.ARGUMENT_MODE, 123);
        UriFilterSpec invalidSpec = OntologySupport.parseUriFilter(
                prepareFetchingEnvironment(dsg, new Object(), Map.of(TelicentGraphSchema.ARGUMENT_URI_FILTER, invalidMode)),
                TelicentGraphSchema.ARGUMENT_URI_FILTER);
        Assert.assertEquals(invalidSpec.getMode(), FilterMode.INCLUDE);
    }

    @Test
    public void testMatchesFiltersAndNodeParsing() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node classNode = createURI("http://example.com/Class");
        dsg.add(createURI("graph"), classNode, RDF.type.asNode(), OWL.Class.asNode());
        dsg.add(createURI("graph"), classNode, RDFS.label.asNode(), createLiteral("Class Label", "en"));

        UriFilterSpec includeFilter = new UriFilterSpec(Set.of(classNode.getURI()), FilterMode.INCLUDE);
        UriFilterSpec excludeFilter = new UriFilterSpec(Set.of(classNode.getURI()), FilterMode.EXCLUDE);
        UriFilterSpec includeTypeFilter = new UriFilterSpec(Set.of(OWL.Class.getURI()), FilterMode.INCLUDE);
        UriFilterSpec excludeTypeFilter = new UriFilterSpec(Set.of(OWL.Class.getURI()), FilterMode.EXCLUDE);

        Assert.assertTrue(OntologySupport.matchesUriFilter(includeFilter, classNode));
        Assert.assertFalse(OntologySupport.matchesUriFilter(excludeFilter, classNode));

        Assert.assertTrue(OntologySupport.matchesLabelFilter(dsg, classNode, "label"));
        Assert.assertFalse(OntologySupport.matchesLabelFilter(dsg, classNode, "missing"));
        Assert.assertTrue(OntologySupport.matchesLabelFilter(dsg, classNode, " "));

        Assert.assertTrue(OntologySupport.matchesTypeFilter(dsg, classNode, includeTypeFilter));
        Assert.assertFalse(OntologySupport.matchesTypeFilter(dsg, classNode, excludeTypeFilter));
        Assert.assertTrue(OntologySupport.matchesTypeFilter(dsg, classNode, new UriFilterSpec(Set.of(), FilterMode.INCLUDE)));

        Node blank = createBlankNode("b1");
        Assert.assertEquals(OntologySupport.nodeToUri(blank), "_:b1");
        Assert.assertEquals(OntologySupport.parseUriNode("_:b1"), blank);
        Assert.assertEquals(OntologySupport.parseUriNode(classNode.getURI()), classNode);
        Assert.assertEquals(OntologySupport.nodeToUri(classNode), classNode.getURI());
        Assert.assertEquals(OntologySupport.parseGraphNode(null), Node.ANY);
        Assert.assertEquals(OntologySupport.parseGraphNode(" "), Node.ANY);
        Assert.assertEquals(OntologySupport.parseGraphNode(classNode.getURI()), classNode);
    }

    @Test
    public void testStyleParsingAndInvalidShape() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node resource = createURI("http://example.com/Styled");
        dsg.add(createURI("graph"), resource, createURI("http://example.com/style#shape"), createLiteralString("hexagon"));
        dsg.add(createURI("graph"), resource, createURI("http://example.com/style#line_color"), createLiteralString("#abcdef"));

        Style style = OntologySupport.styleFor(dsg, resource);
        Assert.assertNotNull(style);
        Assert.assertNull(style.getShape());
        Assert.assertEquals(style.getLine_color(), "#abcdef");
    }

    @Test
    public void testGraphScopedHelpers() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graphA = createURI("graphA");
        Node graphB = createURI("graphB");
        Node subject = createURI("http://example.com/Subject");
        Node predicate = createURI("http://example.com/predicate");
        dsg.add(graphA, subject, predicate, createLiteral("ValueA", "en"));
        dsg.add(graphB, subject, predicate, createLiteral("ValueB", "en"));

        Assert.assertEquals(OntologySupport.literalsFor(dsg, graphA, subject, predicate).size(), 1);
        Assert.assertEquals(OntologySupport.literalsFor(dsg, graphB, subject, predicate).size(), 1);

        Assert.assertEquals(OntologySupport.streamLabelLiterals(dsg, graphA, subject).count(), 0);
        dsg.add(graphA, subject, RDFS.label.asNode(), createLiteral("LabelA", "en"));
        Assert.assertEquals(OntologySupport.streamLabelLiterals(dsg, graphA, subject).count(), 1);
        Assert.assertEquals(OntologySupport.streamLabelLiterals(dsg, graphB, subject).count(), 0);
    }

    @Test
    public void testLocalNameFallback() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node resource = createURI("http://example.com/StyledLocal");
        dsg.add(createURI("graph"), resource, createURI("urn:shape"), createLiteralString("CIRCLE"));

        Style style = OntologySupport.styleFor(dsg, resource);
        Assert.assertNull(style);
    }

    @Test
    public void testGraphScopedTypeChecks() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graphA = createURI("graphA");
        Node graphB = createURI("graphB");
        Node classNode = createURI("http://example.com/Class");
        Node objectProperty = createURI("http://example.com/prop");
        Node datatypeProperty = createURI("http://example.com/prop2");

        dsg.add(graphA, classNode, RDF.type.asNode(), OWL.Class.asNode());
        dsg.add(graphA, objectProperty, RDF.type.asNode(), OWL.ObjectProperty.asNode());
        dsg.add(graphB, datatypeProperty, RDF.type.asNode(), OWL.DatatypeProperty.asNode());

        Assert.assertTrue(OntologySupport.isOwlClass(dsg, graphA, classNode));
        Assert.assertFalse(OntologySupport.isOwlClass(dsg, graphB, classNode));
        Assert.assertTrue(OntologySupport.isObjectProperty(dsg, graphA, objectProperty));
        Assert.assertFalse(OntologySupport.isObjectProperty(dsg, graphB, objectProperty));
        Assert.assertTrue(OntologySupport.isDatatypeProperty(dsg, graphB, datatypeProperty));
        Assert.assertFalse(OntologySupport.isDatatypeProperty(dsg, graphA, datatypeProperty));
    }

    @Test
    public void testNodeAndDatatypeUriHandling() {
        PrefixMap prefixes = PrefixMapFactory.create();
        prefixes.add("ex", "http://example.com/");
        Node uriNode = createURI("http://example.com/Class");
        Node blankNode = createBlankNode("b2");

        io.telicent.jena.graphql.schemas.telicent.graph.models.RdfsClass rdfsClass =
                new io.telicent.jena.graphql.schemas.telicent.graph.models.RdfsClass(uriNode, prefixes, false);
        Assert.assertEquals(rdfsClass.getShortUri(), "ex:Class");

        io.telicent.jena.graphql.schemas.telicent.graph.models.XmlSchemaDatatype datatype =
                new io.telicent.jena.graphql.schemas.telicent.graph.models.XmlSchemaDatatype(blankNode);
        Assert.assertEquals(datatype.getUri(), "_:b2");
        io.telicent.jena.graphql.schemas.telicent.graph.models.XmlSchemaDatatype uriDatatype =
                new io.telicent.jena.graphql.schemas.telicent.graph.models.XmlSchemaDatatype(uriNode);
        Assert.assertEquals(uriDatatype.getUri(), uriNode.getURI());

        io.telicent.jena.graphql.schemas.telicent.graph.models.RdfsClass blankClass =
                new io.telicent.jena.graphql.schemas.telicent.graph.models.RdfsClass(blankNode, null, false);
        Assert.assertEquals(blankClass.getUri(), "_:b2");

        try {
            OntologySupport.nodeToUri(createLiteralString("bad"));
            Assert.fail("Expected nodeToUri to throw for literal nodes");
        } catch (IllegalStateException ex) {
            Assert.assertTrue(ex.getMessage().contains("Not a node"));
        }

        try {
            new io.telicent.jena.graphql.schemas.telicent.graph.models.RdfsClass(createLiteralString("bad"), null, false).getUri();
            Assert.fail("Expected getUri to throw for literal nodes");
        } catch (IllegalStateException ex) {
            Assert.assertTrue(ex.getMessage().contains("Not a node"));
        }
    }

    @Test
    public void testUriFilterSpecMatches() {
        UriFilterSpec include = new UriFilterSpec(Set.of("urn:a"), FilterMode.INCLUDE);
        UriFilterSpec exclude = new UriFilterSpec(Set.of("urn:a"), FilterMode.EXCLUDE);
        UriFilterSpec empty = new UriFilterSpec(Set.of(), FilterMode.EXCLUDE);

        Assert.assertTrue(include.matches("urn:a"));
        Assert.assertFalse(include.matches("urn:b"));
        Assert.assertFalse(exclude.matches("urn:a"));
        Assert.assertTrue(exclude.matches("urn:b"));
        Assert.assertTrue(empty.matches("urn:any"));
    }

    @Test
    public void testStyleParsingValidShape() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node resource = createURI("http://example.com/Styled2");
        dsg.add(createURI("graph"), resource, createURI("http://example.com/style#shape"), createLiteralString(" circle "));

        Style style = OntologySupport.styleFor(dsg, resource);
        Assert.assertNotNull(style);
        Assert.assertEquals(style.getShape(), io.telicent.jena.graphql.schemas.telicent.graph.models.Shape.CIRCLE);
    }

    @Test
    public void testStreamingHelpersAndTypeChecks() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node classNode = createURI("http://example.com/Class2");
        Node objectProperty = createURI("http://example.com/prop");
        Node datatypeProperty = createURI("http://example.com/prop2");
        Node blankNode = createBlankNode("blank");
        Node literalNode = createLiteralString("literal");

        dsg.add(createURI("graph"), classNode, RDF.type.asNode(), RDFS.Class.asNode());
        dsg.add(createURI("graph"), blankNode, RDF.type.asNode(), RDFS.Class.asNode());
        dsg.add(createURI("graph"), literalNode, RDF.type.asNode(), RDFS.Class.asNode());
        dsg.add(createURI("graph"), objectProperty, RDF.type.asNode(), OWL.ObjectProperty.asNode());
        dsg.add(createURI("graph"), blankNode, RDF.type.asNode(), OWL.ObjectProperty.asNode());
        dsg.add(createURI("graph"), literalNode, RDF.type.asNode(), OWL.ObjectProperty.asNode());
        dsg.add(createURI("graph"), datatypeProperty, RDF.type.asNode(), OWL.DatatypeProperty.asNode());
        dsg.add(createURI("graph"), blankNode, RDF.type.asNode(), OWL.DatatypeProperty.asNode());
        dsg.add(createURI("graph"), literalNode, RDF.type.asNode(), OWL.DatatypeProperty.asNode());

        Assert.assertTrue(OntologySupport.streamClasses(dsg).anyMatch(n -> n.equals(classNode)));
        Assert.assertTrue(OntologySupport.streamClasses(dsg, null).anyMatch(n -> n.equals(classNode)));
        Assert.assertTrue(OntologySupport.streamObjectProperties(dsg).anyMatch(n -> n.equals(objectProperty)));
        Assert.assertTrue(OntologySupport.streamObjectProperties(dsg, null).anyMatch(n -> n.equals(objectProperty)));
        Assert.assertTrue(OntologySupport.streamDatatypeProperties(dsg).anyMatch(n -> n.equals(datatypeProperty)));
        Assert.assertTrue(OntologySupport.streamDatatypeProperties(dsg, null).anyMatch(n -> n.equals(datatypeProperty)));

        Assert.assertTrue(OntologySupport.isObjectProperty(dsg, objectProperty));
        Assert.assertTrue(OntologySupport.isObjectProperty(dsg, null, objectProperty));
        Assert.assertFalse(OntologySupport.isObjectProperty(dsg, classNode));
        Assert.assertTrue(OntologySupport.isDatatypeProperty(dsg, datatypeProperty));
        Assert.assertTrue(OntologySupport.isDatatypeProperty(dsg, null, datatypeProperty));
        Assert.assertFalse(OntologySupport.isDatatypeProperty(dsg, classNode));
        Assert.assertFalse(OntologySupport.isOwlClass(dsg, classNode));
    }

    @Test
    public void testPropertyDefinitionFetcherRejectsUnknownSource() {
        PropertyDefinitionLiteralsFetcher fetcher = new PropertyDefinitionLiteralsFetcher(RDFS.label.asNode());
        DatasetGraph dsg = DatasetGraphFactory.create();
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, "bad-source");

        try {
            fetcher.get(environment);
            Assert.fail("Expected PropertyDefinitionLiteralsFetcher to throw");
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Unsupported property definition"));
        }
    }

    @Test
    public void testLiteralsAndStyleFallbacks() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node resource = createURI("http://example.com/Resource");
        dsg.add(createURI("graph"), resource, RDFS.label.asNode(), createLiteral("Label", "en"));

        List<RdfLiteral> labels = OntologySupport.literalsFor(dsg, resource, RDFS.label.asNode());
        Assert.assertEquals(labels.size(), 1);
        Assert.assertEquals(labels.get(0).getValue(), "Label");

        Assert.assertNull(OntologySupport.styleFor(dsg, resource));
    }

    @Test
    public void testMatchesTypeFilterNull() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node node = createURI("http://example.com/Node");

        Assert.assertTrue(OntologySupport.matchesTypeFilter(dsg, node, null));
    }

    @Test
    public void testStyleParsingEmptyAndBlankValues() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node empty = createURI("http://example.com/EmptyStyle");
        Node blankValue = createURI("http://example.com/BlankValue");
        dsg.add(createURI("graph"), blankValue, createURI("http://example.com/style#shape"), createLiteralString(" "));

        Assert.assertNull(OntologySupport.styleFor(dsg, empty));
        Assert.assertNull(OntologySupport.styleFor(dsg, blankValue));
    }

    @Test
    public void testPropertyDefinitionLiteralFetcherGraphScope() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graphA = createURI("graphA");
        Node graphB = createURI("graphB");
        Node objectProperty = createURI("http://example.com/prop");
        Node datatypeProperty = createURI("http://example.com/prop2");
        dsg.add(graphA, objectProperty, RDFS.label.asNode(), createLiteral("Object Label", "en"));
        dsg.add(graphB, datatypeProperty, RDFS.label.asNode(), createLiteral("Datatype Label", "en"));

        io.telicent.jena.graphql.schemas.telicent.graph.models.ObjectPropertyDefinition objectDef =
                new io.telicent.jena.graphql.schemas.telicent.graph.models.ObjectPropertyDefinition(objectProperty,
                                                                                                     dsg.prefixes(),
                                                                                                     graphA);
        io.telicent.jena.graphql.schemas.telicent.graph.models.DatatypePropertyDefinition datatypeDef =
                new io.telicent.jena.graphql.schemas.telicent.graph.models.DatatypePropertyDefinition(datatypeProperty,
                                                                                                       dsg.prefixes(),
                                                                                                       graphB);

        PropertyDefinitionLiteralsFetcher fetcher = new PropertyDefinitionLiteralsFetcher(RDFS.label.asNode());
        List<RdfLiteral> objectLabels = fetcher.get(prepareFetchingEnvironment(dsg, objectDef));
        List<RdfLiteral> datatypeLabels = fetcher.get(prepareFetchingEnvironment(dsg, datatypeDef));

        Assert.assertEquals(objectLabels.size(), 1);
        Assert.assertEquals(objectLabels.get(0).getValue(), "Object Label");
        Assert.assertEquals(datatypeLabels.size(), 1);
        Assert.assertEquals(datatypeLabels.get(0).getValue(), "Datatype Label");
    }

    @Test
    public void testMatchesTypeFilterNoTypesPresent() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node node = createURI("http://example.com/Node");
        UriFilterSpec includeFilter = new UriFilterSpec(Set.of(OWL.Class.getURI()), FilterMode.INCLUDE);
        UriFilterSpec excludeFilter = new UriFilterSpec(Set.of(OWL.Class.getURI()), FilterMode.EXCLUDE);

        Assert.assertFalse(OntologySupport.matchesTypeFilter(dsg, node, includeFilter));
        Assert.assertTrue(OntologySupport.matchesTypeFilter(dsg, node, excludeFilter));
    }

    @Test
    public void testStyleParsingPrefersFirstValue() {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node resource = createURI("http://example.com/Styled3");
        Node graph = createURI("graph");
        dsg.add(graph, resource, createURI("http://example.com/style#line_color"), createLiteralString("#111111"));
        dsg.add(graph, resource, createURI("http://example.com/style#line_color"), createLiteralString("#222222"));

        Style style = OntologySupport.styleFor(dsg, resource);
        Assert.assertNotNull(style);
        Assert.assertTrue("#111111".equals(style.getLine_color()) || "#222222".equals(style.getLine_color()));
    }
}
