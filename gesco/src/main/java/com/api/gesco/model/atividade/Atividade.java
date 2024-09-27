package com.api.gesco.model.atividade;

import com.api.gesco.domain.atividade.DadosAtualizarAtividade;
import com.api.gesco.domain.atividade.DadosCadastroAtividade;
import com.api.gesco.domain.atividade.DadosCadastroTipoAtividade;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.professor.Professor;
import com.api.gesco.model.turmas.Turmas;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "atividade")
@Entity(name = "Atividade")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String data_atividade;
    private Float valor;


    @ManyToOne
    @JoinColumn(name = "id_professor") // Define a chave estrangeira
    @JsonIgnore
    private Professor professor;


    @ManyToOne
    @JoinColumn(name = "id_turma") // Define a chave estrangeira
    @JsonIgnore
    private Turmas turmas;

    @ManyToOne
    @JoinColumn(name = "id_tipo_atividade") // Define a chave estrangeira
    @JsonIgnore
    private Tipo_Atividade tipo_atividade;


    public Atividade(DadosCadastroAtividade dados, Professor professor, Turmas turma, Tipo_Atividade tipo_atividade){

        this.valor = dados.valor();
        this.nome = dados.nome();
        this.data_atividade = dados.data_atividade();
        this.descricao = dados.descricao();
        this.professor = professor;
        this.turmas = turma;
        this.tipo_atividade = tipo_atividade;
    }

    public void atualizarAtividade(DadosAtualizarAtividade dados, Tipo_Atividade tipo){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }

        if(dados.valor() != null){
            this.valor = dados.valor();
        }

        if(dados.descricao() != null){
            this.descricao = dados.descricao();
        }

        if(dados.data_atividade() != null){
            this.data_atividade = dados.data_atividade();
        }
        this.tipo_atividade = tipo;
    }
}
