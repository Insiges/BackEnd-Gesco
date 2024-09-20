package com.api.gesco.repository.professor;

import com.api.gesco.model.professor.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor findOneById(Long id);
}
