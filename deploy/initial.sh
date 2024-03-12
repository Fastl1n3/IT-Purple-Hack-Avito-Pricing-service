#!/bin/bash

./export-envs.sh
./postgresql-config/create-schemas.sh
./postgresql-config/configure-dbs.sh
./postgresql-config/configure-replication.sh
