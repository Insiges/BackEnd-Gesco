package com.api.gesco.model.evento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

import com.api.gesco.domain.evento.DadosCadastradosEvento;
import com.api.gesco.model.escola.Escola;

@Table(name = "evento")
@Entity(name = "Evento")
@Getter
@Setter
@NoArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dia;
    private LocalTime horario;

    @ManyToOne
    @JoinColumn(name = "id_escola", nullable = false)
    private Escola escola;

    public Evento(DadosCadastradosEvento dados, Escola escola) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.dia = dados.dia();
        this.horario = dados.horario();
        this.escola = escola;
    }
 
}
