package com.api.gesco.repository.sexo;

import com.api.gesco.model.endereco.Estado;
import com.api.gesco.model.sexo.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SexoRepository extends JpaRepository<Sexo,Long> {
    Sexo findOneByNome(String nome);
}
