#!/bin/bash

docker-compose up -d

sleep 5

./postgresql-config/create-schemas.sh
./postgresql-config/configure-dbs.sh
./postgresql-config/configure-replication.sh

./neo4j-config/create-schemas.sh
./postgresql-config/fill-matrices.sh