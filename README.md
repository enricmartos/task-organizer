# Task organizer

## Description

This application allows you to track and manage software development projects.

## Personal goals

- To keep getting familiar with Spring Boot framework and learn how to perform basic CRUD operations
- To acquire experience with ORM (Object-Relational Mapping) and JPA (Java Persistence API). Specifically, One-to-Many
and Many-to-One relationships
- To start using Thymeleaf as a front-end template engine and dive into layouts and decorators.
- To implement custom model validations
- To start using Spring Security in order to perform common user management operations such as Sign up, Log in and Role definition
- To implement unit tests by using JUnit

## Core technologies

*Front-end*
- Thymeleaf
- Bootswatch (as a layer on top of Bootstrap)

*Back-end*
- Spring Boot
- Hibernate (as a JPA framework)

*Database*
- MySQL

*Server*
- Apache Tomcat (default embedded server provided by Spring Boot)

*Dependency management tool*
- Gradle

*IDE*
- IntelliJ IDEA

## Build setup

- Clone this repo to your local machine. If you use IntelliJ as IDE, open this project there.

- MySQL and MySQL Workbench must be already installed in your machine. Otherwise, you will have to install them. Please notice that the default parameters (port, username and password) to enable the MySQL connection are defined on application.properties file. So, feel free to edit them in order to match one of your MySQL connections.

```
# Create the db

CREATE SCHEMA `app_db` ;
```

- Run the project as Spring Boot App

- Open your browser and test the application on *localhost:8080*

## References

I have accomplished the aforementioned goals thanks to the following course:

- [Springboot+Spring-Security |Full-Stack web-app](https://www.youtube.com/watch?v=2eQ-ZYn7TBo&list=PLF0fAweo0KojNqSenBH_2wkAIo_WbqFaB&index=1)
