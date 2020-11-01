# User Profile Management
An MVC application for user profile management.

## Features
* User sign-in (with *HttpSession*)
* User registration
* List all users
* Update user profile
* Delete user profile
* Search for users

## Technology stack
* Java Servlet
* JavaServer Pages (JSP)
* JavaServer Pages Standard Tag Library (JSTL)
* Java Database Connectivity (JDBC)
* MySQL
* Bootstrap

## Prerequisites
Before starting the services via `docker-compose`, you need to have a Web Application Runner (WAR) export of the project named `ProjectManagement.war` ready inside the `target/` folder at the root of the project.

This `target/ProjectManagement.war` file will be mounted onto the Tomcat service by `docker-compose up`.

The service then deploys the *WAR* file, which will be accessible via `localhost:8080/ProjectManagement`.

You can export the *WAR* file by using Eclipse or any other IDE.

Also, make sure you've installed `docker` and  `docker-compose` on your machine.

## Deployment

* Clone the repository to your machine and change the working directory:
```sh
git clone git@github.com:roshanadh/ProfileManagement && cd ProfileManagement
```

* Start the services:
```sh
docker-compose up
```

* Open up the project on your browser:
```sh
localhost:8080/ProfileManagement
```
