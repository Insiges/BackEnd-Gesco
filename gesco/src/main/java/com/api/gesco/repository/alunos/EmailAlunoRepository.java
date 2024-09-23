package com.api.gesco.repository.alunos;

import com.api.gesco.model.alunos.EmailAluno;
import com.api.gesco.model.professor.EmailProfessor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailAlunoRepository extends JpaRepository<EmailAluno, Long> {
    EmailAluno findOneByEmail(String email);
    EmailAluno findOneById(Long id);
}
