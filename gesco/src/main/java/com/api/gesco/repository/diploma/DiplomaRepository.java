package com.api.gesco.repository.diploma;

import com.api.gesco.domain.diploma.DadosDetalhamentoDiploma;
import com.api.gesco.model.diploma.Diploma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiplomaRepository extends JpaRepository<Diploma,Long> {

    @Query("SELECT new com.api.gesco.domain.diploma.DadosDetalhamentoDiploma (d.id, d.curso, d.faculdade, p.nome, d.tipoGraduacao.id) " +
            "FROM Diploma d " +
            "JOIN d.professor p " +
            "WHERE p.id = :idProfessor")
    List<DadosDetalhamentoDiploma> findDiplomasByProfessor(@Param("idProfessor") Long idProfessor);

    @Query("SELECT new com.api.gesco.domain.diploma.DadosDetalhamentoDiploma (d.id, d.curso, d.faculdade, p.nome, d.tipoGraduacao.id) " +
            "FROM Diploma d " +
            "JOIN d.professor p ")
    Page<DadosDetalhamentoDiploma> findAllDiplomas(Pageable page);

    Diploma findOneById(Long id);
    void deleteByProfessorId(Long id);
}
