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
package io.telicent.jena.graphql.schemas.telicent.graph.models;

/**
 * Holds information about relationship counts
 * <p>
 * This is intentionally an object so we can potentially augment this with further fields in the future if we need to.
 * </p>
 */
public class RelationshipCounts {

    private final int in, out;

    /**
     * Creates new relationship counts
     *
     * @param in  Count of incoming relationships
     * @param out Count of outgoing relationships
     */
    public RelationshipCounts(int in, int out) {
        this.in = in;
        this.out = out;
    }

    /**
     * Gets the count of incoming relationships
     *
     * @return Incoming relationships count
     */
    public int getIn() {
        return in;
    }

    /**
     * Gets the count of outgoing relationships
     *
     * @return Outgoing relationships count
     */
    public int getOut() {
        return out;
    }
}
