package com.api.gesco.service;

import com.api.gesco.domain.disciplina.DadosDisciplina;
import com.api.gesco.domain.disciplina_professor.DadosCadastroDisciplinaProfessor;
import com.api.gesco.domain.reserva_sala.DadosRetornoReservaSala;
import com.api.gesco.domain.salas.DadosCadastroReservaSala;
import com.api.gesco.model.disciplina_professor.DisciplinaProfesor;
import com.api.gesco.model.reserva_sala.ReservaSala;
import com.api.gesco.repository.disciplina.DisciplinaRepository;
import com.api.gesco.repository.disciplina_professor.Disciplina_ProfessorRepository;
import com.api.gesco.repository.professor.ProfessorRepository;
import com.api.gesco.repository.reserva_sala.ReservaSalaRepository;
import com.api.gesco.repository.salas.SalasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ReservaSalaService {

    @Autowired
    private SalasRepository salasRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ReservaSalaRepository reservaSalaRepository;


    @Transactional
    public ReservaSala cadastrarReservaSala(DadosCadastroReservaSala dados){
        var sala = salasRepository.findOneByNome(dados.sala());
        var professor = professorRepository.findOneById(dados.id_professor());
        var escola = professor.getEscola();

        if (sala != null){

            var reservaSala = reservaSalaRepository.save(new ReservaSala(dados, sala, professor, escola));

            return reservaSala;
        }

        throw new RuntimeException("Erro: Não foi possível encontrar um professor ou disciplina com estes dados.");
    }

    public ResponseEntity<List<ReservaSala>> pegarTodasAsReservasDeUmaSala(Long id){
        var reserva = reservaSalaRepository.findAllBySalaId(id);

        return ResponseEntity.ok(reserva);
    }

    public ResponseEntity pegarTodasAsReservasDeUmaSalaPeloNome(String nome){
        var reserva = reservaSalaRepository.findAllBySalaNome(nome);
        var retorno = reserva.stream().map(DadosRetornoReservaSala::new).toList();

        return ResponseEntity.ok(retorno);
    }

    public ResponseEntity atualizarReservaSala(Long id, DadosCadastroReservaSala dados){
        var reservaSala = reservaSalaRepository.findOneById(id);
        var sala = salasRepository.findOneByNome(dados.sala());
        var professor = professorRepository.findOneById(dados.id_professor());

        if (reservaSala != null){
            reservaSala.atualizarReservaSala(dados, sala, professor);

            reservaSalaRepository.save(reservaSala);
        }

        return  ResponseEntity.ok(reservaSala);

    }

    @Transactional
    public void deletarReservaSala(Long id){
        reservaSalaRepository.deleteById(id);
    }

}
