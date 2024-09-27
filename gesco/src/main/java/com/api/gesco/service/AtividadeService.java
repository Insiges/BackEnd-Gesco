package com.api.gesco.service;

import com.api.gesco.domain.atividade.DadosAtualizarAtividade;
import com.api.gesco.domain.atividade.DadosCadastroAtividade;
import com.api.gesco.domain.atividade.DadosCadastroTipoAtividade;
import com.api.gesco.domain.disciplina.DadosCadastroDisciplina;
import com.api.gesco.model.atividade.Atividade;
import com.api.gesco.model.atividade.Tipo_Atividade;
import com.api.gesco.model.disciplina.Disciplina;
import com.api.gesco.repository.atividade.AtividadeRepository;
import com.api.gesco.repository.atividade.TipoAtividadeRepository;
import com.api.gesco.repository.disciplina.DisciplinaRepository;
import com.api.gesco.repository.professor.ProfessorRepository;
import com.api.gesco.repository.turmas.TurmasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AtividadeService {

    @Autowired
    private TipoAtividadeRepository repository;

    @Autowired
    private TurmasRepository turmasRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Transactional
    public Tipo_Atividade cadastrarTipoAtividade(DadosCadastroTipoAtividade dados){

        var atividade = repository.save(new Tipo_Atividade(dados));

        return atividade;
    }

    public ResponseEntity<List<Tipo_Atividade>> listarTodosOsTipo(){
        var atividade =repository.findAll();

        return ResponseEntity.ok(atividade);
    }

    @Transactional
    public Tipo_Atividade atualizarTipo(Long id, DadosCadastroTipoAtividade dados){
        var atividade = repository.findOneById(id);

        if (atividade != null){
            atividade.atualizarTipoAtividade(dados);

            repository.save(atividade);
        }

        return atividade;
    }

    @Transactional
    public void deletarTipo(Long id){
        repository.deleteById(id);
    }


    @Transactional
    public Atividade cadastrarAtividade(DadosCadastroAtividade dados){

        var turma = turmasRepository.findOneById(dados.id_turma());
        var professor = professorRepository.findOneById(dados.id_professor());
        var tipo = repository.findOneById(dados.id_tipo_atividade());

        return atividadeRepository.save(new Atividade(dados, professor, turma, tipo));
    }

    @Transactional
    public Atividade atualizarAtividade(Long id, DadosAtualizarAtividade dados){
        var atividade = atividadeRepository.findOneById(id);

        if (atividade != null){
            var tipo = repository.findOneById(dados.id_tipo_atividade());
            atividade.atualizarAtividade(dados, tipo);
        }

        assert atividade != null;
        atividadeRepository.save(atividade);

        return atividade;
    }

    @Transactional
    public void deletarAtividade(Long id){
        atividadeRepository.deleteById(id);
    }

    public  ResponseEntity pegarAtividadePeloIdDaTurma(Long id){
        var atividade = atividadeRepository.findAllByTurmasId(id);

        return ResponseEntity.ok(atividade);
    }

    public  ResponseEntity pegarAtividadePeloIdDoProfessor(Long id){
        var atividade = atividadeRepository.findAllByProfessorId(id);

        return ResponseEntity.ok(atividade);
    }
}
