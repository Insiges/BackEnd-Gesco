package com.api.gesco.model.turma;

import java.time.Year;

import com.api.gesco.domain.turmas.DadosCadastradosTurmas;
import com.api.gesco.model.escola.Escola;

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

@Table(name = "turmas")
@Entity(name = "Turmas")
@Getter
@Setter
@NoArgsConstructor
public class Turmas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;
    private Year ano;

    @ManyToOne
    @JoinColumn(name = "id_escola", nullable = false)
    private Escola escola;

    public Turmas(DadosCadastradosTurmas dados, Escola escola) {
        this.nome = dados.nome();
        this.ano = dados.ano();
        this.escola = escola;
    }   
}
