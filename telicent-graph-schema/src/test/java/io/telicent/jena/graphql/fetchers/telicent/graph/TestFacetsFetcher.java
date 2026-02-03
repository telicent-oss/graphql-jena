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

import graphql.language.Field;
import graphql.schema.DataFetcher;
import graphql.execution.MergedField;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentImpl;
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import io.telicent.jena.graphql.schemas.telicent.graph.models.FacetInfo;
import io.telicent.jena.graphql.schemas.telicent.graph.models.FacetInfoPlaceholder;
import io.telicent.jena.graphql.schemas.telicent.graph.models.TelicentGraphNode;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.apache.jena.graph.NodeFactory.createURI;

public class TestFacetsFetcher {

    @Test
    public void testFacetsFetcherDelegatesTypes() throws Exception {
        FacetsFetcher fetcher = new FacetsFetcher();
        List<FacetInfo> expected = List.of(new FacetInfo(createURI("http://example.com/type"), null, 2));
        DataFetcher<List<FacetInfo>> typesFetcher = environment -> expected;
        DataFetcher<List<FacetInfo>> predicatesFetcher = environment -> List.of();
        FacetInfoPlaceholder placeholder = new FacetInfoPlaceholder(new TelicentGraphNode(createURI("node"), null),
                                                                    predicatesFetcher, typesFetcher);

        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                                         .source(placeholder)
                                                                         .mergedField(MergedField.newMergedField(
                                                                                 Field.newField(
                                                                                         TelicentGraphSchema.FIELD_TYPES)
                                                                                      .build())
                                                                                          .build())
                                                                         .build();

        List<FacetInfo> actual = fetcher.get(environment);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFacetsFetcherDelegatesPredicates() throws Exception {
        FacetsFetcher fetcher = new FacetsFetcher();
        List<FacetInfo> expected = List.of(new FacetInfo(createURI("http://example.com/predicate"), null, 1));
        DataFetcher<List<FacetInfo>> typesFetcher = environment -> List.of();
        DataFetcher<List<FacetInfo>> predicatesFetcher = environment -> expected;
        FacetInfoPlaceholder placeholder = new FacetInfoPlaceholder(new TelicentGraphNode(createURI("node"), null),
                                                                    predicatesFetcher, typesFetcher);

        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                                         .source(placeholder)
                                                                         .mergedField(MergedField.newMergedField(
                                                                                 Field.newField(
                                                                                         TelicentGraphSchema.FIELD_PREDICATES)
                                                                                      .build())
                                                                                          .build())
                                                                         .build();

        List<FacetInfo> actual = fetcher.get(environment);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFacetsFetcherRejectsUnknownField() throws Exception {
        FacetsFetcher fetcher = new FacetsFetcher();
        FacetInfoPlaceholder placeholder = new FacetInfoPlaceholder(new TelicentGraphNode(createURI("node"), null),
                                                                    environment -> List.of(),
                                                                    environment -> List.of());

        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl.newDataFetchingEnvironment()
                                                                         .source(placeholder)
                                                                         .mergedField(MergedField.newMergedField(
                                                                                 Field.newField("unknown").build())
                                                                                          .build())
                                                                         .build();

        try {
            fetcher.get(environment);
            Assert.fail("Expected facets fetcher to throw");
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(ex.getMessage().contains("Unsupported field"));
        } catch (Exception ex) {
            Assert.fail("Expected IllegalArgumentException but saw " + ex.getClass().getSimpleName());
        }
    }
}
