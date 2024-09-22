package com.api.gesco.service;

import com.api.gesco.domain.diploma.DadosCadastroDiploma;
import com.api.gesco.model.diploma.Diploma;
import com.api.gesco.repository.diploma.DiplomaRepository;
import com.api.gesco.repository.professor.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiplomaService {

    @Autowired
    private DiplomaRepository diplomaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional
    public ResponseEntity cadastrarDiploma(DadosCadastroDiploma dados){
        var professor = professorRepository.findOneById(dados.id_professor());
        System.out.println(dados);

        var diploma = diplomaRepository.save(new Diploma(dados, professor));

        return ResponseEntity.ok(diploma);
    }

}
