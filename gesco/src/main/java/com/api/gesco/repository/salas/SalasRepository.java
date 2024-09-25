package com.api.gesco.repository.salas;

import com.api.gesco.model.salas.Salas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalasRepository extends JpaRepository<Salas,Long> {
    Salas findOneById(Long id);
    Page<Salas> findAllByEscolaId(Pageable page, Long id);

}
