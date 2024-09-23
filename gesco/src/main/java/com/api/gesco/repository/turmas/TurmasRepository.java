package com.api.gesco.repository.turmas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.turma.Turmas;

@Repository
public interface TurmasRepository extends JpaRepository<Turmas,Long> {
    List<Turmas> findByEscola(Escola escola);

}
