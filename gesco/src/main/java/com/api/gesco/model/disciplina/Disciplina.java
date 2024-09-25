package com.api.gesco.model.disciplina;

import com.api.gesco.domain.disciplina.DadosCadastroDisciplina;
import com.api.gesco.domain.salas.DadosCadastroSalas;
import com.api.gesco.model.escola.Escola;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "disciplina")
@Entity(name = "Disciplina")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Disciplina(DadosCadastroDisciplina dados){

        this.nome = dados.nome();
    }

    public void atualizarDisciplina(DadosCadastroDisciplina dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
    }
}
