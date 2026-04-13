# Task Manager API

[![Java 17](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot 3.3](https://img.shields.io/badge/Spring_Boot-3.3-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Web](https://img.shields.io/badge/Spring-Web-6DB33F?logo=spring&logoColor=white)](https://spring.io/)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?logo=spring&logoColor=white)](https://spring.io/projects/spring-data-jpa)
[![H2 Database](https://img.shields.io/badge/H2-Database-0B5CAB)](https://www.h2database.com/)
[![Maven Wrapper](https://img.shields.io/badge/Maven-Wrapper-C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org/)

API REST para gerenciamento de tarefas construída com Java 17, Spring Boot, Spring Web, Spring Data JPA e banco H2 em memória.

O projeto mantém a proposta original de CRUD de tarefas, mas agora está organizado com uma separação mais próxima de Clean Architecture:

- `domain`: regra e modelo de domínio
- `application`: casos de uso e portas
- `infrastructure`: controller HTTP, persistência e mapeamentos

## Tecnologias

- Java 17
- Spring Boot 3.3
- Spring Web
- Spring Data JPA
- H2 Database
- Maven Wrapper

## Como a aplicação funciona

A aplicação expõe uma API em `/api/tasks` para criar, listar, buscar, atualizar e remover tarefas.

Fluxo resumido:

1. O controller recebe a requisição HTTP.
2. O payload é convertido para comandos de entrada da aplicação.
3. O caso de uso executa a regra de negócio.
4. A persistência é acessada por uma porta (`TaskGateway`) implementada na infraestrutura.
5. O resultado volta como DTO de resposta JSON.

Cada tarefa possui:

- `id`
- `title`
- `description`
- `completed`

## Como rodar

Pré-requisitos:

- Java 17 instalado

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

No Linux/macOS:

```bash
./mvnw spring-boot:run
```

Se quiser executar os testes antes:

No Windows:

```powershell
.\mvnw.cmd test
```

No Linux/macOS:

```bash
./mvnw test
```

## Acessos locais

- API: `http://localhost:8080/api/tasks`
- H2 Console: `http://localhost:8080/h2-console`

Configuração atual do H2:

- JDBC URL: `jdbc:h2:mem:testdb`
- User: `sa`
- Password: `password`

## Endpoints

- `GET /api/tasks` lista todas as tarefas
- `GET /api/tasks/{id}` busca uma tarefa por ID
- `POST /api/tasks` cria uma tarefa
- `PUT /api/tasks/{id}` atualiza uma tarefa existente
- `DELETE /api/tasks/{id}` remove uma tarefa

## Exemplo de requisição

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Estudar Clean Architecture","description":"Separar dominio, aplicacao e infraestrutura","completed":false}'
```

Resposta esperada:

```json
{
  "id": 1,
  "title": "Estudar Clean Architecture",
  "description": "Separar dominio, aplicacao e infraestrutura",
  "completed": false
}
```
