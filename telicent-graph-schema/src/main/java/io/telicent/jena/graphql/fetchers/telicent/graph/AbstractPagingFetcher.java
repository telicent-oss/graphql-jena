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
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.*;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.system.Txn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Abstract base class for GraphQL {@link graphql.schema.DataFetcher} implementations that support paging on their
 * results using {@code limit} and {@code offset} arguments
 *
 * @param <TInput>  Input type that will be {@link #select(DataFetchingEnvironment, DatasetGraph, Object, List)}'d
 * @param <TSource> Source type
 * @param <TOutput> Output type that will be produced as the end result of the data fetch
 */
public abstract class AbstractPagingFetcher<TSource, TInput, TOutput> implements DataFetcher<TOutput> {

    private final long defaultLimit, maxLimit;

    /**
     * Creates a new paging fetcher with default limit settings
     */
    protected AbstractPagingFetcher() {
        this(TelicentGraphSchema.DEFAULT_LIMIT, TelicentGraphSchema.MAX_LIMIT);
    }

    /**
     * Creates a new paging fetcher with custom limit settings
     *
     * @param defaultLimit Default limit to apply if none specified
     * @param maxLimit     Maximum limit to permit
     */
    protected AbstractPagingFetcher(long defaultLimit, long maxLimit) {
        this.defaultLimit = defaultLimit;
        this.maxLimit = maxLimit;
    }

    @Override
    public final TOutput get(DataFetchingEnvironment environment) throws Exception {
        TelicentExecutionContext context = environment.getLocalContext();
        DatasetGraph dsg = context.getDatasetGraph();
        TSource source = getSource(environment);
        List<Filter> filters = new ArrayList<>();
        if (this.enableFilters()) {
            createFilters(environment, filters);
        }

        return Txn.calculateRead(dsg, () -> {
            Stream<TInput> input = select(environment, dsg, source, filters);
            Stream<TInput> paged = applyLimitAndOffset(environment, input);
            return map(environment, dsg, source, paged);
        });
    }

    /**
     * Creates filters, called only if {@link #enableFilters()} returns {@code true}
     * <p>
     * This calls {@link #parseFilter(DataFetchingEnvironment, String)} to parse the filter argument (if present), which
     * calls {@link #createFilter(String, FilterMode, List)} to create the actual filter. May be overridden if a fetcher
     * wants to support additional filters beyond the default predicate and type filters, if you override this then you
     * should also override {@link #createFilter(String, FilterMode, List)}
     * </p>
     *
     * @param environment Data Fetching environment
     * @param filters     Filters list to populate
     */
    protected void createFilters(DataFetchingEnvironment environment, List<Filter> filters) {
        filters.add(this.parseFilter(environment, TelicentGraphSchema.ARGUMENT_PREDICATE_FILTER));
        filters.add(this.parseFilter(environment, TelicentGraphSchema.ARGUMENT_DOMAIN_FILTER));
        filters.add(this.parseFilter(environment, TelicentGraphSchema.ARGUMENT_RANGE_FILTER));
        filters.add(this.parseFilter(environment, TelicentGraphSchema.ARGUMENT_TYPE_FILTER));
    }

    /**
     * Indicates whether filters are enabled for this fetcher, default {@code false}
     * <p>
     * If a derived fetcher supports filters then it should override this method and return {@code true}
     * </p>
     *
     * @return Always false by default
     */
    protected boolean enableFilters() {
        return false;
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
     * @param filters     Any argument driven filters that the fetcher should apply to the initial {@link Quad}
     *                    selection, this will be empty unless the fetcher has overridden {@link #enableFilters()} to
     *                    indicate that it supports filters
     * @return Initial data selection
     */
    protected abstract Stream<TInput> select(DataFetchingEnvironment environment, DatasetGraph dsg, TSource source,
                                             List<Filter> filters);

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
                throw new IllegalArgumentException(
                        "Requested limit " + limit + " exceeds the maximum limit " + maxLimit);
            }
            stream = stream.limit(limit.longValue());
        } else {
            stream = stream.limit(defaultLimit);
        }
        return stream;
    }

    /**
     * Parses a filter
     *
     * @param environment Data Fetching environment
     * @param argument    Argument to parse a filter from
     * @return Filter
     */
    protected final Filter parseFilter(DataFetchingEnvironment environment, String argument) {
        Object rawFilter = environment.getArgument(argument);
        if (rawFilter == null) {
            return IncludeAllFilter.INSTANCE;
        }
        if (rawFilter instanceof Map<?, ?> rawMap) {
            @SuppressWarnings("unchecked")
            Map<String, Object> filterMap = (Map<String, Object>) rawMap;
            FilterMode mode = filterMap.containsKey(TelicentGraphSchema.ARGUMENT_MODE) ?
                              FilterMode.valueOf(filterMap.get(TelicentGraphSchema.ARGUMENT_MODE).toString()) :
                              FilterMode.INCLUDE;
            List<Node> values = new ArrayList<>();
            if (filterMap.containsKey(TelicentGraphSchema.ARGUMENT_VALUES) && filterMap.get(
                    TelicentGraphSchema.ARGUMENT_VALUES) instanceof List<?> rawValues) {
                for (Object rawValue : rawValues) {
                    if (rawValue instanceof String rawUri) {
                        values.add(StartingNodesFetcher.parseStart(rawUri));
                    } else {
                        throw new IllegalArgumentException("values list contains non-String values");
                    }
                }
            }

            // Can't have empty values, unclear what callers intention is in this case
            if (values.isEmpty()) {
                throw new IllegalArgumentException(
                        argument + " filter has empty values, must specify some values to " + (
                                mode == FilterMode.INCLUDE ? "include" : "exclude"));
            }

            return createFilter(argument, mode, values);
        } else {
            throw new IllegalArgumentException("Filter argument " + argument + " MUST be a Map");
        }
    }

    /**
     * Creates a filter
     * <p>
     * May be overridden if a fetcher wants to support additional filter types
     * </p>
     *
     * @param argument Filter argument
     * @param mode     Filter mode
     * @param values   Filter values
     * @return Filter
     */
    protected Filter createFilter(String argument, FilterMode mode, List<Node> values) {
        return switch (argument) {
            case TelicentGraphSchema.ARGUMENT_TYPE_FILTER -> createTypeFilter(mode, values);
            case TelicentGraphSchema.ARGUMENT_PREDICATE_FILTER -> new PredicateFilter(mode, values);
            case TelicentGraphSchema.ARGUMENT_DOMAIN_FILTER -> new SubjectFilter(mode, values);
            case TelicentGraphSchema.ARGUMENT_RANGE_FILTER -> new ObjectFilter(mode, values);
            default -> throw new IllegalArgumentException("Unknown filter argument: " + argument);
        };
    }

    /**
     * Creates a type filter
     *
     * @param mode   Filter mode
     * @param values Filter values
     * @return Type filter
     */
    protected Filter createTypeFilter(FilterMode mode, Collection<Node> values) {
        if (this.enableFilters()) {
            throw new IllegalArgumentException(
                    "Fetcher implementation MUST override createTypeFilter() method to enable type filtering support");
        } else {
            return IncludeAllFilter.INSTANCE;
        }
    }
}
