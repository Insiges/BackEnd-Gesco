package com.api.gesco.repository.atividade;

import com.api.gesco.model.atividade.Atividade;
import com.api.gesco.model.atividade.Tipo_Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade,Long> {
    Atividade findOneById(Long id);
    List<Atividade> findAllByTurmasId(Long id);
    List<Atividade> findAllByProfessorId(Long id);
}
