package com.api.gesco.domain.autenticacao.escola;

public class DadosLoginEscola {
    private String token;

    public DadosLoginEscola(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    
}
