# Deploy services



## Get started

### Create and fill .env file
Use `origin.env` file as example to fill .env file. Use this keys and pass your values.


### Export envs:

```shell
export POSTGRESQL_DB=<db_name> && \
export POSTGRESQL_USER=<username> && \
export POSTGRESQL_PASSWORD=<password>
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