window.BENCHMARK_DATA = {
  "lastUpdate": 1772423337379,
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
      },
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
          "id": "249d08afa75568511b999b12070d24f945ecf36a",
          "message": "Merge pull request #136 from telicent-oss/core_1181_consolidate_java\n\n[CORE-1181] Consolidating Java versions across all repos on v21",
          "timestamp": "2026-02-24T14:27:42Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/249d08afa75568511b999b12070d24f945ecf36a"
        },
        "date": 1772423336903,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.028711095255556675,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.0421176559151831,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.037525879338689325,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.07732259291717315,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.47631889380487985,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.921605860050923,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0007585799548996611,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.0038894441094966176,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.032607912440917285,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0005431007989025312,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0019025629370134146,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.015466030203252663,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0004906262647270284,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0018161953440216335,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.014042820096058874,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0008039538235817472,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.003802276976098564,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.034036677858218116,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.02398596064986933,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.02228180160906669,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      }
    ]
  }
}