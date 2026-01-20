# GraphQL for Jena Schemas

The [Core APIs](core-apis.md) module `graphql-jena-core` includes an `io.telicent.jena.graphql.schemas` package that
contains several predefined GraphQL schemas for accessing RDF data.  In this part of the documentation we describe those
schemas in more detail.

- [Core](#core) - A Core schema for RDF centric queries.
- [Dataset](#dataset) - A basic schema extending Core with the ability to query quads from a dataset.
- [Traversal](#traversal) - A traversal schema extending Core with the ability to traverse the graph from a set of
  starting nodes.
- [Telicent (IES)](#telicent-ies) - An IES compliant schema used in telicent deployments.
- [Telicent Ontology Extensions](#telicent-ontology-extensions) - Ontology-focused fields added to the Telicent schema.

## Core

The Core schema can be found defined in the `core.graphqls` file within the resources of the `graphql-jena-core` module.
To avoid any naming clashes this is contained within the same `io.telicent.jena.graphql.schemas` package.

This schema is RDF centric in that is exposes the structure of RDF terms, or nodes, as this schema refers to them to the
consumer of the API.  This schema **DOES NOT** actually expose any mechanisms by which to query the data, i.e., it has
no root-level operation explicitly defined, it is instead intended to be reused as the base of other RDF centric schemas
e.g. [Dataset](#dataset) and [Traversal](#traversal).

The schema defines the following types:

```graphql
type Triple {
    subject: Node!
    predicate: Node!
    object: Node!
}

type Quad {
    subject: Node!
    predicate: Node!
    object: Node!
    graph: Node!
}

enum NodeKind {
    URI
    BLANK
    PLAIN_LITERAL
    LANGUAGE_LITERAL
    TYPED_LITERAL
    VARIABLE
    TRIPLE
}

type Node {
    kind: NodeKind!
    value: String
    triple: Triple
    datatype: String
    language: String
}

input NodeFilter {
    kind: NodeKind!
    value: String
    triple: TripleFilter
    datatype: String
    language: String
}

input TripleFilter {
    subject: NodeFilter!
    predicate: NodeFilter!
    object: NodeFilter!
}
```

**NB:** Within GraphQL schema objects used as arguments/variables to a query are referred to as Input types and
**MUST** be defined independently of Output types.  Thus, our schema defines a `NodeFilter` input type that mirrors the
structure of our `Node` type so that we can use the same representation for both filtering and reading the data.

## Dataset

The Dataset schema can be found defined in the `dataset.graphqls` file within the resources of the `graphql-jena-core`
module.  To avoid any naming clashes this is contained within the same `io.telicent.jena.graphql.schemas` package.

This schema is RDF centric in that is exposes the structure of RDF terms, or nodes, as this schema refers to them to the
consumer of the API.  This is based upon the aforementioned [Core](#core) schema that defines the basic types used here.
It is intentionally minimalist and designed to show that GraphQL can be used to access the underlying RDF dataset
without any attempt to map the RDF data model into an alternative data model.

The schema provides a single root operation `query` that can be used to retrieve the Quads defined within the dataset.
For example the following query would return full details for all quads:

```graphql
query {
    quads {
        subject {
            kind
            value
        }
        predicate {
            kind
            value
        }
        object {
            kind
            value
            language
            datatype
        }
        graph {
            kind
            value
        }
    }
}
```

Note that since we only expect literals in the `object` field we only select the `language` and `datatype` fields for
that sub-field and not for the other fields.

We can use other GraphQL functionality such as fragments in our queries, for example if we only cared about the `kind`
and `value` for each field of the quads we could do the following:

```graphql
query {
    quads {
        subject {
            ...NodeFragment
        }
        predicate {
            ...NodeFragment
        }
        object {
            ...NodeFragment
        }
        graph {
            ...NodeFragment
        }
    }
}
fragment NodeFragment on Node {
    kind
    value
}
```

The `quads` field takes arguments that allow the quads to be filtered to only those of interest.  If these arguments are
not specified, or specified as `null` then each is treated as a wildcard that matches any value in that field.  For
example consider the following query that uses GraphQL aliases and arguments to the `quads` field to return two
different lists of quads:

```graphql
query {
    types: quads(predicate: { kind: URI, value: "http://www.w3.org/1999/02/22-rdf-syntax-ns#type" }, object: { kind: URI, value: "http://www.w3.org/2000/01/rdf-schema#Class" }) {
        subject {
            kind
            value
        }
    }
    instances: quads(predicate: { kind: URI, value: "http://www.w3.org/1999/02/22-rdf-syntax-ns#type" }) {
        subject {
            kind
            value
        }
    }
}
```

The `DatasetSchema` class provides constants for all the fields defined within this schema.

## Traversal

The Traversal schema can be found defined in the `traversal.graphqls` file within the resources of the
`graphql-jena-core` module.  To avoid any naming clashes this is contained within the same
`io.telicent.jena.graphql.schemas` package.

This schema is RDF centric in that it exposes the structure of RDF terms, or nodes, as this schema refers to them to the
consumer of the API.  This is based upon the aforementioned [Core](#core) schema that defines the basic types used here.
It is intentionally minimalist and designed to show that GraphQL can be used to access the underlying RDF dataset
without any attempt to map the RDF data model into an alternative data model.  

In contrast to our [Dataset](#dataset) schema the Traversal schema is focused upon starting from some set of nodes
within the RDF dataset and then traversing the graph from those.  Traversal at each node can follow outgoing and/or
incoming edges, optionally filtering the traversed edges both by predicate and the kind of node present at the other end
of the edge.

This schema defines the following additional types:

```graphql
type TraversalNode {
    node: Node
    outgoing(predicate: [NodeFilter!], kinds: [NodeKind!]): [TraversalEdge!]
    incoming(predicate: [NodeFilter!], kinds: [NodeKind!]): [TraversalEdge!]
}

type TraversalEdge {
    edge: Node
    direction: EdgeDirection
    target: TraversalNode!
}

enum EdgeDirection {
    OUT
    IN
}
```

And provides a single root operation `query` that can be used to traverse the graph.  This has a single top level fields
`nodes` identifying the nodes from which to start the traversal.  For example all outgoing edges from a specific node
could be retrieved as follows:

```graphql
query {
    nodes(starts: [ { kind: URI, value: "https://example.org/Adam"} ]) {
        node {
            kind
            value
        }
        outgoing {
            edge {
                kind
                value
            }
            direction
            target {
                node {
                    kind
                    value
                }
            }
        }
    }
}
```

The `starts` argument is used to define which nodes to start the traversal from.  This can be omitted to start from all
unique nodes in the RDF dataset **BUT** this is strongly discouraged as this will not scale with dataset size. You
**SHOULD** always supply one/more starting nodes for the traversal.

We can write much more complex queries that traverse multiple steps outwards from the starting nodes e.g.

```graphql
query {
    nodes(starts: [ { kind: URI, value: "https://starwars.com#planet_Tatooine" } ]) {
        node {
            value
        }
        incoming(kinds: [ BLANK, URI ]) {
            edge {
                kind
                value
            }
            target {
                node {
                    kind
                    value
                }
                outgoing(predicate: [ { kind: URI, value: "http://ies.data.gov.uk/ontology/ies4#hasName" }], kinds: [ BLANK, URI ]) {
                    target {
                        node {
                            value
                        }
                        outgoing(predicate: [ { kind: URI, value: "http://ies.data.gov.uk/ontology/ies4#representationValue" }], kinds: [ PLAIN_LITERAL, LANGUAGE_LITERAL, TYPED_LITERAL ]) {
                            target {
                                node {
                                    value
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
```

This query starts from a specific node - `https://starwars.com#planet_Tatooine` - and first traverses the incoming edges
whose other ends are either Blank/URI nodes.  From those nodes it then traverses their outgoing edges of type
`http://ies.data.gov.uk/ontology/ies4#hasName` and follows those nodes outgoing edges of type
`http://ies.data.gov.uk/ontology/ies4#representationValue` to find their actual name representations, provided those are
literals.  In this query we see both the `predicate` and `kinds` arguments used to filter which edges are traversed,
these arguments may be used on both the `outgoing` and `incoming` fields.


## Telicent (IES)

In telicent deployments, we make use of an Information Exchange Standard (IES) compliant schema. The IES ontology is 
an open standard developed by the UK Government for making the sharing of information across knowledge stores easier 
and less complex by means of a common vocabulary. More information can be found [here](https://github.com/dstl/IES4)

The schema definition can be found in the `ies.graphqls` file within the resources of the
`telicent-graph-schema` module. To avoid any naming clashes this is contained within the same
`io.telicent.jena.graphql.schemas` package.

The schema is defined as follows:

```graphql
type Node {
    id: ID! # Used if you want client-side caching, but will return the same string as uri
    uri: String! # The uri of the resource
    uriHash: String! @deprecated # A SHA1 hash of the uri
    shortUri: String! # If a shortened (namespace prefixed) form of the uri is available, otherwise returns full uri
    types(limit: Int = 50, offset: Int = 1): [Node]! # An array of types for the Node - i.e. the classes it is an instance of.
    properties(limit: Int = 50, offset: Int = 1): [Property]! # An array of literal properties of the Node
    outRels(limit: Int = 50, offset: Int = 1, predicateFilter: UriFilter, typeFilter: UriFilter, nodeFilter: UriFilter): [Rel]! # An array of Rels where the current Node is in the domain position
    inRels(limit: Int = 50, offset: Int = 1, predicateFilter: UriFilter, typeFilter: UriFilter, nodeFilter: UriFilter): [Rel]! # An array of Rels where the current Node is in the range position
    relCounts: NodeRelCounts! # Relationship counts information
    relFacets: NodeRelFacets!
    instances(limit: Int = 50, offset: Int = 1): [Node] # If the node is a class, this will return an array of its instances
}

type Rel { # A subject-predicate-object statement
    id: ID! # A (sorta) unique ID made from hashing (SHA1) the "<subject> <predicate> <object>" string
    domain: Node! # AKA subject
    domain_id: String!
    predicate: String! # AKA property
    range_id: String!
    range: Node! # AKA object
}

type NodeRelCounts { # Counts of relationships available for a Node
    inRels(predicateFilter: UriFilter, typeFilter: UriFilter, nodeFilter: UriFilter): Int
    outRels(predicateFilter: UriFilter, typeFilter: UriFilter, nodeFilter: UriFilter): Int
    properties: Int
    types: Int
    instances: Int
}

type NodeRelFacets {
    inRels: RelFacetInfo!
    outRels: RelFacetInfo!
}

type RelFacetInfo {
    types(typeFilter: UriFilter, nodeFilter: UriFilter): [FacetInfo!]!
    predicates(predicateFilter: UriFilter, nodeFilter: UriFilter): [FacetInfo!]!
}

type FacetInfo {
    uri: String! # Full URI
    shortUri: String! # If a shortened (namespace prefixed) form of the uri is available, otherwise returns full uri
    count: Int
}

type Property { #A literal property
    predicate: String!
    shortPredicate: String!
    value: String!
    datatype: String
    language: String
}

enum FilterMode {
    INCLUDE
    EXCLUDE
}

input UriFilter {
    values: [String!]!
    mode: FilterMode = INCLUDE
}

type State {
    uri: String!
    type: String!
    start: String
    end: String
    period: String
    relations(limit: Int = 50, offset: Int = 1): [NonDirectionalRel]!
    relCounts: StateRelCounts!
}

type StateRelCounts {
    relations: Int
}

type NonDirectionalRel {
    predicate: String!
    entity: Node!
}

enum SearchType {
    QUERY,
    TERM,
    PHRASE,
    WILDCARD
}

type SearchResults {
    searchTerm: String!
    searchType: SearchType!
    limit: Int!
    offset: Int!
    maybeMore: Boolean!
    nodes: [Node]
}
```

While this is still fairly RDF centric it models data quite differently than the simpler [Dataset](#dataset) or
[Traversal](#traversal) schemas do, and includes higher level IES concepts like states.  It has the following query
operations defined:

- `search` and `searchWithMetadata` for using a search against Telicent Search API as the starting point for retrieving 
  nodes in 
  the dataset
- `getAllEntities` for retrieving all nodes in the dataset
- `states` for retrieving states (an IES ontology concept) of other entities in the dataset
- `node` for retrieving a single node from the dataset
- `nodes` for retrieving multiple nodes from the dataset

```graphql
type Query {
    search(
        graph: String
        searchTerm: String!
        searchType: SearchType
        limit: Int
        offset: Int
        typeFilter: String
    ): [Node] @deprecated(reason: "Use `searchWithMetadata` which offers richer response schema")
    searchWithMetadata(
        graph: String
        searchTerm: String!
        searchType: SearchType
        limit: Int
        offset: Int
        typeFilter: String
    ): SearchResults
    getAllEntities(graph: String): [Node]
    states(uri: String!, limit: Int = 50, offset: Int = 1): [State]!
    node(graph: String, uri: String!): Node
    nodes(graph: String, uris: [String!]!, limit: Int = 50, offset: Int = 1): [Node]
}
```

### Paging in the Telicent (IES) Schema

As of `0.10.0` the Telicent (IES) Schema offers paging capabilities on schema fields and top level queries that return
arrays.  Paging is achieved by supplying `limit` and `offset` arguments to the relevant fields, bear in mind the
following:

- A maximum limit of `250` is enforced in the implementation even if a higher `limit` value is supplied.
- Offsets are 1 based i.e. `offset: 1` means start from the 1st result

In order to determine whether to page clients can request the `relCounts` field on the `Node` and `State` types, this
field provides total counts for all paging enabled fields.  Clients can use these counts, and the knowledge of the
`limit` and `offset` values they passed in, to determine whether they have retrieved all results.  

### Facets in the Telicent (IES) Schema

As of `0.10.1` the Telicent (IES) Schema offers relationship facet information for the `Node` type, facets can be
computed for both the `inRels` and `outRels`.  Two kinds of facets are available, `predicates` and `types`.  The
`predicates` facet lists the unique predicates associated with a `Node`, and the `count` of relationships that use that
predicate.  The `types` facet lists the unique types associated with the target `Node` of a relationship, i.e. the
`rdf:type`'s expressed on them, again it lists the unique types and the number of occurrences of each type.  For example
the following query computes facets for a node:

```graphql
query Node($uri: String!) {
  node(uri: $uri) {
    id
    uri
    uriHash
    relCounts {
      inRels
      outRels
    }    
    relFacets {
      inRels {
        predicates {
          uri
          count
        }
        types {
          uri
          count
        }
      }
      outRels {
        predicates {
          uri
          count
       }
       types {
          uri
          count
       }
      }
    }
  }
}
```

Callers can use this facet information to add [filters](#filtering-in-the-telicent-ies-schema) to their subsequent
requests.

### Filtering in the Telicent (IES) Schema

As of `0.10.1` the Telicent (IES) Schema offers predicate and type based filtering on some schema fields.  A filter is
expressed in terms of a `mode`, either `INCLUDE` or `EXCLUDE`, and the `values` to filter by.  The `values` to filter by 
are treated as URIs.  For example:

```graphql
query Node($uri: String!) {
  node(uri: $uri) {
    id
    uri
    uriHash
    types {
      uri
    }
    properties {
      predicate
      value
    }
    inRels(predicateFilter: { mode: INCLUDE, values: [ "http://ies.data.gov.uk/ontology/ies4#inLocation" ]}) {
      id
      domain_id
      range_id
      predicate
    }
    outRels(predicateFilter: { mode: INCLUDE, values: [ "http://ies.data.gov.uk/ontology/ies4#isPartOf" ]}) {
      id
      domain_id
      range_id
      predicate
      range {
        uri
      }
    }    
    relCounts {
      inRels(predicateFilter: { mode: INCLUDE, values: [ "http://ies.data.gov.uk/ontology/ies4#inLocation" ]})
      outRels(predicateFilter: { mode: INCLUDE, values: [ "http://ies.data.gov.uk/ontology/ies4#isPartOf" ]})
    }    
    relFacets {
      inRels {
        predicates(predicateFilter: { mode: INCLUDE, values: [ "http://ies.data.gov.uk/ontology/ies4#inLocation" ]}) {
          uri
          count
        }
      }
      outRels {
        predicates(predicateFilter: { mode: INCLUDE, values: [ "http://ies.data.gov.uk/ontology/ies4#isPartOf" ]}) {
          uri
          count
       }
      }
    }
    }
}
```

## Telicent Ontology Extensions

The Telicent schema now exposes ontology information via a dedicated `ontology` root field on the same endpoint. This
adds a lightweight, UI-friendly way to retrieve classes, property definitions, labels, and styling metadata alongside
the existing node and relationship data. It is intended for front-end use cases like styling, type browsing, and schema
inspection without forcing clients to handcraft RDF queries.

At a high level:

- `ontology` is a single entry point for ontology data.
- `classes` returns class definitions (RDFS/OWL) with labels, hierarchy, instances, and style.
- `ObjectPropertyDefinitions` and `DatatypePropertyDefinitions` expose property definitions with domain/range and labels.
- `Style` is included as a structured block for icon/shape/color metadata.

### Detailed Schema Breakdown

The ontology fields are additive and live in `ies.graphqls` within the `telicent-graph-schema` module. These are the
key types and how they map to RDF:

#### Query Root

```graphql
type Query {
  ontology(graph: String): Ontology
}
```

#### Ontology Type

```graphql
type Ontology {
  classes(limit: Int = 50, offset: Int = 1, uriFilter: UriFilter, labelFilter: String, typeFilter: UriFilter): [RdfsClass]!
  classCount(uriFilter: UriFilter, labelFilter: String, typeFilter: UriFilter): Int!
  class(uri: String!): RdfsClass
  DatatypePropertyDefinitions(limit: Int = 50, offset: Int = 1, uriFilter: UriFilter, labelFilter: String): [DatatypePropertyDefinition]!
  DatatypePropertyDefinitionCount(uriFilter: UriFilter, labelFilter: String): Int!
  DatatypePropertyDefinition(uri: String!): DatatypePropertyDefinition
  ObjectPropertyDefinitions(limit: Int = 50, offset: Int = 1, uriFilter: UriFilter, labelFilter: String): [ObjectPropertyDefinition]!
  ObjectPropertyDefinitionCount(uriFilter: UriFilter, labelFilter: String): Int!
  ObjectPropertyDefinition(uri: String!): ObjectPropertyDefinition
}
```

Notes:
- `uriFilter` uses the existing `UriFilter` input (include/exclude semantics).
- `labelFilter` is a case-insensitive substring match over `rdfs:label`, `skos:prefLabel`, and `skos:altLabel`.
- `typeFilter` applies to class `rdf:type` values.
- Counts apply the same filters as their list equivalents.
- `graph` scopes ontology resolution to a named graph when provided.

#### RdfsClass

```graphql
type RdfsClass {
  id: ID!
  isOwlClass: Boolean!
  uri: String!
  domain_object_properties(limit: Int = 50, offset: Int = 1): [ObjectPropertyDefinition]!
  domain_datatype_properties(limit: Int = 50, offset: Int = 1): [DatatypePropertyDefinition]!
  range_properties(limit: Int = 50, offset: Int = 1): [ObjectPropertyDefinition]!
  subClasses(limit: Int = 50, offset: Int = 1): [RdfsClass]!
  superClasses(limit: Int = 50, offset: Int = 1): [RdfsClass]!
  instances(limit: Int = 50, offset: Int = 1): [Node]!
  types(limit: Int = 50, offset: Int = 1): [Node]!
  style: Style
  labels: [RdfLiteral]!
  comments: [RdfLiteral]!
  skosPrefLabels: [RdfLiteral]!
  skosAltLabels: [RdfLiteral]!
}
```

Mapping details:
- Classes are discovered from `rdf:type rdfs:Class` and `rdf:type owl:Class`.
- `subClasses`/`superClasses` use `rdfs:subClassOf`.
- `instances` are `rdf:type` occurrences for that class.
- `style` reads literal predicates on the class whose local name matches the style field names (see below).

#### Property Definitions

```graphql
type ObjectPropertyDefinition {
  domain: RdfsClass
  range: RdfsClass
  subProperties(limit: Int = 50, offset: Int = 1): [ObjectPropertyDefinition]!
  superProperties(limit: Int = 50, offset: Int = 1): [ObjectPropertyDefinition]!
  labels: [RdfLiteral]!
  comments: [RdfLiteral]!
  skosPrefLabels: [RdfLiteral]!
  skosAltLabels: [RdfLiteral]!
}

type DatatypePropertyDefinition {
  domain: RdfsClass
  range: XmlSchemaDatatype
  subProperties(limit: Int = 50, offset: Int = 1): [DatatypePropertyDefinition]!
  superProperties(limit: Int = 50, offset: Int = 1): [DatatypePropertyDefinition]!
  labels: [RdfLiteral]!
  comments: [RdfLiteral]!
  skosPrefLabels: [RdfLiteral]!
  skosAltLabels: [RdfLiteral]!
}
```

Mapping details:
- Object properties are discovered from `rdf:type owl:ObjectProperty`.
- Datatype properties are discovered from `rdf:type owl:DatatypeProperty`.
- `domain`/`range` use `rdfs:domain` and `rdfs:range`.
- `subProperties`/`superProperties` use `rdfs:subPropertyOf`.

#### Supporting Types

```graphql
type RdfLiteral {
  value: String!
  language: String
  datatype: String
}

type XmlSchemaDatatype {
  id: ID!
  uri: String!
}

enum shape {
  CIRCLE
  ELLIPSE
  DIAMOND
  RECTANGLE
  ROUNDED_RECTANGLE
}

type Style {
  shape: shape
  fa_icon_class: String
  fa_icon_unicode: String
  fa_icon_class_free: String
  fa_icon_unicode_free: String
  line_color: String
  fill_color: String
  icon_color: String
  dark_mode_line_color: String
  dark_mode_fill_color: String
  dark_mode_icon_color: String
  description: String
}
```

Style mapping: the implementation looks for literal predicates whose local name matches the fields above (e.g.
`line_color`, `fa_icon_class`). Shapes are parsed case-insensitively.

### Example Queries

Classes with labels and styles:

```graphql
query OntologyClasses {
  ontology {
    classes(limit: 50) {
      uri
      labels { value language }
      style { shape line_color }
    }
  }
}
```

What this does:
- Returns up to 50 class definitions.
- For each class, returns its URI, any `rdfs:label` literals, and style metadata (shape + line color).

Expected response (with the sample fixture loaded):

```json
{
  "data": {
    "ontology": {
      "classes": [
        {
          "uri": "http://example.com/Person",
          "labels": [{ "value": "Person", "language": "en" }],
          "style": { "shape": "CIRCLE", "line_color": "#123456" }
        },
        {
          "uri": "http://example.com/Agent",
          "labels": [],
          "style": null
        },
        {
          "uri": "http://example.com/Place",
          "labels": [],
          "style": null
        }
      ]
    }
  }
}
```

Note: `labels` is empty for `Agent` and `Place` because the fixture only assigns `skos:prefLabel`/`skos:altLabel`.

Property definitions with domains/ranges:

```graphql
query OntologyProperties {
  ontology {
    ObjectPropertyDefinitions(limit: 50) {
      labels { value }
      domain { uri }
      range { uri }
    }
    DatatypePropertyDefinitions(limit: 50) {
      labels { value }
      domain { uri }
      range { uri }
    }
  }
}
```

What this does:
- Returns object and datatype property definitions.
- For each property, returns labels, domain, and range, plus one level of sub/super properties.

Expected response (with the sample fixture loaded):

```json
{
  "data": {
    "ontology": {
      "ObjectPropertyDefinitions": [
        {
          "labels": [{ "value": "Located in" }],
          "domain": { "uri": "http://example.com/Person" },
          "range": { "uri": "http://example.com/Place" },
          "subProperties": [{ "labels": [{ "value": "locatedNear" }] }]
        },
        {
          "labels": [],
          "domain": null,
          "range": null,
          "subProperties": []
        }
      ],
      "DatatypePropertyDefinitions": [
        {
          "labels": [{ "value": "Name" }],
          "domain": { "uri": "http://example.com/Person" },
          "range": { "uri": "http://www.w3.org/2001/XMLSchema#string" },
          "superProperties": []
        },
        {
          "labels": [{ "value": "Full Name" }],
          "domain": null,
          "range": null,
          "superProperties": [{ "labels": [{ "value": "Name" }] }]
        }
      ]
    }
  }
}
```

### Example Test Data

A small RDF fixture is included to illustrate the ontology schema and can be reused in local testing:

- `telicent-graph-schema/src/test/resources/data/ontology-example.ttl`
- `telicent-graph-schema/src/test/resources/data/ontology-example.trig`
- `telicent-graph-schema/src/test/resources/queries/ontology-classes.graphql`
- `telicent-graph-schema/src/test/resources/queries/ontology-properties.graphql`

It contains:

- `ex:Person`, `ex:Agent`, `ex:Place` as classes
- Object and datatype properties with domain/range
- Style literals on `ex:Person`
- A sample instance `ex:Alice`

#### Quick Local Harness

The snippet below shows how to load the TRIG fixture and execute the example GraphQL queries using the Telicent schema:

```java
import io.telicent.jena.graphql.execution.telicent.graph.TelicentGraphExecutor;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import java.nio.file.Files;
import java.nio.file.Path;

public class OntologyQueryHarness {
    public static void main(String[] args) throws Exception {
        DatasetGraph dsg = DatasetGraphFactory.create();
        RDFDataMgr.read(dsg, "telicent-graph-schema/src/test/resources/data/ontology-example.trig");

        String classesQuery = Files.readString(
                Path.of("telicent-graph-schema/src/test/resources/queries/ontology-classes.graphql"));
        String propertiesQuery = Files.readString(
                Path.of("telicent-graph-schema/src/test/resources/queries/ontology-properties.graphql"));

        TelicentGraphExecutor executor = new TelicentGraphExecutor(dsg);
        System.out.println(executor.execute(classesQuery).toSpecification());
        System.out.println(executor.execute(propertiesQuery).toSpecification());
    }
}
```

If you prefer hitting a running GraphQL endpoint instead, you can post the same queries directly. Assuming the Telicent
schema is available at `/dataset/telicent/graphql`:

```bash
curl -s http://localhost:11666/dataset/telicent/graphql \\
  -H 'Content-Type: application/json' \\
  --data-binary "{\"query\": \"$(tr '\\n' ' ' < telicent-graph-schema/src/test/resources/queries/ontology-classes.graphql)\"}"

curl -s http://localhost:11666/dataset/telicent/graphql \\
  -H 'Content-Type: application/json' \\
  --data-binary "{\"query\": \"$(tr '\\n' ' ' < telicent-graph-schema/src/test/resources/queries/ontology-properties.graphql)\"}"
```

In this example we filter `inRels` - incoming relationships - to only those using the `ies:inLocation` predicate, and
`outRels` - outgoing relationships - to only those using the `ies:isPartOf` predicate.  If you want the corresponding
`inRels` and `outRels` count fields of the `relCounts` object to reflect your filtering, then you **MUST** also apply
the filters to those fields, otherwise the counts will not reflect your filter.

From `0.10.2` we also support `nodeFilter` for both `inRels` and `outRels`, this allows the caller to limit the
returned relationships to those coming from/going to specific nodes in the graph.

From `0.10.3` if you want the `types` and `predicates` fields of the `RelFacetInfo` objects to reflect your filtering
then you **MUST** also apply the relevant filters to those fields.

A few things to be aware of when using filters:

- If multiples filters are present then **ALL** filter conditions **MUST** be satisfied.
- For some filters a `mode: INCLUDE` filter will be more performant than a `mode: EXCLUDE` filter, potentially
  substantially so depending on the dataset you are querying.
- As noted above `relCounts`/`relFacets` **SHOULD** also have filters applied otherwise counts/facets won't reflect
  the filters.
- An empty `values` list is an error and will result in a rejected query.
