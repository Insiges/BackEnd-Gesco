package com.api.gesco.model.diploma;

import com.api.gesco.domain.diploma.DadosAtualizarDiploma;
import com.api.gesco.domain.diploma.DadosCadastroDiploma;
import com.api.gesco.model.graduacao.Tipo_Graduacao;
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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_professor") // Define a chave estrangeira
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "id_tipo_graduacao") // Define a chave estrangeira
    private Tipo_Graduacao tipoGraduacao;


    public Diploma(DadosCadastroDiploma dados, Professor professor, Tipo_Graduacao tipoGraduacao){

        this.faculdade = dados.faculdade();
        this.curso = dados.curso();
        this.professor = professor;
        this.tipoGraduacao = tipoGraduacao;
    }

    public Diploma(DadosAtualizarDiploma dados, Professor professor, Tipo_Graduacao tipoGraduacao){

        this.tipoGraduacao = tipoGraduacao;
        this.faculdade = dados.faculdade();
        this.curso = dados.curso();
        this.professor = professor;
    }

    public void atualizarDiploma(DadosAtualizarDiploma dados, Tipo_Graduacao tipoGraduacao){
        if(dados.faculdade() != null){
            this.faculdade = dados.faculdade();
        }
        if(dados.curso() != null){
            this.curso = dados.curso();
        }

        if (tipoGraduacao != null){
            this.tipoGraduacao = tipoGraduacao;
        }
    }
}
