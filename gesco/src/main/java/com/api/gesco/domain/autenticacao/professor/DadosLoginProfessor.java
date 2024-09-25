package com.api.gesco.domain.autenticacao.professor;

public class DadosLoginProfessor {
    private String token;

    public DadosLoginProfessor(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
