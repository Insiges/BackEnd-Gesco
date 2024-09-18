package com.api.gesco.model.endereco;

import com.api.gesco.domain.endereco.DadosEndereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "enderecoescola")
@Entity(name = "EnderecoEscola")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class EnderecoEscola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private Long id_cidade;
    private Long id_estado;
    private Long id_escola;

    public EnderecoEscola(DadosEndereco dados, Long escola, Long estado, Long cidade) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.id_escola = escola;
        this.id_cidade = cidade;
        this.id_estado = estado;
    }
}
