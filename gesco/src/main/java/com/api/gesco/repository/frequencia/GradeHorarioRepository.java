package com.api.gesco.repository.frequencia;

import com.api.gesco.domain.grade_horario.DadosDetalhamentoGradeHorario;
import com.api.gesco.model.frequencia.GradeHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeHorarioRepository extends JpaRepository<GradeHorario,Long> {
    GradeHorario findOneById(Long id);

    @Query("SELECT new com.api.gesco.domain.grade_horario.DadosDetalhamentoGradeHorario(g.id, h.hora, s.dia, p.nome, d.nome, t.nome) " +
            "FROM GradeHorario g " +
            "JOIN g.horario h " +
            "JOIN g.semana s " +
            "JOIN g.professor p " +
            "JOIN g.disciplina d " +
            "JOIN g.turma t " +
            "WHERE p.id = :professorId")
    List<DadosDetalhamentoGradeHorario> findGradeHorarioByProfessorId(@Param("professorId") Long professorId);

    @Query("SELECT new com.api.gesco.domain.grade_horario.DadosDetalhamentoGradeHorario(g.id, h.hora, s.dia, p.nome, d.nome, t.nome) " +
            "FROM GradeHorario g " +
            "JOIN g.horario h " +
            "JOIN g.semana s " +
            "JOIN g.professor p " +
            "JOIN g.disciplina d " +
            "JOIN g.turma t " +
            "WHERE t.id = :turmaId")
    List<DadosDetalhamentoGradeHorario> findGradeHorarioByTurmaId(@Param("turmaId") Long turmaId);

}
