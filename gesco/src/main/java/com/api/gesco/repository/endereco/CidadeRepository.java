package com.api.gesco.repository.endereco;

import com.api.gesco.model.endereco.Cidade;
import com.api.gesco.model.endereco.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade,Long> {
    Cidade findOneByNome(String nome);
}
