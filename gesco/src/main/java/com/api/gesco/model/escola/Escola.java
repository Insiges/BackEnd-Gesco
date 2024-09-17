package com.api.gesco.model.escola;

import com.api.gesco.domain.escola.DadosCadastroEscola;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "escola")
@Entity(name = "Escola")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Escola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String imagem;

    public Escola(DadosCadastroEscola dados) {
        this.nome = dados.nome();
        this.imagem = dados.imagem();
    }
}
