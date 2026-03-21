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
