package com.api.gesco.model.graduacao;

import com.api.gesco.domain.graduacao.DadosCadastroTipoGraduacao;
import com.api.gesco.domain.horario.DadosCadastroHorario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Table(name = "tipo_graduacao")
@Entity(name = "Tipo_Graduacao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tipo_Graduacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Tipo_Graduacao(DadosCadastroTipoGraduacao dados){

        this.nome = dados.nome();

    }

    public void atualizarTipoGraduacao(DadosCadastroTipoGraduacao dados){

        if (dados.nome() != null){
            this.nome = dados.nome();
        }
    }
}
