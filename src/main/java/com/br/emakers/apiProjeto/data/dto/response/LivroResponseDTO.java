package com.br.emakers.apiProjeto.data.dto.response;

import java.time.LocalDate;

import com.br.emakers.apiProjeto.data.entity.Livro;

public record LivroResponseDTO(
    Long id,
    String nome,
    String autor,
    LocalDate data_lancamento

) {
    public LivroResponseDTO(Livro livro) {
        this(livro.getIdLivro(), livro.getNome(), livro.getAutor(), livro.getData_lancamento());
    }
}
