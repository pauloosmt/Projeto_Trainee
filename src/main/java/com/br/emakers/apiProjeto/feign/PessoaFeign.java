package com.br.emakers.apiProjeto.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.br.emakers.apiProjeto.data.dto.response.PessoaResponseDTO;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viaCep")
public interface PessoaFeign {

    @GetMapping("/{cep}/json")
    PessoaResponseDTO buscaEnderecoCEP(@PathVariable("cep") String cep);

}