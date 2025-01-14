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
package io.telicent.jena.graphql.fuseki;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.system.Txn;

public class CountFetcher implements DataFetcher<Integer> {
    @Override
    public Integer get(DataFetchingEnvironment environment) {
        DatasetGraph dsg = environment.getLocalContext();
        return Txn.calculateRead(dsg, () -> (int) dsg.stream().count());
    }
}
