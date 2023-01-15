# Cobalt Winds Backend
## Using Spring Boot + MS SQL

### Introduction

This project contains the codebase and installation scripts for the Cobalt Winds Backend using Spring Boot 4.0 and MSSQL trough `Docker`.
This project serves a REST API that is used for the Cobalt Winds Website.

Cobalt Winds is an environment for amature League of Legends players can participate in a competition and win cash and skin prizes. Players can sign up and join a team or create a team. Team captains can invite, accept join requests or join competitions.

## Creating the infrastructure
### Prerequisite
The following programs and tools must be installed to run this project

* Java 8 or higher
* Maven 3 or higher
* Docker (to run `docker-compose.yaml` in order to setup MSSQL)

### Step 0: Clone the project or download zip
```bash
git clone https://github.com/HarisSpahija/CobaltWindsBackend.git
cd CobaltWindsBackend
```

### Step 1: Build the project
In the root directory of your project, run the following command to build the project:

```bash
mvn clean package
```

### Step 2: Create docker container for MSSQL
In the root directory of your project run `docker-compose up`.
This will construct the MSSQL server using the `mcr.microsoft.com/mssql/server` image
```bash
docker-compose up
```
To stop the MSSQL server run `docker-compose down` or `docker-compose down -v` to also remove the database volume.

## Running the project
Inside the root directory of your project
```bash
./mvnw package
java -jar target/*.jar
```


