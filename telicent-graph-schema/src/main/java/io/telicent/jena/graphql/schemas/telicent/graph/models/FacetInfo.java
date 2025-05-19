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

import org.apache.jena.graph.Node;
import org.apache.jena.riot.system.PrefixMap;

/**
 * Represents facet information
 */
public class FacetInfo extends TelicentGraphNode {

    private final int count;

    /**
     * Creates new facet information
     *
     * @param node     Node that identifies the facet
     * @param prefixes Prefixes map, used to provide the shortened URI if possible
     * @param count    Count for the facet
     */
    public FacetInfo(Node node, PrefixMap prefixes, int count) {
        super(node, prefixes);
        this.count = count;
    }

    /**
     * Gets the count for this facet
     *
     * @return Count
     */
    public int getCount() {
        return this.count;
    }

}
