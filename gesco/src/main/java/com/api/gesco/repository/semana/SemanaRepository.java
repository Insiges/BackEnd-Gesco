package com.api.gesco.repository.semana;

import com.api.gesco.model.horario.Horario;
import com.api.gesco.model.semana.Semana;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemanaRepository extends JpaRepository<Semana,Long> {

    Semana findOneById(Long id);
}
