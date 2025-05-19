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
package io.telicent.jena.graphql.execution.telicent.graph;

import graphql.schema.PropertyDataFetcher;
import graphql.schema.idl.NaturalEnumValuesProvider;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.telicent.jena.graphql.execution.AbstractDatasetExecutor;
import io.telicent.jena.graphql.fetchers.telicent.graph.*;
import io.telicent.jena.graphql.schemas.models.EdgeDirection;
import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;
import io.telicent.jena.graphql.schemas.telicent.graph.models.SearchType;
import org.apache.jena.sparql.core.DatasetGraph;

import java.io.IOException;
import java.util.Map;

/**
 * A GraphQL Execution over a {@link DatasetGraph} using the {@link TelicentGraphSchema}
 */
public class TelicentGraphExecutor extends AbstractDatasetExecutor {

    /**
     * Creates a new execution
     *
     * @param dsg The default dataset over which the execution operates
     * @throws IOException Thrown if the schema cannot be loaded
     */
    public TelicentGraphExecutor(DatasetGraph dsg) throws IOException {
        super(dsg);
    }

    @Override
    protected TypeDefinitionRegistry loadRawSchema() throws IOException {
        return TelicentGraphSchema.loadTelicentGraphSchema();
    }

    @Override
    protected boolean extendsCoreSchema() {
        return false;
    }

