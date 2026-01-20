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
package io.telicent.jena.graphql.schemas.telicent.graph.models;

import org.apache.jena.graph.Node;
import org.apache.jena.riot.system.PrefixMap;
import org.apache.jena.riot.system.PrefixMapFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.apache.jena.graph.NodeFactory.createBlankNode;
import static org.apache.jena.graph.NodeFactory.createLiteralString;
import static org.apache.jena.graph.NodeFactory.createURI;

public class TestOntologyModels {

    @Test
    public void testRdfsClassUris() {
        PrefixMap prefixes = PrefixMapFactory.create();
        prefixes.add("ex", "http://example.com/");
        Node uriNode = createURI("http://example.com/Class");
        Node blankNode = createBlankNode("b1");

        RdfsClass uriClass = new RdfsClass(uriNode, prefixes, true);
        RdfsClass blankClass = new RdfsClass(blankNode, prefixes, false);
        RdfsClass scopedClass = new RdfsClass(uriNode, prefixes, true, createURI("graph"));

        Assert.assertEquals(uriClass.getId(), uriNode.getURI());
        Assert.assertEquals(uriClass.getUri(), uriNode.getURI());
        Assert.assertEquals(uriClass.getShortUri(), "ex:Class");
        Assert.assertTrue(uriClass.isOwlClass());

        Assert.assertEquals(blankClass.getUri(), "_:b1");
        Assert.assertEquals(blankClass.getShortUri(), "_:b1");
        Assert.assertFalse(blankClass.isOwlClass());
        Assert.assertEquals(scopedClass.getGraphNode().getURI(), "graph");

        PrefixMap missingPrefix = PrefixMapFactory.create();
        RdfsClass fallbackClass = new RdfsClass(uriNode, missingPrefix, false);
        Assert.assertEquals(fallbackClass.getShortUri(), uriNode.getURI());
    }

    @Test
    public void testRdfsClassLiteralThrows() {
        RdfsClass literalClass = new RdfsClass(createLiteralString("bad"), null, false);
        try {
            literalClass.getUri();
            Assert.fail("Expected getUri to throw for literal nodes");
        } catch (IllegalStateException ex) {
            Assert.assertTrue(ex.getMessage().contains("Not a node"));
        }
    }

    @Test
    public void testRdfLiteralValues() {
        RdfLiteral literal = new RdfLiteral(createLiteralString("value"));
        Assert.assertEquals(literal.getValue(), "value");
        Assert.assertEquals(literal.getLanguage(), "");
        Assert.assertEquals(literal.getDatatype(), "http://www.w3.org/2001/XMLSchema#string");
    }

    @Test
    public void testRdfLiteralRequiresLiteral() {
        try {
            new RdfLiteral(createURI("http://example.com/not-literal"));
            Assert.fail("Expected constructor to throw for non-literal");
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("literal"));
        }
    }

    @Test
    public void testXmlSchemaDatatypeUris() {
        Node uriNode = createURI("http://example.com/datatype");
        Node blankNode = createBlankNode("b2");

        XmlSchemaDatatype uriDatatype = new XmlSchemaDatatype(uriNode);
        XmlSchemaDatatype blankDatatype = new XmlSchemaDatatype(blankNode);

        Assert.assertEquals(uriDatatype.getId(), uriNode.getURI());
        Assert.assertEquals(blankDatatype.getUri(), "_:b2");
    }

    @Test
    public void testXmlSchemaDatatypeLiteralThrows() {
        XmlSchemaDatatype literalDatatype = new XmlSchemaDatatype(createLiteralString("bad"));
        try {
            literalDatatype.getUri();
            Assert.fail("Expected getUri to throw for literal nodes");
        } catch (IllegalStateException ex) {
            Assert.assertTrue(ex.getMessage().contains("Not a node"));
        }
    }

    @Test
    public void testPropertyDefinitionWrappers() {
        PrefixMap prefixes = PrefixMapFactory.create();
        Node predicate = createURI("http://example.com/predicate");

        ObjectPropertyDefinition objectDef = new ObjectPropertyDefinition(predicate, prefixes);
        DatatypePropertyDefinition datatypeDef = new DatatypePropertyDefinition(predicate, prefixes);
        ObjectPropertyDefinition scopedObject = new ObjectPropertyDefinition(predicate, prefixes, createURI("graph"));
        DatatypePropertyDefinition scopedDatatype = new DatatypePropertyDefinition(predicate, prefixes, createURI("graph"));
        ObjectPropertyDefinition nullGraphObject = new ObjectPropertyDefinition(predicate, prefixes, null);
        DatatypePropertyDefinition nullGraphDatatype = new DatatypePropertyDefinition(predicate, prefixes, null);

        Assert.assertEquals(objectDef.getPredicate(), predicate);
        Assert.assertEquals(objectDef.getPrefixes(), prefixes);
        Assert.assertEquals(datatypeDef.getPredicate(), predicate);
        Assert.assertEquals(datatypeDef.getPrefixes(), prefixes);
        Assert.assertEquals(scopedObject.getGraphNode().getURI(), "graph");
        Assert.assertEquals(scopedDatatype.getGraphNode().getURI(), "graph");
        Assert.assertEquals(nullGraphObject.getGraphNode(), org.apache.jena.graph.Node.ANY);
        Assert.assertEquals(nullGraphDatatype.getGraphNode(), org.apache.jena.graph.Node.ANY);
    }

    @Test
    public void testStyleAccessors() {
        Style style = new Style();
        style.setShape(Shape.CIRCLE);
        style.setLine_color("#123456");
        style.setFill_color("#abcdef");
        style.setIcon_color("#111111");
        style.setFa_icon_class("fa-user");
        style.setFa_icon_unicode("f123");
        style.setFa_icon_class_free("fa-user-alt");
        style.setFa_icon_unicode_free("f124");
        style.setDark_mode_line_color("#222222");
        style.setDark_mode_fill_color("#333333");
        style.setDark_mode_icon_color("#444444");
        style.setDescription("Example style");

        Assert.assertEquals(style.getShape(), Shape.CIRCLE);
        Assert.assertEquals(style.getLine_color(), "#123456");
        Assert.assertEquals(style.getFill_color(), "#abcdef");
        Assert.assertEquals(style.getIcon_color(), "#111111");
        Assert.assertEquals(style.getFa_icon_class(), "fa-user");
        Assert.assertEquals(style.getFa_icon_unicode(), "f123");
        Assert.assertEquals(style.getFa_icon_class_free(), "fa-user-alt");
        Assert.assertEquals(style.getFa_icon_unicode_free(), "f124");
        Assert.assertEquals(style.getDark_mode_line_color(), "#222222");
        Assert.assertEquals(style.getDark_mode_fill_color(), "#333333");
        Assert.assertEquals(style.getDark_mode_icon_color(), "#444444");
        Assert.assertEquals(style.getDescription(), "Example style");
    }

    @Test
    public void testShapeEnumValues() {
        Assert.assertEquals(Shape.valueOf("CIRCLE"), Shape.CIRCLE);
        Assert.assertEquals(Shape.valueOf("RECTANGLE"), Shape.RECTANGLE);
    }

    @Test
    public void testOntologyPlaceholderConstructor() {
        OntologyPlaceholder placeholder = new OntologyPlaceholder();
        Assert.assertNotNull(placeholder);
    }
}
