# GraphQL for Jena Schemas

The [Core APIs](core-apis.md) module `graphql-jena-core` includes an `io.telicent.jena.graphql.schemas` package that
contains several predefined GraphQL schemas for accessing RDF data.  In this part of the documentation we describe those
schemas in more detail.

- [Core](#core) - A Core schema for RDF centric queries.
- [Dataset](#dataset) - A basic schema extending Core with the ability to query quads from a dataset.
- [Traversal](#traversal) - A traversal schema extending Core with the ability to traverse the graph from a set of
  starting nodes.
- [Telicent (IES)](#telicent-ies) - An IES compliant schema used in telicent deployments.

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
In telicent deployments, we make use of an Information Exchange Standard (IES) compliant schema. The IES is 
an open standard developed by the UK Government for making the sharing of information across knowledge stores easier 
and less complex by means of a common vocabulary. More information can be found [here](https://github.com/dstl/IES4)

The schema definition can be found in the `ies.graphqls` file within the resources of the
`telicent-graph-schema` module. To avoid any naming clashes this is contained within the same
`io.telicent.jena.graphql.schemas` package.

The schema is defined as follows:
```graphql
type Node {
    id: ID!
    uri: String!
    shortUri: String!
    types: [Node]!
    properties: [Property]!
    outRels: [Rel]!
    inRels: [Rel]!
    instances: [Node]
}

type Rel {
    id: ID!
    domain: Node!
    domain_id: String!
    predicate: String! 
    range_id: String!
    range: Node!
}

type Property { 
    predicate: String!
    shortPredicate: String!
    value: String!
    datatype: String
    language: String
}

type State {
    uri: String!
    type: String!
    start: String
    end: String
    period: String
    relations: [NonDirectionalRel]!
}

type NonDirectionalRel {
    predicate: String!
    entity: Node!
}
```

Furthermore, the following Query types are defined for operations that should be self-evident:
- search
- getAllEntities
- states
- node
- nodes

```graphql
type Query {
    search(
        graph: String
        searchTerm: String!
    ): [Node]
    getAllEntities(graph: String): [Node]
    states(uri: String!): [State]!
    node(graph: String, uri: String!): Node
    nodes(graph: String, uris: [String!]!): [Node]
}
```
