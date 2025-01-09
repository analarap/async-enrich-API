# Async API
The goal of this challenge is to create an application that asynchronously fetches posts from an external API, enriches them with comment data, and mainstains a log of processing updates.

The client should be able to search and view the history of states through the API in real time.

### Technical Details
- The project must run on port 8080;
- The database must be embedded h2 database;
- The Spring configuration spring.jpa.hibernate.ddl-auto should be set to create-drop;
- If there is a message broker, it should be embedded as well.

### External API
https://jsonplaceholder.typicode.com

### Technologies used
- Java;
- Spring Boot;
- Spring Data JPA;
- Spring Web;
- ActiveMq;
- H2.

### Dependencies
- spring-boot-starter-data-jpa: Easy support for relational databases using JPA.
- spring-boot-starter-web: Facilitates web application development with Spring MVC.
- spring-cloud-starter-openfeign: Declarative HTTP integration between services.
- spring-boot-starter-amqp: Support for asynchronous messaging with AMQP.
- spring-boot-devtools: Tools for streamlined development.
- spring-boot-starter-activemq: Integration with the ActiveMQ messaging system.
- activemq-broker: Facilitate interaction between multiple clients and servers.
- jackson-databind: Convert Java objects to JSON and vice versa.
- spring-boot-starter-amqp: Support for asynchronous messaging with RabbitMQ.
- lombok: A tool that decreases the amount of repetitive code needed in Java classes.
- spring-boot-starter-test: Support for unit testing Spring Boot applications.
- spring-cloud-dependencies: Simplifies the use of Spring Cloud in cloud applications.
- spring-boot-maven-plugin: Maven tool for creating and running Spring Boot applications.

### Prerequisites
- Java 17 or higher;
- H2 database;
- Maven;
- Activemq;
- IDE of your preference (IntelliJ or Eclipse).

### Usage
- For the best experience, it is recommended that you use an HTTP client (preferably Postman or Insomnia).

### ActiveMQ
- ActiveMQ is an open source asynchronous messaging system for efficient communication between distributed system components.
- The activeMQ in the project handles the processing of posts and state updates, allowing the different workflow steps to communicate asynchronously via messages.

### Database
For the best user experience, the project contains a database embedded in the application, which is h2.

- To access the database console: http://localhost:8080/h2-console;
- JDBC URL: jdbc:h2:mem:mydb;
- Username: adminuser;
- Password: admin.
