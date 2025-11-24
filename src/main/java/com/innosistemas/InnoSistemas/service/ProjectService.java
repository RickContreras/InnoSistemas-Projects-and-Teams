package com.innosistemas.InnoSistemas.service;

import com.innosistemas.InnoSistemas.domain.Project;
import com.innosistemas.InnoSistemas.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    // Buscar todos los proyectos paginados
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    // Buscar proyectos por curso
    public Page<Project> getByCourseId(Integer courseId, Pageable pageable) {
        return projectRepository.findByCourseId(courseId, pageable);
    }

    // Buscar proyectos por estado
    public List<Project> getByStatusId(Integer statusId) {
        return projectRepository.findByStatusId(statusId);
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

    // Contar proyectos de un estudiante
    public Long countProjectsByStudentId(Long studentId) {
        return projectRepository.countProjectsByStudentId(studentId);
    }
}