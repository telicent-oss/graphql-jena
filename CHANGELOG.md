# GraphQL Extensions for Apache Jena

# 0.10.4

- Build Improvements:
    - Apache Commons FileUpload upgraded to 2.0.0-M4
    - Airline upgraded to 3.1.0
    - Jackson upgraded to 2.19.1
    - Test dependencies upgraded to latest available

# 0.10.3

- Telicent Graph Schema improvements:
    - Added several new arguments to the fields of the `RelFacetInfo` type:
          - Added `typeFilter` argument to the `types` field
          - Added `predicateFilter` argument to the `predicates` field
          - Added `nodeFilter` argument to both fields
    - This allows restricting computed facets in line with how `inRels`/`outRels`, and they `relCounts` may already be
      restricted.
- Build Improvements:
    - Upgraded Apache Commons BeanUtils to 1.11.0
    - Upgraded GraphQL Java to 24.1
    - Upgraded Smart Caches Core to 0.29.1

# 0.10.2

- Telicent Graph Schema improvements:
    - Added `nodeFilter` argument to `inRels` and `outRels` fields to filter relationships to only those which relate
      to specific nodes in the graph
    - Improved performance of `INCLUDE` filters for `predicateFilter`, and `nodeFilter` by translating these into
      direct `dsg.stream()` calls for pre-filtering instead of post-filtering a more general `dsg.stream()` call.

# 0.10.1

- Telicent Graph Schema improvements:
    - Added `limit` and `offset` arguments to top level `nodes` and `states` queries
    - If requested `limit` exceeds maximum permitted limit then execution fails with an error
    - Added `predicateFilter` and `typeFilter` arguments to `inRels` and `outRels` field on `Node` type to allow
      filtering relationships by their predicate and/or type of the related `Node`
    - Added new `relFacets` property to `Node` type to allow summarising basic information about available relationships

# 0.10.0

- Telicent Graph Schema improvements:
    - Added new `relCounts` property to `Node` and `State` types to allow summarising available relationships
    - Added `limit` and `offset` arguments to various list fields, in combination with the new `relCounts` field this
      allows paging through the results

# 0.9.3

- Build improvements:
    - Apache Jena upgraded to 5.4.0
    - GraphQL Java upgraded to 23.1
    - Jackson upgraded to 2.19.0
    - JWT Servlet Auth upgrade to 1.0.1
    - Logback upgraded to 1.5.18
    - SLF4J upgraded to 2.0.17
    - Smart Caches Core upgraded to 0.28.2
    - Various build and test dependencies upgraded to latest available

# 0.9.2

- Build improvements:
  - Jackson upgraded to 2.18.2
  - Jena upgraded to 5.3.0
  - Logback upgraded to 1.5.16
  - Smart Caches Core upgraded to 0.27.1
  - Various build and test libraries upgraded to latest available

# 0.9.1

- Build improvements:
  - Updating Jena dependency to 5.2.0

# 0.9.0

- General improvements:
    - All `DataFetcher` implementations that interact with a `DatasetGraph` now do so inside a `Txn.calculateRead()` to
      fix an intermittent `Not in a transaction` error caused by how GraphQL Java dispatches query execution potentially
      across multiple threads
- Build improvements:
    - GraphQL Java upgraded to 22.3
    - Excluded vulnerable Protobuf dependency as isn't needed by these libraries
    - Various build and test dependencies upgraded to latest available

# 0.8.2

- Telicent Graph Schema improvements:
    - Added new `searchWithMetadata()` query with richer response schema
    - Added optional `searchType` and `typeFilter` arguments to both `search()` and `searchWithMetadata()` queries
    - Search queries have behavioural parity with `node()` and `nodes()` queries in treating any returned URI from
      search as node if it occurs in the subject/object of any triples in the data
- Build improvements:
    - Logback upgraded to 1.5.8
    - JWT Servlet Auth upgraded to 0.16.0
    - Smart Caches Core Libraries upgraded to 0.22.0
    - Various build and test dependencies upgraded to latest available

# 0.8.1

- Telicent Graph Schema now returns Nodes even if they only occur in the object position of triples in the data
- Build improvements:
    - GraphQL Java upgraded to 22.2
    - Jersey upgraded to 3.1.8
    - SLF4J upgraded to 2.0.16


# 0.8.0

- Support for validating only GraphQL endpoints as demonstration functionality in the standalone GraphQL server
- Telicent Graph Schema adds `limit` and `offset` arguments to `search` queries
- Build improvements:
    - Apache Jena upgraded to 5.1.0
    - JWT Servlet Auth upgraded to 0.15.3
    - Smart Caches Core Libraries upgraded to 0.21.2
    - Various build and test dependencies upgraded to latest available

# 0.7.0

- Upgrades to GraphQL Java 22.1

# 0.6.0

- First public release to Maven Central
- Upgrades various dependencies to most recent available releases

# 0.5.0

- Upgrades to Jena 5
- Upgrades various other dependencies to most recent releases
- The Fuseki module can now be used as a `FusekiAutoModule` if desired
- `telicent-graph-schema` improvements:
    - `period` file is now populated for `State` objects where appropriate
    - Making a `search` query now provides more informative error message when underlying Search service is unavailable

# 0.4.0

- Upgrade various dependencies to more recent usable releases

# 0.3.0

- **BREAKING** Changed namespace URI for GraphQL Fuseki Module to be telicent.io controlled

# 0.2.0

- Renamed `graphql-telicent-graph` module to `telicent-graph-schema` to make its content and intended use clearer

# 0.1.0

- First prototype release of Core APIs, example schemas, standalone server and Fuseki extension module
