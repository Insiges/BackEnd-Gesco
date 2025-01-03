package com.api.gesco.repository.frequencia_chamada;


import com.api.gesco.domain.frequencia.DadosRetornoFrequenciaTotal;
import com.api.gesco.domain.frequencia.Presenca;
import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.disciplina.Disciplina;
import com.api.gesco.model.frequencia.Frequencia;
import com.api.gesco.model.professor.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface FrequenciaRepository extends JpaRepository<Frequencia, Long> {

    List<Frequencia> findByDia(LocalDate dia);
    List<Frequencia> findByAlunoId(Long alunoId);
    List<Frequencia> findByDisciplinaId(Long disciplinaId);
    List<Frequencia> findByProfessorId(Long professorId);
    List<Frequencia> findByPresenca(Presenca presenca);
    List<Frequencia> findByAlunoIdAndDia(Long alunoId, LocalDate dia);
    List<Frequencia> findByProfessorIdAndDisciplinaIdAndDia(Long professorId, Long disciplinaId, LocalDate dia);
    List<Frequencia> findByDiaBetween(LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT * FROM frequencia WHERE id_aluno = :alunoId AND dia = :dia", nativeQuery = true)
    List<Frequencia> findFrequenciaByAlunoAndDia(@Param("alunoId") Long alunoId, @Param("dia") LocalDate dia);

    Long countByAlunoId(Long alunoId);
    Long countByDisciplinaId(Long disciplinaId);

    void deleteByAlunoId(Long alunoId);
    void deleteByDisciplinaId(Long disciplinaId);

    @Query(value = "SELECT * FROM frequencia WHERE id_aluno = :alunoId AND id_disciplina = :disciplina order by frequencia.dia", nativeQuery = true)
    List<Frequencia> findFrequenciaByAlunoAndDisciplina(@Param("alunoId") Long alunoId, @Param("disciplina") Long disciplina);

    @Query(value = "SELECT id_disciplina FROM frequencia WHERE id_aluno = :alunoId", nativeQuery = true)
    List<Long> findDisciplinasByAluno(@Param("alunoId") Long alunoId);

    @Query("SELECT new com.api.gesco.domain.frequencia.DadosRetornoFrequenciaTotal( " +
            "SUM(CASE WHEN f.presenca = PRESENTE THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN f.presenca = AUSENTE THEN 1 ELSE 0 END), " +
            "COUNT(f)) " +
            "FROM Frequencia f " +
            "WHERE f.presenca IN (PRESENTE,AUSENTE) AND f.aluno.id = :id AND f.disciplina.id = :disciplina")
    List<DadosRetornoFrequenciaTotal> contarFrequenciasPorPresenca(@Param("id") Long alunoId, @Param("disciplina") Long disciplina);

    @Modifying
    @Transactional
    @Query("DELETE FROM Frequencia f WHERE f.disciplina.id = :idDisciplina AND f.professor.id = :idProfessor AND f.aluno.id IN :idsAluno AND f.dia = :dia")
    void deleteByDisciplinaProfessorAndAlunos(@Param("idDisciplina") Long idDisciplina,
                                              @Param("idProfessor") Long idProfessor,
                                              @Param("dia") LocalDate dia,
                                              @Param("idsAluno") List<Long> idsAluno);


    List<Frequencia> findAllByDisciplinaIdAndProfessorIdAndDiaAndPresenca(Long disciplina, Long professor, LocalDate dia, Presenca presenca);
}

