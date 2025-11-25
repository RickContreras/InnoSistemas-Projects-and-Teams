package com.innosistemas.projects.service;

import com.innosistemas.projects.domain.Project;
import com.innosistemas.projects.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    // Crear o actualizar un proyecto
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    // Buscar todos los proyectos
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    // Buscar un proyecto por ID
    public Optional<Project> getById(Integer id) {
        return projectRepository.findById(id);
    }

    // Eliminar un proyecto
    public void delete(Integer id) {
        projectRepository.deleteById(id);
    }

    // Obtener proyectos donde el estudiante es miembro de algún equipo
    public List<Project> getProjectsByStudentId(Long studentId) {
        return projectRepository.findProjectsByStudentId(studentId);
    }
}