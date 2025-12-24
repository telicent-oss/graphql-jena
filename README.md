# GraphQL Extensions for Apache Jena

This repository provides GraphQL query capabilities as extensions to Apache Jena.  This is done by providing common 
functionality around GraphQL queries over RDF data backed by Apache Jena APIs.  

## Build

A basic Maven build can be performed like so:

```bash
$ mvn clean install
```

See [Build](BUILD.md) for more detailed requirements and instructions.

## Usage

The following is a simple example of using these APIs with our [Dataset](docs/schemas.md#dataset) schema to make a very basic
GraphQL query against a dataset:

```java
DatasetGraph dsg = DatasetGraphFactory.createTxnMem();
RDFParserBuilder.create()
                .source("example-data.trig")
                .build()
                .parse(dsg);

DatasetExecutor executor = new DatasetExecutor(dsg);

// Define our query
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

// Do something with the results, it's a bit clunky as GraphQL returns everything as a Map<String, Object>
Map<String, Object> data = result.getData();
if (data.containsKey("quads")) {
    List<Map<String, Object>> quads = data.get("quads");
    for (Map<String, Object> quad : quads) {
        Map<String, Object> subject = quad.get("subject");
        System.out.println(subject.get("value"));
    }
}
```

A more typical use case is to expose a GraphQL endpoint as part of a Fuseki server which can be done by modifying your
Fuseki configuration file e.g.

```ttl
fuseki:endpoint [ fuseki:operation graphql:graphql ;
                  ja:context [ ja:cxtName "graphql:executor" ;
                               ja:cxtValue "io.telicent.jena.graphql.execution.DatasetExecutor" ];
                  fuseki:name "dataset-graphql" ];
```

See the [Fuseki Module](docs/fuseki-module.md) for more detailed configuration instructions.

Please refer to the full [Documentation](docs/index.md) for more detailed usage examples and explanations of how you can
use the APIs provided in these modules to implement your own GraphQL Schemas over Jena `DatasetGraph`'s.

### Depending on these Modules

To use these modules in your own projects just declare an appropriate dependency.  For example using Maven:

```xml
<dependency>
  <groupId>io.telicent.jena.graphql</groupId>
  <artifactId>ARTIFACT_ID</artifactId>
  <version>VERSION</version>
</dependency>
```

Where `ARTIFACT_ID` and `VERSION` are replaced with appropriate values.  For release history and changes see the [Change
Log](CHANGELOG.md).

## License

This code is Copyright Telicent Ltd and licensed under the Apache License 2.0, see [LICENSE](LICENSE) and
[NOTICE](NOTICE) for details.
