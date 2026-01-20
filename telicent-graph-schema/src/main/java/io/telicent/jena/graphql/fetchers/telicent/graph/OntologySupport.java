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
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.SKOS;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Shared helpers for ontology data access.
 */
public final class OntologySupport {

    private static final Set<Node> CLASS_TYPES = Set.of(RDFS.Class.asNode(), OWL.Class.asNode());

    private OntologySupport() {
    }

    /**
     * Streams all class nodes.
     *
     * @param dsg Dataset graph
     * @return Stream of class nodes
     */
    public static Stream<Node> streamClasses(DatasetGraph dsg) {
        return streamClasses(dsg, Node.ANY);
    }

    /**
     * Streams all class nodes scoped to a graph.
     *
     * @param dsg      Dataset graph
     * @param graph    Graph node scope
     * @return Stream of class nodes
     */
    public static Stream<Node> streamClasses(DatasetGraph dsg, Node graph) {
        Node graphNode = graph != null ? graph : Node.ANY;
        return dsg.stream(graphNode, Node.ANY, RDF.type.asNode(), Node.ANY)
                  .filter(q -> CLASS_TYPES.contains(q.getObject()))
                  .map(Quad::getSubject)
                  .filter(n -> n.isURI() || n.isBlank())
                  .distinct();
    }

    /**
     * Streams all object property nodes.
     *
     * @param dsg Dataset graph
     * @return Stream of object property nodes
     */
    public static Stream<Node> streamObjectProperties(DatasetGraph dsg) {
        return streamObjectProperties(dsg, Node.ANY);
    }

    /**
     * Streams all object property nodes scoped to a graph.
     *
     * @param dsg      Dataset graph
     * @param graph    Graph node scope
     * @return Stream of object property nodes
     */
    public static Stream<Node> streamObjectProperties(DatasetGraph dsg, Node graph) {
        Node graphNode = graph != null ? graph : Node.ANY;
        return dsg.stream(graphNode, Node.ANY, RDF.type.asNode(), OWL.ObjectProperty.asNode())
                  .map(Quad::getSubject)
                  .filter(n -> n.isURI() || n.isBlank())
                  .distinct();
    }

    /**
     * Streams all datatype property nodes.
     *
     * @param dsg Dataset graph
     * @return Stream of datatype property nodes
     */
    public static Stream<Node> streamDatatypeProperties(DatasetGraph dsg) {
        return streamDatatypeProperties(dsg, Node.ANY);
    }

    /**
     * Streams all datatype property nodes scoped to a graph.
     *
     * @param dsg      Dataset graph
     * @param graph    Graph node scope
     * @return Stream of datatype property nodes
     */
    public static Stream<Node> streamDatatypeProperties(DatasetGraph dsg, Node graph) {
        Node graphNode = graph != null ? graph : Node.ANY;
        return dsg.stream(graphNode, Node.ANY, RDF.type.asNode(), OWL.DatatypeProperty.asNode())
                  .map(Quad::getSubject)
                  .filter(n -> n.isURI() || n.isBlank())
                  .distinct();
    }

    /**
     * Checks whether a node is an owl:Class.
     *
     * @param dsg  Dataset graph
     * @param node Node to check
     * @return True if owl:Class
     */
    public static boolean isOwlClass(DatasetGraph dsg, Node node) {
        return isOwlClass(dsg, Node.ANY, node);
    }

    /**
     * Checks whether a node is an owl:Class within a graph scope.
     *
     * @param dsg   Dataset graph
     * @param graph Graph node scope
     * @param node  Node to check
     * @return True if owl:Class
     */
    public static boolean isOwlClass(DatasetGraph dsg, Node graph, Node node) {
        Node graphNode = graph != null ? graph : Node.ANY;
        return dsg.contains(graphNode, node, RDF.type.asNode(), OWL.Class.asNode());
    }

    /**
     * Checks whether a node is an owl:ObjectProperty.
     *
     * @param dsg  Dataset graph
     * @param node Node to check
     * @return True if object property
     */
    public static boolean isObjectProperty(DatasetGraph dsg, Node node) {
        return isObjectProperty(dsg, Node.ANY, node);
    }

