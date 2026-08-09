# Inventory Management System

A Spring Boot application used as a continuous DevOps learning project.

## Current features

- Product management REST API
- In-memory product storage
- Validation
- Unit tests
- Maven build
- JaCoCo coverage
- Jenkins pipeline

## Build locally

```bash
mvn clean test
mvn clean package
```

## Run

```bash
java -jar target/inventory-management-system-1.0.0.jar
```

The application starts on port 8080.

## API

- `GET /api/products`
- `GET /api/products/{id}`
- `POST /api/products`
- `PUT /api/products/{id}`
- `DELETE /api/products/{id}`

Example POST body:

```json
{
  "name": "Laptop",
  "category": "Electronics",
  "quantity": 10,
  "price": 75000.00
}
```

This project will be extended through the DevOps learning path:
Git -> Maven -> Jenkins -> SonarQube -> Nexus -> Docker -> Kubernetes -> AWS.
