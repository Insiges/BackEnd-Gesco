package com.api.gesco.model.escola;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "emailescola")
@Entity(name = "EmailEscola")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class EmailEscola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private Long id_escola;

    public EmailEscola(String email, Long id) {
        this.email = email;
        this.id_escola = id;
    }
}
