package com.innosistemas.InnoSistemas.controller;

import com.innosistemas.InnoSistemas.domain.Project;
import com.innosistemas.InnoSistemas.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<Project> getAll() {
        return projectService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Project> getById(@PathVariable Integer id) {
        return projectService.getById(id);
    }

    @PostMapping
    public Project create(@RequestBody Project project) {
        return projectService.save(project);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        projectService.delete(id);
    }

    @GetMapping("/student/{studentId}")
    public List<Project> getProjectsByStudent(@PathVariable Long studentId) {
        return projectService.getProjectsByStudentId(studentId);
    }

    @GetMapping("/student/{studentId}/count")
    public Long countProjectsByStudent(@PathVariable Long studentId) {
        return projectService.countProjectsByStudentId(studentId);
    }
}