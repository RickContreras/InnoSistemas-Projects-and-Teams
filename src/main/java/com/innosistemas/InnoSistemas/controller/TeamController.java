package com.innosistemas.InnoSistemas.controller;

import com.innosistemas.InnoSistemas.domain.Team;
import com.innosistemas.InnoSistemas.service.TeamService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

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
    public Optional<Team> getById(@PathVariable Integer id) {
        return teamService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        teamService.delete(id);
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