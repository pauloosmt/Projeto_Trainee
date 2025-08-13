package com.br.emakers.apiProjeto.exceptions.entity;

public class EmprestimoDevolvido extends RuntimeException {
    public EmprestimoDevolvido() {
        super("Emprestimo já devolvido");
    }
}

