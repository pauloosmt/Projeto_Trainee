package com.br.emakers.apiProjeto.data.dto.response;

import com.br.emakers.apiProjeto.data.entity.Pessoa;




public record PessoaResponseDTO(
    Long id,
    String nome,
    String cpf,
    String cep,
    String email,
    String senha,
    String logradouro,
    String bairro,
    String localidade,
    String uf

){
    public PessoaResponseDTO(Pessoa pessoa) {
        this(pessoa.getIdPessoa(), pessoa.getNome(), pessoa.getCpf(), pessoa.getCep(),pessoa.getEmail(),pessoa.getSenha(), pessoa.getLogradouro(), pessoa.getBairro(), pessoa.getLocalidade(), pessoa.getUf());
    }
}
