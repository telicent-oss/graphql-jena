window.BENCHMARK_DATA = {
  "lastUpdate": 1773633684519,
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
      }
    ]
  }
}