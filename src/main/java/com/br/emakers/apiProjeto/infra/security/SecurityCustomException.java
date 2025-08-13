package com.br.emakers.apiProjeto.infra.security;


public class SecurityCustomException extends RuntimeException {
    public SecurityCustomException(String message) {
        super(message);
    }
}

