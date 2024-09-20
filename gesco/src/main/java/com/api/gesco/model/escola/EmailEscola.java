package com.api.gesco.model.escola;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Table(name = "emailescola")
@Entity(name = "EmailEscola")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class EmailEscola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_escola") // Define a chave estrangeira
    @JsonIgnore
    private Escola escola;

    public EmailEscola(String email, Escola escola) {
        this.email = email;
        this.escola = escola;
    }

    public void atualizarEmail(String email) {
        System.out.println("Atualizando o email para: " + email);
        if (email != null) {
            this.email = email;
        }
    }


}
