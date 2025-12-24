# Benchmarking

## Introduction
This repository uses JMH to measure performance of the GraphQL execution layer over Jena datasets.

The benchmarks are intended to provide a repeatable measure of query execution costs and to track
regressions over time. Results should be treated as indicators, not absolute numbers.

## How It Works

The benchmarks live in the `graphql-jena-benchmarks` module and are built into a runnable JAR:

```bash
mvn -pl graphql-jena-benchmarks -am -DskipTests package
java -jar graphql-jena-benchmarks/target/benchmarks.jar
```

JMH is configured with warmup iterations, measurement iterations, and forks. These are important:

- Warmup iterations allow the JVM to JIT compile hot code before measurements are recorded.
- Measurement iterations capture steady-state performance.
- Forks run benchmarks in isolated JVMs to reduce cross-test interference.

The benchmark JAR accepts JMH arguments. For example, this uses one fork and collects average
time in milliseconds per operation:

```bash
java -jar graphql-jena-benchmarks/target/benchmarks.jar -wi 2 -i 3 -f 1 -bm avgt -tu ms
```

## Benchmark Tests Included

Benchmarks are intentionally small and focus on core query execution paths:

- Dataset queries (DatasetQueryBenchmark)
  - `executeSimple`: executes `simple-quads.graphql` and scales the dataset size (10, 100, 1000).
  - `executeFiltered`: executes `filtered-quads.graphql` across the same sizes.
  - These tests exercise `DatasetExecutor`, `QuadsFetcher`, and selection/filter evaluation.
  - Expect `executeSimple` to scale with dataset size and allocations, while `executeFiltered`
    should remain flatter for these inputs.

- Traversal queries (TraversalQueryBenchmark)
  - `executeSimple`: runs `simple-traversal.graphql` over `traversals.trig`.
  - `executeFriends`: runs `friends.graphql` over the same dataset.
  - These tests exercise `TraversalExecutor`, `TraversalEdgesFetcher`, and traversal wiring.
  - Expect roughly constant time for this fixed dataset unless traversal logic changes.

Resources used by the benchmarks are stored under:

- `graphql-jena-benchmarks/src/main/resources/queries`
- `graphql-jena-benchmarks/src/main/resources/data`

## Profiling Modes

The `graphql-jena-benchmarks/run_tests.sh` script runs a standard suite of profilers and writes
JSON results into `graphql-jena-benchmarks/target/`:

- `profile-gc.json`: GC allocation rate, allocation per operation, and GC time.
- `profile-stack.json`: a coarse stack sampling view of hot methods.
- `profile-comp.json`: JIT compiler activity during the run.
- `profile-mempool.json`: memory pool usage snapshots.
- `profile-pauses.json`: pause summaries (e.g., GC/safe-point pauses).
- `profile-safepoints.json`: safe-point timing samples.
- `profile-cl.json`: class loading/unloading activity.
- `profile-jfr.json`: JFR summary (requires attach permissions).

Run the script from the benchmarks module after building:

```bash
cd graphql-jena-benchmarks
./run_tests.sh
```

Notes:

- JFR requires the ability to attach to the JVM; sandboxed environments may block this.
- Forked runs (`-f 1`) are recommended for stable measurements.
- Use the same JVM version and hardware when comparing results over time.

## Output Files

Typical output artifacts:

- `graphql-jena-benchmarks/target/benchmark_results.json` for the baseline run.
- `graphql-jena-benchmarks/target/profile-*.json` for profiler runs.


