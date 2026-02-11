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
import io.telicent.jena.graphql.schemas.telicent.graph.models.*;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.FilterMode;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.SKOS;
import org.apache.jena.vocabulary.XSD;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.apache.jena.graph.NodeFactory.*;

public class TestOntologyFetchers extends AbstractFetcherTests {

    private static final String EX = "http://example.com/";
    private static final Node GRAPH = createURI("graph");
    private static final Node CLASS_AGENT = createURI(EX + "Agent");
    private static final Node CLASS_PERSON = createURI(EX + "Person");
    private static final Node CLASS_PLACE = createURI(EX + "Place");
    private static final Node PROP_LOCATED_IN = createURI(EX + "locatedIn");
    private static final Node PROP_LOCATED_NEAR = createURI(EX + "locatedNear");
    private static final Node PROP_NAME = createURI(EX + "name");
    private static final Node PROP_FULL_NAME = createURI(EX + "fullName");
    private static final Node STYLE_SHAPE = createURI(EX + "style#shape");
    private static final Node STYLE_LINE_COLOR = createURI(EX + "style#line_color");
    private static final Node STYLE_ICON = createURI(EX + "style#fa_icon_class");
    private static final Node STYLE_DESCRIPTION = createURI(EX + "style#description");

    @Test
    public void testOntologyClassesAndCountsWithFilters() throws Exception {
        DatasetGraph dsg = createOntologyDataset();
        Map<String, Object> uriFilter = Map.of(
                TelicentGraphSchema.ARGUMENT_VALUES, List.of(CLASS_PERSON.getURI()),
                TelicentGraphSchema.ARGUMENT_MODE, FilterMode.INCLUDE
        );
        Map<String, Object> typeFilter = Map.of(
                TelicentGraphSchema.ARGUMENT_VALUES, List.of(OWL.Class.getURI()),
                TelicentGraphSchema.ARGUMENT_MODE, FilterMode.INCLUDE
        );
        Map<String, Object> arguments = Map.of(
                TelicentGraphSchema.ARGUMENT_URI_FILTER, uriFilter,
                TelicentGraphSchema.ARGUMENT_TYPE_FILTER, typeFilter
        );

        OntologyClassesFetcher classesFetcher = new OntologyClassesFetcher();
        OntologyClassCountFetcher countFetcher = new OntologyClassCountFetcher();

        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new OntologyPlaceholder(), arguments);

        List<RdfsClass> classes = classesFetcher.get(environment);
        Integer count = countFetcher.get(environment);

