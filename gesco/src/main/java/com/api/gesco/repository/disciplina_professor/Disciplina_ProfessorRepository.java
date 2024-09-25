package com.api.gesco.repository.disciplina_professor;

import com.api.gesco.model.aluno_responsavel.Aluno_Responsavel;
import com.api.gesco.model.disciplina_professor.DisciplinaProfesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Disciplina_ProfessorRepository extends JpaRepository<DisciplinaProfesor, Long> {
    DisciplinaProfesor findOneById(Long id);
}
