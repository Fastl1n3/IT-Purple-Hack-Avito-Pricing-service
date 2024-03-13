#!/bin/bash
databases=("postgresql_master" "postgresql_replica_1" "postgresql_replica_2")
d_matrix="./postgresql-config/d_matrix_1.sql"
b_matrix="./postgresql-config/b_matrix_1.sql"

for db in "${databases[@]}"; do
    echo "Load data to $db..."
    docker cp "$d_matrix" "$db:/tmp/schema.sql"

    echo "Loading schema in $db..."
    docker exec -i "$db" psql -U "$POSTGRESQL_USER" -d "$POSTGRESQL_DB" -f /tmp/schema.sql

    echo "Load data to $db..."
    docker cp "$b_matrix" "$db:/tmp/schema.sql"

    echo "Loading schema in $db..."
    docker exec -i "$db" psql -U "$POSTGRESQL_USER" -d "$POSTGRESQL_DB" -f /tmp/schema.sql
done

