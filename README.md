# REST API - Spring Boot - MongoDB

## Description - Exercise statement
This project implements a REST API to manage fruit orders using Spring Boot.
Each order contains the client name, delivery date, and a list of fruit items with quantity in kilos.
The API persists data using MongoDB and exposes CRUD operations for orders.

## Technologies used
- Oracle OpenJDK 21.0.8
- Spring Boot 3.5.8
- Maven 3.9.11
- MongoDB
- Docker
- Postman
- IntelliJ IDEA Community Edition

## Requirements
- Oracle OpenJDK 21.0.8
- Spring Boot 3.5.8
- Maven 3.9.11
- Docker
- Java IDE

## Installation
1. Clone repository (https://github.com/AlbertMedina/4.2-Spring-Framework-Basics-Nivell3.git).
```git clone https://github.com/AlbertMedina/4.2-Spring-Framework-Basics-Nivell3.git```
2. Navigate to project folder.
```cd fruit-order-api```
3. Ensure Docker is installed and running

## Execution
1. Build and start the containers.
```docker-compose up --build```
This will start MongoDB and the Spring Boot API. The API will be available at http://localhost:8080.
2. Test endpoints with Postman or any HTTP client.
- Example endpoints:
  - POST / GET -> /orders
  - GET / PUT / DELETE -> /orders/{id}
- Example POST JSON:
```{
  "clientName": "Joan",
  "deliveryDate": "2025-12-18",
  "items": [
    { "fruitName": "Apple", "quantityInKilos": 5 },
    { "fruitName": "Banana", "quantityInKilos": 3 }
  ]
}```
