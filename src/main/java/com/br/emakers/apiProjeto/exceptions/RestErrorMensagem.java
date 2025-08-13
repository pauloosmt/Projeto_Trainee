package com.br.emakers.apiProjeto.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

public record RestErrorMensagem(
    HttpStatus status,
    String message,
    Date timestamp
) {
    public RestErrorMensagem(HttpStatus status, String message) {
        this(status, message, new Date());
    }
}
