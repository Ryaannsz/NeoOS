# 🖥️ NeoOS

This project simulates the core logic of an Operating System (OS) using a backend API developed in **Spring Boot**. It models how processes are created, managed, and transitioned through different states such as `NEW`, `READY`, `RUNNING`, `WAITING` and `TERMINATED`.

## 📚 Overview

- Each **App** registered in the system can have multiple **Processes**.
- Processes are created when an App is opened and can have individual **instance names**.
- A **scheduler** periodically updates processes from `NEW` to `READY`.
- The system supports **user actions** like opening and closing apps, which impact the process state.
- Multiple instances of the same app can run concurrently, like opening different tabs.

---

## 🚀 Features

- 📦 Register and manage apps
- 🔄 Create and control processes with lifecycle states
- ⚙️ Scheduler updates `NEW` → `READY`
- 🧠 User-defined actions via strategy pattern
- 🔍 Query processes by app and instance
- 🌐 REST API powered by Spring Boot

---

## ⚙️ Tech Stack

- Java 17+
- Spring Boot
- Kafka
- Spring Data JPA
- MySQL
- Lombok
- Swagger (SpringDoc)

---

## 📁 Project Structure

```
src/
├── components/        # User actions (e.g., OPEN_APP, CLOSE_APP)
├── controller/        # REST API controllers
├── dto/               # Data Transfer Objects
├── enums/             # Enum definitions (e.g., process states)
├── exception/         # Custom exception handling
├── mapper/            # Entity-DTO mappers
├── models/            # Entity definitions
├── repository/        # JPA repositories
├── scheduler/         # Background scheduler
├── service/           # Business logic
```

## 📦 Setup & Run
### ✅ Requirements
- Java 17 or higher

- MySQL 8+

- Maven

## 🔧 Configuration
Create a database named neoos in MySQL:

```
CREATE DATABASE neoos;
```

Configure your credentials in application.properties:


```
spring.datasource.url=jdbc:mysql://localhost:3306/neoos
spring.datasource.username=root
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update
```

## ▶️ Running the Project

```
./mvnw spring-boot:run
```

Once started, the backend will be available at:

```
http://localhost:8080
```
## 📖 API Documentation
Interactive Swagger docs available at:

```
http://localhost:8080/docs
```
## 📌 Process Lifecycle Example
Start App → creates a process in NEW state.

Scheduler picks it up and updates it to READY.

User opens app → process state becomes RUNNING.

User saves app → process state becomes WAITING.

User closes app → process moves to TERMINATED.

## ✍️ Author
Ryan Carvalho Bernardo
