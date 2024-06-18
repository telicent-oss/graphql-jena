# Building GraphQL Extensions for Apache Jena

This project is a Java based project built with Apache Maven.

## Requirements

- JDK 21+
- Apache Maven 3.8.3+

## Build

A straightforward Maven build is provided:

```bash
$ mvn clean install
```

## Test

Decent test coverage is provided and enforced during builds via the Jacoco Plugin **BUT** users should be aware that
this project is experimental and there may be bugs, or scenarios we haven't tested.

Logging is disabled in tests but can be enabled with the following:
```bash
$ mvn test -Dlogback.configurationFile=logback-debug.xml
```
