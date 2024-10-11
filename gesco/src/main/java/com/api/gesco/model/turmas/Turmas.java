package com.api.gesco.model.turmas;

import java.time.Year;
import java.util.List;

import com.api.gesco.domain.turmas.DadosCadastradosTurmas;
import com.api.gesco.model.atividade.Atividade;
import com.api.gesco.model.disciplina_professor.DisciplinaProfesor;
import com.api.gesco.model.escola.Escola;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @JsonIgnore
    @JoinColumn(name = "id_escola", nullable = false)
    private Escola escola;

    @OneToMany(mappedBy = "turmas", cascade = CascadeType.ALL)
    private List<Atividade> atividade;

    public Turmas(DadosCadastradosTurmas dados, Escola escola) {
        this.nome = dados.nome();
        this.ano = dados.ano();
        this.escola = escola;
    }   
}
