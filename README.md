# Watch-Friends
The Watch-Friends application is a cloud-native microservices system that allows users to discover, track, and share movies and TV shows with their friends. The project leverages a canonical data model and bounded contexts to organize functionality into loosely coupled microservices.


## Canonical Data Model & Bounded Contexts
For Phase 1, I designed a canonical data model divided into separate bounded contexts. Each microservice contains its own:
- Entities (persisted in a relational database)
- Repository layer
- Service layer
- Controller layer with CRUD REST endpoints

### Microservices to be Implemented
- **Authentication Sevice:** Handles account registration, login, and JWT token generation. Entities: `UserCredentials`, `Role`. Integrates with `User Service` for profile data.
- **User Service:** Stores user details and extended profile info. Entities: `User`, `Profile`.
- **Catalog Service:** Manages movies and TV shows. Entities: `Movie`, `Show`.
- **Friend Service:** Handles user connectinos and social interactions. Entities: `Friendship`.
- **Shared Infrastructure Services**
    - **Config Server:** Centralized configuration with two profiles (dev, prod).
    - **Eureka Server:** Service registry and discovery.
    - **Gateway Server:** Routes requests and enforces token-based authorization.
    - **Docker:** Dockerized microservices with automated deployment.

*Each service is independent but aligned with the canonical data model for interoperability.*

![Canonical Data Model](Canonical%20Data%20Model.png)

#### How to read image:
- **Authentication Service** manages `UserCredentials` and `Role`.
- Each `UserCredentials` maps **1-to-1** to a `User` in the **User Service**.
- Users have `Profiles` with preferences or bio data.
- Users connect via **Frienship** (Friend Service).
- Users maintain **many-to-many** watchlists with `Movies` and `Shows` in the **Catalog Service**.
- `Role` entities allow multiple roles per user (e.g., ADMIN, USER).

## Running the Application
Below is a list of prerequisite software that must be installed to run the application.
- Docker
- docker compose
- Java 21
- Maven

### Steps
1. Clone the repository.
```
git clone https://github.com/ben-feuerborn/Watch-Friends.git
cd Watch-Friends
```

2. Build and run all services.
```
mvn clean package -DskipTests
docker compose -f docker/docker-compose.yml build --no-cache
docker compose -f docker/docker-compose.yml up -d
```

3. Verify service are runnnig.
    - Eureka Dashboard: <http://localhost:8070>
    - Keycloak Dashboard: <http://localhost:8080>

### Profiles
- **Dev Profile (`dev`):** Lightweight configuration for local development
- **Prod Profile (`prod`):** Production-ready setup with stricter configs.

You can switch profiles by editing `application.yml` or using:
```
-Dspring.profiles.active=dev
-Dspring.profiles.active=prod
```

### Testing the System
1. Import the provided Postman Workspace.
2. Test CRUD endpoints for:
    - `/users` (User Service)
    - `/catalog` (Catalog Srvice)
    - `/friends` (Friend Service)
3. Confirm service are correclty routed via the API Gateay.