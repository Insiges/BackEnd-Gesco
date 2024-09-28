package com.api.gesco.model.semana;

import com.api.gesco.domain.horario.DadosCadastroHorario;
import com.api.gesco.domain.semana.DadosCadastroSemana;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Table(name = "semana")
@Entity(name = "Semana")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Semana {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dia;

    public Semana(DadosCadastroSemana dados){

        this.dia = dados.dia();

    }

    public void atualizarSemana(DadosCadastroSemana dados){

        if (dados.dia() != null){
            this.dia = dados.dia();
        }
    }
}
