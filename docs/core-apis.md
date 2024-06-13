# GraphQL for Jena Core APIs

The `graphql-jena-core` module provides the core APIs for executing GraphQL queries over RDF data.

## Schemas

The `io.telicent.jena.graphql.schemas` package contains [Schema Definitions](schemas.md) and some helper classes for
working with these.

The `GraphQLJenaSchemas` class provides static methods for loading up one of our predefined schemas in the form of a
GraphQL `TypeDefinitionRegistry` e.g.

```java
TypeDefinitionRegistry schema = GraphQLJenaSchemas.loadDatasetSchema();
```

You can then use the schema to create a `RuntimeWiring` that allows GraphQL queries to be evaluated.  For our predefined
schemas there may be a corresponding [Executor](#executors) defined.  For each schema you will also find a corresponding
class containing static constants for the field and type names within that schema e.g. `DatasetSchema.SUBJECT_FIELD`.

There is also the ability to load in arbitrary user defined schema files via `GraphQLJenaSchemas.loadSchema()` that
accepts the paths to one/more classpath resources that should be read and combined into a single GraphQL schema.  This
is the underlying mechanism used by the other load methods in this class to read in their schema plus our core schema.

### Model Classes

The `models` package under the `schemas` package also contains classes and enumerations that are used in parsing and
evaluating GraphQL queries against our schemas:

1. `NodeKind` - An enumeration of possible node kinds
2. `EdgeDirection` - An enumeration of possible edge directions
3. `WrappedNode` - A lightweight wrapper around a Jena `Node` that allows us to reuse `graphql-java`'s existing
   automatic support for reading fields from POJOs.
4. `TraversalNode` - A wrapper around a Jena `Node` specifically for the [Traversal](schemas.md#traversal) schema.
5. `TraversalEdge` - A wrapper around an edge in the RDF graph specifically for the [Traversal](schemas.md#traversal)
   schema.

## Fetchers

The `io.telicent.jena.graphql.fetchers` package contains our [`DataFetcher`][1] implementations.  A `DataFetcher` is
often referred to as a "resolver" in other GraphQL implementations, it handles the heavy lifting of retrieving the data
necessary to fulfill different parts of a GraphQL Query.

### `QuadsFetcher`

The `QuadsFetcher` retrieves `Quad` instances from a Jena `DatasetGraph` (that is supplied to the GraphQL execution
context).  It then translates those into `Triple` or `Map` objects depending upon which fields of the `Quad` the GraphQL
query is requesting.

### `NodeFetcher`

The `NodeFetcher` retrieves `WrappedNode` instances from the current source object, which may be a `Quad`, `Triple` or
`Map`, as produced by the `QuadsFetcher` bound to the parent field within the query.  This will produce an error during
execution if a query tries to access an invalid field for the current source object.

### `TraversalStartsFetcher`

The `TraversalStartsFetcher` retrieves `TraversalNode` instances from a Jena `DatasetGraph` that act as the starting
points of a graph traversal using the [Traversal](schemas.md#traversal) schema.

### `TraversalEdgesFetcher`

The `TraversalEdgesFetcher` retrieves `TraversalEdge` instances associated with a `TraversalNode` that is the current
source object.  This may be recursively applied if the [Traversal](schemas.md#traversal) query makes multiple hops out
from the initial starting nodes.

## Executors

We define a `GraphQLExecutor` interface which provides a simple API around the action of executing a GraphQL query.  It
has several overloads depending on how much control you want over execution:

```java
// Create an executor somehow
GraphQLExecutor executor = createExecutor();

// Run a simple query
ExecutionResult result = executor.execute("<your-graphql-query>");

// Run a query with variables
result 
  = executor.execute("<your-graphql-query>", 
                     Map.of("a", 1234, "b", true, "c", "some-string"));

// Run a query with an operation, variables and extensions
result 
  = executor.execute("<your-graphql-query>", 
                     "<your-operation>", 
                     Map.of("d", "example"), 
                     Map.of("extension", "foo"));
```

We also define a `GraphQLOverDatasetExecutor` interface which handles the common case of evaluating GraphQL queries over
a `DatasetGraph` instance, this provides the ability for each query to target a different `DatasetGraph` instance:

```java
// Create some datasets from somewhere
DatasetGraph a = createDataset("a.trig");
DatasetGraph b = createDataset("b.trig");

// Get an executor from somewhere
GraphQLOverDatasetExecutor executor = createExecutor();

// Define our GraphQL Request
GraphQLRequest request = new GraphQLRequest();
request.setQuery("<your-graphql-query>");

// Run a query against Dataset A
ExecutionResult resultsA = executor.execute(a, request);

// Run the same query against Dataset B
ExecutionResult resultsB = executor.execute(b, request);
```

### `AbstractDatasetExecutor`

As part of our implementation we provide an `AbstractDatasetExecutor` that implements both the aforementioned
interfaces. It takes a `DatasetGraph` as a constructor argument that acts as the default dataset over which queries are
executed.  However, by implementing the `GraphQLOverDatasetExecutor` interface you can also direct queries to other
`DatasetGraph` instances as needed.

You can extend this class and then supply implementations/overrides of several methods to control the resulting
executor's behaviour:

- `TypeDefinitionRegistry loadRawSchema()` - **MUST** be implemented in order to supply a GraphQL schema that the
  executor will implement.
- `RuntimeWiring.Builder buildRuntimeWiring()` -  **MUST** be implemented in order to wire up the `DataFetcher`'s
  necessary for queries in your schema to be executed.
- `boolean extendsCoreSchema()` - **SHOULD** be overridden to return `false` if your schema does not use our [Core
  Schema](schemas.md#core) as a base.
- `createLocalContext()` - **MAY** be overridden to return a custom GraphQL execution context object.  The default
  implementation just returns the `DatasetGraph` for the query.

### `DatasetExecution`

The `DatasetExecution` class is a concrete implementation of the `GraphQLExecutor`, it requires a Jena `DatasetGraph` to
execute queries over and uses our [Dataset](schemas.md#dataset) schema.

For example to get all subjects in the dataset:

```java
// Create an executor with a DatasetGraph supplied somehow
DatasetExecution executor = new DatasetExecution(createDatasetGraph());

// Execute our query
String query = """
query {
    quads {
        subject {
            kind
            value
        }
    }
}""";
ExecutionResult result = executor.execute(query);

// Do something with the results...
```

### `TraversalExecution`

The `TraversalExecution` class is similar to the [`DatasetExecution`](#datasetexecution) except it executes queries
using our [Traversal](schemas.md#traversal) schema.

## Utilities

The `io.telicent.jena.graphql.utils` package contains static utilities intended for use by other portions of the APIs,
or for applications built atop these APIs.

### `NodeFilter` 

`NodeFilter` provides static `parse(Map<String, Object>)` and `make(Node)` methods for parsing, or making, a `Node`
instance for use as a filter within a query.  This is used by `QuadsFetcher` for parsing filters supplied in queries,
and in test cases for generating filters to supply to test queries.

There are other static methods for parsing other arguments used as various forms of Node filtering within our 
data fetchers.

## Servers

The `io.telicent.jena.graphql.server` package contains static utilities and data model classes intended for use in
exposing GraphQL Requests and Responses via a server.

### `GraphQLRequest`

The `GraphQLRequest` model class represents the data structure used to communicate GraphQL Requests.  It is typically
expected to be serialised to/from JSON.

### `GraphQLOverHttp`

`GraphQLOverHttp` contains a variety of constants and methods related to servicing GraphQL Requests over HTTP.  For
example there is a `parseRequest(InputStream)` method that parses a `GraphQLRequest` from an input stream.

This is used within the [Standalone Server](standalone-server.md) module to aid in parsing GraphQL over HTTP Requests
ready for execution.

This is also used within the [Fuseki Module](fuseki-module.md) to support the parsing and serialisation of the GraphQL
requests and responses.

[1]: https://www.graphql-java.com/documentation/data-fetching
