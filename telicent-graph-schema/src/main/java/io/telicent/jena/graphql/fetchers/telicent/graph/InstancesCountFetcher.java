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
import io.telicent.jena.graphql.schemas.telicent.graph.models.RelationshipCounts;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;

import java.util.stream.Stream;

/**
 * A data fetcher that calculates the total number of instances available
 */
public class InstancesCountFetcher extends AbstractInstancesFetcher<Integer> {

    /**
     * Default constructor
     */
    public InstancesCountFetcher() {
        super();
    }

    @Override
    protected TelicentGraphNode getSource(DataFetchingEnvironment environment) {
        RelationshipCounts counts = environment.getSource();
        return counts.parent();
    }

    @Override
    protected Integer map(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node,
                          Stream<Node> input) {
        return Math.toIntExact(input.count());
    }

    @Override
    protected Stream<Node> applyLimitAndOffset(DataFetchingEnvironment environment, Stream<Node> stream) {
        // Want the full count so don't apply paging
        return stream;
    }
}
