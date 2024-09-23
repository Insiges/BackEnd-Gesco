package com.api.gesco.model.endereco;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "cidade")
@Entity(name = "Cidade")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EnderecoEscola> enderecos;

    @ManyToOne
    @JoinColumn(name = "id_estado") // Define a chave estrangeira

    private Estado estado;

    public Cidade(String nome,  Estado estado){
        this.nome = nome;
        this.estado = estado;

    }
}
