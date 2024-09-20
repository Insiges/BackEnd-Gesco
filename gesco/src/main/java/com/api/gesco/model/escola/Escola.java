package com.api.gesco.model.escola;

import com.api.gesco.domain.escola.DadosAtualizarEscola;
import com.api.gesco.domain.escola.DadosCadastroEscola;
import com.api.gesco.model.endereco.EnderecoEscola;
import com.api.gesco.model.professor.Professor;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    // Relacionamento com TelefoneEscola
    @OneToMany(mappedBy = "escola", cascade = CascadeType.ALL)
    private List<TelefoneEscola> telefones;

    // Relacionamento com EmailEscola
    @OneToMany(mappedBy = "escola", cascade = CascadeType.ALL)
    private List<EmailEscola> emails;

    // Relacionamento com EmailEscola
    @OneToMany(mappedBy = "escola", cascade = CascadeType.ALL)
    private List<EnderecoEscola> enderecos;

    @OneToMany(mappedBy = "escola", cascade = CascadeType.ALL)
    private List<Professor> professores;

    public Escola(DadosCadastroEscola dados) {
        this.nome = dados.nome();
        this.imagem = dados.imagem();
    }

    public void atualizarEscola(DadosAtualizarEscola dados){
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.imagem() != null){
            this.imagem = dados.imagem();
        }
    }
}
