package com.api.gesco.repository.logins;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.gesco.model.logins.LoginAluno;

public interface LoginAlunoRepository extends JpaRepository<LoginAluno, Long> {
    LoginAluno findByEmail(String email);
}
