# Rate Limiter Service

A configurable rate-limiting service built with Java and Spring Boot. This service protects your APIs from abuse by limiting the number of requests a user or IP can make within a specified time window.

## Features

- **Rate Limiting Strategies**: Supports Fixed Window and Sliding Window algorithms (configurable).
- **In-Memory Storage**: Uses efficient in-memory storage (HashMap) for tracking request counts.
- **Configurable Limits**: Set limits per user or based on IP address.
- **Scalable Design**: Designed to be easily integrated as middleware.

## Prerequisites

- Java 17 or higher
- Maven

## How to Run

1.  Clone the repository:
    ```bash
    git clone https://github.com/akhilsurnedi5479/rate-limiter-service.git
    cd rate-limiter-service
    ```

2.  Build the project:
    ```bash
    mvn clean install
    ```

3.  Run the application:
    ```bash
    mvn spring-boot:run
    ```

The application will start on `http://localhost:8080` (or your configured port).

## API Usage

The rate limiter intercepts requests to the configured endpoints.

### Example Request

```http
GET /api/resource
X-User-ID: user123
```

### Configuration

You can configure the rate limits in `src/main/resources/application.properties`:

```properties
ratelimiter.limit=100
ratelimiter.window-seconds=60
ratelimiter.strategy=fixed-window # or sliding-window
```
