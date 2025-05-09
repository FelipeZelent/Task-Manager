# 📝 Task Manager API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

A simple **Task Manager API** built using **Java**, **Spring Boot**, and **H2 in-memory database**.

This project was developed as a study/practice exercise for building CRUD operations using Spring Boot, following REST principles.

---

## 📚 Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Database](#database)

---

## ⚙️ Installation

1. Clone the repository:

```bash
https://github.com/FelipeZelent/Task-Manager.git
```
2. Build the project using Maven:
```bash
./mvnm clean install
 ```
---

## 🔧 Configuration

No special configuration is needed. The project uses H2 (in-memory) by default.
All settings can be found in:
```bash
src/main/resources/application.properties
```

---

### 🚀 Usage

1. Run the application
```bash
./mvnw spring-boot:run
```
2. Access the API at:
```bash
http://localhost:8080/api/tasks
```

### 📬 API Endpoints

GET TASKS
```bash
GET /api/tasks
```
GET TASK BY ID
```bash
GET /api/tasks/{id}
```
POST TASK
```bash
POST /api/tasks
```
Cria uma nova tarefa

Request Body:
```bash
{
  "title": "Estudar Spring Boot",
  "description": "Finalizar módulo de API",
  "completed": false
}
```
PUT TASK
```bash
PUT /api/tasks/{id}
```
Atualiza os dados de uma tarefa existente.

DELETE TASK
```bash
DELETE /api/tasks/{id}
```
Remove uma tarefa do sistema.

## 🗄️ Database
Este projeto utiliza o H2 Database, um banco de dados em memória ideal para testes e desenvolvimento local.

Acesse o console em:
```bash
http://localhost:8080/h2-console
```
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: (em branco)
