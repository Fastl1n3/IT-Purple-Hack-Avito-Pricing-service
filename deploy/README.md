# Deploy services



## Get started

### Create .env file
Use `origin.env` file as example to fill .env file. Use this keys and pass your values.

### Grand execution privileges next scripts:
```shell
chmod +x ./postgresql-config/* ./initial.sh ./export-envs.sh
```

### Run command:
```shell
docker-compose up && \
./initiasl.sh
```