-- ===================================================================
--      SCRIPT DE ACTUALIZACIÓN DE BASE DE DATOS "INNOSISTEMAS"
-- Este script crea las nuevas tablas y actualiza los permisos.
-- Está diseñado para ejecutarse una sola vez sobre la BD existente.
-- ===================================================================
-- PASO 1: CREAR TODAS LAS TABLAS NUEVAS
-- -------------------------------------------------------------------
-- Almacena todos los estados posibles para entidades como proyectos y tareas.
CREATE TABLE IF NOT EXISTS status (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    CONSTRAINT status_name_type_unique UNIQUE (name, type)
);
<<<<<<< HEAD

-- 2. COURSES (necesario para projects)
CREATE TABLE IF NOT EXISTS courses (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE,
    description TEXT
);

-- 3. PROJECTS (depende de status y courses)
CREATE TABLE IF NOT EXISTS projects (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status_id INTEGER NOT NULL REFERENCES status(id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    course_id INTEGER NOT NULL REFERENCES courses(id) ON DELETE RESTRICT
);

-- 4. USERS (independiente)
=======
-- Tabla de usuarios básica (necesaria para referenciar estudiantes/profesores)

>>>>>>> origin/develop
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    updated_at TIMESTAMPTZ
);
<<<<<<< HEAD

-- 5. TEAMS (depende de projects)
=======
-- Almacena los cursos que se ofrecen en la plataforma.
CREATE TABLE IF NOT EXISTS courses (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE,
    description TEXT
);
-- Almacena los proyectos, cada uno asociado a un curso y con un estado.
CREATE TABLE IF NOT EXISTS projects (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status_id INTEGER NOT NULL REFERENCES status(id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    course_id INTEGER NOT NULL REFERENCES courses(id) ON DELETE RESTRICT
);
-- Define los equipos de trabajo, cada uno ligado a un único proyecto.
>>>>>>> origin/develop
CREATE TABLE IF NOT EXISTS teams (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    project_id INTEGER NOT NULL REFERENCES projects(id) ON DELETE CASCADE
);
<<<<<<< HEAD

-- 6. TEAM_MEMBERS (depende de teams y users)
=======
-- Tabla Pivote: Asigna estudiantes a los equipos.
>>>>>>> origin/develop
CREATE TABLE IF NOT EXISTS team_members (
    team_id INTEGER NOT NULL REFERENCES teams(id) ON DELETE CASCADE,
    student_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    PRIMARY KEY (team_id, student_id)
<<<<<<< HEAD
);

-- ========================================
-- SEED BÁSICO (siempre funciona)
-- ========================================

-- Status básicos
INSERT INTO status (name, type) VALUES
('Pendiente', 'project'),
('En Progreso', 'project'),
('Completado', 'project'),
('Bloqueado', 'project')
ON CONFLICT (name, type) DO NOTHING;

-- Curso inicial
INSERT INTO courses (id, nombre, description) VALUES
(1, 'Arquitectura de Software 2025', 'Curso principal del semestre')
ON CONFLICT (id) DO NOTHING;

-- Proyecto principal (id=1 siempre)
INSERT INTO projects (id, name, description, status_id, course_id, created_at) VALUES
(1, 'InnoSistemas Plataforma', 'Sistema de gestión de equipos y proyectos', 2, 1, NOW())
ON CONFLICT (id) DO NOTHING;

-- Mensaje de éxito
SELECT '¡V1 CORREGIDO! Tablas creadas en orden correcto + seed básico (project id=1 garantizado)'
=======
);
>>>>>>> origin/develop
