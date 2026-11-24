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
package io.telicent.jena.graphql.access;

import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.DatasetGraphWrapper;
import org.apache.jena.sparql.util.Context;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestGraphAccessLookup {

    @Test
    public void givenNullDataset_whenLookingUp_thenNone() {
        // Given, When and Then
        Assert.assertNull(GraphAccess.get(null));
    }

    @Test
    public void givenInMemoryDataset_whenLookingUp_thenNone() {
        // Given
        DatasetGraph dsg = DatasetGraphFactory.createTxnMem();

        // When and Then
        Assert.assertNull(GraphAccess.get(dsg));
    }

    @Test
    public void givenDatasetWithAccessInContext_whenLookingUp_thenFound() {
        // Given
        DatasetGraph dsg = DatasetGraphFactory.createTxnMem();
        GraphAccess access = new DefaultGraphAccess();
        dsg.getContext().set(GraphAccess.SYMBOL, access);

        // When and Then
        Assert.assertSame(GraphAccess.get(dsg), access);
    }

    @Test
    public void givenWrappedDataset_whenLookingUp_thenFoundThroughTheWrapper() {
        // Given
        DatasetGraph dsg = DatasetGraphFactory.createTxnMem();
        GraphAccess access = new DefaultGraphAccess();
        dsg.getContext().set(GraphAccess.SYMBOL, access);
        DatasetGraph wrapper = new DatasetGraphWrapper(dsg);

        // When and Then
        Assert.assertSame(GraphAccess.get(wrapper), access);
    }

    @Test
    public void givenSymbolWithOtherValue_whenLookingUp_thenNone() {
        // Given
        DatasetGraph dsg = DatasetGraphFactory.createTxnMem();
        dsg.getContext().set(GraphAccess.SYMBOL, "not a graph access");

        // When and Then
        Assert.assertNull(GraphAccess.get(dsg));
    }

    @Test
    public void givenDatasetWithNoContext_whenLookingUp_thenNone() {
        // Given
        DatasetGraph dsg = new DatasetGraphWrapper(DatasetGraphFactory.createTxnMem()) {
            @Override
            public Context getContext() {
                return null;
            }
        };

        // When and Then
        Assert.assertNull(GraphAccess.get(dsg));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void givenNoDirection_whenCounting_thenRejected() {
        // Given
        DatasetGraph dsg = DatasetGraphFactory.createTxnMem();

        // When and Then
        new DefaultGraphAccess().countRelationships(dsg, List.of(AbstractGraphAccessTests.uri("a")), null, null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void givenNoNodes_whenCounting_thenRejected() {
        // Given
        DatasetGraph dsg = DatasetGraphFactory.createTxnMem();

        // When and Then
        new DefaultGraphAccess().countRelationships(dsg, null, EdgeDirection.OUT, null);
    }
}
