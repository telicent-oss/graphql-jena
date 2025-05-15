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
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.system.Txn;

import java.util.stream.Stream;

/**
 * Abstract base class for GraphQL {@link graphql.schema.DataFetcher} implementations that support paging on their
 * results using {@code limit} and {@code offset} arguments
 *
 * @param <TSource> The source type for the field, typically the parent object in the GraphQL query
 * @param <TInput> The type of elements selected and paged over before mapping to output
 * @param <TOutput> The result type returned from the fetcher to the GraphQL engine
 */
public abstract class AbstractLimitOffsetPagingFetcher<TSource, TInput, TOutput> implements DataFetcher<TOutput> {

    private final long defaultLimit, maxLimit;

    /**
     * Creates a new paging fetcher with default limit settings
     */
    protected AbstractLimitOffsetPagingFetcher() {
        this(TelicentGraphSchema.DEFAULT_LIMIT, TelicentGraphSchema.MAX_LIMIT);
    }

    /**
     * Creates a new paging fetcher with custom limit settings
     *
     * @param defaultLimit Default limit to apply if none specified
     * @param maxLimit     Maximum limit to permit
     */
    protected AbstractLimitOffsetPagingFetcher(long defaultLimit, long maxLimit) {
        this.defaultLimit = defaultLimit;
        this.maxLimit = maxLimit;
    }

    @Override
    public final TOutput get(DataFetchingEnvironment environment) throws Exception {
        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        TSource source = getSource(environment);
        return Txn.calculateRead(dsg, () -> {
            Stream<TInput> input = select(environment, dsg, source);
            Stream<TInput> paged = applyLimitAndOffset(environment, input);
            return map(environment, dsg, source, paged);
        });
    }

    /**
     * Gets the source needed for this fetcher implementation
     * <p>
     * This is provided for fetchers where the source won't be directly of the type this fetcher requires
     * <strong>BUT</strong> they can use their actual source type to get an instance of the necessary source type.
     * </p>
     *
     * @param environment Data Fetching environment
     * @return Source
     */
    protected TSource getSource(DataFetchingEnvironment environment) {
        return environment.getSource();
    }

    /**
     * Performs the initial select of relevant data
     *
     * @param environment Data Fetching Environment
     * @param dsg         Dataset Graph
     * @param source      Source object used to control what is selected
     * @return Initial data selection
     */
    protected abstract Stream<TInput> select(DataFetchingEnvironment environment, DatasetGraph dsg, TSource source);

    /**
     * Performs the mapping of the paged input data to output data
     *
     * @param environment Data Fetching Environment
     * @param dsg         Dataset Graph
     * @param source      Source object from which the input data was selected
     * @param input       Paged input data
     * @return Output data
     */
    protected abstract TOutput map(DataFetchingEnvironment environment, DatasetGraph dsg, TSource source,
                                   Stream<TInput> input);

    /**
     * Applies limit and offset, if any, to the given stream
     *
     * @param environment Data Fetching environment
     * @param stream      Stream to apply paging to
     * @return Stream with paging applied
     */
    protected Stream<TInput> applyLimitAndOffset(DataFetchingEnvironment environment, Stream<TInput> stream) {
        return applyLimitAndOffset(environment, stream, this.defaultLimit, this.maxLimit);
    }

    /**
     * Applies limit and offset, if any, to the given stream
     *
     * @param environment  Data Fetching environment
     * @param stream       Stream to apply paging to
     * @param defaultLimit Default limit to apply if not provided in arguments
     * @param maxLimit     Maximum limit to apply
     * @param <T>          Element type
     * @return Stream with paging applied
     */
    public static <T> Stream<T> applyLimitAndOffset(DataFetchingEnvironment environment, Stream<T> stream,
                                                    long defaultLimit, long maxLimit) {
        Integer limit = environment.getArgument(TelicentGraphSchema.ARGUMENT_LIMIT);
        Integer offset = environment.getArgument(TelicentGraphSchema.ARGUMENT_OFFSET);
        if (offset != null && offset > 1) {
            stream = stream.skip(offset.longValue() - 1);
        }
        if (limit != null && limit > 0) {
            if (limit > maxLimit) {
                throw new IllegalArgumentException("Requested limit " + limit + " exceeds the maximum limit " + maxLimit);
            }
            stream = stream.limit(limit.longValue());
        } else {
            stream = stream.limit(defaultLimit);
        }
        return stream;
    }
}
