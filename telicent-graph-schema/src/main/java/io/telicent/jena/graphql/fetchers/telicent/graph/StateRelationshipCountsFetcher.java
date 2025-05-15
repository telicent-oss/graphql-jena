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
import io.telicent.jena.graphql.schemas.telicent.graph.models.State;
import io.telicent.jena.graphql.schemas.telicent.graph.models.StateRelationshipCounts;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;

import java.util.stream.Stream;

/**
 * A data fetcher that counts the available relationships for a state
 */
public class StateRelationshipCountsFetcher extends AbstractStateRelationshipsFetcher<Integer> {
    @Override
    protected State getSource(DataFetchingEnvironment environment) {
        StateRelationshipCounts counts = environment.getSource();
        return counts.parent();
    }

    @Override
    protected Stream<Quad> applyLimitAndOffset(DataFetchingEnvironment environment, Stream<Quad> stream) {
        // Want total count so don't apply paging
        return stream;
    }

    @Override
    protected Integer map(DataFetchingEnvironment environment, DatasetGraph dsg, State state, Stream<Quad> input) {
        return Math.toIntExact(input.count());
    }
}
