package com.api.gesco.repository.escola;

import com.api.gesco.domain.escola.DadosDetalhamentoEscolas;
import com.api.gesco.model.escola.Escola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EscolaRepository extends JpaRepository<Escola,Long> {

    @Query("SELECT new com.api.gesco.domain.escola.DadosDetalhamentoEscolas( " +
            "e.id, e.nome, e.imagem,ee.id, ee.email,te.id, te.telefone, " +
            "en.logradouro, en.cep, en.bairro, en.numero, en.complemento, " +
            "c.id, c.nome, es.sigla) " +
            "FROM Escola e " +
            "JOIN e.emails ee " +
            "JOIN e.telefones te " +
            "JOIN e.enderecos en " +
            "JOIN en.cidade c " +
            "JOIN c.estado es")
    List<DadosDetalhamentoEscolas> pegarTodosOsDadosDasEscolaS();

    @Query("SELECT new com.api.gesco.domain.escola.DadosDetalhamentoEscolas( " +
            "e.id, e.nome, e.imagem, ee.id, ee.email,te.id, te.telefone, " +
            "en.logradouro, en.cep, en.bairro, en.numero, en.complemento, " +
            "c.id, c.nome, es.sigla) " +
            "FROM Escola e " +
            "JOIN e.emails ee " +
            "JOIN e.telefones te " +
            "JOIN e.enderecos en " +
            "JOIN en.cidade c " +
            "JOIN c.estado es  " +
            "WHERE e.id = :id")
    List<DadosDetalhamentoEscolas> pegarTodosOsDadosDeUmaEscola( @Param("id") Long id);

    Escola getEscolaById(Long id);
    Escola findOneById(Long id);
}