        Assert.assertEquals(classes.size(), 1);
        Assert.assertEquals(count, Integer.valueOf(1));
        Assert.assertEquals(classes.get(0).getUri(), CLASS_PERSON.getURI());
    }

    @Test
    public void testOntologyClassesWithoutGraphScope() throws Exception {
        DatasetGraph dsg = createOntologyDataset();
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new Object());

        List<RdfsClass> classes = new OntologyClassesFetcher().get(environment);
        Integer count = new OntologyClassCountFetcher().get(environment);

        Assert.assertFalse(classes.isEmpty());
        Assert.assertEquals(count, Integer.valueOf(classes.size()));
    }

    @Test
    public void testOntologyRootFetcher() throws Exception {
        OntologyFetcher fetcher = new OntologyFetcher();
        DatasetGraph dsg = DatasetGraphFactory.create();
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new Object());

        OntologyPlaceholder result = fetcher.get(environment);

        Assert.assertNotNull(result);
    }

    @Test
    public void testOntologyGraphScoping() throws Exception {
        DatasetGraph dsg = DatasetGraphFactory.create();
        Node graphA = createURI("graphA");
        Node graphB = createURI("graphB");
        Node classA = createURI(EX + "ClassA");
        Node classB = createURI(EX + "ClassB");
        dsg.add(graphA, classA, RDF.type.asNode(), RDFS.Class.asNode());
        dsg.add(graphB, classB, RDF.type.asNode(), RDFS.Class.asNode());

        OntologyPlaceholder scoped = new OntologyPlaceholder(graphA);
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, scoped);

        List<RdfsClass> classes = new OntologyClassesFetcher().get(environment);

        Assert.assertEquals(classes.size(), 1);
        Assert.assertEquals(classes.get(0).getUri(), classA.getURI());
    }

    @Test
    public void testOntologyClassFetcher() throws Exception {
        DatasetGraph dsg = createOntologyDataset();
        Map<String, Object> arguments = Map.of(TelicentGraphSchema.ARGUMENT_URI, CLASS_PERSON.getURI());
        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new OntologyPlaceholder(), arguments);
        OntologyClassFetcher fetcher = new OntologyClassFetcher();

        RdfsClass result = fetcher.get(environment);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.isOwlClass());
        Assert.assertEquals(result.getUri(), CLASS_PERSON.getURI());
        Assert.assertEquals(result.getShortUri(), CLASS_PERSON.getURI());
    }

    @Test
    public void testOntologyClassFetcherMissing() throws Exception {
        DatasetGraph dsg = createOntologyDataset();
        Map<String, Object> arguments = Map.of(TelicentGraphSchema.ARGUMENT_URI, "http://example.com/Missing");
        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new OntologyPlaceholder(), arguments);
        OntologyClassFetcher fetcher = new OntologyClassFetcher();

        RdfsClass result = fetcher.get(environment);

        Assert.assertNull(result);
    }

    @Test
    public void testOntologyClassFetcherWithoutGraphScope() throws Exception {
        DatasetGraph dsg = createOntologyDataset();
        Map<String, Object> arguments = Map.of(TelicentGraphSchema.ARGUMENT_URI, CLASS_PERSON.getURI());
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new Object(), arguments);

        RdfsClass result = new OntologyClassFetcher().get(environment);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getUri(), CLASS_PERSON.getURI());
    }

    @Test
    public void testPropertyDefinitionListsAndCounts() throws Exception {
        DatasetGraph dsg = createOntologyDataset();
        Map<String, Object> arguments = Map.of(TelicentGraphSchema.ARGUMENT_LABEL_FILTER, "Name");
        DataFetchingEnvironment environment =
                prepareFetchingEnvironment(dsg, new OntologyPlaceholder(), arguments);

        OntologyDatatypePropertyDefinitionsFetcher datatypeFetcher = new OntologyDatatypePropertyDefinitionsFetcher();
        OntologyDatatypePropertyDefinitionCountFetcher datatypeCountFetcher =
                new OntologyDatatypePropertyDefinitionCountFetcher();
        List<DatatypePropertyDefinition> datatypeProps = datatypeFetcher.get(environment);
        Integer datatypeCount = datatypeCountFetcher.get(environment);

        Assert.assertEquals(datatypeProps.size(), 2);
        Assert.assertEquals(datatypeCount, Integer.valueOf(2));

        OntologyObjectPropertyDefinitionsFetcher objectFetcher = new OntologyObjectPropertyDefinitionsFetcher();
        OntologyObjectPropertyDefinitionCountFetcher objectCountFetcher =
                new OntologyObjectPropertyDefinitionCountFetcher();
        List<ObjectPropertyDefinition> objectProps = objectFetcher.get(prepareFetchingEnvironment(dsg,
                                                                                                  new OntologyPlaceholder()));
        Integer objectCount = objectCountFetcher.get(prepareFetchingEnvironment(dsg, new OntologyPlaceholder()));

        Assert.assertEquals(objectProps.size(), 2);
        Assert.assertEquals(objectCount, Integer.valueOf(2));
    }

    @Test
    public void testPropertyDefinitionsWithoutGraphScope() throws Exception {
        DatasetGraph dsg = createOntologyDataset();
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new Object());

        List<ObjectPropertyDefinition> objectProps = new OntologyObjectPropertyDefinitionsFetcher().get(environment);
        List<DatatypePropertyDefinition> datatypeProps = new OntologyDatatypePropertyDefinitionsFetcher().get(environment);

        Assert.assertFalse(objectProps.isEmpty());
        Assert.assertFalse(datatypeProps.isEmpty());
    }

    @Test
    public void testPropertyDefinitionCountsWithoutGraphScope() throws Exception {
        DatasetGraph dsg = createOntologyDataset();
        DataFetchingEnvironment environment = prepareFetchingEnvironment(dsg, new Object());

        Integer objectCount = new OntologyObjectPropertyDefinitionCountFetcher().get(environment);
        Integer datatypeCount = new OntologyDatatypePropertyDefinitionCountFetcher().get(environment);

        Assert.assertEquals(objectCount, Integer.valueOf(2));
        Assert.assertEquals(datatypeCount, Integer.valueOf(2));
    }

    @Test
    public void testClassRelationshipsAndInstances() throws Exception {
        DatasetGraph dsg = createOntologyDataset();
        RdfsClass agent = new RdfsClass(CLASS_AGENT, dsg.prefixes(), false);
        RdfsClass person = new RdfsClass(CLASS_PERSON, dsg.prefixes(), true);
        RdfsClass place = new RdfsClass(CLASS_PLACE, dsg.prefixes(), false);

        List<RdfsClass> subclasses = new RdfsClassSubClassesFetcher().get(prepareFetchingEnvironment(dsg, agent));
        List<RdfsClass> superclasses = new RdfsClassSuperClassesFetcher().get(prepareFetchingEnvironment(dsg, person));
        List<TelicentGraphNode> instances = new RdfsClassInstancesFetcher().get(prepareFetchingEnvironment(dsg, person));
        List<TelicentGraphNode> types = new RdfsClassTypesFetcher().get(prepareFetchingEnvironment(dsg, person));

        Assert.assertEquals(subclasses.size(), 1);
        Assert.assertEquals(subclasses.get(0).getUri(), CLASS_PERSON.getURI());
        Assert.assertEquals(superclasses.size(), 1);
        Assert.assertEquals(superclasses.get(0).getUri(), CLASS_AGENT.getURI());
        Assert.assertEquals(instances.size(), 1);
        Assert.assertEquals(types.size(), 2);

        List<ObjectPropertyDefinition> rangeProps =
                new RdfsClassRangePropertiesFetcher().get(prepareFetchingEnvironment(dsg, place));
        Assert.assertEquals(rangeProps.size(), 1);
        Assert.assertEquals(rangeProps.get(0).getPredicate(), PROP_LOCATED_IN);
    }

    @Test
    public void testClassPropertiesAndStyle() throws Exception {
        DatasetGraph dsg = createOntologyDataset();
        RdfsClass person = new RdfsClass(CLASS_PERSON, dsg.prefixes(), true);

        List<ObjectPropertyDefinition> domainObjectProps =
                new RdfsClassDomainObjectPropertiesFetcher().get(prepareFetchingEnvironment(dsg, person));
        List<DatatypePropertyDefinition> domainDatatypeProps =
                new RdfsClassDomainDatatypePropertiesFetcher().get(prepareFetchingEnvironment(dsg, person));

        Assert.assertEquals(domainObjectProps.size(), 1);
        Assert.assertEquals(domainDatatypeProps.size(), 1);

        Style style = new RdfsClassStyleFetcher().get(prepareFetchingEnvironment(dsg, person));
        Assert.assertNotNull(style);
        Assert.assertEquals(style.getShape(), Shape.CIRCLE);
        Assert.assertEquals(style.getLine_color(), "#123456");
        Assert.assertEquals(style.getFa_icon_class(), "fa-user");
        Assert.assertEquals(style.getDescription(), "Person style");

        List<RdfLiteral> labels =
                new RdfsClassLiteralsFetcher(RDFS.label.asNode()).get(prepareFetchingEnvironment(dsg, person));
        List<RdfLiteral> comments =
                new RdfsClassLiteralsFetcher(RDFS.comment.asNode()).get(prepareFetchingEnvironment(dsg, person));

        Assert.assertEquals(labels.size(), 1);
        Assert.assertEquals(labels.get(0).getValue(), "Person");
        Assert.assertEquals(comments.size(), 1);
        Assert.assertEquals(comments.get(0).getValue(), "A person");
    }

    @Test
    public void testPropertyFetchersAndLiterals() throws Exception {
        DatasetGraph dsg = createOntologyDataset();
        ObjectPropertyDefinition objectProperty = new ObjectPropertyDefinition(PROP_LOCATED_IN, dsg.prefixes());
        DatatypePropertyDefinition datatypeProperty = new DatatypePropertyDefinition(PROP_NAME, dsg.prefixes());

        RdfsClass domain = new ObjectPropertyDomainFetcher().get(prepareFetchingEnvironment(dsg, objectProperty));
        RdfsClass range = new ObjectPropertyRangeFetcher().get(prepareFetchingEnvironment(dsg, objectProperty));
        List<ObjectPropertyDefinition> subProps =
                new ObjectPropertySubPropertiesFetcher().get(prepareFetchingEnvironment(dsg, objectProperty));
        List<ObjectPropertyDefinition> superProps =
                new ObjectPropertySuperPropertiesFetcher().get(prepareFetchingEnvironment(dsg, objectProperty));

        Assert.assertEquals(domain.getUri(), CLASS_PERSON.getURI());
        Assert.assertEquals(range.getUri(), CLASS_PLACE.getURI());
        Assert.assertEquals(subProps.size(), 1);
        Assert.assertEquals(superProps.size(), 0);

        RdfsClass datatypeDomain =
                new DatatypePropertyDomainFetcher().get(prepareFetchingEnvironment(dsg, datatypeProperty));
        XmlSchemaDatatype datatypeRange =
                new DatatypePropertyRangeFetcher().get(prepareFetchingEnvironment(dsg, datatypeProperty));
        List<DatatypePropertyDefinition> datatypeSubProps =
                new DatatypePropertySubPropertiesFetcher().get(prepareFetchingEnvironment(dsg, datatypeProperty));
        List<DatatypePropertyDefinition> datatypeSuperProps =
                new DatatypePropertySuperPropertiesFetcher().get(prepareFetchingEnvironment(dsg, datatypeProperty));

        Assert.assertEquals(datatypeDomain.getUri(), CLASS_PERSON.getURI());
        Assert.assertEquals(datatypeRange.getUri(), XSD.xstring.getURI());
        Assert.assertEquals(datatypeSubProps.size(), 1);
        Assert.assertEquals(datatypeSuperProps.size(), 0);

        List<RdfLiteral> labels =
                new PropertyDefinitionLiteralsFetcher(RDFS.label.asNode()).get(prepareFetchingEnvironment(dsg, objectProperty));
        List<RdfLiteral> comments =
                new PropertyDefinitionLiteralsFetcher(RDFS.comment.asNode()).get(prepareFetchingEnvironment(dsg, datatypeProperty));

        Assert.assertEquals(labels.size(), 1);
        Assert.assertEquals(labels.get(0).getValue(), "Located in");
        Assert.assertEquals(comments.size(), 1);
        Assert.assertEquals(comments.get(0).getValue(), "Name of person");
    }

    @Test
    public void testPropertyFetchersMissingRanges() throws Exception {
        DatasetGraph dsg = createOntologyDataset();
        Node noRangeObjectProp = createURI(EX + "noRangeObject");
        dsg.add(GRAPH, noRangeObjectProp, RDF.type.asNode(), OWL.ObjectProperty.asNode());
        Node noRangeDatatypeProp = createURI(EX + "noRangeDatatype");
        dsg.add(GRAPH, noRangeDatatypeProp, RDF.type.asNode(), OWL.DatatypeProperty.asNode());

        ObjectPropertyDefinition objectProperty = new ObjectPropertyDefinition(noRangeObjectProp, dsg.prefixes());
        DatatypePropertyDefinition datatypeProperty = new DatatypePropertyDefinition(noRangeDatatypeProp, dsg.prefixes());

        Assert.assertNull(new ObjectPropertyRangeFetcher().get(prepareFetchingEnvironment(dsg, objectProperty)));
        Assert.assertNull(new DatatypePropertyRangeFetcher().get(prepareFetchingEnvironment(dsg, datatypeProperty)));
    }

    private static DatasetGraph createOntologyDataset() {
        DatasetGraph dsg = DatasetGraphFactory.create();

        dsg.add(GRAPH, CLASS_AGENT, RDF.type.asNode(), RDFS.Class.asNode());
        dsg.add(GRAPH, CLASS_AGENT, SKOS.altLabel.asNode(), createLiteral("Agent", "en"));

        dsg.add(GRAPH, CLASS_PERSON, RDF.type.asNode(), RDFS.Class.asNode());
        dsg.add(GRAPH, CLASS_PERSON, RDF.type.asNode(), OWL.Class.asNode());
        dsg.add(GRAPH, CLASS_PERSON, RDFS.label.asNode(), createLiteral("Person", "en"));
        dsg.add(GRAPH, CLASS_PERSON, RDFS.comment.asNode(), createLiteral("A person", "en"));

        dsg.add(GRAPH, CLASS_PLACE, RDF.type.asNode(), RDFS.Class.asNode());
        dsg.add(GRAPH, CLASS_PLACE, SKOS.prefLabel.asNode(), createLiteral("Place", "en"));

        dsg.add(GRAPH, CLASS_PERSON, RDFS.subClassOf.asNode(), CLASS_AGENT);

        dsg.add(GRAPH, PROP_LOCATED_IN, RDF.type.asNode(), OWL.ObjectProperty.asNode());
        dsg.add(GRAPH, PROP_LOCATED_IN, RDFS.domain.asNode(), CLASS_PERSON);
        dsg.add(GRAPH, PROP_LOCATED_IN, RDFS.range.asNode(), CLASS_PLACE);
        dsg.add(GRAPH, PROP_LOCATED_IN, RDFS.label.asNode(), createLiteral("Located in", "en"));

        dsg.add(GRAPH, PROP_LOCATED_NEAR, RDF.type.asNode(), OWL.ObjectProperty.asNode());
        dsg.add(GRAPH, PROP_LOCATED_NEAR, RDFS.subPropertyOf.asNode(), PROP_LOCATED_IN);

        dsg.add(GRAPH, PROP_NAME, RDF.type.asNode(), OWL.DatatypeProperty.asNode());
        dsg.add(GRAPH, PROP_NAME, RDFS.domain.asNode(), CLASS_PERSON);
        dsg.add(GRAPH, PROP_NAME, RDFS.range.asNode(), XSD.xstring.asNode());
        dsg.add(GRAPH, PROP_NAME, RDFS.label.asNode(), createLiteral("Name", "en"));
        dsg.add(GRAPH, PROP_NAME, RDFS.comment.asNode(), createLiteral("Name of person", "en"));

        dsg.add(GRAPH, PROP_FULL_NAME, RDF.type.asNode(), OWL.DatatypeProperty.asNode());
        dsg.add(GRAPH, PROP_FULL_NAME, RDFS.subPropertyOf.asNode(), PROP_NAME);
        dsg.add(GRAPH, PROP_FULL_NAME, RDFS.label.asNode(), createLiteral("Full Name", "en"));

        Node instance = createURI(EX + "Alice");
        dsg.add(GRAPH, instance, RDF.type.asNode(), CLASS_PERSON);

        dsg.add(GRAPH, CLASS_PERSON, STYLE_SHAPE, createLiteral("CIRCLE"));
        dsg.add(GRAPH, CLASS_PERSON, STYLE_LINE_COLOR, createLiteral("#123456"));
        dsg.add(GRAPH, CLASS_PERSON, STYLE_ICON, createLiteral("fa-user"));
        dsg.add(GRAPH, CLASS_PERSON, STYLE_DESCRIPTION, createLiteral("Person style"));

        return dsg;
    }
}
