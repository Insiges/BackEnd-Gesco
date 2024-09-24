package com.api.gesco.model.sexo;

import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.endereco.Cidade;
import com.api.gesco.model.professor.Professor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "sexo")
@Entity(name = "Sexo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sigla;

    @OneToMany(mappedBy = "sexo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Professor> professores;

    @OneToMany(mappedBy = "sexo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Aluno> alunos;
}
