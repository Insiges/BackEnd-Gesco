package com.api.gesco.repository.logins;

import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.escola.Escola;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.gesco.model.logins.LoginAluno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginAlunoRepository extends JpaRepository<LoginAluno, Long> {
    LoginAluno findByEmail(String email);

    @Query("SELECT aluno FROM LoginAluno WHERE email = :email")
    Aluno findOnlyAlunoIdByEmail(@Param("email") String email);
}
