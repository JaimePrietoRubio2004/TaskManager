# TaskManager

A team task management REST API implemented **three times with the same contract** — in Java/Spring Boot, C#/.NET, and Python/FastAPI. Any backend can be swapped for another without changing the client.

## Why three backends?

The goal of this project is to demonstrate that a well-defined API contract is independent of the technology that implements it. All three backends expose the same endpoints, accept the same DTOs, and return the same responses, so they are interchangeable.

| Backend | Stack | Folder |
|---------|-------|--------|
| Java | Java · Spring Boot · Maven · JPA | [`backend-java/`](backend-java/) |
| C# | .NET · ASP.NET Core Web API | [`backend-dotnet/`](backend-dotnet/) |
| Python | Python 3 · FastAPI · SQLAlchemy | [`backend-python/`](backend-python/) |

## API overview

The API manages three resources: **Users**, **Projects**, and **Tasks**, each with its own controller and full CRUD operations.

Example (Tasks):

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/apiTask/taskList` | List tasks (filterable via DTO) |
| GET | `/apiTask/{task_id}` | Get a single task |
| POST | `/apiTask/createTask` | Create a task |
| PUT | `/apiTask/{task_id}` | Update a task |
| DELETE | `/apiTask/{task_id}` | Delete a task |

Users and Projects follow the same pattern under their own prefixes.

## Getting started

### Java / Spring Boot

```bash
cd backend-java
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

### C# / .NET

```bash
cd backend-dotnet
dotnet run
```

Sample requests are available in [`backend-dotnet.http`](backend-dotnet/backend-dotnet.http).

### Python / FastAPI

```bash
cd backend-python
pip install -r requirements.txt
uvicorn main:app --reload
```

Interactive API docs available at `/docs` (Swagger UI, FastAPI built-in).

## Architecture

Each backend follows a layered architecture:

```
Controller  →  Service  →  Data access (DAO / Repository)  →  Database
     ↕
    DTOs
```

## Roadmap

- [ ] Web client (Angular / React) — planned as a separate repository
- [ ] Unit tests per backend
- [ ] docker-compose to run any backend + database with one command

## Author

**Jaime Prieto Rubio** — [LinkedIn](https://linkedin.com/in/jaime-prieto-rubio-941bba337) · [GitHub](https://github.com/JaimePrietoRubio2004)
