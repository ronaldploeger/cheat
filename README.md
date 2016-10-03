# cheat

To build fat jar call:
sbt assembly
OR
activator assembly

To invoke cheat call: 
./cheat.sh [optional path] [optional max-level]

Examples:
./cheat.sh 1
./cheat.sh vi
./cheat.sh vi 1
./cheat.sh vi.edit

Make it more convenient - add an alias to your .bashrc: 
alias cheat='[path-to-cheat]/cheat.sh'