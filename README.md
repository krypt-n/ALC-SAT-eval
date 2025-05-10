Steps to reproduce table X

Create a virtual env using python 3.10 (version is important for evolearner)
```
    python3.10 -m venv sml
    source sml/bin/activate
```

Run script to set everything up
```
./setup.sh
```

Potentially if on macos, make GNU utils available in PATH:
```
    PATH="$(brew --prefix)/opt/coreutils/libexec/gnubin:$PATH"
    PATH="$(brew --prefix)/opt/grep/libexec/gnubin:$PATH"
    PATH="$(brew --prefix)/opt/gnu-sed/libexec/gnubin:$PATH"
```


Create a symlink from `learningsystems/alc_sat/spell` to the directory `spell` of ALC-SAT:
```
    ls -s PATH_TO_ALC_SAT/spell learningsystems/alc_sat
```

Install the requirements for ALC-SAT
```
    pip install -r PATH_TO_ALC_SAT/requirements.txt
```

Run the script to reproduce the experiments:
```
./reproduce.sh
```

Enjoy!
