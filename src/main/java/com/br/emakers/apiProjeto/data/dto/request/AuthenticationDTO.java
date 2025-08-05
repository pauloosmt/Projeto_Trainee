package com.br.emakers.apiProjeto.data.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
    @NotBlank(message = "Email obrigatório")
    @Email(message = "Email inválido")
    String email,

    @NotBlank(message = "Senha obrigatória")
    String senha
) { }