    /**
     * Checks whether a node is an owl:ObjectProperty within a graph scope.
     *
     * @param dsg   Dataset graph
     * @param graph Graph node scope
     * @param node  Node to check
     * @return True if object property
     */
    public static boolean isObjectProperty(DatasetGraph dsg, Node graph, Node node) {
        Node graphNode = graph != null ? graph : Node.ANY;
        return dsg.contains(graphNode, node, RDF.type.asNode(), OWL.ObjectProperty.asNode());
    }

    /**
     * Checks whether a node is an owl:DatatypeProperty.
     *
     * @param dsg  Dataset graph
     * @param node Node to check
     * @return True if datatype property
     */
    public static boolean isDatatypeProperty(DatasetGraph dsg, Node node) {
        return isDatatypeProperty(dsg, Node.ANY, node);
    }

    /**
     * Checks whether a node is an owl:DatatypeProperty within a graph scope.
     *
     * @param dsg   Dataset graph
     * @param graph Graph node scope
     * @param node  Node to check
     * @return True if datatype property
     */
    public static boolean isDatatypeProperty(DatasetGraph dsg, Node graph, Node node) {
        Node graphNode = graph != null ? graph : Node.ANY;
        return dsg.contains(graphNode, node, RDF.type.asNode(), OWL.DatatypeProperty.asNode());
    }

    /**
     * Parses a node from a URI string.
     *
     * @param uri URI value
     * @return Parsed node
     */
    public static Node parseUriNode(String uri) {
        if (uri.startsWith(TelicentGraphSchema.BLANK_NODE_PREFIX)) {
            return NodeFactory.createBlankNode(uri.substring(TelicentGraphSchema.BLANK_NODE_PREFIX.length()));
        }
        return NodeFactory.createURI(uri);
    }

    /**
     * Parses a graph node from a string (or returns {@link Node#ANY} if blank).
     *
     * @param graph Graph string
     * @return Graph node
     */
    public static Node parseGraphNode(String graph) {
        if (StringUtils.isBlank(graph)) {
            return Node.ANY;
        }
        return parseUriNode(graph);
    }

    /**
     * Converts a node into a URI string.
     *
     * @param node Node to convert
     * @return URI string
     */
    public static String nodeToUri(Node node) {
        if (node.isURI()) {
            return node.getURI();
        } else if (node.isBlank()) {
            return TelicentGraphSchema.BLANK_NODE_PREFIX + node.getBlankNodeLabel();
        }
        throw new IllegalStateException("Not a node with a URI");
    }

    /**
     * Parses a URI filter argument.
     *
     * @param environment Data fetching environment
     * @param argument    Argument name
     * @return Parsed filter or null
     */
    public static UriFilterSpec parseUriFilter(DataFetchingEnvironment environment, String argument) {
        Object rawFilter = environment.getArgument(argument);
        if (rawFilter == null) {
            return null;
        }
        if (!(rawFilter instanceof Map<?, ?> filterMap)) {
            throw new IllegalArgumentException("Argument " + argument + " expected Map but got " + rawFilter.getClass());
        }
        Object rawValues = filterMap.get(TelicentGraphSchema.ARGUMENT_VALUES);
        if (!(rawValues instanceof List<?> valuesList)) {
            throw new IllegalArgumentException("Argument " + argument + " missing values list");
        }
        Set<String> values = new LinkedHashSet<>();
        for (Object value : valuesList) {
            if (!(value instanceof String)) {
                throw new IllegalArgumentException("Argument " + argument + " values must be strings");
            }
            values.add((String) value);
        }
        Object rawMode = filterMap.get(TelicentGraphSchema.ARGUMENT_MODE);
        FilterMode mode = FilterMode.INCLUDE;
        if (rawMode instanceof FilterMode) {
            mode = (FilterMode) rawMode;
        } else if (rawMode instanceof String) {
            mode = FilterMode.valueOf((String) rawMode);
        }
        return new UriFilterSpec(values, mode);
    }

