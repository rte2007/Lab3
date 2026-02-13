#!/usr/bin/env bash
set -e
java -Dfile.encoding=UTF-8 -cp src story.StorySimulation "$@"
