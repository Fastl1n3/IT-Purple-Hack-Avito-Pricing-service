# Deploy services



## Get started

### Create and fill .env file
Use `origin.env` file as example to fill .env file. Use this keys and pass your values.


### Export envs:

```shell
export POSTGRESQL_DB=<pg_db_name> && \
export POSTGRESQL_USER=<pg_username> && \
export POSTGRESQL_PASSWORD=<pg_password> && \
export NEO4J_USER=<neo4j_username> && \
export NEO4J_PASSWORD=<neo4j_password> 
```

where values in `<>` are values from .env file

### Grand execution privileges next scripts:
```shell
chmod +x ./postgresql-config/* ./initial.sh ./export-envs.sh
```

### Deploy:
```shell
./initial.sh
```