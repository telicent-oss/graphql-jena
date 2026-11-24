# Graph Access

`GraphAccess`, in the `io.telicent.jena.graphql.access` package of `graphql-jena-core`, is an optional interface
through which a store can answer common graph questions in its own engine, rather than having fetchers stream every
relevant quad through Jena's `DatasetGraph` API.

It is store-neutral: it depends only on Jena, its questions are plain graph questions, and it knows nothing about any
GraphQL schema or ontology.  It is entirely optional: a dataset that does not provide one, which includes TDB2 and
Jena's in-memory datasets, is served exactly as it would be without the interface.

## Questions

The interface currently asks two questions.  Further questions may be added in future versions.

| Method | Question |
|---|---|
| `countRelationships(dsg, nodes, direction, predicates)` | For each node, how many relationships go from it (`OUT`) or to it (`IN`) |
| `relationships(dsg, node, direction, predicates, offset, limit)` | One page of those relationships |

A *relationship* of a node is a distinct `(subject, predicate, object)` triple, in any graph of the dataset (default or
named), counted once however many graphs contain it, whose subject (`OUT`) or object (`IN`) is the node and whose other
end is a URI or a blank node.  `predicates` restricts the relationships to those predicates, `null` or empty means any
predicate.

## Contract

- Answers must equal those of `DefaultGraphAccess`, which streams quads from the dataset via
  `DatasetGraph.stream()` and de-duplicates them by triple.
- Methods are called with the dataset the fetcher was given, inside the caller's transaction.  An implementation finds
  its own store beneath any dataset wrappers and answers as that dataset would, so that any access control or graph
  visibility the dataset applies to `stream()` applies to its answers too.
- `countRelationships` returns one count per node, in the order of `nodes`.
- `relationships` returns each distinct triple once, as a `Quad` whose graph carries no meaning, in an order that is
  stable for the same arguments while the dataset is unchanged.  `offset` is 0-based, i.e. an offset of `0` starts
  from the first relationship, and negative values of `offset` or `limit` are rejected.

The contract tests are in the `graphql-jena-core` test JAR as `AbstractGraphAccessTests`.  A store with its own
implementation can extend that class, supplying its own dataset, to show that it gives the same answers.

## Providing an Implementation

A store places its implementation in the dataset's context:

```java
dsg.getContext().set(GraphAccess.SYMBOL, new MyStoreGraphAccess());
```

Because it is found through the context, it is still found when the dataset is wrapped, e.g. by Fuseki or by an
access-control layer, provided the wrapper shares the dataset's context, as Jena's `DatasetGraphWrapper` does.

## Using it from a Fetcher

`GraphAccess.get(dsg)` returns the dataset's implementation, or `null` if it has none, in which case a fetcher uses the
`DatasetGraph` API as it always has.  For example, a fetcher that counts the outgoing relationships of a node:

```java
public class OutgoingCountFetcher implements DataFetcher<Long> {

    @Override
    public Long get(DataFetchingEnvironment environment) {
        DatasetGraph dsg = environment.getLocalContext();
        Node node = NodeFactory.createURI(environment.getArgument("uri"));
        return Txn.calculateRead(dsg, () -> {
            GraphAccess access = GraphAccess.get(dsg);
            if (access != null) {
                // Counted by the store, without reading the relationships into Java
                return access.countRelationships(dsg, List.of(node), EdgeDirection.OUT, null)[0];
            }
            // No GraphAccess: stream and de-duplicate as before
            Set<Triple> seen = new HashSet<>();
            return dsg.stream(Node.ANY, node, Node.ANY, Node.ANY)
                      .filter(q -> q.getObject().isURI() || q.getObject().isBlank())
                      .filter(q -> seen.add(q.asTriple()))
                      .count();
        });
    }
}
```

## Use in the Telicent Graph Schema

- `RelationshipCountsFetcher` (`relCounts`) asks `countRelationships` for the count.
- `RelationshipsFetcher` (`inRels`, `outRels`) asks `relationships` for just the requested page, converting the
  schema's 1-based `offset` argument to the interface's 0-based offset.

Both do so only when the request's filters can be expressed as a `GraphAccess` call, i.e. there is no filter or only
predicate `INCLUDE` filters, which become the `predicates` argument.  Requests with type filters, node filters or
predicate `EXCLUDE` filters are served as before.  When another field of the same request, such as the relationship
facets, has already built the full list of the same relationships, that list is used instead.

The module's tests run twice, once without a `GraphAccess` and once with a `DefaultGraphAccess` in every test dataset,
to show that the fetchers give the same answers either way.
