package com.api.gesco.model.diploma;

import com.api.gesco.domain.diploma.DadosAtualizarDiploma;
import com.api.gesco.domain.diploma.DadosCadastroDiploma;
import com.api.gesco.model.professor.Professor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "diploma")
@Entity(name = "Diploma")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Diploma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String faculdade;
    private String curso;
    private String inicio;
    private String fim;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_professor") // Define a chave estrangeira
    private Professor professor;

    public Diploma(DadosCadastroDiploma dados, Professor professor){

        this.faculdade = dados.faculdade();
        this.fim = dados.fim();
        this.curso = dados.curso();
        this.inicio = dados.inicio();
        this.professor = professor;
    }

    public Diploma(DadosAtualizarDiploma dados, Professor professor){

        this.faculdade = dados.faculdade();
        this.fim = dados.fim();
        this.curso = dados.curso();
        this.inicio = dados.inicio();
        this.professor = professor;
    }

    public void atualizarDiploma(DadosAtualizarDiploma dados){
        if(dados.faculdade() != null){
            this.faculdade = dados.faculdade();
        }
        if(dados.fim() != null){
            this.fim = dados.fim();
        }
        if(dados.curso() != null){
            this.curso = dados.curso();
        }
        if(dados.inicio() != null){
            this.inicio = dados.inicio();
        }
    }
}
