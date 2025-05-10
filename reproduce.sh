#!/bin/bash

add_line() {
    sed '$d' "temp1$2" > "$1$2"
    tail -1 "temp2$2" >> "$1$2"
    tail -1 "temp1$2" >> "$1$2"
}

name='EvoLearner'
name2='CELOE'
name3='SPaCEL'
name4='ALC-SAT'

mkdir -p results
results=()

mvn -e exec:java -Dexec.mainClass=org.aksw.mlbenchmark.Benchmark -Dexec.args=scripts/evolearner.plist
results+=("textResult-evolearner.xml")

mvn -e exec:java -Dexec.mainClass=org.aksw.mlbenchmark.Benchmark -Dexec.args=scripts/celoe.plist
results+=("textResult-celoe.xml")

mvn -e exec:java -Dexec.mainClass=org.aksw.mlbenchmark.Benchmark -Dexec.args=scripts/spacel.plist
results+=("textResult-spacel.xml")

mvn -e exec:java -Dexec.mainClass=org.aksw.mlbenchmark.Benchmark -Dexec.args=scripts/alc-sat.plist
results+=("textResult-alc-sat.xml")

rm "$config_file"
python scripts/sml_parser.py "${results[@]}" "$name"

python scripts/combine_frames.py "${name}f1"   "results/f1_systems.md"
python scripts/combine_frames.py "${name}acc"   "results/accuracy_systems.md"
python scripts/combine_frames.py "${name}length"   "results/length_systems.md"
