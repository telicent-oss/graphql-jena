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

import graphql.schema.DataFetchingEnvironment;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDF;

import java.util.stream.Stream;

/**
 * An abstract paging data fetcher that finds instances of types
 *
 * @param <TOutput> Output type
 */
public abstract class AbstractInstancesFetcher<TOutput>
        extends AbstractLimitOffsetPagingFetcher<TelicentGraphNode, Node, TOutput> {
    private static final Node RDF_TYPE = RDF.type.asNode();

    /**
     * Creates a new instance fetcher with default paging settings.
     */
    protected AbstractInstancesFetcher() {
        super();
    }

    @Override
    protected Stream<Node> select(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node) {
        return dsg.stream(Node.ANY, Node.ANY, RDF_TYPE, node.getNode())
                  .filter(q -> q.getSubject().isURI() || q.getSubject().isBlank())
                  .map(Quad::getSubject)
                  .distinct();
    }
}
