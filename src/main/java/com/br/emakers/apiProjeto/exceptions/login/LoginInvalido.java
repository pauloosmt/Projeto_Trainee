package com.br.emakers.apiProjeto.exceptions.login;

public class LoginInvalido extends RuntimeException {
    public LoginInvalido() {
        super("Email ou senha inválidos!");
    }
}
