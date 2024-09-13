# Advertisement

## Table of Contents ##
1. [Input Data](#Input-Data)
2. [Running the server as Docker Container](#Running-the-server-as-Docker-Container)
3. [Documentation](#Documentation)

## Input Data ##
There are two input data samples for click and impression events in the ```resources/data/``` path.
ImpressionClickDataLoader component will load the mentioned inputs into the database at first time of running application.

## Running the server as Docker Container ##
To Build image use the below command:

```docker-compose -f ./.docker/docker-compose.yaml up -d```

To enable BuildKit with docker-compose use the below command:

```COMPOSE_DOCKER_CLI_BUILD=1 DOCKER_BUILDKIT=1 docker-compose -f ./.docker/docker-compose.yaml up -d```

## Documentation ##
Swagger is enabled in the project. Use the below url to see the api documentations:

```http://localhost:8070/api/swagger-ui.html```
