package com.api.gesco.repository.reserva_sala;

import com.api.gesco.domain.disciplina.DadosDisciplina;
import com.api.gesco.model.disciplina_professor.DisciplinaProfesor;
import com.api.gesco.model.reserva_sala.ReservaSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservaSalaRepository extends JpaRepository<ReservaSala, Long> {
    ReservaSala findOneById(Long id);
    List<ReservaSala> findAllBySalaId(Long id);
    List<ReservaSala> findAllByDiaAndSalaId(LocalDate dia, Long id);
//
//    @Query("SELECT new com.api.gesco.domain.disciplina.DadosDisciplina(d.id, d.nome) " +
//            "FROM Disciplina d " +
//            "JOIN DisciplinaProfessor dp ON d.id = dp.disciplina.id " +
//            "JOIN Professor p ON dp.professor.id = p.id " +
//            "WHERE p.id = :idProfessor")
//    List<DadosDisciplina> findDisciplinasByProfessorId(@Param("idProfessor") Long idProfessor);

}
