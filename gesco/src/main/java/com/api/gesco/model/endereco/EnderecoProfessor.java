package com.api.gesco.model.endereco;

import com.api.gesco.domain.endereco.DadosEndereco;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.professor.Professor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "enderecoprofessor")
@Entity(name = "EnderecoProfessor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class EnderecoProfessor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "id_cidade") // Define a chave estrangeira
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "id_professor") // Define a chave estrangeira
    @JsonIgnore
    private Professor professor;

    public EnderecoProfessor(DadosEndereco dados, Professor professor, Cidade cidade) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.professor = professor;
        this.cidade = cidade;
    }

    public void atualizarEndereco(EnderecoProfessor dados){
        if (dados.logradouro != null){
            this.logradouro = dados.logradouro;
        }
        if (dados.bairro != null){
            this.bairro = dados.bairro;
        }
        if (dados.cep != null){
            this.cep = dados.cep;
        }
        if (dados.numero != null){
            this.numero = dados.numero;
        }
        if (dados.complemento != null){
            this.complemento = dados.complemento;
        }
        if (dados.cidade != null){
            this.cidade = dados.cidade;
        }
    }
}