# Driver Service (Spring Boot)

This is a ready-to-import Maven project for the Driver Service (part of the Ride-Hailing assignment).

## How to run locally (without Docker)
1. Create a MySQL database named `driverdb`.
2. Update `src/main/resources/application.yml` with your DB credentials.
3. Build & run:
   ```bash
   mvn clean package
   mvn spring-boot:run
   ```
4. The service will be available at `http://localhost:8081/v1/drivers`

## How to run with Docker Compose
1. Ensure Docker & Docker Compose are installed.
2. From project root:
   ```bash
   docker-compose up --build
   ```
3. Service endpoint: `http://localhost:8081/v1/drivers`

## Project structure
Standard Maven + Spring Boot layout.

