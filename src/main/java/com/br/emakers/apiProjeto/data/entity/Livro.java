package com.br.emakers.apiProjeto.data.entity;

import java.time.LocalDate;

import com.br.emakers.apiProjeto.data.dto.request.LivroRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "livro")

public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "autor", nullable = false, length = 100)
    private String autor;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDate data_lancamento;

    @Column (name = "livro_disponivel")
    private Boolean livro_disponivel = true;


    @Builder
    public Livro(LivroRequestDTO livroRequestDTO) {
        this.nome = livroRequestDTO.nome();
        this.autor = livroRequestDTO.autor();
        this.data_lancamento = livroRequestDTO.data_lancamento();
    }
}
