# .bash_profile
CS1302=/usr/local/mepcott/cs1302.profile; [[ -f $CS1302 ]] && . $CS1302
export EDITOR="emacs -nw"

# Get the aliases and functions
if [ -f ~/.bashrc ]; then
	. ~/.bashrc
fi

# User specific environment and startup programs

PATH=$PATH:$HOME/.local/bin:$HOME/bin

export PATH
