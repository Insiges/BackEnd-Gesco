package com.api.gesco.model.horario;

import com.api.gesco.domain.diploma.DadosAtualizarDiploma;
import com.api.gesco.domain.diploma.DadosCadastroDiploma;
import com.api.gesco.domain.horario.DadosCadastroHorario;
import com.api.gesco.model.professor.Professor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Table(name = "horario")
@Entity(name = "Horario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Time hora;

    public Horario(DadosCadastroHorario dados){

        this.hora = dados.hora();

    }

    public void atualizarHorario(DadosCadastroHorario dados){

        if (dados.hora() != null){
            this.hora = dados.hora();
        }
    }
}
