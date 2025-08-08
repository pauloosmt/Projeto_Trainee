package com.br.emakers.apiProjeto.exceptions.general;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id) {
        super("Entidade não encontrada com o id: " +id);
    }
}
