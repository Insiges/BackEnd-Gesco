package com.api.gesco.repository.salas;

import com.api.gesco.model.salas.Salas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalasRepository extends JpaRepository<Salas,Long> {
    Salas findOneById(Long id);
    Salas findOneByNome(String nome);

}
