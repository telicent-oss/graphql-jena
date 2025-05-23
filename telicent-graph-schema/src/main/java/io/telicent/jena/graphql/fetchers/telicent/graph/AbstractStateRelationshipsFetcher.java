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
package io.telicent.jena.graphql.fetchers.telicent.graph;

import graphql.com.google.common.collect.Streams;
import graphql.schema.DataFetchingEnvironment;
import io.telicent.jena.graphql.schemas.telicent.graph.models.State;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.Filter;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;

import java.util.List;
import java.util.stream.Stream;

/**
 * Abstract data fetcher for fetching relationships for states
 *
 * @param <TOutput> Output type
 */
public abstract class AbstractStateRelationshipsFetcher<TOutput>
        extends AbstractPagingFetcher<State, Quad, TOutput> {

    /**
     * Default constructor
     */
    protected AbstractStateRelationshipsFetcher() {
        super();
    }

    private static Stream<Quad> outbound(DatasetGraph dsg, State target) {
        return dsg.stream(Node.ANY, target.getStateNode(), Node.ANY, Node.ANY)
                  .filter(q -> q.getObject().isURI() || q.getObject().isBlank())
                  .filter(q -> !q.getPredicate().equals(RDF.type.asNode()) && !q.getObject()
                                                                                .equals(target.getEntityNode()));
    }

    private static Stream<Quad> inbound(DatasetGraph dsg, State target) {
        return dsg.stream(Node.ANY, Node.ANY, Node.ANY, target.getStateNode())
                  .filter(q -> q.getSubject().isURI() || q.getSubject().isBlank())
                  .filter(q -> dsg.contains(Node.ANY, q.getSubject(), RDF.type.asNode(), Node.ANY));

    }

    @Override
    protected Stream<Quad> select(DataFetchingEnvironment environment, DatasetGraph dsg, State state, List<Filter> filters) {
        // NB - Filters not enabled for state relationships
        return Streams.concat(AbstractStateRelationshipsFetcher.outbound(dsg, state),
                              AbstractStateRelationshipsFetcher.inbound(dsg, state));
    }
}
