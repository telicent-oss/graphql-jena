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
import graphql.schema.DataFetchingEnvironmentImpl;
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.Filter;
import io.telicent.jena.graphql.schemas.telicent.graph.models.inputs.IncludeAllFilter;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class TestFilterParsing extends AbstractPagingFetcher<TelicentGraphNode, Quad, List<Quad>> {
    @Override
    protected Stream<Quad> select(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node,
                                  List<Filter> filters) {
        return Stream.empty();
    }

    @Override
    protected List<Quad> map(DataFetchingEnvironment environment, DatasetGraph dsg, TelicentGraphNode node,
                             Stream<Quad> input) {
        return List.of();
    }

    private DataFetchingEnvironment prepareEnvironment(Map<String, Object> arguments) {
        return DataFetchingEnvironmentImpl.newDataFetchingEnvironment().arguments(arguments).build();
    }

    @Test
    public void givenNoFilter_whenParsing_thenIncludeAll() {
        // Given
        DataFetchingEnvironment environment = prepareEnvironment(Collections.emptyMap());

        // When
        Filter filter = this.parseFilter(environment, TelicentGraphSchema.ARGUMENT_TYPE_FILTER);

        // Then
        Assert.assertTrue(filter instanceof IncludeAllFilter);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*MUST be a Map")
    public void givenFilterWithNonMapValue_whenParsing_thenIllegalArgument() {
        // Given
        Map<String, Object> arguments = Map.of(TelicentGraphSchema.ARGUMENT_TYPE_FILTER,
                                               List.of(1, 2, 3));
        DataFetchingEnvironment environment = prepareEnvironment(arguments);

        // When and Then
        this.parseFilter(environment, TelicentGraphSchema.ARGUMENT_TYPE_FILTER);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*empty values.*")
    public void givenFilterWithMissingValues_whenParsing_thenIllegalArgument() {
        // Given
        Map<String, Object> arguments = Map.of(TelicentGraphSchema.ARGUMENT_TYPE_FILTER,
                                               Collections.emptyMap());
        DataFetchingEnvironment environment = prepareEnvironment(arguments);

        // When and Then
        this.parseFilter(environment, TelicentGraphSchema.ARGUMENT_TYPE_FILTER);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*non-String values")
    public void givenFilterWithMalformedValues_whenParsing_thenIllegalArgument() {
        // Given
        Map<String, Object> arguments = Map.of(TelicentGraphSchema.ARGUMENT_TYPE_FILTER,
                                               Map.of(TelicentGraphSchema.ARGUMENT_VALUES, List.of(1, 2, 3)));
        DataFetchingEnvironment environment = prepareEnvironment(arguments);

        // When and Then
        this.parseFilter(environment, TelicentGraphSchema.ARGUMENT_TYPE_FILTER);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*empty values.*")
    public void givenFilterWithNonListValues_whenParsing_thenIllegalArgument() {
        // Given
        Map<String, Object> arguments = Map.of(TelicentGraphSchema.ARGUMENT_TYPE_FILTER,
                                               Map.of(TelicentGraphSchema.ARGUMENT_VALUES, "foo"));
        DataFetchingEnvironment environment = prepareEnvironment(arguments);

        // When and Then
        this.parseFilter(environment, TelicentGraphSchema.ARGUMENT_TYPE_FILTER);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*empty values.*")
    public void givenFilterWithEmptyValues_whenParsing_thenIllegalArgument() {
        // Given
        Map<String, Object> arguments = Map.of(TelicentGraphSchema.ARGUMENT_TYPE_FILTER,
                                               Map.of(TelicentGraphSchema.ARGUMENT_VALUES, Collections.emptyList()));
        DataFetchingEnvironment environment = prepareEnvironment(arguments);

        // When and Then
        this.parseFilter(environment, TelicentGraphSchema.ARGUMENT_TYPE_FILTER);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Unknown filter argument: foo")
    public void givenFilterWithBadArgument_whenParsing_thenIllegalArgument() {
        // Given
        Map<String, Object> arguments = Map.of("foo",
                                               Map.of(TelicentGraphSchema.ARGUMENT_VALUES, List.of("test")));
        DataFetchingEnvironment environment = prepareEnvironment(arguments);

        // When and Then
        this.parseFilter(environment, "foo");
    }
    
    @Test
    public void givenTypeFilterWith_filtersDisabled_returnAll() {
        // Given
        // When
        Filter result = this.createTypeFilter(null, null);
        // Then
        Assert.assertEquals(IncludeAllFilter.INSTANCE, result);
    }

    private static class TestTypePagingFetcher extends TestFilterParsing {
        @Override
        protected boolean enableFilters() {
            return true;
        }
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Fetcher implementation MUST override createTypeFilter\\(\\) method to enable type filtering support")
    public void givenTypeFilterWith_filtersEnabled__thenIllegalArgument() {
        // Given
        TestFilterParsing test = new TestTypePagingFetcher();
        // When
        // Then
        test.createTypeFilter(null, null);
    }

}
