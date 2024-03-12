#!/bin/bash

if [ -f .env ]; then
    while IFS= read -r line; do
        if [[ ! "$line" =~ ^\s*# && ! -z "$line" ]]; then
          export "${line?}"
        fi

    done < .env
    echo "The environment variables have been successfully exported"
else
    echo "File .env not found"
    exit 1
fi