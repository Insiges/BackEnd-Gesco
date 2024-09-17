package com.api.gesco.repository.endereco;

import com.api.gesco.model.endereco.EnderecoEscola;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoEscola, Long> {
}
