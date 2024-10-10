package com.api.gesco.service;

import com.api.gesco.domain.diploma.DadosAtualizarDiploma;
import com.api.gesco.domain.diploma.DadosCadastroDiploma;
import com.api.gesco.domain.diploma.DadosDetalhamentoDiploma;
import com.api.gesco.model.diploma.Diploma;
import com.api.gesco.repository.diploma.DiplomaRepository;
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

    @Transactional
    public Diploma cadastrarDiploma(DadosCadastroDiploma dados){
        var professor = professorRepository.findOneById(dados.id_professor());
        System.out.println(dados);

        var diploma = diplomaRepository.save(new Diploma(dados, professor));

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

        if (diploma != null){
            diploma.atualizarDiploma(dados);

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
        System.out.println(dados);

        var diploma = diplomaRepository.save(new Diploma(dados, professor));

        return diploma;
    }


}
