# Simple DB application

Simple application with data base access, created using Java 8.

## Pre-requisites

- A running PostgreSQL server.
- Java 8.

## Instructions

1. Clone the repo.
2. `mvn install` the project.
3. Grab the jar with dependencies file.
4. Run `java -jar simple-db-app-1.0-SNAPSHOT-jar-with-dependencies.jar -help` for help on available commands.
5. You must provide the following environment variables:
  - `db-connection-url`: the jdbc host url for a running PostgresQL server e.g. `jdbc:postgresql://localhost:5432/postgres`.
  - `user`: the postgres server username e.g. `postgres`.
  - `password`: the postgres server password e.g. `root`.
6. Run the `create-schema` command using `java -Ddb-connection-url=jdbc:postgresql://localhost:5432/postgres -Duser=postgres -Dpassword=root -jar simple-db-app-1.0-SNAPSHOT-jar-with-dependencies.jar create-schema`. This sets up the stored procedures and servers table in public schema in database specified in db-connection-url.
7. Run `add-server` passing in the id, name and description with -i -n and -d options e.g. `java -Ddb-connection-url=jdbc:postgresql://localhost:5432/postgres -Duser=postgres -Dpassword=root -jar simple-db-app-1.0-SNAPSHOT-jar-with-dependencies.jar add-server -i "1" -n "servername" -d "server description"`.
8. Run `edit-server` passing the id of the server to edit, altered name and altered description e.g. `java -Ddb-connection-url=jdbc:postgresql://localhost:5432/postgres -Duser=postgres -Dpassword=root -jar simple-db-app-1.0-SNAPSHOT-jar-with-dependencies.jar edit-server -i "1" -n "newName" -d "New description"`.
9. Run `list-servers` to output the list of servers e.g. `java -Ddb-connection-url=jdbc:postgresql://localhost:5432/postgres -Duser=postgres -Dpassword=root -jar simple-db-app-1.0-SNAPSHOT-jar-with-dependencies.jar list-servers`.
10. Run `delete-server` to delete a server e.g. `java -Ddb-connection-url=jdbc:postgresql://localhost:5432/postgres -Duser=postgres -Dpassword=root -jar simple-db-app-1.0-SNAPSHOT-jar-with-dependencies.jar delete-server -i "1"`.
11. Run `count-servers` to output the total number of servers e.g. `java -Ddb-connection-url=jdbc:postgresql://localhost:5432/postgres -Duser=postgres -Dpassword=root -jar simple-db-app-1.0-SNAPSHOT-jar-with-dependencies.jar count-servers`.

## Known Issues

- In add-server it is currently necessary to pass the id of the server (e.g. `-i 123`), and there is currently no auto-increment. But the id has a unique constraint.
- There is currently no test coverage.
