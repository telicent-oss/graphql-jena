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

import org.apache.jena.sparql.core.DatasetGraph;

/**
 * Runs the fetcher tests with or without a {@link GraphAccess}
 * <p>
 * The build runs this module's tests twice: once as normal, with no {@link GraphAccess}, and once with the
 * {@value #PROPERTY} system property set to {@code true}, in which case test datasets are given a
 * {@link DefaultGraphAccess}. Every test must pass both ways, which shows that fetchers give the same answers through
 * a {@link GraphAccess} as without one.
 * </p>
 */
public final class GraphAccessTestMode {

    /**
     * System property that, when {@code true}, gives test datasets a {@link DefaultGraphAccess}
     */
    public static final String PROPERTY = "graphql.jena.tests.graphAccess";

    private GraphAccessTestMode() {
    }

    /**
     * Gets whether tests are running with a {@link GraphAccess}
     *
     * @return True if test datasets are given a {@link DefaultGraphAccess}
     */
    public static boolean enabled() {
        return Boolean.getBoolean(PROPERTY);
    }

    /**
     * Gives the dataset a {@link DefaultGraphAccess} if tests are running with one
     *
     * @param dsg Dataset graph
     * @param <T> Dataset graph type
     * @return The same dataset graph
     */
    public static <T extends DatasetGraph> T apply(T dsg) {
        if (enabled()) {
            dsg.getContext().set(GraphAccess.SYMBOL, new DefaultGraphAccess());
        }
        return dsg;
    }
}
