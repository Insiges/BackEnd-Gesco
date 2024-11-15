package com.api.gesco.repository.atividade;

import com.api.gesco.model.atividade.Tipo_Atividade;
import com.api.gesco.model.disciplina.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoAtividadeRepository extends JpaRepository<Tipo_Atividade,Long> {
    Tipo_Atividade findOneById(Long id);

}
