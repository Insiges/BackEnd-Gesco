package com.api.gesco.repository.endereco;

import com.api.gesco.model.endereco.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado,Long> {
    Estado findOneBySigla(String sigla);
}
