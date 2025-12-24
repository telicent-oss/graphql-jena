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
package io.telicent.jena.graphql.benchmarks;

import org.apache.jena.graph.NodeFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.vocabulary.RDFS;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Shared benchmark helpers for loading resources and constructing datasets.
 */
final class BenchmarkResources {

    private BenchmarkResources() {
    }

    /**
     * Loads a text resource into memory.
     *
     * @param resourcePath Classpath resource path.
     * @return Resource contents as UTF-8 text.
     */
    static String loadResource(String resourcePath) {
        try (InputStream input = BenchmarkResources.class.getResourceAsStream(resourcePath)) {
            if (input == null) {
                throw new IllegalArgumentException("Missing resource: " + resourcePath);
            }
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            input.transferTo(output);
            return output.toString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Failed reading resource: " + resourcePath, e);
        }
    }

    /**
     * Loads a dataset graph from a TRIG resource.
     *
     * @param resourcePath Classpath resource path.
     * @return Dataset graph populated from the TRIG content.
     */
    static DatasetGraph loadDatasetFromTrig(String resourcePath) {
        DatasetGraph dsg = DatasetGraphFactory.create();
        try (InputStream input = BenchmarkResources.class.getResourceAsStream(resourcePath)) {
            Objects.requireNonNull(input, "Missing resource: " + resourcePath);
            RDFDataMgr.read(dsg, input, Lang.TRIG);
        } catch (IOException e) {
            throw new IllegalStateException("Failed reading trig dataset: " + resourcePath, e);
        }
        return dsg;
    }

    /**
     * Generates a synthetic dataset with a predictable number of quads.
     *
     * @param size Number of quads to insert.
     * @return Dataset graph populated with synthetic data.
     */
    static DatasetGraph generateDataset(int size) {
        DatasetGraph dsg = DatasetGraphFactory.create();
        for (int i = 1; i <= size; i++) {
            dsg.add(Quad.defaultGraphIRI, NodeFactory.createURI("https://example.org/" + i), RDFS.comment.asNode(),
                    NodeFactory.createLiteralLang("foo", "en-gb"));
        }
        return dsg;
    }
}
