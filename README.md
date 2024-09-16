# Authentication Service

This project is a Spring Boot-based authentication service that supports user registration, login, and role-based access control.

## Project Overview

The `HomeController` handles user registration, login, and provides access to various endpoints based on user roles. This service uses JWT for authentication and authorization.

## Table of Contents

- [Project Overview](#project-overview)
- [Setup Instructions](#setup-instructions)
- [API Documentation](#api-documentation)
    - [Welcome Endpoint](#welcome-endpoint)
    - [Register Endpoint](#register-endpoint)
    - [Login Endpoint](#login-endpoint)
    - [Admin Endpoint](#admin-endpoint)
    - [Coach Endpoint](#coach-endpoint)
    - [Player Endpoint](#player-endpoint)
- [Testing](#testing)
- [Dependencies](#dependencies)

## Setup Instructions

### Prerequisites

1. **Java 17 or Higher**

   Ensure you have Java 17 or higher installed. Check your Java version with:

   ```bash
   java -version


## API Documentation

For detailed API documentation, including endpoints, request/response formats, and examples, please refer to the following link:

[API Documentation](https://documenter.getpostman.com/view/37740341/2sAXqp8iQv)

## Installation

### Prerequisites

Make sure you have Maven installed for managing project dependencies.

### Clone the Repository

Clone the repository using the following command:

    
    git clone https://github.com/mohan-277/Authentication-service-.git
    cd authentication-service

## Post-Installation Steps

After installing dependencies and building the project, follow these steps to get your Spring Boot application up and running:

### 1. Configuration

Ensure you have properly configured your application by setting up the `application.properties` or `application.yml` file in `src/main/resources/`. You need to configure:

- **JWT Secret Key**: A secret key used for signing JWT tokens.
- **Database Connection**: Database URL, username, and password.

Example `application.properties`:

    ```properties
    
    jwt.secret=your_jwt_secret_key
    spring.datasource.url=jdbc:mysql://localhost:3306/your_db
    spring.datasource.username=your_db_user
    spring.datasource.password=your_db_password

----
## Run the Application

To start the application, use the following command:

    mvn spring-boot:run




## Access the Application

    Once the application is running, you can access it at the following URL:
    
    [http://localhost:8080](http://localhost:9090)
 ## Testing

### Running Tests

To ensure the application is working as expected, you can run the unit and integration tests using Maven. To execute the tests, use the following command:
 
    mvn test