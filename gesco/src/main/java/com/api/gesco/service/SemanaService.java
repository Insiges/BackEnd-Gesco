package com.api.gesco.service;

import com.api.gesco.domain.horario.DadosCadastroHorario;
import com.api.gesco.domain.semana.DadosCadastroSemana;
import com.api.gesco.model.horario.Horario;
import com.api.gesco.model.semana.Semana;
import com.api.gesco.repository.horario.HorarioRepository;
import com.api.gesco.repository.semana.SemanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SemanaService {

    @Autowired
    private SemanaRepository repository;

    @Transactional
    public Semana cadastrarSemana(DadosCadastroSemana dados){

        return repository.save(new Semana(dados));
    }

    public ResponseEntity listarTodosOsDias(){
        var semana = repository.findAll();
        return ResponseEntity.ok(semana);
    }

    public ResponseEntity pegarUmDia(Long id){
        var semana = repository.findOneById(id);

        return  ResponseEntity.ok(semana);
    }

    @Transactional
    public ResponseEntity atualizarSemana(Long id,DadosCadastroSemana dados){
        var horario = repository.findOneById(id);

        if (horario != null){
            horario.atualizarSemana(dados);

            repository.save(horario);
        }

        return ResponseEntity.ok(horario);
    }

    @Transactional
    public void deletarSemana(Long id){
        repository.deleteById(id);
    }

}
