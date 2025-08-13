package com.br.emakers.apiProjeto.exceptions.entity;

public class EmailCadastradoException extends RuntimeException {
    public EmailCadastradoException(String message) {
        super("Erro, o email ja está cadastrado!");
    }
}