package com.br.emakers.apiProjeto.data.entity;


import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;



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

    public Long getId() {
        return id;
    }
    public Livro getLivro() { 
        return livro; 
    }
    public Pessoa getPessoa() { 
        return pessoa; 
    }
    public LocalDate getDataEmprestimo() { 
        return dataEmprestimo; 
    }

    @Builder
    public Emprestimo(Livro livro, Pessoa pessoa, LocalDate dataEmprestimo) {
        this.livro = livro;
        this.pessoa = pessoa;
        this.dataEmprestimo = dataEmprestimo;
    }
}
