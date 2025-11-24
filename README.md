# InnoSistemas

API REST para gestión de proyectos académicos, equipos y estudiantes. Sistema diseñado para facilitar la administración de proyectos en cursos universitarios.

## 🚀 Inicio Rápido en Codespaces

Si estás usando GitHub Codespaces, simplemente ejecuta:

```bash
./start-app.sh
```

Este script:
- ✅ Detecta automáticamente la URL de Codespaces
- 🔨 Compila el proyecto
- 🚀 Inicia la aplicación
- 📚 Muestra la URL de Swagger UI

Una vez iniciada la aplicación, accede a:
- **Swagger UI:** `https://{tu-codespace}-8080.{dominio}/swagger-ui.html`
- **API Docs:** `https://{tu-codespace}-8080.{dominio}/api-docs`

## 🚀 Características

- Gestión completa de proyectos académicos
- Organización de equipos y asignación de estudiantes
- Seguimiento de estados de proyectos
- Consultas para relaciones estudiante-equipo-proyecto
- Documentación interactiva con Swagger UI
- Migraciones de base de datos automatizadas con Flyway

## 📋 Requisitos

- **Java:** 17 o superior
- **Maven:** 3.6+ (o usa el wrapper incluido `mvnw`)
- **PostgreSQL:** 12 o superior

## 🏗️ Arquitectura del Proyecto
```
InnoSistemas/
├── src/main/java/com/innosistemas/InnoSistemas/
│   ├── controller/          # Controladores REST
│   │   ├── ProjectController.java
│   │   └── TeamController.java
│   ├── domain/              # Entidades JPA
│   │   ├── Project.java
│   │   └── Team.java
│   ├── repository/          # Repositorios de datos
│   │   ├── ProjectRepository.java
│   │   └── TeamRepository.java
│   └── service/             # Lógica de negocio
│       ├── ProjectService.java
│       └── TeamService.java
└── src/main/resources/
    ├── application.properties
    └── db/migration/        # Scripts de migración Flyway
        ├── V1__create_model_and_seed.sql
        └── V2__seed_users_and_members.sql
```

## 🔧 Configuración e Instalación

### 1. Configurar PostgreSQL

#### Opción A: PostgreSQL con Docker (Recomendado)

Levanta una instancia local de PostgreSQL con la configuración correcta:
```powershell
docker run --name postgres-innosistemas `
  -e POSTGRES_USER=localuser `
  -e POSTGRES_PASSWORD=localpass123 `
  -e POSTGRES_DB=innosistemas_local-teams-projects `
  -p 5432:5432 `
  -d postgres:15
```

#### Opción B: PostgreSQL Instalado Localmente

Si ya tienes PostgreSQL instalado, crea la base de datos y usuario:
```sql
CREATE DATABASE "innosistemas_local-teams-projects";
CREATE USER localuser WITH PASSWORD 'localpass123';
GRANT ALL PRIVILEGES ON DATABASE "innosistemas_local-teams-projects" TO localuser;
```

### 2. Verificar Configuración

La aplicación viene preconfigurada en `application.properties` para conectarse a PostgreSQL local:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/innosistemas_local-teams-projects
spring.datasource.username=localuser
spring.datasource.password=localpass123
```

Si necesitas usar una configuración diferente, puedes:

**Opción 1:** Modificar directamente `src/main/resources/application.properties`

**Opción 2:** Usar variables de entorno (sobrescribe application.properties):
```powershell
$env:SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/tu_base_datos"
$env:SPRING_DATASOURCE_USERNAME="tu_usuario"
$env:SPRING_DATASOURCE_PASSWORD="tu_contraseña"
```

### 3. Instalar Dependencias
```powershell
.\mvnw.cmd clean install
```

### 4. Aplicar Migraciones (Automático)

Las migraciones de Flyway se ejecutan automáticamente al iniciar la aplicación gracias a:
```properties
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true
```

Esto creará las tablas y datos de prueba automáticamente. Si prefieres ejecutarlas manualmente antes:
```powershell
.\mvnw.cmd flyway:migrate
```

**Las migraciones incluyen:**
- `V1__create_model_and_seed.sql`: Crea las tablas (status, courses, projects, users, teams, team_members) y datos iniciales
- `V2__seed_users_and_members.sql`: Inserta usuarios de prueba (profesor + 6 estudiantes), equipos y asignaciones

### 5. Ejecutar la Aplicación
```powershell
.\mvnw.cmd spring-boot:run
```

La aplicación estará disponible en: **http://localhost:8080**

## 📚 Documentación API

### Swagger UI

Accede a la documentación interactiva en:
- **http://localhost:8080/swagger-ui.html**

### Endpoints Principales

#### 🗂️ Projects

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/projects` | Obtener todos los proyectos |
| `GET` | `/api/projects/{id}` | Obtener proyecto por ID |
| `POST` | `/api/projects` | Crear nuevo proyecto |
| `DELETE` | `/api/projects/{id}` | Eliminar proyecto |
| `GET` | `/api/projects/student/{studentId}` | Proyectos donde participa un estudiante |
| `GET` | `/api/projects/student/{studentId}/count` | Contar proyectos de un estudiante |

**Ejemplo de creación:**
```json
{
  "name": "Sistema de Inventario",
  "description": "Proyecto de gestión de inventarios",
  "statusId": 2,
  "courseId": 1
}
```

#### 👥 Teams

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/teams` | Obtener todos los equipos |
| `GET` | `/api/teams/{id}` | Obtener equipo por ID |
| `POST` | `/api/teams` | Crear nuevo equipo |
| `DELETE` | `/api/teams/{id}` | Eliminar equipo |
| `GET` | `/api/teams/project/{projectId}` | Equipos de un proyecto específico |
| `GET` | `/api/teams/student/{studentId}` | Equipos donde participa un estudiante |

**Ejemplo de creación:**
```json
{
  "name": "Equipo Backend",
  "projectId": 1
}
```

## 🗄️ Modelo de Datos

### Entidades Principales

- **Status:** Estados de proyectos (Pendiente, En Progreso, Completado, Bloqueado)
- **Courses:** Cursos académicos
- **Projects:** Proyectos con descripción, estado y curso asociado
- **Users:** Estudiantes y profesores
- **Teams:** Equipos asignados a proyectos
- **Team_Members:** Relación muchos-a-muchos entre equipos y estudiantes

## 🛠️ Tecnologías Utilizadas

- **Spring Boot 3.5.7:** Framework principal
- **Spring Data JPA:** Persistencia de datos
- **PostgreSQL:** Base de datos relacional
- **Flyway:** Versionado y migraciones de BD
- **SpringDoc OpenAPI:** Documentación Swagger
- **Hibernate Validator:** Validación de datos
- **Maven:** Gestión de dependencias