package com.api.gesco.model.atividade;

import com.api.gesco.domain.atividade.DadosCadastroTipoAtividade;
import com.api.gesco.domain.disciplina.DadosCadastroDisciplina;
import com.api.gesco.model.disciplina_professor.DisciplinaProfesor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "tipo_atividade")
@Entity(name = "Tipo_Atividade")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tipo_Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "tipo_atividade", cascade = CascadeType.ALL)
    private List<Atividade> atividades;

    public Tipo_Atividade(DadosCadastroTipoAtividade dados){
        this.nome = dados.nome();
    }

    public void atualizarTipoAtividade(DadosCadastroTipoAtividade dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
    }
}
