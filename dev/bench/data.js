window.BENCHMARK_DATA = {
  "lastUpdate": 1771852850979,
  "repoUrl": "https://github.com/telicent-oss/graphql-jena",
  "entries": {
    "StoreBenchmark": [
      {
        "commit": {
          "author": {
            "name": "Rob Vesse",
            "username": "rvesse",
            "email": "rob.vesse@telicent.io"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "2d3fe9b5b506993a916a0485497cef638e46e4ac",
          "message": "Merge pull request #133 from telicent-oss/minor/fix_weekly_benchmarks\n\n[Minor] Fixing weekly benchmark failures and updating documentation w…",
          "timestamp": "2026-02-23T13:06:06Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/2d3fe9b5b506993a916a0485497cef638e46e4ac"
        },
        "date": 1771852850425,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.025159628266716447,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.061353612225651076,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.04193589126475739,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.08680638595106537,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.5203079265782756,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 4.839094141958926,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0007859144881099443,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.00383021409861403,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03261469599741552,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0005682176323631065,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.001941893857798984,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.014887421215293536,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0005677810780889207,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0020130695094611965,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.013520186912066322,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0007718916122654594,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.0036629664839737783,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.02798220420975399,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.03549894879990059,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.02669601386583967,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      }
    ]
  }
}