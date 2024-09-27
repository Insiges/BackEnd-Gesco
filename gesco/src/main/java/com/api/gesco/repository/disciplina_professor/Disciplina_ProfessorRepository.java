package com.api.gesco.repository.disciplina_professor;

import com.api.gesco.domain.disciplina.DadosDisciplina;
import com.api.gesco.model.aluno_responsavel.Aluno_Responsavel;
import com.api.gesco.model.disciplina_professor.DisciplinaProfesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Disciplina_ProfessorRepository extends JpaRepository<DisciplinaProfesor, Long> {
    DisciplinaProfesor findOneById(Long id);

    @Query("SELECT new com.api.gesco.domain.disciplina.DadosDisciplina(d.id, d.nome) " +
            "FROM Disciplina d " +
            "JOIN DisciplinaProfessor dp ON d.id = dp.disciplina.id " +
            "JOIN Professor p ON dp.professor.id = p.id " +
            "WHERE p.id = :idProfessor")
    List<DadosDisciplina> findDisciplinasByProfessorId(@Param("idProfessor") Long idProfessor);

}
