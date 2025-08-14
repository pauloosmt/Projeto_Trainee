package com.br.emakers.apiProjeto.data.dto.request;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PessoaRequestDTO(

    @NotBlank(message = "Nome é obrigatório")
    String nome,

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "CPF inválido")
    String cpf,

    
    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "\\d{8}", message = "CEP inválido") //"d{5}-?\\d{3}" serve para aceitar cep com ou sem o traço("12345-000" "12345000")
    String cep,

    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    String email,

    @NotBlank(message = "Senha obrigatoria")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    String senha
) {
     public PessoaRequestDTO {
        // remove tudo que não é número do CPF
        if (cpf != null) {
            cpf = cpf.replaceAll("\\D", "");
        }
        
        if(cep != null) {
            cep = cep.replaceAll("\\D", "");
        }
    }


}
