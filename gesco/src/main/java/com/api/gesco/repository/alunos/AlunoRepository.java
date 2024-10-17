package com.api.gesco.repository.alunos;

import com.api.gesco.domain.alunos.DadosDetalhamentoAluno;
import com.api.gesco.domain.professor.DadosDetalhamentoProfessores;
import com.api.gesco.domain.alunos.DadosDetalhamentoAlunoCompleto;
import com.api.gesco.model.alunos.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Aluno findOneById(Long id);
    @Query("SELECT new com.api.gesco.domain.alunos.DadosDetalhamentoAluno(" +
            "a.id, a.nome, a.foto, a.cpf, a.dataNascimento, a.matricula, eal.id, eal.email, " +
            "tal.id, tal.telefone, s.nome, edal.logradouro, edal.cep, edal.bairro, edal.numero, edal.complemento, " +
            "c.id, c.nome, e.sigla) " +
            "FROM Aluno a " +
            "JOIN EmailAluno eal ON a.id = eal.aluno.id " +
            "JOIN TelefoneAluno tal ON a.id = tal.aluno.id " +
            "JOIN EnderecoAluno edal ON a.id = edal.aluno.id " +
            "JOIN Cidade c ON edal.cidade.id = c.id " +
            "JOIN Estado e ON c.estado.id = e.id " +
            "JOIN Sexo s ON s.id = a.sexo.id " +
            "WHERE a.escola.id = :idEscola")
    List<DadosDetalhamentoAluno> findAlunosByEscola(@Param("idEscola") Long idEscola);

    @Query("SELECT new com.api.gesco.domain.alunos.DadosDetalhamentoAluno(" +
            "a.id, a.nome, a.foto, a.cpf, a.dataNascimento, a.matricula, eal.id, eal.email, " +
            "tal.id, tal.telefone, s.nome, edal.logradouro, edal.cep, edal.bairro, edal.numero, edal.complemento, " +
            "c.id, c.nome, e.sigla) " +
            "FROM Aluno a " +
            "JOIN EmailAluno eal ON a.id = eal.aluno.id " +
            "JOIN TelefoneAluno tal ON a.id = tal.aluno.id " +
            "JOIN EnderecoAluno edal ON a.id = edal.aluno.id " +
            "JOIN Cidade c ON edal.cidade.id = c.id " +
            "JOIN Estado e ON c.estado.id = e.id " +
            "JOIN Sexo s ON s.id = a.sexo.id ")
    Page<DadosDetalhamentoAluno> findAllAluno(Pageable paginacao);

    @Query("SELECT new com.api.gesco.domain.alunos.DadosDetalhamentoAluno(" +
            "a.id, a.nome, a.foto, a.cpf, a.dataNascimento, a.matricula, eal.id, eal.email, " +
            "tal.id, tal.telefone, s.nome, edal.logradouro, edal.cep, edal.bairro, edal.numero, edal.complemento, " +
            "c.id, c.nome, e.sigla) " +
            "FROM Aluno a " +
            "JOIN EmailAluno eal ON a.id = eal.aluno.id " +
            "JOIN TelefoneAluno tal ON a.id = tal.aluno.id " +
            "JOIN EnderecoAluno edal ON a.id = edal.aluno.id " +
            "JOIN Cidade c ON edal.cidade.id = c.id " +
            "JOIN Estado e ON c.estado.id = e.id " +
            "JOIN Sexo s ON s.id = a.sexo.id " +
            "WHERE a.escola.id = :idAluno")
    DadosDetalhamentoAluno findAlunosByIdEscola(@Param("idAluno") Long idAluno);

    @Query("SELECT new com.api.gesco.domain.alunos.DadosDetalhamentoAlunoCompleto(" +
            "a.id, a.nome, a.foto, a.cpf, a.dataNascimento, a.matricula, eal.id, eal.email, " +
            "tal.id, tal.telefone, s.nome, edal.logradouro, edal.cep, edal.bairro, edal.numero, edal.complemento, " +
            "c.id, c.nome, e.sigla, t.id, es.id) " +
            "FROM Aluno a " +
            "JOIN EmailAluno eal ON a.id = eal.aluno.id " +
            "JOIN TelefoneAluno tal ON a.id = tal.aluno.id " +
            "JOIN EnderecoAluno edal ON a.id = edal.aluno.id " +
            "JOIN Cidade c ON edal.cidade.id = c.id " +
            "JOIN Estado e ON c.estado.id = e.id " +
            "JOIN Sexo s ON s.id = a.sexo.id " +
            "JOIN Alunos_turmas at ON at.aluno.id = a.id "+
            "JOIN Turmas t ON t.id = at.turma.id " +
            "JOIN Escola es ON es.id = a.escola.id " +
            "WHERE a.id = :idAluno and t.ano = :data")
    DadosDetalhamentoAlunoCompleto findAlunoById(@Param("idAluno") Long idAluno,@Param("data") Year data);
}