    /**
     * Tests whether a node matches a URI filter.
     *
     * @param filter Filter specification
     * @param node   Node to test
     * @return True if matched
     */
    public static boolean matchesUriFilter(UriFilterSpec filter, Node node) {
        if (filter == null) {
            return true;
        }
        return filter.matches(nodeToUri(node));
    }

    /**
     * Tests whether a node matches a label filter.
     *
     * @param dsg         Dataset graph
     * @param node        Node to test
     * @param labelFilter Filter string
     * @return True if matched
     */
    public static boolean matchesLabelFilter(DatasetGraph dsg, Node node, String labelFilter) {
        return matchesLabelFilter(dsg, Node.ANY, node, labelFilter);
    }

    /**
     * Tests whether a node matches a label filter within a graph scope.
     *
     * @param dsg         Dataset graph
     * @param graph       Graph node scope
     * @param node        Node to test
     * @param labelFilter Filter string
     * @return True if matched
     */
    public static boolean matchesLabelFilter(DatasetGraph dsg, Node graph, Node node, String labelFilter) {
        if (StringUtils.isBlank(labelFilter)) {
            return true;
        }
        String needle = labelFilter.toLowerCase(Locale.ROOT);
        return streamLabelLiterals(dsg, graph, node)
                .map(n -> n.getLiteralLexicalForm().toLowerCase(Locale.ROOT))
                .anyMatch(value -> value.contains(needle));
    }

    /**
     * Tests whether a node matches a type filter.
     *
     * @param dsg        Dataset graph
     * @param node       Node to test
     * @param typeFilter Filter specification
     * @return True if matched
     */
    public static boolean matchesTypeFilter(DatasetGraph dsg, Node node, UriFilterSpec typeFilter) {
        return matchesTypeFilter(dsg, Node.ANY, node, typeFilter);
    }

    /**
     * Tests whether a node matches a type filter within a graph scope.
     *
     * @param dsg        Dataset graph
     * @param graph      Graph node scope
     * @param node       Node to test
     * @param typeFilter Filter specification
     * @return True if matched
     */
    public static boolean matchesTypeFilter(DatasetGraph dsg, Node graph, Node node, UriFilterSpec typeFilter) {
        if (typeFilter == null) {
            return true;
        }
        Set<String> filterValues = typeFilter.getValues();
        if (filterValues.isEmpty()) {
            return true;
        }
        Node graphNode = graph != null ? graph : Node.ANY;
        Stream<Node> types = dsg.stream(graphNode, node, RDF.type.asNode(), Node.ANY)
                                .map(Quad::getObject);
        if (typeFilter.getMode() == FilterMode.INCLUDE) {
            return types.anyMatch(type -> filterValues.contains(nodeToUri(type)));
        }
        return types.noneMatch(type -> filterValues.contains(nodeToUri(type)));
    }

    /**
     * Collects literal values for a predicate.
     *
     * @param dsg       Dataset graph
     * @param node      Subject node
     * @param predicate Predicate node
     * @return Literal values
     */
    public static List<RdfLiteral> literalsFor(DatasetGraph dsg, Node node, Node predicate) {
        return literalsFor(dsg, Node.ANY, node, predicate);
    }

    /**
     * Collects literal values for a predicate within a graph scope.
     *
     * @param dsg       Dataset graph
     * @param graph     Graph node scope
     * @param node      Subject node
     * @param predicate Predicate node
     * @return Literal values
     */
    public static List<RdfLiteral> literalsFor(DatasetGraph dsg, Node graph, Node node, Node predicate) {
        Node graphNode = graph != null ? graph : Node.ANY;
        return dsg.stream(graphNode, node, predicate, Node.ANY)
                  .map(Quad::getObject)
                  .filter(Node::isLiteral)
                  .map(RdfLiteral::new)
                  .collect(Collectors.toList());
    }

    /**
     * Streams all label-like literals.
     *
     * @param dsg  Dataset graph
     * @param node Subject node
     * @return Stream of label literals
     */
    public static Stream<Node> streamLabelLiterals(DatasetGraph dsg, Node node) {
        return streamLabelLiterals(dsg, Node.ANY, node);
    }

