# 📝 Task Manager API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

API REST para gerenciamento de tarefas, desenvolvida em Java + Spring Boot como projeto de estudo.  
Este projeto foi desenvolvido como exercício de prática para construção de operações CRUD seguindo os princípios REST.  
CRUD → Create · Read · Update · Delete (Criar · Listar · Atualizar · Excluir)

---

## Tecnologias
- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven

---

## Como executar
```bash
git clone https://github.com/FelipeZelent/Task-Manager.git
cd Task-Manager
./mvnw clean install
./mvnw spring-boot:run
```
- API: http://localhost:8080/api/tasks
- H2 Console: http://localhost:8080/h2-console
  - JDBC: jdbc:h2:mem:testdb | user: sa | password: (vazio)

---
## Endpoints
- GET /api/tasks → lista todas as tarefas
- GET /api/tasks/{id} → busca tarefa por ID
- POST /api/tasks → cria nova tarefa
- PUT /api/tasks/{id} → atualiza tarefa existente
- DELETE /api/tasks/{id} → remove tarefa

---
## Exemplo de criação (POST)
curl -X POST http://localhost:8080/api/tasks \
 -H "Content-Type: application/json" \
 -d '{"title":"Estudar Spring","description":"Criar endpoints","completed":false}'



