package com.innosistemas.projects.repository;

import com.innosistemas.projects.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    
    List<Team> findByProjectId(Integer projectId);
    
    @Query(value = "SELECT DISTINCT t.* FROM teams t " +
                   "JOIN team_members tm ON tm.team_id = t.id " +
                   "WHERE tm.student_id = :studentId", 
           nativeQuery = true)
    List<Team> findTeamsByStudentId(@Param("studentId") Long studentId);
}