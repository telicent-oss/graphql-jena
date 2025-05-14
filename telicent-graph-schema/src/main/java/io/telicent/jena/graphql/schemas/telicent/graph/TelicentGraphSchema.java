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
package io.telicent.jena.graphql.schemas.telicent.graph;

import graphql.schema.idl.TypeDefinitionRegistry;
import io.telicent.jena.graphql.schemas.GraphQLJenaSchemas;

import java.io.IOException;

/**
 * Constants relating to the GraphQL Schema used by the Telicent Graph application
 */
public class TelicentGraphSchema {

    /**
     * Default limit applied to fields that support paging
     */
    public static final long DEFAULT_LIMIT = 50;
    /**
     * Maximum permitted limit applied to fields that support paging
     */
    public static final long MAX_LIMIT = 250;

    private TelicentGraphSchema() {}
    /**
     * The classpath resource that contains the GraphQL schema file
     */
    public static final String SCHEMA_RESOURCE =
            "/io/telicent/jena/graphql/schemas/telicent/graph/ies.graphqls";

    /**
     * Loads the schema
     *
     * @return Schema
     * @throws IllegalArgumentException                If the schema is not present on the Classpath
     * @throws IOException                             If the schema cannot be read
     * @throws graphql.schema.idl.errors.SchemaProblem If the schema is invalid
     */
    public static TypeDefinitionRegistry loadTelicentGraphSchema() throws IOException {
        return GraphQLJenaSchemas.loadSchema(SCHEMA_RESOURCE);
    }

    /**
     * Node type
     */
    public static final String TYPE_NODE = "Node";
    /**
     * Relationship type
     */
    public static final String TYPE_RELATIONSHIP = "Rel";
    /**
     * Relationship counts type
     */
    public static final String TYPE_RELATIONSHIP_COUNTS = "RelCounts";
    /**
     * Property type
     */
    public static final String TYPE_PROPERTY = "Property";
    /**
     * State type
     */
    public static final String TYPE_STATE = "State";
    /**
     * Search results type
     */
    public static final String TYPE_SEARCH_RESULTS = "SearchResults";
    /**
     * Non-directional relationship type
     */
    public static final String TYPE_NON_DIRECTIONAL_RELATIONSHIP = "NonDirectionalRel";
    /**
     * Search query
     */
    public static final String QUERY_SEARCH = "search";
    /**
     * Search query version 2
     */
    public static final String QUERY_SEARCH_WITH_METADATA = "searchWithMetadata";
    /**
     * Single node query
     */
    public static final String QUERY_SINGLE_NODE = "node";
    /**
     * Multiple nodes query
     */
    public static final String QUERY_MULTIPLE_NODES = "nodes";
    /**
     * States query
     */
    public static final String QUERY_STATES = "states";
    /**
     * Get all entities query
     */
    public static final String QUERY_GET_ALL_ENTITIES = "getAllEntities";
    /**
     * URI argument used to specify the node of interest
     */
    public static final String ARGUMENT_URI = "uri";
    /**
     * URIs argument used to specify the nodes of interest
     */
    public static final String ARGUMENT_URIS = "uris";
    /**
     * Graph argument used to specify the graph of interest
     */
    public static final String ARGUMENT_GRAPH = "graph";
    /**
     * Search term argument used to specify the search term passed onto Telicent Search API
     */
    public static final String ARGUMENT_SEARCH_TERM = "searchTerm";
    /**
     * Search type arguments used to specify the type of search used for the Telicent Search API
     */
    public static final String ARGUMENT_SEARCH_TYPE = "searchType";
    /**
     * Limit argument used to control paging on queries that support it
     */
    public static final String ARGUMENT_LIMIT = "limit";
    /**
     * Offset argument used to control paging on queries that support it
     */
    public static final String ARGUMENT_OFFSET = "offset";
    /**
     * Type filter argument used to specify a type filter for the Telicent Search API
     */
    public static final String ARGUMENT_TYPE_FILTER = "typeFilter";
    /**
     * Type field
     */
    public static final String FIELD_TYPE = "type";
    /**
     * Types field
     */
    public static final String FIELD_TYPES = "types";
    /**
     * Properties field
     */
    public static final String FIELD_PROPERTIES = "properties";
    /**
     * Inbound relationships field
     */
    public static final String FIELD_INBOUND_RELATIONSHIPS = "inRels";
    /**
     * Outbound relationships field
     */
    public static final String FIELD_OUTBOUND_RELATIONSHIPS = "outRels";
    /**
     * Relationship counts field
     */
    public static final String FIELD_RELATIONSHIP_COUNTS = "relCounts";
    /**
     * URI field
     */
    public static final String FIELD_URI = ARGUMENT_URI;
    /**
     * ID field
     */
    public static final String FIELD_ID = "id";
    /**
     * Short URI field, contains a prefixed name form of the URI if available
     */
    public static final String FIELD_SHORT_URI = "shortUri";
    /**
     * Instances field, contains the URIs of instances of a type
     */
    public static final String FIELD_INSTANCES = "instances";
    /**
     * Start field
     */
    public static final String FIELD_START = "start";
    /**
     * End field
     */
    public static final String FIELD_END = "end";
    /**
     * Period field
     */
    public static final String FIELD_PERIOD = "period";
    /**
     * Domain ID field
     */
    public static final String FIELD_DOMAIN_ID = "domain_id";
    /**
     * Range ID field
     */
    public static final String FIELD_RANGE_ID = "range_id";
    /**
     * Relations field
     */
    public static final String FIELD_RELATIONS = "relations";
    /**
     * Value field
     */
    public static final String FIELD_VALUE = "value";
    /**
     * Extension property used to supply the users authentication token that may be passed on by some
     * {@link graphql.schema.DataFetcher} instances when they need to query other Telicent services
     */
    public static final String EXTENSION_AUTH_TOKEN = "authToken";

    /**
     * Prefix used in Short URIs to denote that the URI represents a Blank Node Identifier
     */
    public static final String BLANK_NODE_PREFIX = "_:";
}
