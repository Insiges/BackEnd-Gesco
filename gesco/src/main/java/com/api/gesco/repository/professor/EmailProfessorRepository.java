package com.api.gesco.repository.professor;

import com.api.gesco.model.professor.EmailProfessor;
import com.api.gesco.model.professor.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailProfessorRepository extends JpaRepository<EmailProfessor, Long> {
    EmailProfessor findOneByEmail(String email);
    EmailProfessor findOneById(Long id);
}
