package com.api.gesco.repository.escola;

import com.api.gesco.model.endereco.EnderecoEscola;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoEscolaRepository extends JpaRepository<EnderecoEscola, Long> {
    EnderecoEscola findOneById(Long id);
}
