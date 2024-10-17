package com.api.gesco.repository.frequencia_chamada;


import com.api.gesco.domain.frequencia.Presenca;
import com.api.gesco.model.disciplina.Disciplina;
import com.api.gesco.model.frequencia.Frequencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
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

    @Query(value = "SELECT * FROM frequencia WHERE id_aluno = :alunoId AND id_disciplina = :disciplina", nativeQuery = true)
    List<Frequencia> findFrequenciaByAlunoAndDisciplina(@Param("alunoId") Long alunoId, @Param("disciplina") Long disciplina);

    @Query(value = "SELECT id_disciplina FROM frequencia WHERE id_aluno = :alunoId", nativeQuery = true)
    List<Long> findDisciplinasByAluno(@Param("alunoId") Long alunoId);
}

