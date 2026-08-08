# RNG Service

Secure random number generator for the demoRGS RGS platform.
Game engines call this service to produce fair, unpredictable outcomes.

## What it does

- Generates cryptographically secure random **integers** (within a range) and **doubles** ([0, 1)).
- **stateless** - no database, no shared state. Scales Horizontally.
- Self-checks output fairness with a **chi-square uniformity test**, exposed as a metric(regulatory requirement for gambling).

## Tech stack

- Java 25, Spring Boot 4.1
- Maven
- Rest(gRPS planned - future migration)
- springfoc-openapi (Swagger UI)
- Micrometer + Prometeus (metrics)
- Apache Common Math  (chi-square)

## Consumers

| Caller | Uses |
|--------|------|
| Scratch Card Engine | `/integers`, `/doubles` to pick symbols / outcomes |

## API

Base path: `/api/v1/rng` · Port: `7772`

| Method | Endpoint | Body | Returns |
|--------|----------|------|---------|
| POST | `/integers` | `{ "min": 0, "max": 9, "count": 10 }` | list of integers in `[min, max]` |
| POST | `/doubles`  | `{ "count": 5 }` | list of doubles in `[0, 1)` |

Invalid input (`count <= 0`, `min > max`) → `400 Bad Request` with an error body.

**Interactive docs (Swagger UI):** http://localhost:7772/swagger-ui/index.html
**OpenAPI spec:** http://localhost:7772/v3/api-docs

## Running locally 
```bash
./mvnw spring-boot:run 
```

## Monitoring

- Health: http://localhost:7772/actuator/health
- Metrics (Prometheus): http://localhost:7772/actuator/prometheus
- Chi-square failures counter: `rng_chisquare_failures`

## Configuration

All settings live in `src/main/resources/application.yaml`.

| Property | Default | Description |
|----------|---------|-------------|
| `server.port` | `7772` | Port for REST endpoints and Actuator |

## Notes

- No secrets or credentials — nothing sensitive to configure.
- gRPC is planned for internal calls; REST is the current interface.