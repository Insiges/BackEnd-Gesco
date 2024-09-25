package com.api.gesco.repository.responsavel;

import com.api.gesco.domain.alunos.DadosDetalhamentoAluno;
import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.responsavel.Responsavel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
    Responsavel findOneById(Long id);
    List<Responsavel> findAllByEscolaId(Long id, Pageable page);
}
