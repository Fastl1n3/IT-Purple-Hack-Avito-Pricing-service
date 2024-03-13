#!/bin/bash
databases=("postgresql_master" "postgresql_replica_1" "postgresql_replica_2")
schema_file="./postgresql-config/postgresql-schema.sql"

for db in "${databases[@]}"; do
    echo "Copying schema to $db..."
    docker cp "$schema_file" "$db:/tmp/schema.sql"

    echo "Loading schema in $db..."
    docker exec -i "$db" psql -U "$POSTGRESQL_USER" -d "$POSTGRESQL_DB" -f /tmp/schema.sql

    echo "Removing schema file from $db..."
    docker exec -i "$db" rm /tmp/schema.sql

    echo "Schema loaded successfully in $db"
done
