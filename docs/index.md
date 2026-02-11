# GraphQL Extensions for Apache Jena Usage

This documentation provides details on the APIs and Tools in this repository and how to utilise them.

Please refer to [Depending on these Modules](../README.md#depending-on-these-modules) for how to declare dependencies on 
these modules in other projects.

## Core API

The `graphql-jena-core` module provides the [core APIs](core-apis.md) and [schemas](schemas.md) for running GraphQL
queries over RDF data.  It primarily serves to demonstrate how to use the `graphql-java` to build up an executable
GraphQL schema over Jena backed RDF data.  You'll also find a bunch of helper utilities and constants for reuse in other
modules here.

### Telicent GraphQL Schema

The `telicent-graph-schema` module provides a Telicent specific schema and associated execution logic for use by our
Telicent Graph application.

This schema now includes ontology-focused fields (under the `ontology` root) for classes, property definitions, labels,
and styling metadata. See [Schemas](schemas.md) for a high-level overview and a detailed breakdown.

## GraphQL Server

The `graphql-server` module provides a standalone HTTP server that offers GraphQL access to RDF data.  See the
[Standalone Server](standalone-server.md) documentation for more details.

## Fuseki Module

The `graphql-fuseki-module` module provides a [Fuseki Module](fuseki-module.md) that can be added to a Fuseki server
deployment to allow adding GraphQL endpoints to a Fuseki dataset using Fuseki's configuration syntax.

## Benchmarks

The [Benchmarks](benchmarks.md) documentation covers how to run the JMH benchmarks and interpret profiler outputs.
