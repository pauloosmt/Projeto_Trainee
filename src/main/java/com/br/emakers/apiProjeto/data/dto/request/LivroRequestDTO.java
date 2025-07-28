package com.br.emakers.apiProjeto.data.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record LivroRequestDTO( 

    @NotBlank(message = "Nome é obrigatório")
    String nome,

    @NotBlank(message = "Autor é obrigatório")
    String autor,


    @NotNull(message = "Data é obrigatória")
    @PastOrPresent(message = "A data deve ser válida")
    LocalDate data_lancamento


)
{
 }
