# GraphQL Extensions for Apache Jena

# 0.8.2

- Telicent Graph Schema improvements:
    - Added new `searchV2()` query with richer response schema
    - Added optional `searchType` and `typeFilter` arguments to both `search()` and `searchV2()` queries
    - Search queries have behavioural parity with `node()` and `nodes()` queries in treating any returned URI from
      search as node if it occurs in the subject/object of any triples in the data

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
