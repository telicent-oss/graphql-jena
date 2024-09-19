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
package io.telicent.jena.graphql.fetchers;

import graphql.execution.MergedField;
import graphql.language.Field;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentImpl;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.testng.annotations.Test;

public class TestTraversalEdgesFetcher {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void test_get_unrecognisedField() {
        // given
        MergedField mergedField = MergedField.newMergedField().addField(new Field("NoMatch")).build();
        DataFetchingEnvironment environment = DataFetchingEnvironmentImpl
                .newDataFetchingEnvironment()
                .localContext(DatasetGraphFactory.empty())
                .mergedField(mergedField)
                .build();
        TraversalEdgesFetcher traversalEdgesFetcher = new TraversalEdgesFetcher();
        // when
        // then
        traversalEdgesFetcher.get(environment);
    }
}
