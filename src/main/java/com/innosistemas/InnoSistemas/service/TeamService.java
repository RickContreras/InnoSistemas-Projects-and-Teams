package com.innosistemas.InnoSistemas.service;

import com.innosistemas.InnoSistemas.domain.Team;
import com.innosistemas.InnoSistemas.repository.TeamRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    public Optional<Team> getById(Integer id) {
        return teamRepository.findById(id);
    }

    public void delete(Integer id) {
        teamRepository.deleteById(id);
    }

    public List<Team> getTeamsByProjectId(Integer projectId) {
        return teamRepository.findByProjectId(projectId);
    }

    public List<Team> getTeamsByStudentId(Long studentId) {
        return teamRepository.findTeamsByStudentId(studentId);
    }
}