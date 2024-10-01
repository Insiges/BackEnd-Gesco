package com.api.gesco.repository.alunos_turmas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.gesco.model.alunos_turmas.Alunos_turmas;

@Repository
public interface AlunosTurmasRepository extends JpaRepository<Alunos_turmas, Long> {
    Alunos_turmas findOneById(Long id);
}