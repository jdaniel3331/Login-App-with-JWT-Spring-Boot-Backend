# Login with JWT 🔐

This project is a simple Spring Boot application that demonstrates how to implement a secure login system using JSON Web Tokens (JWT).

It can be used as a service/microservice blueprint for authentication and authorization.

## Features ✨

*   **User Registration:** New users can register on the application.
*   **User Login:** Registered users can log in with their email and password to receive a JWT.
*   **Token-Based Authentication:** The application uses JWTs to secure its endpoints.
*   **Token Refresh:** The application provides an endpoint to refresh expired JWTs (uses token rotation).

## Endpoints 🚀

The following are the main endpoints provided by the application:

*   `POST /auth/register`: Registers a new user.
*   `POST /auth/login`: Authenticates a user and returns a JWT.
*   `POST /auth/refresh`: Refreshes an expired JWT.
*   `GET /auth/test`: A test endpoint to verify token authentication.

## Technologies 🛠️

*   **Java:** The core programming language used in the project.
*   **Spring Boot:** The framework used to build the application.
*   **Spring Security:** Used to handle authentication and authorization.
*   **JSON Web Tokens (JWT):** Used for token-based authentication.
*   **PostgreSQL** used as database management system.

## How to run?

To run the application using Docker, you can use the provided `docker-compose.yml` file. This will start the PostgreSQL database required for the application.

1.  **Prerequisites:**
    *   Docker and Docker Compose installed on your machine.
    *   A `.env` file in the root of the project with the following variables:
        ```
        DB_USER=your_username
        DB_PASSWORD=your_password
        DB_NAME=your_database_name
        DB_PORT=your_port
        ```

2.  **Start the container:**
    Open a terminal in the root of the project and run the following command:
    ```bash
    docker-compose up -d
    ```
    This command will start the PostgreSQL container in detached mode.

3.  **Run the application:**
    You can then run the Spring Boot application from your IDE. The application will connect to the database running in the Docker container.
