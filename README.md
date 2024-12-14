# APUORE-RE - Java Spring Boot backend

## Prerequisites

- Maven 3.9+
- Java 21
- Docker 20.10+

## Getting started

1. Clone the repository.
2. Copy the `.env.example` file as `.env` and fill in the required environment variables.
3. Run the project.

The `docker compose up` command will start 3 containers:

- PostgreSQL database (needed also when running the Spring Boot locally)
- Python Embedding Service
- Spring Boot backend

```bash
docker compose up
```

Above approach is recommended for example when working mostly with the Next.js frontend.
If you are working mostly with the Spring Boot backend, you can stop the Spring Boot container and run the applicatio locally instead:

```bash
mvn clean install
mvn spring-boot:run
```
