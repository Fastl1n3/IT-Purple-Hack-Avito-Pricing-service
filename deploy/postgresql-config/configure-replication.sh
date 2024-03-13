#!/bin/bash

# Define the master database and the replicas
master_db="postgresql_master"
replicas=("postgresql_replica_1" "postgresql_replica_2")

# Configure the master database
echo "Configuring $master_db as master..."
docker exec -i "$master_db" psql -U "$POSTGRESQL_USER" -d "$POSTGRESQL_DB" -c "DROP PUBLICATION IF EXISTS my_publication;"
docker exec -i "$master_db" psql -U "$POSTGRESQL_USER" -d "$POSTGRESQL_DB" -c "CREATE PUBLICATION my_publication FOR ALL TABLES;"

# Configure each replica to follow the master
for replica in "${replicas[@]}"; do
    echo "Configuring $replica to replicate from $master_db..."

    # Construct the connection string
    conninfo="host=$master_db port=5432 user=$POSTGRESQL_USER password=$POSTGRESQL_PASSWORD dbname=$POSTGRESQL_DB}"

    # Configure the subscription on the replica
    docker exec -i "$replica" psql -U "$POSTGRESQL_USER" -d "$POSTGRESQL_DB" -c "DROP SUBSCRIPTION IF EXISTS ${replica}_subscription;"
    docker exec -i "$replica" psql -U "$POSTGRESQL_USER" -d "$POSTGRESQL_DB" -c "CREATE SUBSCRIPTION ${replica}_subscription CONNECTION 'dbname=$POSTGRESQL_DB host=$master_db user=$POSTGRESQL_USER password=$POSTGRESQL_PASSWORD' PUBLICATION my_publication;"

    echo "$replica configured to replicate from $master_db"
done

echo "Master and replicas configured successfully for logical replication"