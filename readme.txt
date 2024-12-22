### README: Library System

---

## **Overview**

The **Library System** is a Spring Boot-based application designed to manage library operations efficiently. It supports functionalities for managing authors, books, and customers, allowing authors to add books and customers to borrow one book at a time. The system adheres to a layered architecture to ensure scalability, maintainability, and separation of concerns.

---

## **Key Features**

1. **User Management**:
   - The system has four primary entities: `Author`, `Book`, `Customer`, and `User`.
   - The `Author` and `Customer` classes inherit common attributes from the `User` base class.

2. **Book Management**:
   - Authors can add books to the system.
   - Customers can borrow one book at a time.

3. **Robust Architecture**:
   - Separation of concerns with well-defined models, service interfaces, implementations, and controllers.

4. **Exception Handling**:
   - Custom exceptions extend `RuntimeException` for clear error handling.
   - `@ControllerAdvice` is used to manage and provide meaningful error responses to the client.

5. **DTO Usage**:
   - Data Transfer Objects (DTOs) are implemented for all models to ensure:
     - Encapsulation of sensitive data.
     - Clear separation between the API layer and the database models.
     - Simplified request/response payloads.

6. **Database Interaction**:
   - Utilizes Java Persistence API (JPA) for seamless database interactions.
   - Repositories are created using JPA for CRUD operations.

7. **Docker Integration**:
   - The application is containerized using Docker for ease of deployment.
   - The `Dockerfile` is included in the project for building the Docker image.

---

## **Project Architecture**

1. **Models**:
   - Represent the database entities: `Author`, `Book`, `Customer`, `User`.

2. **Services**:
   - Interfaces and their implementations encapsulate business logic.
   - Example: `BookService` interface and `BookServiceImplementation` class.

3. **Repositories**:
   - JPA-based repositories provide database access and operations.

4. **Controllers**:
   - Define RESTful endpoints for managing the entities and interacting with the system.

5. **DTOs**:
   - Abstract and simplify model representations in API requests and responses.

6. **Custom Exceptions**:
   - Custom exception classes for handling specific errors, enhancing the clarity of error messages.

7. **Controller Advice**:
   - Global exception handling for providing consistent and meaningful error responses.

---

## **How to Run the Project**

### Prerequisites:
- **Java**: Ensure Java 17+ is installed.
- **Docker**: Ensure Docker is installed and running.
- **Database**: The project is configured to use PostgreSQL.

### Steps:

1. **Build the Application**:
   - Use Maven to package the application:
     ```bash
     mvn clean package
     ```

2. **Run Locally**:
   - Configure the database connection in `application.properties`.
   - Run the application:
     ```bash
     java -jar target/<your-jar-file>.jar
     ```

3. **Run with Docker**:
   - Build the Docker image:
     ```bash
     docker build -t librarysystem:latest .
     ```
   - Run the Docker container:
     ```bash
     docker run -p 8080:8080 librarysystem:latest
     ```

---

## **Endpoints**

### **Author**
- **Create**: `POST /authors`
  Add a new author.
- **Read All**: `GET /authors`
  List all authors.
- **Read One**: `GET /authors/{id}`
  Get details of a specific author.
- **Update**: `PUT /authors/{id}`
  Update author details.
- **Delete**: `DELETE /authors/{id}`
  Remove an author.
- **addBook**: `POST /{author_id}/addBook`
  add new book by this author to the library.


### **Book**
- **Create**: `POST /books`
  Add a new book.
- **Read All**: `GET /books`
  List all books.
- **Read One**: `GET /books/{id}`
  Get details of a specific book.
- **Update**: `PUT /books/{id}`
  Update book details.
- **Delete**: `DELETE /books/{id}`
  Remove a book.

### **Customer**
- **Create**: `POST /customers`
  Add a new customer.
- **Read All**: `GET /customers`
  List all customers.
- **Read One**: `GET /customers/{id}`
  Get details of a specific customer.
- **Update**: `PUT /customers/{id}`
  Update customer details.
- **Delete**: `DELETE /customers/{id}`
  Remove a customer.
- **assignNewBook**: `POST customers/assignbook/{customer_id}/{book_id}`
  borrow new book.


---

## **Technologies Used**

- **Spring Boot**: Backend framework.
- **Java JPA**: Database interaction.
- **PostgreSQL**: Relational database.
- **Docker**: Containerization.
- **Maven**: Build automation tool.

---

## **Benefits of DTOs**

- **Encapsulation**: Prevents direct exposure of entity fields, enhancing security.
- **Flexibility**: Allows transformation of data before sending or receiving, adapting to API requirements.
- **Performance**: Reduces payload size by including only necessary fields.

---

## **Error Handling**

- **Custom Exceptions**: Specialized exceptions provide clarity on errors (e.g., `BookNotFoundException`, `CustomerNotFoundException`).
- **Global Exception Handling**:
  - Managed using `@ControllerAdvice`.
  - Consistent and user-friendly error responses.

---

## **Docker Integration**

- Docker simplifies deployment by packaging the application and its dependencies into a container.
- Use the provided `Dockerfile` to build and run the application in any environment with Docker support.

---

This system is a robust, scalable library management solution with a modern architecture and seamless deployment using Docker. It is designed to be extendable for future features and integrations.