package com.br.emakers.apiProjeto.data.entity;

import jakarta.persistence.*;


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

}
