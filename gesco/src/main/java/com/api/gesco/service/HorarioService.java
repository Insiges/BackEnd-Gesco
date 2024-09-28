package com.api.gesco.service;

import com.api.gesco.domain.diploma.DadosAtualizarDiploma;
import com.api.gesco.domain.diploma.DadosCadastroDiploma;
import com.api.gesco.domain.diploma.DadosDetalhamentoDiploma;
import com.api.gesco.domain.horario.DadosCadastroHorario;
import com.api.gesco.model.diploma.Diploma;
import com.api.gesco.model.horario.Horario;
import com.api.gesco.repository.diploma.DiplomaRepository;
import com.api.gesco.repository.horario.HorarioRepository;
import com.api.gesco.repository.professor.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository repository;


    @Transactional
    public Horario cadastrarHorario(DadosCadastroHorario dados){

        return repository.save(new Horario(dados));
    }

    public ResponseEntity listarTodosOsHorarios(){
        var horario = repository.findAll();
        return ResponseEntity.ok(horario);
    }

    public ResponseEntity pegarUmHorario(Long id){
        var horario = repository.findOneById(id);

        return  ResponseEntity.ok(horario);
    }

    @Transactional
    public ResponseEntity atualizarHorario(Long id,DadosCadastroHorario dados){
        var horario = repository.findOneById(id);

        if (horario != null){
            horario.atualizarHorario(dados);

            repository.save(horario);
        }

        return ResponseEntity.ok(horario);
    }

    @Transactional
    public void deletarHorario(Long id){
        repository.deleteById(id);
    }


}
