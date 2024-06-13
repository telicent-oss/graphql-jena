# GraphQL Extensions for Apache Jena

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
