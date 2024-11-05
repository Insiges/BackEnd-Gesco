package com.api.gesco.repository.sexo;

import com.api.gesco.domain.escola.DadosRetornoSexo;
import com.api.gesco.model.endereco.Estado;
import com.api.gesco.model.sexo.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SexoRepository extends JpaRepository<Sexo,Long> {
    Sexo findOneByNome(String nome);

    @Query("SELECT new com.api.gesco.domain.escola.DadosRetornoSexo( " +
            "SUM(CASE WHEN a.sexo.id = 1 THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN a.sexo.id = 2 THEN 1 ELSE 0 END), " +
            "COUNT(a)) " +
            "FROM Aluno a " +
            "WHERE a.sexo.id IN (1, 2) AND a.escola.id = :id")
    DadosRetornoSexo quantidadeTotal(@Param("id") Long id);

}