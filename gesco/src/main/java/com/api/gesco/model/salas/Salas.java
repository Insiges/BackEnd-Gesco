package com.api.gesco.model.salas;

import com.api.gesco.domain.diploma.DadosAtualizarDiploma;
import com.api.gesco.domain.diploma.DadosCadastroDiploma;
import com.api.gesco.domain.salas.DadosCadastroSalas;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.professor.Professor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "salas")
@Entity(name = "Salas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Salas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_escola") // Define a chave estrangeira
    private Escola escola;

    public Salas(DadosCadastroSalas dados, Escola escola){

        this.nome = dados.nome();
        this.escola = escola;
    }

    public void atualizarSala(DadosCadastroSalas dados, Escola escola){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if (escola != null){
            this.escola = escola;
        }
    }
}
