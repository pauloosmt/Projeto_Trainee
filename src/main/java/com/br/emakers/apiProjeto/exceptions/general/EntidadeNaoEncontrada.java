package com.br.emakers.apiProjeto.exceptions.general;

public class EntidadeNaoEncontrada extends RuntimeException {
    public EntidadeNaoEncontrada(Long id) {
        super("Entidade não encontrada com o id: " +id);
    }
}
