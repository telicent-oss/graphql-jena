window.BENCHMARK_DATA = {
  "lastUpdate": 1788145945384,
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
      },
      {
        "commit": {
          "author": {
            "name": "Paul Gallagher",
            "username": "TelicentPaul",
            "email": "132362215+TelicentPaul@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "b41e84cfe1b76f47d9ef5e77f88eb0f10c2fd18f",
          "message": "Merge pull request #139 from telicent-oss/release/0.11.0\n\nComplete Release 0.11.0",
          "timestamp": "2026-03-03T16:13:55Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/b41e84cfe1b76f47d9ef5e77f88eb0f10c2fd18f"
        },
        "date": 1773028175908,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.01973986278074439,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.04075063779305744,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.027395908659709676,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.07771553413837473,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.40702906062634486,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.815201683697806,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0007437510897348889,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.0038741168728491683,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03180689176620443,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0005529175696985169,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0019666364593499378,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.015913913032927284,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0005198814460306779,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0017589892986309252,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.014317753812293868,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.000697924970510834,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.0036721260361282306,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.03131553219134766,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.023386745226006322,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.02178174655626866,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
      {
        "commit": {
          "author": {
            "name": "Paul Gallagher",
            "username": "TelicentPaul",
            "email": "132362215+TelicentPaul@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "b41e84cfe1b76f47d9ef5e77f88eb0f10c2fd18f",
          "message": "Merge pull request #139 from telicent-oss/release/0.11.0\n\nComplete Release 0.11.0",
          "timestamp": "2026-03-03T16:13:55Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/b41e84cfe1b76f47d9ef5e77f88eb0f10c2fd18f"
        },
        "date": 1773633684193,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.029053236363178095,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.03336006861749172,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.035132601701418166,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.0650713523805639,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.4477230739902566,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.869460797268793,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0008159478288714229,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.003965173798035508,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03182328221122774,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0005039209662065894,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0019327764108104679,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.015912012317763007,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0004798826780103866,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.001768299509625131,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.014608359261063778,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0007540544029580867,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.003876084893100623,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.03432707026425605,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.02365434621441985,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.02210765806320818,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
      {
        "commit": {
          "author": {
            "name": "Paul Gallagher",
            "username": "TelicentPaul",
            "email": "132362215+TelicentPaul@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "b41e84cfe1b76f47d9ef5e77f88eb0f10c2fd18f",
          "message": "Merge pull request #139 from telicent-oss/release/0.11.0\n\nComplete Release 0.11.0",
          "timestamp": "2026-03-03T16:13:55Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/b41e84cfe1b76f47d9ef5e77f88eb0f10c2fd18f"
        },
        "date": 1774238135540,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.022961030254698273,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.046918229169508975,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.03977839712381912,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.06571920548860281,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.43562851324952967,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 4.026117459949346,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0007992599047429886,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.0038934424066273897,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03150466326372355,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0005058810377494425,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.001867162646905705,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.013153729187529307,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0005088778772887369,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0018022404510021266,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.014002915784659606,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0007508635455734268,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.0039018556140446186,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.03440061841014525,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.023871542090933284,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.024213811378195605,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
      {
        "commit": {
          "author": {
            "name": "Paul Gallagher",
            "username": "TelicentPaul",
            "email": "132362215+TelicentPaul@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "fd5a0de517c73d42ebef86db938a1136ad6130ec",
          "message": "Merge pull request #143 from telicent-oss/release/0.11.1\n\nComplete Release 0.11.1",
          "timestamp": "2026-03-25T17:32:30Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/fd5a0de517c73d42ebef86db938a1136ad6130ec"
        },
        "date": 1774843397495,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.02262804050097587,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.02867540787795661,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.037981649788754145,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.06255897039110908,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.39428779443320955,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.627068903595117,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0006770575518135039,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.003952636630564541,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03665149501438425,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.00047498583025579617,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.002023639156619925,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.014840579171850483,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.00046871262756995714,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0018272340560199051,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.015320420897251675,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0006673456110869042,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.004082740088312305,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.0341002096808793,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.020659303127631052,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.021579373887899673,
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
          "id": "1ef557824b7d18dd59f324b38670b257993912fc",
          "message": "Merge pull request #144 from telicent-oss/dependabot/maven/patches-e0e520c0bb\n\nBump the patches group with 3 updates",
          "timestamp": "2026-03-31T08:14:05Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/1ef557824b7d18dd59f324b38670b257993912fc"
        },
        "date": 1775448155563,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.024585724959536634,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.05212364893860754,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.03480796581982815,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.07305078008478016,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.4674993566024747,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 4.159089802434408,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.000775128253205456,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.003965441020653521,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03284708119668469,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0005345843533743641,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0019236229532586934,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.0155246243589938,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0004967550177247703,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0018127799187463737,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.014712007257244522,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0007116574219779549,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.003884859187786934,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.032768294748628246,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.023971856363803366,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.022557349982681982,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
      {
        "commit": {
          "author": {
            "name": "Paul Gallagher",
            "username": "TelicentPaul",
            "email": "132362215+TelicentPaul@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "d07977f4847a7dd5ebe4cfb301b60461ab30badc",
          "message": "Merge pull request #148 from telicent-oss/dependabot/maven/patches-e3541c0abc\n\nBump the patches group with 4 updates",
          "timestamp": "2026-04-07T07:40:12Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/d07977f4847a7dd5ebe4cfb301b60461ab30badc"
        },
        "date": 1776053286360,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.020128788462427164,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.02422732810056855,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.02636445543901851,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.0612918973001377,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.3728846585167105,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.9572815297592996,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0006695312704012856,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.004044876870385434,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03649289189841542,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0004752795019400905,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0019780253918047497,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.017440292170196738,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0004795782868013265,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0017839455458101754,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.01608622505771089,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0006676750310646605,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.004052003192795381,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.03574781848517845,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.02216794291877026,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.02272966278888493,
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
          "id": "32a37f0c6e60418ae3b950e94f0a94816fb49912",
          "message": "Merge pull request #150 from telicent-oss/release/0.12.0\n\nComplete Release 0.12.0",
          "timestamp": "2026-04-14T10:06:00Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/32a37f0c6e60418ae3b950e94f0a94816fb49912"
        },
        "date": 1776658263461,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.013435836532352424,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.018236560390338,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.01803617005137403,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.03996550719582242,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.2801491105839491,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 2.8998340638998723,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.0034688920302798068,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.019749038771189175,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.08713523029670427,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.005642763237580432,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04148961133448333,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.1997151891157479,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.00355959556968564,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.019099847874158915,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.09375242830283696,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.0062622348945874096,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04051178084494141,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.19269378016182412,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.000511751941693634,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.003012740732576298,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.02639199999436397,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0003703929013234764,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0015161911604844296,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.013251162210162626,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0003531968298214191,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0014473882787035923,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.012532519973083744,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0005095364814194313,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.0030570009792681117,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.026021449759441904,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.033286371569190815,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.31933551708176355,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 1.542235316295289,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.04064190714521417,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.38221222462431576,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 1.883209534524503,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.0073661125650757555,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.04281277387130259,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.21020016615845027,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.013014197740678938,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.08712869655182087,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.4334871010059293,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.013661424268672263,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.070787192825559,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.4635919389262873,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.0035625851241374144,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.03031761506648481,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.19389146224451625,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.015861680904577364,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.015487472112528768,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
      {
        "commit": {
          "author": {
            "name": "Paul Gallagher",
            "username": "TelicentPaul",
            "email": "132362215+TelicentPaul@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "ca5d4d76f8639d242493f94b79fa171ef9ad7eac",
          "message": "Merge pull request #145 from telicent-oss/core_1234_call_tailored_uri_search_endpoint\n\n[CORE-1234] Call tailored Search endpoint",
          "timestamp": "2026-04-22T13:00:03Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/ca5d4d76f8639d242493f94b79fa171ef9ad7eac"
        },
        "date": 1777263487119,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.023528532915021046,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.0233182909672292,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.03034474051000008,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.059245833902131485,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.3855933747683707,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.8713076288796824,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004086452553051629,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02417488796258465,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.11517821812872216,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.006973323274863723,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.0442657750603184,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.23773453997937483,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004510347503939413,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.024887372688468648,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.12093672570885343,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.008045036915504494,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.052634749505542075,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.25951795515677434,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0006772732277840924,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.003870215437974483,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03232528581263807,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0004708314287683685,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0019981341892031258,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.01734720841793705,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.00044941374105410427,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0018344595347072562,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.015234861198508927,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0006731231020546345,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.00386303024045668,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.03409067494086817,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.043147208030635785,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.42273728848576914,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.0638422421471563,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.052241178539289425,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.48865748297759204,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.435793126474478,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.0095591066589018,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.05483049900531358,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2725053544429366,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.01669071043781295,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.10842870794102318,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5508492814623276,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.017558047138818252,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.08560162117498289,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5911384413477371,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.004642150736988873,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.03809858726523471,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2507570100353403,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.020729990150508942,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.021752969075063588,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
      {
        "commit": {
          "author": {
            "name": "Paul Gallagher",
            "username": "TelicentPaul",
            "email": "132362215+TelicentPaul@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "dce14b82b0c8b5bb391398bfc081ded603776da2",
          "message": "Merge pull request #152 from telicent-oss/dependabot/maven/patches-1929ee3a10\n\nBump commons-io:commons-io from 2.21.0 to 2.22.0 in the patches group",
          "timestamp": "2026-04-28T07:48:34Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/dce14b82b0c8b5bb391398bfc081ded603776da2"
        },
        "date": 1777868575075,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.025210958944551016,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.023185952553895495,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.02387786302437739,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.05617427125683037,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.36118618012145354,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.82647154122412,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004167258852891774,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02302180794575675,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.10945418363313719,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.006687671391708792,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04711034234586146,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.24449190876028376,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004541532147464014,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02496134290489325,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.1245363834080988,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.007984529166472422,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.053984807453116956,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.26573226662339916,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0006776621876683975,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.003908576145682734,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03249594078735111,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0004807073512421532,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.002049674336291204,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.015277177802080594,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.00044655450775493884,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0018803954635849805,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.01586084427465638,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0006877584715908915,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.004025757352128952,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.036322855526269175,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.0426827860701803,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.4192429835907478,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.0218811808769046,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.05211089775035666,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.491454191639685,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.5188759647955608,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009674839201300203,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.054818013552764444,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.27485748685184075,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.016777171985233413,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.10937095710359146,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5575605111781322,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.018353328347452957,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.08944141640777413,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5714800697149434,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.0046357009664431586,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.03794210222123409,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.24218891418238528,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.02143612927369538,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.019591753992522384,
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
          "id": "8f02158690cb341fdc3813df169be65dc47eeb36",
          "message": "Merge pull request #154 from telicent-oss/dependabot/maven/patches-749dd7f15b\n\nBump com.fasterxml.jackson:jackson-bom from 2.21.2 to 2.21.3 in the patches group",
          "timestamp": "2026-05-05T08:24:27Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/8f02158690cb341fdc3813df169be65dc47eeb36"
        },
        "date": 1778473820604,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.019717155140713788,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.030358134574667583,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.033421095953241116,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.06242484489144864,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.3567143785783318,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.3915599819830997,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004152978116467325,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02294641337523628,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.10799500991080754,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.0067990781768740875,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04808560092885507,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.23413378171985152,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.0045325585587026,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.024831009797278848,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.1222215611046729,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.008072360193868984,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.054703494761278115,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.26121810564591835,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0006725466911056802,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.004025307762943634,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03622761808085168,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.000470292145110609,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0019201461036884168,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.014561554298782873,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0004753319370500903,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0019041416914538152,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.015903482265884495,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0006612098453778623,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.003841149150503849,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.033575915255823284,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.04266705625901496,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.41483025454009886,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 1.9348281674644188,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.052363912222200715,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.4897583141861841,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.3960431946853316,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009571359640504108,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.05485029696119029,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2821594337490479,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.01661551409875495,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.10869930376080189,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5501216650015719,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.018481999177608923,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.08568582118703409,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5766717080621402,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.004656769153423404,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.03810383850779113,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.24830487506701102,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.020394216889374237,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.01835483945671669,
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
          "id": "7e1941374a5a04abf54e23726e16e9257efed688",
          "message": "Merge pull request #156 from telicent-oss/release/0.12.2\n\nComplete Release 0.12.2",
          "timestamp": "2026-05-14T13:05:13Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/7e1941374a5a04abf54e23726e16e9257efed688"
        },
        "date": 1779078805858,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.01825874011186895,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.02886577831442359,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.03769651672965708,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.055136513881023023,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.35841513636943684,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.475589481166415,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004324724486135124,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.022976261451265457,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.11322530723339227,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.006768406668443886,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04776283998947497,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.24536374362045024,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004552399050853695,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.024717877907016003,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.1252557903834844,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.007978772078920445,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.053189510011276676,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.25431917848206287,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0006831526507424567,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.003934057921016981,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.034050642854940055,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0004715546938434953,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0019702797589607195,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.017212257186463978,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0004891709992798834,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.001848043433028884,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.01560953208115036,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0006569926915175557,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.003870897245619107,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.0341826403412728,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.043136149090277714,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.42016752154324394,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 1.9703309623013041,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.053015943607549854,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.49948661206938616,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.472285831317127,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009598369658947824,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.055088767226484085,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2760136480048887,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.016618477308883082,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.10891764368404062,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5737296783398039,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.01802153164406511,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.09046534780606831,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.6112218125019463,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.004588392296970941,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.037632699707254746,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.24089141057164268,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.019555519988573588,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.018145864290061386,
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
          "id": "7e1941374a5a04abf54e23726e16e9257efed688",
          "message": "Merge pull request #156 from telicent-oss/release/0.12.2\n\nComplete Release 0.12.2",
          "timestamp": "2026-05-14T13:05:13Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/7e1941374a5a04abf54e23726e16e9257efed688"
        },
        "date": 1779683725770,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.025120036797970726,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.03381977497068376,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.029384891315807127,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.07508522961870197,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.4005567990810405,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.4273676059578335,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004017046283454503,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.023312212959700294,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.10826608185638513,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.006848232070625298,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04598231743348251,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.22619124697831788,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004406918037525301,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02314839629726742,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.11872586471102572,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.0077898894674162,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.046630911285452374,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.2458945660099039,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.00074255832598233,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.003897814457007141,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03458935145581058,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0005101406238164594,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.002001478411412483,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.01562899054302066,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0005094634471654491,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0018065550197015568,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.014570063173173558,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.000747620601810026,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.003809745516149207,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.0343593748722526,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.04528729838624584,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.43253594296746867,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.006728690141397,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.054164054247920694,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.4961300501779866,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.457999194900238,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009396270091894124,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.05336870442624672,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2606237202299923,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.01618683664429411,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.10652062837088216,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5349221932177373,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.02105092992466186,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.09601870963417752,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.6131552419430704,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.00479125619154301,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.03837263716136447,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2523155303635898,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.022334878309869684,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.022933015428962183,
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
          "id": "7e1941374a5a04abf54e23726e16e9257efed688",
          "message": "Merge pull request #156 from telicent-oss/release/0.12.2\n\nComplete Release 0.12.2",
          "timestamp": "2026-05-14T13:05:13Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/7e1941374a5a04abf54e23726e16e9257efed688"
        },
        "date": 1780288602851,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.020163081973739224,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.03720254427974503,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.02813557127004537,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.0601266292766914,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.4061469410699268,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.1148617765862667,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.0041627755808272,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02290131417217481,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.11174562446404934,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.0070232366242861275,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04435416580617316,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.20670573024176803,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.00456170585922196,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.025389925657312278,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.12620591309747992,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.00810096565242106,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.05450561243863421,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.25827821112057425,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0006785967813464568,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.0040986152772976995,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03404812709113846,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.00048086939835253177,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0020120738884786145,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.014588112170305994,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0004700523229662277,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.001844355555931567,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.015496192816032194,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.000681176369181015,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.0039029577999345247,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.03401108940784798,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.04340330782847209,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.41673790034584046,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 1.983689021935452,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.05266704906657852,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.4943523668339502,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.45618504037375,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009557470268089277,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.05547023414615002,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.28148929864273836,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.01668930522439029,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.11002945995013892,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5736588347929148,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.0188117969636571,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.09072461427032084,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.6158987838192315,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.004624182934620997,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.038827450809286325,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.24782618512655294,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.019468585723005555,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.01991836430647849,
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
          "id": "88e0687b8f143d35de3be5a090b5d225bc6e023b",
          "message": "Merge pull request #157 from telicent-oss/dependabot/maven/patches-9fc303e19a\n\nBump the patches group with 9 updates",
          "timestamp": "2026-06-04T09:07:02Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/88e0687b8f143d35de3be5a090b5d225bc6e023b"
        },
        "date": 1780893354730,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.01978722820303053,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.03567235566215325,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.02593201744662521,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.050980578123794,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.37770924230481523,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.0248542812726433,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004187195306830265,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02368509771487502,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.11384143167392251,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.007178133682330221,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04755029366064654,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.23995775521714782,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.0045350290711069505,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.024777648290360513,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.12539655639372044,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.008105668590996392,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.05337645584992814,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.24871751289791413,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0007136908403122343,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.004131591106239935,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03446868537554806,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0004877705452625749,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0020520780938316266,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.014831923592316762,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0004700745775259219,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.00182133213167918,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.015762225655352385,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0006748604526389668,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.0037789850161678053,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.034590218184934594,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.0438470023946014,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.4266263350123665,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 1.974430529229301,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.05233910646302988,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.5035042065784286,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.477762151379174,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009557364696528937,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.05490891209791618,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2864864652701023,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.0165889124831349,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.11166701170659311,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5387280308919702,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.018326911633028343,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.09041291301203747,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.6035200814241568,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.004640190099461778,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.039825744676529155,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.25468137306324296,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.020911741711166,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.020480087679946937,
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
          "id": "06be19a776979609b22f12d56d2a73979aa2fc5c",
          "message": "Merge pull request #158 from telicent-oss/dependabot/maven/patches-38fd330ad6\n\nBump the patches group with 2 updates",
          "timestamp": "2026-06-10T10:18:31Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/06be19a776979609b22f12d56d2a73979aa2fc5c"
        },
        "date": 1781498192664,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.02471531115050146,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.04757717977300785,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.02988533751616934,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.05688980784830342,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.30799806184403494,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.644038041593392,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004107022284889604,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.022785894278778966,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.10874620652748275,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.007093635335880484,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04710063641287678,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.23155738724530664,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.0045343742958613075,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.024831809765688283,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.12029601641136929,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.00800635108215193,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.05405982791487981,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.25142345078716355,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0006748757604629056,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.004028197050499314,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03432066586946323,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.000493620954035,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0019946169814652457,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.01739262356534614,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.00047570279959355775,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0018533339025067297,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.015516883958970063,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0006666747392631752,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.0039473080871667045,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.032235306998821295,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.043766515260439696,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.41828814156996746,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.0396064061374415,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.05309112717213682,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.4976413985841195,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.444979586032334,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009555659712096891,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.05483247114669639,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2815022809281367,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.016632994820285938,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.10878284168411911,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5714440483687856,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.017449206106457782,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.0909851475252912,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.626731267466866,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.004602354390253673,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.03767677766614271,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.24105117348023533,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.023405296242740712,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.01882950899373788,
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
          "id": "4bde14bb584239d2ababb5d7deee9b7f01f1cbe1",
          "message": "Merge pull request #159 from telicent-oss/dependabot/maven/patches-b78e5375e9\n\nBump the patches group with 3 updates",
          "timestamp": "2026-06-16T08:10:51Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/4bde14bb584239d2ababb5d7deee9b7f01f1cbe1"
        },
        "date": 1782103001164,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.021842518959529312,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.03220234421221458,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.02507781136965412,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.05173442802500255,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.3685395227349484,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.5229699442129974,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004157989867389528,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.022915536656670836,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.11168831500540077,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.006684473512663774,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04779810146152049,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.23639636101131348,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004543163895587339,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02471056333757004,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.12321506563291482,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.00805617079449954,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.05521485843685098,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.236572587144284,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0007212877073487115,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.004019325402437503,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.0326656007450115,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.00047306513246248586,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0020306095888311068,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.015258342369281653,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0004923232790101525,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.001861200204498035,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.016027093858274395,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.000675797249384733,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.00403518714033864,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.03427303857322652,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.042792326164565415,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.4187347729068243,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 1.9406083504746032,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.05291553228733531,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.4968009188037336,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.431037799000564,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009547792852681383,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.055359340368864134,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2732637611496219,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.016574762647839456,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.10900824560666807,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5648090614888739,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.018971582725243328,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.09114000615015416,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.6014375056840258,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.0046167762803644334,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.03854412572080743,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.24477366540418877,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.02013045080285077,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.019116657109547283,
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
          "id": "f4c204ed09fcb8bf01372633aeefa11afc2a203b",
          "message": "Merge pull request #162 from telicent-oss/dependabot/github_actions/actions/checkout-7\n\nBump actions/checkout from 6 to 7",
          "timestamp": "2026-06-23T08:10:26Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/f4c204ed09fcb8bf01372633aeefa11afc2a203b"
        },
        "date": 1782707750578,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.02302856220231737,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.046267649440031894,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.04724278908890673,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.0871814557445351,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.3618601841654918,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.4486075764168684,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004024130409133521,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02343142903266148,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.10379844106570449,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.006831178405003741,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04568680347995816,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.22246770744434938,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004352843811448438,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02328463928935993,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.11723765319529263,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.007674485519488411,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.05042973121705628,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.2435019517199993,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0007644037400827678,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.003895693806431797,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03190524455894556,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0005500278866201846,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0018591866101464345,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.013855639564676947,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0005015980505483873,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0017887627423834292,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.014125219141417218,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0007540069394234103,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.003928407160019548,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.031439073949500594,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.0452867294595014,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.4428042299356641,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.0417308047070217,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.05483229962058789,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.48892622084880166,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.4645799372328345,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009253558244413127,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.05311587476452664,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2637271843484166,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.016200429607429758,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.10565350624257497,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5391300068574988,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.019045864507890333,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.09770050476567556,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.6333943494175304,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.004887650522194413,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.039849120962266824,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2548070335037831,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.0223803068990422,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.019258341704890266,
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
          "id": "df452fdda28a5424b6984c0382ef88792dcfe0e4",
          "message": "Merge pull request #163 from telicent-oss/dependabot/maven/patches-7410e20916\n\nBump the patches group with 2 updates",
          "timestamp": "2026-06-30T08:38:01Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/df452fdda28a5424b6984c0382ef88792dcfe0e4"
        },
        "date": 1783312340634,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.025801258624728655,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.033785800686524974,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.04831810670868664,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.06770856619609834,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.4095659503361431,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.735096015109301,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.003990922743412374,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02192240888398943,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.10440952947798847,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.006713595375629415,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.045402219705619265,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.22359629353191704,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004379100694130135,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.022907032201609542,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.116878078627774,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.007886302481678566,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.05107771764050005,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.22422539068488806,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0007794868915098507,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.004057610935175882,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03513092164302587,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0005377973426363661,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.001966923970549456,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.015604699408015834,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0004970762572695679,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0017531078284551312,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.014331320876318965,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0007829079325156141,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.004043678834173077,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.032812903080750216,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.042917823596668396,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.43160479577378674,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.0019978633816717,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.057292887872309704,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.4957585086581436,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.4849466951674226,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009443316117190579,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.05335421213047384,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2675972297597901,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.016342877605449228,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.10785266460227305,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5594197335239476,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.018505368114473564,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.09383511237980059,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.6120509232239094,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.004753786562593929,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.038692410666852674,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.25239128604222716,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.024973771405018604,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.020514064801317344,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
      {
        "commit": {
          "author": {
            "name": "Paul Gallagher",
            "username": "TelicentPaul",
            "email": "132362215+TelicentPaul@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "f6dd29644c786f970e172eaa88aa52ce56b23018",
          "message": "Merge pull request #166 from telicent-oss/dependabot/maven/patches-f31d1f1b13\n\nBump the patches group across 1 directory with 5 updates",
          "timestamp": "2026-07-08T10:20:21Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/f6dd29644c786f970e172eaa88aa52ce56b23018"
        },
        "date": 1783916275139,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.018702451509193137,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.03729627550565541,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.030034918245582146,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.05246249569144957,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.37945224835134633,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.52064749644899,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.0042570477326788255,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.023319022115032877,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.11326975735160491,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.0076916536120853075,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.047265245806809866,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.23795106486442985,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004576522539217722,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02546282678739583,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.12163493650676412,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.008324506566664152,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.05360071357119352,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.2704219237019842,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0006760739379805562,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.003932663831633178,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.034621133866602316,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.00048232605261048085,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0020077375222314676,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.015399288160940381,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0004874153156484027,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0018462294522859706,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.015560875421207354,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0006946357193073476,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.004097477228282183,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.03465603887748792,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.04357953021951844,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.42237078756912066,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.1916693720408333,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.053065872855656254,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.5126808376498088,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.413007512069012,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009665807551751225,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.05490759868815717,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.28057517091130224,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.01672246203096491,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.11091392640410662,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5684410280522695,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.017736517906849446,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.09070658798412762,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.6221308277825223,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.004691063195869038,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.03806128313098269,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.24808610606524248,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.021264230939411998,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.019841847781253842,
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
          "id": "1858602c920a350e238626ba5d07419e8e372b5e",
          "message": "Merge pull request #167 from telicent-oss/dependabot/maven/patches-45fd085bcc",
          "timestamp": "2026-07-14T06:54:19Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/1858602c920a350e238626ba5d07419e8e372b5e"
        },
        "date": 1784521244451,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.020021849225471754,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.025918224093014322,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.03415822125158142,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.0538731660493166,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.3395607122271839,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.48539826229495,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004142966339956055,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02288718575355971,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.10828172213992744,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.00710579354375782,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04554700546659139,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.23783987569464257,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004478299464522922,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02499400132985567,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.12189233874242093,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.008043011351207496,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.054647949250270764,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.2623381349239853,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0006899870876331234,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.003924245030566356,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03428676205144411,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0004711399933327286,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0020529842902356293,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.014981518560657487,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.00044502362402344615,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0017936700846695956,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.015942510050478035,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0006588089640770637,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.0038596159132261616,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.033902155429248265,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.04275845538974731,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.4296576796111457,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.0344710177524976,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.05252911657141402,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.5470486299896784,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.383961806142802,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009643801223074526,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.055985576419915056,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.28852075327724563,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.016646493200794383,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.1089175520882465,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5523749609701616,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.01771868016571176,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.09213587209507895,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5688495315533135,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.004793010228767334,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.03772278177534196,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.23736772109919713,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.024239683077892815,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.018524997910979158,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
      {
        "commit": {
          "author": {
            "name": "Paul Gallagher",
            "username": "TelicentPaul",
            "email": "132362215+TelicentPaul@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "8ff77aaba9487026f81e27eadd50f6f950416608",
          "message": "Merge pull request #170 from telicent-oss/dependabot/maven/io.telicent.smart-caches-jaxrs-base-server-1.0.2\n\nBump io.telicent.smart-caches:jaxrs-base-server from 0.40.3 to 1.0.2",
          "timestamp": "2026-07-23T13:05:13Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/8ff77aaba9487026f81e27eadd50f6f950416608"
        },
        "date": 1785126040536,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.018526474069054156,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.023928526561528714,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.032953924911208875,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.05619711789535394,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.3236129552049949,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 2.9623012945415526,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.0041602832957510095,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.022850238358136637,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.10675892753715825,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.006638205016776541,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04740746382412494,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.23266715868258844,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004485081687888535,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.024784589983983467,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.1251171714444846,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.00797364396252377,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.054684320960700125,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.25879350311355204,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0006839109417399652,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.004064756080626064,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.032679912329892036,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0004715945488673002,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0020162682812619533,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.015254550099502289,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0004711442433908048,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0018328197246674546,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.01582551890448223,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0006753309884836413,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.003977751894789911,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.03730258593591101,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.04271754367134158,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.4208084038448583,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.155932060051042,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.05256727700998576,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.4957842671393009,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.4282692156896855,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009557655291429865,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.05471592732099012,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2907694779414847,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.016517173291609854,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.10846048984748868,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5593705889369759,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.017405671511906824,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.08973026718763344,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5884939815614526,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.004748278959927161,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.03791773289100338,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.24569362743297218,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.02116413909878101,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.01925895636769358,
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
          "id": "a01ad8378a11262e3ac163e26adb424e83beaff1",
          "message": "Merge pull request #172 from telicent-oss/dependabot/maven/patches-e02379a771\n\nBump the patches group with 5 updates",
          "timestamp": "2026-07-29T09:58:33Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/a01ad8378a11262e3ac163e26adb424e83beaff1"
        },
        "date": 1785730635480,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.02663871178132259,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.03337125434993988,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.029549240982188103,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.08506446883035558,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.40534714689890694,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.6469775242838356,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.003993153686627029,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02365308990238769,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.09988560528388186,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.006630658863699615,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.0455023771930179,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.23251793465278459,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004397408572573644,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.023534956897949145,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.11837599939462191,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.007607306347853252,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04909226109801744,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.24511844430231394,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0007096377409419779,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.00391450454785872,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03183371480773498,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0005364472630651392,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0019697165385346143,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.01351090832784758,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0004989590489391978,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.001761468592354703,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.014338219160678825,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0007474123827390667,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.003836865099346719,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.03158267206955423,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.045040651700528254,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.4535008081951375,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.0236203787404317,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.05750193895893806,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.5169650166529833,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.4397403793241446,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009227568568451992,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.052647944756147015,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2650371667230689,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.01606596712137563,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.10604879844373583,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.6048054351606043,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.020013853771167916,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.0928671451485979,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.6309967653647003,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.004772157053442469,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.04061586818107985,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.25869602508952033,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.0255952304031753,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.019600824591517213,
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
          "id": "a01ad8378a11262e3ac163e26adb424e83beaff1",
          "message": "Merge pull request #172 from telicent-oss/dependabot/maven/patches-e02379a771\n\nBump the patches group with 5 updates",
          "timestamp": "2026-07-29T09:58:33Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/a01ad8378a11262e3ac163e26adb424e83beaff1"
        },
        "date": 1786333103931,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.027970047207919856,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.03893392862962986,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.032032328420377655,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.06290680968061933,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.42590892213315706,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.534551155052242,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.003985464293467732,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.023346533325690887,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.10392587888915479,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.006708080226026122,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04548934849864599,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.22990614825699945,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.004389309477223902,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.022792088914723375,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.11424104987434527,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.007663436093363269,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.0498909124109316,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.2240632263284977,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0007618451699017964,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.0038977131351197025,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.0316622472005979,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0005096985493889275,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0019381648170160519,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.015755883057112122,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0005002878460101589,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.001767932355023345,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.013951880319119205,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0007567675705741506,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.003897516425439704,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.03139317525641083,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.04608312779689558,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.43491968483204535,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.135285362472094,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.054327713308380515,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.5272947714600602,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.462790283535191,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009279256799099298,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.053369723977200155,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.27267047359319113,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.015970792087995647,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.1065875982854451,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5609427483858177,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.018817412556192962,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.09734833905246915,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.6062150553003822,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.004897391289501034,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.038688250943245316,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2635653091724333,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.02080423298950758,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.019396825282405262,
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
          "id": "42f05ab150c27fa195e67b45419cd6c29b8abd03",
          "message": "Merge pull request #174 from telicent-oss/dependabot/maven/patches-8ea86cfe22\n\nBump the patches group across 1 directory with 9 updates",
          "timestamp": "2026-08-11T08:35:05Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/42f05ab150c27fa195e67b45419cd6c29b8abd03"
        },
        "date": 1786937018662,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.02145065919150629,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.034547613584609005,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.03187816781805016,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.06260498733162928,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.3981843678521914,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.3582168459629247,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.005008994589752245,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02595890021027112,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.1235187130969388,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.008733323027991862,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04971123959756825,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.24721493141009349,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.00470309638920823,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02627141154514966,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.12756371224300214,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.00776388965070717,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.05223881941844437,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.25809802677636756,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0009811229788049566,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.0046519327258272465,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.040064542807269586,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0007813141475798967,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.002775751094379389,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.021766862556728994,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0006931048600730891,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.002034848756118305,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.015119746175571125,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0009675927798193843,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.004563389794233537,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.03886000597628631,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.05921929104072019,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.5745204469925892,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.816907651492997,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.07018229258502934,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.6476216018206545,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 3.165193020382502,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009871461602956782,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.05585381962661731,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2976289594738267,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.017951836074554264,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.11429321236849414,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5778332021848972,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.03210894180554163,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.15055944238102606,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.8736032349926496,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.006853418010384391,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.051972303533673214,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.3296836269523374,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.029842171935051207,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.02962497398829569,
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
          "id": "17d8a3db9340e9f2c7dbb521339173fd2f0a9272",
          "message": "Merge pull request #175 from telicent-oss/dependabot/maven/patches-31c8d982f2\n\nBump the patches group with 2 updates",
          "timestamp": "2026-08-18T08:31:22Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/17d8a3db9340e9f2c7dbb521339173fd2f0a9272"
        },
        "date": 1787541954047,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.01338933032761575,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.01808969146768388,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.017814531821681332,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.03554529564030714,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.2373827219819408,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 2.5449915869928916,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.003644571759128448,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.01875932756968985,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.09783821656125674,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.006130092931902907,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.036733662233571794,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.19267295381801347,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.0038982951862169745,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.01730919533682649,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.08576374353664817,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.0050737183432856825,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.033578899565644436,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.1541841684945573,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0006895559488067481,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.0034111415481265102,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03133112062891977,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.000504584374571745,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.0023898924345350132,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.02007151779747261,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0004568714839237207,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0018218627768794703,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.012304292488758617,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.0006755982759820214,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.00337866488599768,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.031108438264307075,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.03475407953760202,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.3382682536121486,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 1.8723963915185704,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.04422208847517698,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.419799486432944,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.1505272199508325,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.0066230302394227085,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.0360541888508881,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.16505490744228057,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.01152569316490828,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.07133818126572701,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.3782417229761624,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.01855892522181837,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.09772068794322923,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5217105166016079,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.00400401356592294,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.03338802702377164,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.19465143430127585,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.012818616540247756,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.013743559964013101,
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
          "id": "b6d120d2ecd101f9b79e6145179dab1c053fed2a",
          "message": "Merge pull request #179 from telicent-oss/core_1473_address_sq_issues\n\n[CORE-1473] Addressing all SonarQube issues",
          "timestamp": "2026-08-25T12:59:08Z",
          "url": "https://github.com/telicent-oss/graphql-jena/commit/b6d120d2ecd101f9b79e6145179dab1c053fed2a"
        },
        "date": 1788145944544,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"10\"} )",
            "value": 0.027489438860172025,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"100\"} )",
            "value": 0.042769975223657525,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeFiltered ( {\"size\":\"1000\"} )",
            "value": 0.039410711985535286,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"10\"} )",
            "value": 0.08059706823404157,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"100\"} )",
            "value": 0.4251493787641955,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.DatasetQueryBenchmark.executeSimple ( {\"size\":\"1000\"} )",
            "value": 3.5792334738222418,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.005026605642661114,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.02541284430640354,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.12256702877566446,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.00867911133574927,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.04959982729195406,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.propertiesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.2347325822694286,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"100\"} )",
            "value": 0.0044860762403567634,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.026442392296335094,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_cached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.11489685966348742,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"100\"} )",
            "value": 0.007652910098834421,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"1000\"} )",
            "value": 0.0511207375142386,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.NodeTypeCacheBenchmark.typesAndCount_uncached ( {\"nodeCount\":\"5000\"} )",
            "value": 0.26082274655969184,
            "unit": "ms/op",
            "extra": "iterations: 5\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"10\"} )",
            "value": 0.0010308349607492686,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"100\"} )",
            "value": 0.0045545917165946805,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"subject-only\",\"size\":\"1000\"} )",
            "value": 0.03899048416457253,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"10\"} )",
            "value": 0.0007786349781814999,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"100\"} )",
            "value": 0.002730075422557685,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"triple\",\"size\":\"1000\"} )",
            "value": 0.02206116251227337,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"10\"} )",
            "value": 0.0006858190420677389,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"100\"} )",
            "value": 0.0019891381845240264,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"all-fields\",\"size\":\"1000\"} )",
            "value": 0.014933528768047098,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"10\"} )",
            "value": 0.001034885012704127,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"100\"} )",
            "value": 0.0044034550285736,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.QuadsFetcherMappingBenchmark.mapQuads ( {\"selection\":\"graph-only\",\"size\":\"1000\"} )",
            "value": 0.03924745551295621,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.05845686399090084,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.5866162667944886,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 2.741461326748659,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.0693865440517615,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.6785056298814823,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndAllFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 3.111528335185754,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.009571050140661957,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.05521641496702465,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_cached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.2923932378653322,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"100\"} )",
            "value": 0.017199047638100214,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.1142180233174168,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipFetcherReuseBenchmark.outRelsCountsAndPredicateFacets_uncached ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.5753615888680131,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.031246869848815962,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.15202777836799597,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.legacyTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.8521231742066561,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"100\"} )",
            "value": 0.006735609995674002,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"1000\"} )",
            "value": 0.05173439727547571,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.RelationshipTypeFacetBenchmark.optimizedTypeFacets ( {\"relationshipCount\":\"5000\"} )",
            "value": 0.32027119250057373,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeFriends",
            "value": 0.023982188079709883,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "io.telicent.jena.graphql.benchmarks.TraversalQueryBenchmark.executeSimple",
            "value": 0.020609578380394334,
            "unit": "ms/op",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      }
    ]
  }
}