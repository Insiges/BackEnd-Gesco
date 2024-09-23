package com.api.gesco.model.professor;

import com.api.gesco.model.escola.Escola;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "emailprofessor")
@Entity(name = "EmailProfessor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class EmailProfessor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_professor") // Define a chave estrangeira
    @JsonIgnore
    private Professor professor;

    public EmailProfessor(String email, Professor professor) {
        this.email = email;
        this.professor = professor;
    }

    public void atualizarEmail(String email) {
        if (email != null) {
            this.email = email;
        }
    }


}
