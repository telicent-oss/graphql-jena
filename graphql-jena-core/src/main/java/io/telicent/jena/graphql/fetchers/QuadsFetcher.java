/**
 * Copyright (C) Telicent Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package io.telicent.jena.graphql.fetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.telicent.jena.graphql.schemas.CoreSchema;
import io.telicent.jena.graphql.utils.NodeFilter;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.system.Txn;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A GraphQL Data Fetcher that fetches quads
 */
public class QuadsFetcher implements DataFetcher<List<Object>> {

    /**
     * Creates a new Quads Fetcher that fetches quads from an underlying RDF dataset
     */
    public QuadsFetcher() {

    }

    @Override
    public List<Object> get(DataFetchingEnvironment environment) {
        Node subject = NodeFilter.parse(environment.getArgument(CoreSchema.SUBJECT_FIELD));
        Node predicate = NodeFilter.parse(environment.getArgument(CoreSchema.PREDICATE_FIELD));
        Node object = NodeFilter.parse(environment.getArgument(CoreSchema.OBJECT_FIELD));
        Node graph = NodeFilter.parse(environment.getArgument(CoreSchema.GRAPH_FIELD));

        boolean includesSubject = environment.getSelectionSet().contains(CoreSchema.SUBJECT_FIELD + "/**");
        boolean includesPredicate = environment.getSelectionSet().contains(CoreSchema.PREDICATE_FIELD + "/**");
        boolean includesObject = environment.getSelectionSet().contains(CoreSchema.OBJECT_FIELD + "/**");
        boolean includesGraph = environment.getSelectionSet().contains(CoreSchema.GRAPH_FIELD + "/**");
        boolean includesAll = includesSubject && includesPredicate && includesObject && includesGraph;
        boolean includesTriple = includesSubject && includesPredicate && includesObject;

        DatasetGraph dsg = environment.getLocalContext();

        return Txn.calculateRead(dsg, () -> {
            if (!includesAll) {
                if (includesTriple) {
                    return dsg.stream(graph, subject, predicate, object)
                              .map(Quad::asTriple)
                              .collect(Collectors.toList());
                } else {
                    return dsg.stream(graph, subject, predicate, object)
                              .map(q -> map(q, includesSubject, includesPredicate, includesObject, includesGraph))
                              .collect(
                                      Collectors.toList());
                }
            }
            return dsg.stream(graph, subject, predicate, object).collect(Collectors.toList());
        });
    }

    private Object map(Quad q, boolean includesSubject, boolean includesPredicate, boolean includesObject,
                       boolean includesGraph) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        if (includesSubject) {
            map.put(CoreSchema.SUBJECT_FIELD, q.getSubject());
        }
        if (includesPredicate) {
            map.put(CoreSchema.PREDICATE_FIELD, q.getPredicate());
        }
        if (includesObject) {
            map.put(CoreSchema.OBJECT_FIELD, q.getObject());
        }
        if (includesGraph) {
            map.put(CoreSchema.GRAPH_FIELD, q.getGraph());
        }
        return map;
    }
}
