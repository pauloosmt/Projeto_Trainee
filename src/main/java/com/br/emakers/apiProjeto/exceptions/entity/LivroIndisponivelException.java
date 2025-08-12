package com.br.emakers.apiProjeto.exceptions.entity;


public class LivroIndisponivelException extends RuntimeException {
    public LivroIndisponivelException() {
        super("Livro Indisponivel");
    }
}

