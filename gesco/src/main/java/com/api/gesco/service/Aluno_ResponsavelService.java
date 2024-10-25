package com.api.gesco.service;

import com.api.gesco.domain.alunos_responsavel.DadosCadastroAluno_Responsavel;
import com.api.gesco.model.aluno_responsavel.Aluno_Responsavel;
import com.api.gesco.model.responsavel.Responsavel;
import com.api.gesco.repository.aluno_responsavel.Aluno_ResponsavelRepository;
import com.api.gesco.repository.alunos.AlunoRepository;
import com.api.gesco.repository.responsavel.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Aluno_ResponsavelService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private Aluno_ResponsavelRepository intermediariaRepository;


    @Transactional
    public ResponseEntity cadastrarAlunoResponsavel(DadosCadastroAluno_Responsavel dados){
        var aluno = alunoRepository.findOneById(dados.id_aluno());
        var responsavel = responsavelRepository.findOneById(dados.id_responsavel());

        if (aluno != null && responsavel != null){

            var alunoResponsavel = intermediariaRepository.save(new Aluno_Responsavel(aluno, responsavel));

            return ResponseEntity.ok(alunoResponsavel);
        }

        throw new RuntimeException("Erro: Não foi possível encontrar um aluno ou responsáveis com estes dados.");
    }

    public ResponseEntity atualizarAlunoResponsavel(Long id, DadosCadastroAluno_Responsavel dados){
        var alunoResponsavel = intermediariaRepository.findOneById(id);
        var aluno = alunoRepository.findOneById(dados.id_aluno());
        var responsavel = responsavelRepository.findOneById(dados.id_responsavel());

        if (alunoResponsavel != null){
            alunoResponsavel.atualizarAluno(aluno, responsavel);

            intermediariaRepository.save(alunoResponsavel);
        }

        return  ResponseEntity.ok(alunoResponsavel);

    }

    @Transactional
    public void deletarAlunoResponsavel(Long id){
        intermediariaRepository.deleteById(id);
    }

    public List<Responsavel> pegarResponsaveisByAluno(Long id){
        var lista = intermediariaRepository.findByAlunoId(id);

        var responsaveis = lista.stream().map(it -> responsavelRepository.findOneById(it.getResponsavel().getId())).collect(Collectors.toList());

        return responsaveis;
    }

    @Transactional
    public Aluno_Responsavel verificarECadastrarAlunoResponsavel(DadosCadastroAluno_Responsavel dados){
        var aluno = alunoRepository.findOneById(dados.id_aluno());
        var responsavel = responsavelRepository.findOneById(dados.id_responsavel());

        if (aluno != null && responsavel != null){

            var alunoResponsavel = intermediariaRepository.findByAlunoIdAndResponsavelId(dados.id_aluno(), dados.id_responsavel());

            if (alunoResponsavel != null){
                return alunoResponsavel;
            }else{
                alunoResponsavel = intermediariaRepository.save(new Aluno_Responsavel(aluno, responsavel));

                return alunoResponsavel;
            }
        }
        throw new RuntimeException("Erro: Não foi possível encontrar um aluno ou responsáveis com estes dados.");
    }
}
