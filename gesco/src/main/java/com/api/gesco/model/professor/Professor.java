package com.api.gesco.model.professor;


import com.api.gesco.domain.professor.DadosCadastroProfessor;
import com.api.gesco.model.endereco.EnderecoProfessor;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.sexo.Sexo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "professor")
@Entity(name = "Professor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String foto;


    // Relacionamento com TelefoneEscola
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<TelefoneProfessor> telefones;

    // Relacionamento com EmailEscola
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<EmailProfessor> emails;

    // Relacionamento com EmailEscola
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<EnderecoProfessor> enderecos;


    @ManyToOne
    @JoinColumn(name = "id_escola") // Define a chave estrangeira
    @JsonIgnore
    private Escola escola;

    @ManyToOne
    @JoinColumn(name = "id_sexo") // Define a chave estrangeira
    @JsonIgnore
    private Sexo sexo;

    public Professor(DadosCadastroProfessor dados, Escola escola, Sexo sexo){
        this.cpf = dados.cpf();
        this.dataNascimento = dados.datanascimento();
        this.foto = dados.foto();
        this.nome = dados.nome();
        this.escola = escola;
        this.sexo = sexo;
    }

}
