Steps:

python3.10 -m venv sml

source sml/bin/activate

./setup.sh

Potentially:
PATH="$(brew --prefix)/opt/coreutils/libexec/gnubin:$PATH"
PATH="$(brew --prefix)/opt/grep/libexec/gnubin:$PATH"
PATH="$(brew --prefix)/opt/gnu-sed/libexec/gnubin:$PATH"

Symlink ALC-sat dir

pip install -r alc-sat/requirements.txt

./reproduce.sh

Enjoy!
