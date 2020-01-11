# User API Gateway

Homework project demonstrating API gateway.

Gets user with their posts.
Gets user and their posts separately from the target API
and merges the posts into the user data.


## Prerequisites

- Java 8+


## Tech stack

- Java 8
- Spring Boot
- Spring Webflux + annotated controllers


## Design

- Layers:
    - Controllers: REST interface
    - Services: business logic (the library code to be called)
    - Clients: wrap external API calls
- Organize code by business entity (packages, layer components)


## ToDo

- generalize exception processing
- return standard error response body on not found
- handle io.netty.handler.timeout.TimeoutException as 504
- timeout tests?


## Commands

### Clean build

To build the application uber-JAR:

    mvnw clean install

The uber-JAR is then created at `target/user-api-gateway-0.0.1-SNAPSHOT.jar`.

### Run the application

To run the application from sources:

    mvnw -Prun

Then press `CTRL-C` to stop the application.

While the app is running, you can execute individual Integration tests.

### Run System / Integration tests

To execute all Integration tests:

    mvnw -Pit

### Run load tests

Start the application, then execute:

    mvnw gatling:test


## Load tests results

Single host, dev ntb.

- 500 concurrent users, 400 rps:
    - [results index.html](load-tests-results/loadtest-500users/index.html)
