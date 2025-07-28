package com.br.emakers.apiProjeto.data.entity;

import jakarta.persistence.*;
import lombok.Builder;


@Entity
@IdClass(EmprestimoId.class)
@Table(name = "emprestimo")

public class Emprestimo {
    @Id
    @ManyToOne
    @JoinColumn(name = "idLivro")
    private Livro livro;

    @Id
    @ManyToOne
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;


    @Builder
    public Emprestimo(Livro livro, Pessoa pessoa) {
        this.livro = livro;
        this.pessoa = pessoa;
    }
}
