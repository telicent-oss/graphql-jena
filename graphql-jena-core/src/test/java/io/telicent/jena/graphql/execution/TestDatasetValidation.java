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
package io.telicent.jena.graphql.execution;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.ParseAndValidateResult;
import graphql.validation.ValidationError;
import io.telicent.jena.graphql.schemas.CoreSchema;
import io.telicent.jena.graphql.utils.NodeFilter;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.vocabulary.RDFS;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static graphql.validation.ValidationErrorType.InvalidSyntax;

@SuppressWarnings("unchecked")
public class TestDatasetValidation extends AbstractExecutionTests {

    private static final String QUERY_BASE = "/queries/dataset/";

    private static String loadQuery(String queryResource) {
        return loadQuery(QUERY_BASE, queryResource);
    }

    public static final String SIMPLE_QUADS_QUERY = loadQuery("simple-quads.graphql");

    public static final String FILTERED_QUADS_QUERY = loadQuery("filtered-quads.graphql");

    public static final String VARIABLE_QUADS_QUERY = loadQuery("variable-quads.graphql");

    public static final String ALIASED_QUADS_QUERY = loadQuery("aliased-quads.graphql");

    public static final String FRAGMENT_QUADS_QUERY = loadQuery("fragment-quads.graphql");


    @DataProvider(name = "validQueries")
    private static Object[] validQueryList() {
        return new Object[] {
                SIMPLE_QUADS_QUERY,
                FILTERED_QUADS_QUERY,
                ALIASED_QUADS_QUERY,
                FRAGMENT_QUADS_QUERY,
                VARIABLE_QUADS_QUERY
        };
    }


    @DataProvider(name = "invalidQueries")
    private static Object[] invalidQueryList() {
        return new Object[] {
                "",
                "random",
                "query={}",
                "query={",
                "query=query($subject: NodeFilter, $predicate: NodeFilter, $object: NodeFilter, $graph: NodeFilter) {}"
        };
    }

    @Test(dataProvider = "validQueries")
    public void dataset_valid_queries(String validQuery) throws IOException {
        DatasetExecutor execution = new DatasetExecutor(DatasetGraphFactory.create());

        verifyValidationSuccess(execution, validQuery);
    }

    @Test(dataProvider = "invalidQueries")
    public void dataset_invalid_queries(String invalidQuery) throws IOException {
        DatasetExecutor execution = new DatasetExecutor(DatasetGraphFactory.create());

        verifyValidationFailure(execution, invalidQuery);
    }

}
