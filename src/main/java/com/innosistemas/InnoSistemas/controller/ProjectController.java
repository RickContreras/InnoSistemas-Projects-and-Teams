package com.innosistemas.InnoSistemas.controller;

import com.innosistemas.InnoSistemas.domain.Project;
import com.innosistemas.InnoSistemas.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
    public ResponseEntity<Project> getById(@PathVariable Integer id) {
        return ResponseEntity.of(projectService.getById(id));
    }

    @PostMapping
    public Project create(@RequestBody Project project) {
        return projectService.save(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (projectService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        projectService.delete(id);
        return ResponseEntity.noContent().build();
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