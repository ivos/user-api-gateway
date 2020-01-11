# User API Gateway

Homework project demonstrating API gateway.


## Prerequisites

- Java 8+


## Tech stack

- Java 8
- Spring Boot
- Spring Webflux + annotated controllers


## ToDo

- generalize exception processing
- return standard error response body on not found
- handle io.netty.handler.timeout.TimeoutException as 504
- timeout tests?
- load test?


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
