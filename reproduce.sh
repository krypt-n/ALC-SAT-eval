#!/bin/bash

mkdir -p results

mvn -e exec:java -Dexec.mainClass=org.aksw.mlbenchmark.Benchmark -Dexec.args=scripts/evolearner.plist
python scripts/sml_parser.py "testResult-evolearner.xml" "evolearner"

mvn -e exec:java -Dexec.mainClass=org.aksw.mlbenchmark.Benchmark -Dexec.args=scripts/celoe.plist
python scripts/sml_parser.py "testResult-celoe.xml" "celoe"

mvn -e exec:java -Dexec.mainClass=org.aksw.mlbenchmark.Benchmark -Dexec.args=scripts/spacel.plist
python scripts/sml_parser.py "testResult-spacel.xml" "spacel"

mvn -e exec:java -Dexec.mainClass=org.aksw.mlbenchmark.Benchmark -Dexec.args=scripts/alc-sat.plist
python scripts/sml_parser.py "testResult-alc-sat.xml" "alc-sat"

python scripts/combine_frames.py "evolearnerf1" "celoef1" "spacelf1" "alc-satf1"   "results/f1_systems.md"
python scripts/combine_frames.py "evolearneracc" "celoeacc" "spacelacc" "alc-satacc"   "results/acc_systems.md"
python scripts/combine_frames.py "evolearnerlength" "celoelength" "spacellength" "alc-satlength"   "results/length_systems.md"
