package com.api.gesco.repository.disciplina;

import com.api.gesco.model.disciplina.Disciplina;
import com.api.gesco.model.salas.Salas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina,Long> {
    Disciplina findOneById(Long id);

}
