#!/bin/bash

BASE_PATH="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

java -jar "$BASE_PATH/target/scala-2.11/cheat-assembly-1.0.jar" $@