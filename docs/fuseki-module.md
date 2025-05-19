# Fuseki GraphQL Module

The `graphql-fuseki-module` module provides a [Fuseki Module][1] that allows users to add GraphQL endpoints to a Fuseki
Server.

## Enabling the Module

### Automatic

The module includes the relevant `META-INF/services` file so if your Fuseki deployment is using automatic module
configuration the module will be automatically detected and loaded.

### Manual

If you are manually controlling modules then it can be enabled by adding it to the list of [Fuseki Modules][1] via
programmatic configuration e.g.

```java
FusekiServer.Builder builder
  = FusekiServer.create()
                .fusekiModules(FusekiModules.create(new FMod_GraphQL()))
                .parseConfigFile("your-config.ttl");
```

## Defining GraphQL Endpoints

GraphQL endpoints are defined as `fuseki:endpoint` instances on a `fuseki:Service` in the Fuseki configuration file.
For example:

```ttl
PREFIX :        <#>
PREFIX fuseki:  <http://jena.apache.org/fuseki#>
PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#>
PREFIX ja:      <http://jena.hpl.hp.com/2005/11/Assembler#>
PREFIX graphql: <https://telicent.io/fuseki/modules/graphql#>

:service1 rdf:type fuseki:Service ;
    fuseki:name "ds" ;

    fuseki:endpoint [ fuseki:operation fuseki:query ; fuseki:name "query" ] ;
    fuseki:endpoint [ fuseki:operation fuseki:gsp-rw ; fuseki:name "gsp" ] ;

    # Defining multiple GraphQL operations with different schemas
    fuseki:endpoint [ fuseki:operation graphql:graphql ;
                      ja:context [ ja:cxtName "graphql:executor" ;
                                   ja:cxtValue "io.telicent.jena.graphql.execution.DatasetExecutor" ];
                      fuseki:name "dataset-graphql" ];
    fuseki:endpoint [ fuseki:operation graphql:graphql ;
                      ja:context [ ja:cxtName "graphql:executor" ;
                                   ja:cxtValue "io.telicent.jena.graphql.execution.TraversalExecutor" ];
                      fuseki:name "traversal-graphql" ];

    fuseki:dataset :dataset ;
    .

# Local dev
:dataset rdf:type ja:MemoryDataset .
```

In this example we have a dataset exposed at `/ds` which has two GraphQL endpoints added to it, one available at
`/ds/dataset-graphql` and one at `/ds/traversal-graphql`.

Note that each endpoint must define a `graphql:executor` context property that provides the fully qualified name of a
Java class that implements the `GraphQLOverDatasetExecutor` interface.  This implementation **MUST** provide a single
argument constructor that takes in a `DatasetGraph`.  If this property is not specified, or its value is invalid, a
warning will be logged to the Fuseki configuration log indicating the problem.  If you are seeing these warnings then
please adjust your configuration, and/or custom `GraphQLOverDatasetExecutor` implementation accordingly.

In the case of a warning being issued the endpoint will still exist, but it will not have a Fuseki `ActionProcessor`
associated with it and thus will be unable to handle GraphQL Requests.  In this event all requests to that endpoint will
be rejected with a `400 Bad Request` with a `text/plain` response body containing a message like the following:

```
No processor: dataset=/ds: op=graphql
```

## Making GraphQL Requests

Once you have defined some GraphQL endpoints you can then make GraphQL requests to them.  This module implements the
[GraphQL Over HTTP Specification][2] which means GraphQL requests are accepted in two ways:

1. As a `GET` request with at least the `query` parameter supplied containing a GraphQL query.
2. As a `POST` request with an `application/json` request body containing a JSON encoding of the GraphQL request
   parameters as defined in the specification.

If a non-GraphQL request is received by these endpoints, e.g. insufficient parameters for a `GET`, wrong `Content-Type`
for a `POST`, then the endpoint responds with a `400 Bad Request` with a brief textual error message in the response
body.

If a GraphQL request is received by these endpoints then it attempts to execute the request and responds with a
`Content-Type` of `application/graphql-response+json`.  The request body will encode a GraphQL Response per the GraphQL
specifications.  The response will be one of the following status codes:

- A `200 OK` if the request succeeded, or partially succeeded (meaning there may be some errors but some data was
  returned).
- A `400 Bad Request` if the request was invalid, or failed without generating any data.

In the `400 Bad Request` case the response will contain sufficient error details to allow the user to understand why
their request was invalid and adjust it accordingly.

The endpoints do support GraphQL introspection queries so a user can query the endpoints to discover the supported
GraphQL schemas if they are not aware of those ahead of time.

### Using GraphQL from Browser Applications

The GraphQL Endpoints will respond to `OPTIONS` requests appropriately.  This relies upon your Fuseki server instance
having Cross Origin Resource Sharing (CORS) enabled and configured appropriately.  Assuming this is true then browser
based applications will be able to make GraphQL queries against your endpoint(s).

## Extending this Module

### For Users

For end users the main way of extending the functionality of this module is to write your own GraphQL schema and then
create a `GraphQLOverDatasetExecutor` implementation that wires up that schema to the `DataFetcher` implementations
necessary to answer queries over your schema.

See the [Dataset Execution](core-apis.md#datasetexecution) and [Fetcher](core-apis.md#fetchers) documentation for more
information about this.


### For Developers

This Fuseki module has also been designed with a couple of extension points to allow developers to build their own
Fuseki modules on top of this if they need to modify the default GraphQL execution behaviour we provide.

Firstly `ActionGraphQL`, which is the implementation of GraphQL execution for Fuseki, provides a protected method
`DatasetGraph prepare(HttpAction action, GraphQLRequest request, DatasetGraph dsg)` that is called prior to the GraphQL
request actually being executed.  This allows developers to modify the `GraphQLRequest` before it executes, e.g. inject
implementation specific extensions.  Also, if desired you can change the `DatasetGraph` upon which the query will 
execute by returning a new `DatasetGraph`.  For example a security conscious extension might override this method 
and wrap the `DatasetGraph` to enforce a particular security model.

In order to replace the use of `ActionGraphQL` with a developers own action you will also need to extend `FMod_GraphQL`
and override the protected `ActionProcessor createActionProcessor(GraphQLOverDatasetExecutor executor)` method to supply
your own `ActionProcessor` implementation.  Most likely this will be extending from `ActionGraphQL` as noted above.

[1]: https://jena.apache.org/documentation/fuseki2/fuseki-modules.html
[2]: https://github.com/graphql/graphql-over-http/blob/main/spec/GraphQLOverHTTP.md
