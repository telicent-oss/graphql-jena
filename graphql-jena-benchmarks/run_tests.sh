#
# Copyright (C) Telicent Ltd
#
# Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
# the License. You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
# an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
# specific language governing permissions and limitations under the License.
#

  #!/bin/bash
  # GC
  java -jar target/benchmarks.jar -wi 2 -i 3 -f 1 -bm avgt -tu ms -prof gc \
    -rf json -rff target/profile-gc.json

  # Stack
  java -jar target/benchmarks.jar -wi 2 -i 3 -f 1 -bm avgt -tu ms -prof stack \
    -rf json -rff target/profile-stack.json

  # Compiler
  java -jar target/benchmarks.jar -wi 2 -i 3 -f 1 -bm avgt -tu ms -prof comp \
    -rf json -rff target/profile-comp.json

  # Memory pools
  java -jar target/benchmarks.jar -wi 2 -i 3 -f 1 -bm avgt -tu ms -prof mempool \
    -rf json -rff target/profile-mempool.json

  # Pauses
  java -jar target/benchmarks.jar -wi 2 -i 3 -f 1 -bm avgt -tu ms -prof pauses \
    -rf json -rff target/profile-pauses.json

  # Safepoints
  java -jar target/benchmarks.jar -wi 2 -i 3 -f 1 -bm avgt -tu ms -prof safepoints \
    -rf json -rff target/profile-safepoints.json

  # Classloading
  java -jar target/benchmarks.jar -wi 2 -i 3 -f 1 -bm avgt -tu ms -prof cl \
    -rf json -rff target/profile-cl.json

  # JFR
  java -jar target/benchmarks.jar -wi 2 -i 3 -f 1 -bm avgt -tu ms \
    -prof jfr:dir=target,configName=profile \
    -rf json -rff target/profile-jfr.json
