package com.api.gesco.repository.professor;

import com.api.gesco.model.professor.TelefoneProfessor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneProfessorRepository extends JpaRepository<TelefoneProfessor, Long> {
    TelefoneProfessor findOneByTelefone(String telefone);
    TelefoneProfessor findOneById(Long id);
}
