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

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.telicent.jena.graphql.execution.telicent.graph.TelicentExecutionContext;
import io.telicent.jena.graphql.schemas.telicent.graph.models.RdfLiteral;
import io.telicent.jena.graphql.schemas.telicent.graph.models.RdfsClass;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.system.Txn;

import java.util.List;

/**
 * Fetches literal values for a class predicate.
 */
public class RdfsClassLiteralsFetcher implements DataFetcher<List<RdfLiteral>> {

    private final Node predicate;

    /**
     * Creates a new literals fetcher for the given predicate.
     *
     * @param predicate Predicate to query
     */
    public RdfsClassLiteralsFetcher(Node predicate) {
        this.predicate = predicate;
    }

    @Override
    public List<RdfLiteral> get(DataFetchingEnvironment environment) {
        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        RdfsClass rdfsClass = environment.getSource();

        return Txn.calculateRead(dsg,
                                 () -> OntologySupport.literalsFor(dsg, rdfsClass.getGraphNode(), rdfsClass.getNode(),
                                                                   predicate));
    }
}
