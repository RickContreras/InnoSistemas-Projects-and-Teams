package com.innosistemas.projects.repository;

import com.innosistemas.projects.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Page<Project> findByCourseId(Integer courseId, Pageable pageable);
    List<Project> findByStatusId(Integer statusId);

    // Buscar proyectos donde el usuario es miembro de algún equipo
    @Query(value = "SELECT DISTINCT p.* FROM projects p " +
                   "JOIN teams t ON t.project_id = p.id " +
                   "JOIN team_members tm ON tm.team_id = t.id " +
                   "WHERE tm.student_id = :studentId", 
           nativeQuery = true)
    List<Project> findProjectsByStudentId(@Param("studentId") Long studentId);
    
    // Contar proyectos de un estudiante
    @Query(value = "SELECT COUNT(DISTINCT p.id) FROM projects p " +
                   "JOIN teams t ON t.project_id = p.id " +
                   "JOIN team_members tm ON tm.team_id = t.id " +
                   "WHERE tm.student_id = :studentId", 
           nativeQuery = true)
    Long countProjectsByStudentId(@Param("studentId") Long studentId);
}
