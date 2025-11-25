package com.innosistemas.InnoSistemas.controller;

import com.innosistemas.InnoSistemas.domain.Team;
import com.innosistemas.InnoSistemas.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public Team create(@RequestBody Team team) {
        return teamService.save(team);
    }

    @GetMapping
    public List<Team> getAll() {
        return teamService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getById(@PathVariable Integer id) {
        return ResponseEntity.of(teamService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (teamService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/project/{projectId}")
    public List<Team> getTeamsByProject(@PathVariable Integer projectId) {
        return teamService.getTeamsByProjectId(projectId);
    }

    @GetMapping("/student/{studentId}")
    public List<Team> getTeamsByStudent(@PathVariable Long studentId) {
        return teamService.getTeamsByStudentId(studentId);
    }
}