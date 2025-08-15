package com.br.emakers.apiProjeto.exceptions.entity;


public class LivroNaoEncontrado extends RuntimeException {
    public LivroNaoEncontrado() {
        super("Livro não encontrado");
    }
}

