-- V2: Seed usuarios y miembros de equipos
-- Este archivo contiene los datos artificiales para usuarios
-- y la asignación de miembros a equipos.
-- Insertar usuarios de ejemplo (id explícitos para reproducibilidad)
INSERT INTO users (id, username, full_name, email, active)
VALUES (
        1,
        'daniel',
        'Daniel Sanchez',
        'daniel@example.com',
        true
    ),
    (
        10,
        'prof_layton',
        'Profesor Layton',
        'layton@example.com',
        true
    ),
    (
        20,
        'student_alice',
        'Alice Estudiante',
        'alice@example.com',
        true
    ),
    (
        21,
        'student_bob',
        'Bob Estudiante',
        'bob@example.com',
        true
    ) ON CONFLICT (id) DO NOTHING;
-- Insertar los estados base para proyectos y tareas
INSERT INTO status (id, name, type)
VALUES (1, 'Planificado', 'project'),
    (2, 'En Progreso', 'project'),
    (3, 'Completado', 'project'),
    (4, 'Pendiente', 'task'),
    (5, 'En Progreso', 'task'),
    (6, 'Completada', 'task') ON CONFLICT (id) DO NOTHING;
-- Crear un curso de ejemplo
INSERT INTO courses (id, nombre, description)
VALUES (
        101,
        'Ingeniería de Software II',
        'Curso enfocado en el desarrollo ágil y colaborativo de software.'
    ) ON CONFLICT (id) DO NOTHING;
-- Crear un proyecto para ese curso
INSERT INTO projects (id, name, description, status_id, course_id)
VALUES (
        201,
        'InnoSistemas Plataforma de Feedback',
        'Desarrollar la plataforma web para la gestión de proyectos.',
        1,
        101
    ),
    (
        202,
        'Desarrollo de Plataforma Móvil',
        'Crear una aplicación móvil para la gestión de tareas.',
        1,
        101
    ) ON CONFLICT (id) DO NOTHING;
-- Crear un equipo para ese proyecto
INSERT INTO teams (id, name, project_id)
VALUES (301, 'Equipo Alfa', 201),
    (302, 'Equipo Beta', 202) ON CONFLICT (id) DO NOTHING;
-- Insertar miembros del equipo (ahora que la tabla users existe)
INSERT INTO team_members (team_id, student_id)
VALUES (301, 20),
    (301, 21),
    (301, 1),
    (302, 1) ON CONFLICT (team_id, student_id) DO NOTHING;
-- Mensaje de finalización
SELECT '¡Migración de base de datos completada con éxito!' AS message;
