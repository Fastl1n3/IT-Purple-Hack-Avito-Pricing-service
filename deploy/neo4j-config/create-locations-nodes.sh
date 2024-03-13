#!/bin/bash

schema_file="./neo4j-config/locations-schema.cql"

docker cp "$schema_file" "neo4j:/tmp/schema.cql"

docker exec -i neo4j cypher-shell -u "$NEO4J_USER" -p "$NEO4J_PASSWORD" < /tmp/schema.cql