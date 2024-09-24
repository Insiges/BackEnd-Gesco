package com.api.gesco.repository.aluno_responsavel;

import com.api.gesco.model.aluno_responsavel.Aluno_Responsavel;
import com.api.gesco.model.responsavel.Responsavel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Aluno_ResponsavelRepository extends JpaRepository<Aluno_Responsavel, Long> {
    Aluno_Responsavel findOneById(Long id);
}
