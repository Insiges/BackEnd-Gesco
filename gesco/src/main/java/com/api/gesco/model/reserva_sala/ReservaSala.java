package com.api.gesco.model.reserva_sala;


import com.api.gesco.domain.salas.DadosCadastroReservaSala;
import com.api.gesco.model.disciplina.Disciplina;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.professor.Professor;
import com.api.gesco.model.salas.Salas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Table(name = "reserva_sala")
@Entity(name = "ReservaSala")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ReservaSala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dia;
    private String turno;

    @ManyToOne
    @JoinColumn(name = "id_sala") // Define a chave estrangeira
    private Salas sala;

    @ManyToOne
    @JoinColumn(name = "id_professor") // Define a chave estrangeira
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "id_escola") // Define a chave estrangeira
    private Escola escola;

    public ReservaSala(DadosCadastroReservaSala dados, Salas sala, Professor professor, Escola escola) {
        this.dia = dados.dia();
        this.turno = dados.turno();
        this.sala = sala;
        this.professor = professor;
        this.escola = escola;
    }

    public void atualizarReservaSala(DadosCadastroReservaSala dados, Salas sala, Professor professor){
        if (dados.dia() != null){
            this.dia = dados.dia();
        }
        if (dados.turno() != null){
            this.turno = dados.turno();
        }
        if (professor != null){
            this.professor = professor;
        }
        if(sala != null){
            this.sala = sala;
        }
    }

}
