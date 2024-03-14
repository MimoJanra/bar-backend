# MyBar Backend Application

## Overview

This backend application is designed for managing a bar. It supports operations such as managing ingredients, cocktails, orders, and provides functionalities for both the administrative and client sides. It's built with Spring Boot and Kotlin, leveraging a Postgres database for persistence.

## System Requirements

- JDK 17
- Kotlin 1.9.22
- PostgreSQL

## Setting Up

### Install JDK 17

Ensure that Java Development Kit (JDK) 17 is installed on your system. You can download it from [AdoptOpenJDK](https://adoptopenjdk.net/) or [Oracle's website](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).

### Database Setup

This project uses PostgreSQL as its database. Make sure you have PostgreSQL installed and running. Create a database named `mybar` (or your preferred name) and update `src/main/resources/application.properties` with your database connection details:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/mybar
spring.datasource.username=<your-db-username>
spring.datasource.password=<your-db-password>
spring.jpa.hibernate.ddl-auto=update
```

## Running the Application

To run the application, use the following command in the terminal from the root directory of the project:
```
./gradlew bootRun
```
The application will start and be accessible on http://localhost:8080.

## Key Features & Fields
### Managing Ingredients

    Add Ingredient: Adds a new ingredient to the stock.
        Fields: name, quantity, unit, barId
    Update Ingredient: Updates existing ingredient details.
        Fields: id, name, quantity, unit, barId
    Delete Ingredient: Removes an ingredient from the stock.
        Fields: id

### Managing Cocktails

    Create Cocktail: Allows creation of a new cocktail recipe.
        Fields: name, ingredients (list of ingredient IDs and their quantities), price, barId
    Update Cocktail: Updates details of an existing cocktail.
        Fields: id, name, ingredients, price, barId
    List Cocktails: Lists all cocktails available in a bar.
        Fields: barId

### Managing Orders

    Place Order: Places a new order for cocktails.
        Fields: cocktails (list of cocktail IDs), customerName, barId
    Update Order Status: Updates the status of an existing order (e.g., from 'pending' to 'completed').
        Fields: orderId, newStatus

### User Authentication & Authorization

    User Login: Authenticates a user and issues a JWT token.
        Fields: email, password
    User Registration: Registers a new user.
        Fields: email, password, roles, barsManaged (for admins)