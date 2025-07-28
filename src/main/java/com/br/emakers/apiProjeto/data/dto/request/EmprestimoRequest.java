package com.br.emakers.apiProjeto.data.dto.request;

import jakarta.validation.constraints.NotBlank;

public record EmprestimoRequest(

    @NotBlank(message = "O nome do livro é obrigatório")
    String nome_livro

) {

}