    /**
     * Streams all label-like literals within a graph scope.
     *
     * @param dsg   Dataset graph
     * @param graph Graph node scope
     * @param node  Subject node
     * @return Stream of label literals
     */
    public static Stream<Node> streamLabelLiterals(DatasetGraph dsg, Node graph, Node node) {
        Node graphNode = graph != null ? graph : Node.ANY;
        return Stream.of(RDFS.label.asNode(), SKOS.prefLabel.asNode(), SKOS.altLabel.asNode())
                     .flatMap(predicate -> dsg.stream(graphNode, node, predicate, Node.ANY).map(Quad::getObject))
                     .filter(Node::isLiteral);
    }

    /**
     * Builds a style instance from literal properties.
     *
     * @param dsg  Dataset graph
     * @param node Subject node
     * @return Style instance or null
     */
    public static Style styleFor(DatasetGraph dsg, Node node) {
        return styleFor(dsg, Node.ANY, node);
    }

    /**
     * Builds a style instance from literal properties within a graph scope.
     *
     * @param dsg   Dataset graph
     * @param graph Graph node scope
     * @param node  Subject node
     * @return Style instance or null
     */
    public static Style styleFor(DatasetGraph dsg, Node graph, Node node) {
        Node graphNode = graph != null ? graph : Node.ANY;
        Map<String, String> values = dsg.stream(graphNode, node, Node.ANY, Node.ANY)
                                        .filter(q -> q.getPredicate().isURI() && q.getObject().isLiteral())
                                        .collect(Collectors.toMap(q -> localName(q.getPredicate().getURI()),
                                                                  q -> q.getObject().getLiteralLexicalForm(),
                                                                  (existing, replacement) -> existing));
        if (values.isEmpty()) {
            return null;
        }
        Style style = new Style();
        setIfPresent(values, "shape", v -> style.setShape(parseShape(v)));
        setIfPresent(values, "fa_icon_class", style::setFa_icon_class);
        setIfPresent(values, "fa_icon_unicode", style::setFa_icon_unicode);
        setIfPresent(values, "fa_icon_class_free", style::setFa_icon_class_free);
        setIfPresent(values, "fa_icon_unicode_free", style::setFa_icon_unicode_free);
        setIfPresent(values, "line_color", style::setLine_color);
        setIfPresent(values, "fill_color", style::setFill_color);
        setIfPresent(values, "icon_color", style::setIcon_color);
        setIfPresent(values, "dark_mode_line_color", style::setDark_mode_line_color);
        setIfPresent(values, "dark_mode_fill_color", style::setDark_mode_fill_color);
        setIfPresent(values, "dark_mode_icon_color", style::setDark_mode_icon_color);
        setIfPresent(values, "description", style::setDescription);
        if (style.getShape() == null
                && style.getFa_icon_class() == null
                && style.getFa_icon_unicode() == null
                && style.getFa_icon_class_free() == null
                && style.getFa_icon_unicode_free() == null
                && style.getLine_color() == null
                && style.getFill_color() == null
                && style.getIcon_color() == null
                && style.getDark_mode_line_color() == null
                && style.getDark_mode_fill_color() == null
                && style.getDark_mode_icon_color() == null
                && style.getDescription() == null) {
            return null;
        }
        return style;
    }

    private static Shape parseShape(String raw) {
        if (StringUtils.isBlank(raw)) {
            return null;
        }
        String normalized = raw.trim().toUpperCase(Locale.ROOT);
        try {
            return Shape.valueOf(normalized);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    private static void setIfPresent(Map<String, String> values, String key, java.util.function.Consumer<String> setter) {
        String value = values.get(key);
        if (StringUtils.isNotBlank(value)) {
            setter.accept(value);
        }
    }

    private static String localName(String uri) {
        int hashIndex = uri.lastIndexOf('#');
        int slashIndex = uri.lastIndexOf('/');
        int index = Math.max(hashIndex, slashIndex);
        return index >= 0 ? uri.substring(index + 1) : uri;
    }
}
