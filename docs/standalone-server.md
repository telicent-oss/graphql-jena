# Standalone GraphQL Server for Apache Jena

The `graphql-server` module provides a standalone JAX-RS based server that exposes our [schemas](schemas.md) over a RDF
`DatasetGraph` as provided by Jena.

## Running the Server

This can be run via the `graphql-jena-server` script within that module, assuming that you have first built the project
successfully per the [Build](../BUILD.md) instructions.

When run without any options it uses an empty in-memory dataset which is not particularly useful.  You can supply either
the `--location` option pointing to an existing Jena TDB2 database, or the `--data` option pointing to an existing RDF
file to load into memory e.g.

```bash
$ ./graphql-server/graphql-jena-server --location /Users/rvesse/Documents/Data/tdb2/starwars 
11:09:25.222 INFO  AbstractAppEntrypoint - Attempting to start Standalone GraphQL Jena Server...
Jun 09, 2023 11:09:25 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:11666]
Jun 09, 2023 11:09:25 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer] Started.
Jun 09, 2023 11:09:25 AM org.glassfish.grizzly.servlet.WebappContext deploy
INFO: Starting application [Standalone GraphQL Jena Server] ...
11:09:25.283 INFO  DatasetInitializer - Using persistent TDB Dataset at /Users/rvesse/Documents/Data/tdb2/starwars
Jun 09, 2023 11:09:25 AM org.glassfish.grizzly.servlet.WebappContext initServlets
INFO: [Standalone GraphQL Jena Server] Servlet [org.glassfish.jersey.servlet.ServletContainer] registered for url pattern(s) [[/*]].
Jun 09, 2023 11:09:25 AM org.glassfish.grizzly.servlet.WebappContext deploy
INFO: Application [Standalone GraphQL Jena Server] is ready to service requests.  Root: [/].
11:09:25.855 INFO  AbstractAppEntrypoint - Standalone GraphQL Jena Server started, check prior logs for any application startup errors.
11:09:25.855 INFO  AbstractAppEntrypoint - Standalone GraphQL Jena Server available at http://localhost:11666/
11:09:25.855 INFO  AbstractAppEntrypoint - Stop the server by sending an interrupt to this process e.g. using CTRL+C
```

As can be seen in the example log output the server runs on `localhost:11666`, we can then make GraphQL queries by
sending requests to one of the GraphQL endpoints, such as `http://localhost:11666/dataset/graphql`, e.g.

```bash
$ curl http://localhost:11666/dataset/graphql -G --data-urlencode query@aliased-quads.graphql | jq
{
  "data": {
    "types": [],
    "instances": [
      {
        "subject": {
          "kind": "URI",
          "value": "https://starwars.com#planet_A-Foroon"
        }
      },
      {
        "subject": {
          "kind": "URI",
          "value": "https://starwars.com#planet_Aaeton"
        }
      },
      ...
```

## Endpoints

The standalone server offers the following GraphQL endpoints:

- `/dataset/graphql` - GraphQL Queries using the [Dataset](schemas.md#dataset) schema.
- `/dataset/traversal/graphql` - GraphQL Queries using the [Traversal](schemas.md#traversal) schema.
- `/dataset/telicent/graphql` - GraphQL Queries using the [Telicent](schemas.md#telicent) schema.
