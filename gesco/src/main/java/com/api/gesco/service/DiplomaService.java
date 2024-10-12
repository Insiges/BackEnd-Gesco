package com.api.gesco.service;

import com.api.gesco.domain.diploma.DadosAtualizarDiploma;
import com.api.gesco.domain.diploma.DadosCadastroDiploma;
import com.api.gesco.domain.diploma.DadosDetalhamentoDiploma;
import com.api.gesco.model.diploma.Diploma;
import com.api.gesco.repository.diploma.DiplomaRepository;
import com.api.gesco.repository.graduacao.TipoGraduacaoRepository;
import com.api.gesco.repository.professor.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiplomaService {

    @Autowired
    private DiplomaRepository diplomaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private TipoGraduacaoRepository graduacaoRepository;

    @Transactional
    public Diploma cadastrarDiploma(DadosCadastroDiploma dados){
        var professor = professorRepository.findOneById(dados.id_professor());
        var graduacao = graduacaoRepository.findOneById(dados.id_tipo_graduacao());

        var diploma = diplomaRepository.save(new Diploma(dados, professor, graduacao));

        return diploma;
    }

    public List<DadosDetalhamentoDiploma> listarDiplomasDeUmProfessor( Long id){
        var diploma = diplomaRepository.findDiplomasByProfessor(id);

        return diploma;
    }

    public Page<DadosDetalhamentoDiploma> listarTodosOsDiplomas(Pageable page){
        var diploma = diplomaRepository.findAllDiplomas(page);

        return diploma;
    }

    public ResponseEntity pegarUmDiploma(Long id){
        var diploma = diplomaRepository.findOneById(id);

        return  ResponseEntity.ok(diploma);
    }

    @Transactional
    public ResponseEntity atualizarDiploma(Long id,DadosAtualizarDiploma dados){
        var diploma = diplomaRepository.findOneById(id);
        var graduacao = graduacaoRepository.findOneById(dados.id_tipo_graduacao());

        if (diploma != null){
            diploma.atualizarDiploma(dados, graduacao);

            diplomaRepository.save(diploma);
        }

        return ResponseEntity.ok(diploma);

    }

    @Transactional
    public void deletarDiploma(Long id){
        diplomaRepository.deleteById(id);
    }

    public Diploma cadastrarDiplomaPeloProfessor(DadosAtualizarDiploma dados, Long id){
        var professor = professorRepository.findOneById(id);
        var graduacao = graduacaoRepository.findOneById(dados.id_tipo_graduacao());

        var diploma = diplomaRepository.save(new Diploma(dados, professor, graduacao));

        return diploma;
    }


}
