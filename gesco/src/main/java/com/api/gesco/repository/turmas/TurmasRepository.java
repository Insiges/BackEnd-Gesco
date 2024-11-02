package com.api.gesco.repository.turmas;

import java.util.List;

import com.api.gesco.domain.grade_horario.DadosDetalhamentoGradeHorario;
import com.api.gesco.domain.turmas.DadosRetornoTurmas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.gesco.model.turmas.Turmas;
import com.api.gesco.model.escola.Escola;

@Repository
public interface TurmasRepository extends JpaRepository<Turmas, Long> {
    List<Turmas> findByEscola(Escola escola);
    Turmas findOneById(Long id);

    @Query("SELECT new com.api.gesco.domain.turmas.DadosRetornoTurmas(t.id, t.nome, t.serie, t.ano, t.escola.id, gh.professor.id) " +
            "FROM GradeHorario gh " +
            "JOIN gh.turma t " +
            "WHERE gh.professor.id = :professor")
    List<DadosRetornoTurmas> findTurmasByProfessor(@Param("professor") Long professor);
}
