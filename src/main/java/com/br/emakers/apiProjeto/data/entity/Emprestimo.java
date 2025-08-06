package com.br.emakers.apiProjeto.data.entity;


import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "emprestimo")

public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @ManyToOne
    @JoinColumn(name = "idLivro")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;

    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucao;


    @Builder
    public Emprestimo(Livro livro, Pessoa pessoa, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.livro = livro;
        this.pessoa = pessoa;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }
}
