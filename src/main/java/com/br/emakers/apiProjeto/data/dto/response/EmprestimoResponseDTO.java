package com.br.emakers.apiProjeto.data.dto.response;

import java.time.LocalDate;

import com.br.emakers.apiProjeto.data.entity.Emprestimo;

public record EmprestimoResponseDTO(
    Long id,
    String nome_livro,
    String nomePessoa,
    LocalDate dataEmprestimo
) {
    public EmprestimoResponseDTO(Emprestimo emprestimo) {
        this(
            emprestimo.getId(),
            emprestimo.getLivro().getNome(),
            emprestimo.getPessoa().getNome(),
            emprestimo.getDataEmprestimo()
        );
    }
}

