package com.api.gesco.repository.graduacao;

import com.api.gesco.model.graduacao.Tipo_Graduacao;
import com.api.gesco.model.horario.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoGraduacaoRepository extends JpaRepository<Tipo_Graduacao,Long> {

    Tipo_Graduacao findOneById(Long id);
}
