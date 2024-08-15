#!/bin/bash
# URL=$1
# [[ -x $BROWSER ]] && exec "$BROWSER" "$URL"
# path=$(which open -a Google\ Chrome || which open -a Safari) && exec "$path" "$URL"
# echo "Can't find browser"

open -a Safari index.html || open -a Google\ Chrome