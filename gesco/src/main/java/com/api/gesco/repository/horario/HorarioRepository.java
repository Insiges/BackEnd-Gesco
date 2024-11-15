package com.api.gesco.repository.horario;

import com.api.gesco.domain.diploma.DadosDetalhamentoDiploma;
import com.api.gesco.model.diploma.Diploma;
import com.api.gesco.model.horario.Horario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HorarioRepository extends JpaRepository<Horario,Long> {

    Horario findOneById(Long id);
}
