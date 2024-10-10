package com.api.gesco.repository.professor;

import com.api.gesco.domain.professor.DadosDetalhamentoProfessores;
import com.api.gesco.model.professor.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Query("SELECT new com.api.gesco.domain.professor.DadosDetalhamentoProfessores(" +
            "p.id, p.nome, p.foto, p.cpf, p.dataNascimento, ep.id, ep.email, tp.id, tp.telefone, s.nome, " +
            "epf.logradouro, epf.cep, epf.bairro, epf.numero, epf.complemento, c.id, c.nome, e.sigla) " +
            "FROM Professor p " +
            "JOIN EmailProfessor ep ON p.id = ep.professor.id " +
            "JOIN TelefoneProfessor tp ON p.id = tp.professor.id " +
            "JOIN EnderecoProfessor epf ON p.id = epf.professor.id " +
            "JOIN Cidade c ON epf.cidade.id = c.id " +
            "JOIN Estado e ON c.estado.id = e.id " +
            "JOIN Sexo s ON s.id = p.sexo.id " +
            "WHERE p.escola.id = :idEscola")
    List<DadosDetalhamentoProfessores> findProfessoresByEscola(@Param("idEscola") Long idEscola);

    @Query("SELECT new com.api.gesco.domain.professor.DadosDetalhamentoProfessores(" +
            "p.id, p.nome, p.foto, p.cpf, p.dataNascimento, ep.id, ep.email, tp.id, tp.telefone, s.nome, " +
            "epf.logradouro, epf.cep, epf.bairro, epf.numero, epf.complemento, c.id, c.nome, e.sigla) " +
            "FROM Professor p " +
            "JOIN EmailProfessor ep ON p.id = ep.professor.id " +
            "JOIN TelefoneProfessor tp ON p.id = tp.professor.id " +
            "JOIN EnderecoProfessor epf ON p.id = epf.professor.id " +
            "JOIN Cidade c ON epf.cidade.id = c.id " +
            "JOIN Estado e ON c.estado.id = e.id " +
            "JOIN Sexo s ON s.id = p.sexo.id " +
            "WHERE p.id = :idProfessor")
    DadosDetalhamentoProfessores findProfessorById(@Param("idProfessor") Long idProfessor);

    @Query("SELECT new com.api.gesco.domain.professor.DadosDetalhamentoProfessores(" +
            "p.id, p.nome, p.foto, p.cpf, p.dataNascimento, ep.id, ep.email, tp.id, tp.telefone, s.nome, " +
            "epf.logradouro, epf.cep, epf.bairro, epf.numero, epf.complemento, c.id, c.nome, e.sigla) " +
            "FROM Professor p " +
            "JOIN EmailProfessor ep ON p.id = ep.professor.id " +
            "JOIN TelefoneProfessor tp ON p.id = tp.professor.id " +
            "JOIN EnderecoProfessor epf ON p.id = epf.professor.id " +
            "JOIN Cidade c ON epf.cidade.id = c.id " +
            "JOIN Estado e ON c.estado.id = e.id " +
            "JOIN Sexo s ON s.id = p.sexo.id ")
    Page<DadosDetalhamentoProfessores> pegarTodosOsProfessores(Pageable paginacao);

    Professor findOneById(Long id);
}
