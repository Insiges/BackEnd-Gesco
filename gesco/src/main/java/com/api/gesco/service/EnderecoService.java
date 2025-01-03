package com.api.gesco.service;

import com.api.gesco.domain.endereco.DadosEndereco;
import com.api.gesco.infra.exception.ValidacaoException;
import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.endereco.*;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.professor.Professor;
import com.api.gesco.repository.alunos.EnderecoAlunoRepository;
import com.api.gesco.repository.endereco.CidadeRepository;
import com.api.gesco.repository.endereco.EstadoRepository;
import com.api.gesco.repository.escola.EnderecoEscolaRepository;
import com.api.gesco.repository.professor.EnderecoProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoService {

    @Autowired
    private EstadoRepository repository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EnderecoEscolaRepository enderecoRepository;

    @Autowired
    private EnderecoProfessorRepository enderecoProfessorRepository;

    @Autowired
    private EnderecoAlunoRepository enderecoAlunoRepository;

    public Estado pesquisarEstado(String sigla){
        var estado = repository.findOneBySigla(sigla.toUpperCase());
        return estado;
    }

    public Cidade cadastrarCidade(String nome, String estado){
        var cidade = cidadeRepository.findOneByNome(nome.toUpperCase());

        if (cidade != null){
            return cidade;
        }

        var siglaEstado = pesquisarEstado(estado);

        var cadastrarCidade = cidadeRepository.save(new Cidade(nome.toUpperCase(),siglaEstado));

        return cadastrarCidade;
    }

    //Escola
    public EnderecoEscola cadastrarEnderecoEscola(DadosEndereco dados, Escola escola, Cidade cidade){
        var endereco = enderecoRepository.save(new EnderecoEscola(dados, escola, cidade));

        return endereco;
    }

    @Transactional
    public EnderecoEscola atualizarEnderecoEscola(Long id, EnderecoEscola enderecoEscola){
        var endereco = enderecoRepository.findOneById(id);
        if (endereco != null) {
            var cidade = cadastrarCidade(enderecoEscola.getCidade().getNome(), enderecoEscola.getCidade().getEstado().getSigla());
            enderecoEscola.setCidade(cidade);
            endereco.atualizarEndereco(enderecoEscola);
            return enderecoRepository.save(endereco);
        } else {
            throw new ValidacaoException(String.format("Não foi possível encontrar um email com esse id!"));
        }
    }

    //Professor
    @Transactional
    public EnderecoProfessor cadastrarEnderecoProfessor(DadosEndereco dados, Professor professor, Cidade cidade){
        var endereco = enderecoProfessorRepository.save(new EnderecoProfessor(dados, professor, cidade));

        return endereco;
    }

    @Transactional
    public EnderecoProfessor atualizarEnderecoProfessor(Long id, EnderecoProfessor enderecoProfessor){
        var endereco = enderecoProfessorRepository.findOneById(id);
        if (endereco != null) {
            var cidade = cadastrarCidade(enderecoProfessor.getCidade().getNome(), enderecoProfessor.getCidade().getEstado().getSigla());
            enderecoProfessor.setCidade(cidade);
            endereco.atualizarEndereco(enderecoProfessor);
            return enderecoProfessorRepository.save(endereco);
        } else {
            throw new ValidacaoException(String.format("Não foi possível encontrar um endereco com esse id!"));
        }
    }

    //Alunos
    @Transactional
    public EnderecoAluno cadastrarEnderecoAluno(DadosEndereco dados, Aluno aluno, Cidade cidade){
        var endereco = enderecoAlunoRepository.save(new EnderecoAluno(dados, aluno, cidade));

        return endereco;
    }

    @Transactional
    public EnderecoAluno atualizarEnderecoAluno(Long id, EnderecoAluno enderecoAluno){
        var endereco = enderecoAlunoRepository.findOneById(id);
        if (endereco != null) {
            var cidade = cadastrarCidade(enderecoAluno.getCidade().getNome(), enderecoAluno.getCidade().getEstado().getSigla());
            enderecoAluno.setCidade(cidade);
            endereco.atualizarEndereco(enderecoAluno);
            return enderecoAlunoRepository.save(endereco);
        } else {
            throw new ValidacaoException(String.format("Não foi possível encontrar um email com esse id!"));
        }
    }
}
