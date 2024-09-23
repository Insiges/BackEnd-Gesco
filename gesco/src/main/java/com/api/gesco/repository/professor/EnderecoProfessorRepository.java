package com.api.gesco.repository.professor;

import com.api.gesco.model.endereco.EnderecoEscola;
import com.api.gesco.model.endereco.EnderecoProfessor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoProfessorRepository extends JpaRepository<EnderecoProfessor, Long> {
    EnderecoProfessor findOneById(Long id);
}
