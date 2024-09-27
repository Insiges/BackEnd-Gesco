package com.api.gesco.domain.autenticacao.aluno;

public class DadosLoginAluno {
    private String token;

    public DadosLoginAluno(String token){
        this.token = token;
    }
    
    public String getToken() {
        return token;
    }
}