    @Override
    protected RuntimeWiring.Builder buildRuntimeWiring() {
        final StatePeriodFetcher periodFetcher = new StatePeriodFetcher();
        final NodePlaceholderFetcher nodePlaceholderFetcher = new NodePlaceholderFetcher();
        NaturalEnumValuesProvider<SearchType> nodeKinds = new NaturalEnumValuesProvider<>(SearchType.class);
        //@formatter:off
        return RuntimeWiring.newRuntimeWiring()
                            .type("Query",
                                  t -> t.dataFetcher(TelicentGraphSchema.QUERY_SINGLE_NODE, new StartingNodesFetcher(false))
                                        .dataFetcher(TelicentGraphSchema.QUERY_MULTIPLE_NODES, new StartingNodesFetcher(true))
                                        .dataFetcher(TelicentGraphSchema.QUERY_SEARCH, new StartingSearchFetcher())
                                        .dataFetcher(TelicentGraphSchema.QUERY_SEARCH_WITH_METADATA, new StartingSearchWithMetadataFetcher()).enumValues(nodeKinds)
                                        .dataFetcher(TelicentGraphSchema.QUERY_STATES, new StartingStatesFetcher())
                                        .dataFetcher(TelicentGraphSchema.QUERY_GET_ALL_ENTITIES, new AllEntitiesFetcher())
                            )
                            .type(TelicentGraphSchema.TYPE_NODE,
                                  t -> t.dataFetcher(TelicentGraphSchema.FIELD_TYPES, new NodeTypesFetcher())
                                        .dataFetcher(TelicentGraphSchema.FIELD_PROPERTIES, new LiteralPropertiesFetcher())
                                        .dataFetcher(TelicentGraphSchema.FIELD_INBOUND_RELATIONSHIPS, new RelationshipsFetcher(EdgeDirection.IN))
                                        .dataFetcher(TelicentGraphSchema.FIELD_OUTBOUND_RELATIONSHIPS, new RelationshipsFetcher(EdgeDirection.OUT))
                                        .dataFetcher(TelicentGraphSchema.FIELD_INSTANCES, new InstancesFetcher())
                                        .dataFetcher(TelicentGraphSchema.FIELD_RELATIONSHIP_COUNTS, nodePlaceholderFetcher)
                                        .dataFetcher(TelicentGraphSchema.FIELD_RELATIONSHIP_FACETS, nodePlaceholderFetcher)
                            )
                            .type(TelicentGraphSchema.TYPE_RELATIONSHIP,
                                  // The Telicent Graph schema uses underscores in these property names which defeats
                                  // graphql-java's default logic of looking for an equivalent Java property name so
                                  // have to explicitly declare the fetchers for these
                                  t -> t.dataFetcher(TelicentGraphSchema.FIELD_DOMAIN_ID, new PropertyDataFetcher<String>("domainId"))
                                        .dataFetcher(TelicentGraphSchema.FIELD_RANGE_ID, new PropertyDataFetcher<String>("rangeId"))
                            )
                            .type(TelicentGraphSchema.TYPE_RELATIONSHIP_COUNTS,
                                        t -> t.dataFetcher(TelicentGraphSchema.FIELD_INBOUND_RELATIONSHIPS, new RelationshipCountsFetcher(EdgeDirection.IN))
                                              .dataFetcher(TelicentGraphSchema.FIELD_OUTBOUND_RELATIONSHIPS, new RelationshipCountsFetcher(EdgeDirection.OUT))
                                              .dataFetcher(TelicentGraphSchema.FIELD_INSTANCES, new InstancesCountFetcher())
                                              .dataFetcher(TelicentGraphSchema.FIELD_TYPES, new NodeTypeCountsFetcher())
                                              .dataFetcher(TelicentGraphSchema.FIELD_PROPERTIES, new LiteralsCountFetcher())
                            )
                            // The facets are a bit of a workaround
                            // We want to lazily compute the facet information BUT it's nested two levels beneath our
                            // Node type.  Therefore, for the NodeRelFacets type we use a fetcher that simply injects
                            // a placeholder value that includes the fetchers to use at the lower level.
                            .type(TelicentGraphSchema.TYPE_RELATIONSHIP_FACETS,
                                        t -> t.dataFetcher(TelicentGraphSchema.FIELD_INBOUND_RELATIONSHIPS,
                                                                  new FacetPlaceholderFetcher(new RelationshipPredicateFacetsFetcher(EdgeDirection.IN),
                                                                                              new RelationshipTypeFacetsFetcher(EdgeDirection.IN)))
                                              .dataFetcher(TelicentGraphSchema.FIELD_OUTBOUND_RELATIONSHIPS,
                                                           new FacetPlaceholderFetcher(new RelationshipPredicateFacetsFetcher(EdgeDirection.OUT),
                                                                                       new RelationshipTypeFacetsFetcher(EdgeDirection.OUT)))
                            )
                            // Then at the RelFacetInfo level we use the general purpose FacetsFetcher, this inspects
                            // the placeholder to find the Node we're computing facets for and selects the appropriate
                            // fetcher to actually call based upon the field being fetched
                            .type(TelicentGraphSchema.TYPE_RELATIONSHIP_FACET_INFO,
                                        t -> t.dataFetcher(TelicentGraphSchema.FIELD_TYPES, new FacetsFetcher())
                                                     .dataFetcher(TelicentGraphSchema.FIELD_PREDICATES, new FacetsFetcher())
                            )
                            .type(TelicentGraphSchema.TYPE_STATE,
                                  t -> t.dataFetcher(TelicentGraphSchema.FIELD_TYPE, new StateTypeFetcher())
                                        .dataFetcher(TelicentGraphSchema.FIELD_RELATIONS, new StateRelationshipsFetcher())
                                        .dataFetcher(TelicentGraphSchema.FIELD_START, periodFetcher)
                                        .dataFetcher(TelicentGraphSchema.FIELD_END, periodFetcher)
                                        .dataFetcher(TelicentGraphSchema.FIELD_PERIOD, periodFetcher)
                                        .dataFetcher(TelicentGraphSchema.FIELD_RELATIONSHIP_COUNTS, new StateRelationshipCountsPlaceholderFetcher())
                            )
                            .type(TelicentGraphSchema.TYPE_STATE_RELATIONSHIP_COUNTS,
                                  t -> t.dataFetcher(TelicentGraphSchema.FIELD_RELATIONS, new StateRelationshipCountsFetcher())
                            );
        //@formatter:on
    }

    @Override
    protected Object createLocalContext(DatasetGraph dsg, Map<String, Object> extensions) {
        // Get the auth token for the request (if any)
        String authToken = (String) extensions.get(TelicentGraphSchema.EXTENSION_AUTH_TOKEN);
        return new TelicentExecutionContext(dsg, authToken);
    }
}
