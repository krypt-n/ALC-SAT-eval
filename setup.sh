#!/bin/bash

dllearner_version=1.5.0

wget "https://github.com/SmartDataAnalytics/DL-Learner/releases/download/${dllearner_version}/dllearner-${dllearner_version}.tar.gz"
tar xf "dllearner-${dllearner_version}.tar.gz"
cp -R "dllearner-${dllearner_version}" learningsystems/celoe/
rm "dllearner-${dllearner_version}.tar.gz"
mvn package -D skipTests
pip install "pandas==2.2.3"
pip install "ontolearn==0.8.1"
pip install "tabulate"
mkdir learningtasks/premierleague/owl/data
wget --output-document learningtasks/premierleague/owl/data/premierleague.owl \
    https://github.com/SmartDataAnalytics/SML-Bench/blob/updates/learningtasks/premierleague/owl/data/premierleague.owl?raw=true
