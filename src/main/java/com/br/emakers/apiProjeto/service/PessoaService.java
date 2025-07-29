package com.br.emakers.apiProjeto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.emakers.apiProjeto.data.dto.response.PessoaResponseDTO;
import com.br.emakers.apiProjeto.data.entity.Pessoa;
import com.br.emakers.apiProjeto.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaResponseDTO> getAllPessoas() {
       List<Pessoa> pessoas = pessoaRepository.findAll();
        
       // Converte cada objeto Pessoa da lista original em um PessoaResponseDTO, criando uma nova lista apenas com os dados que serão enviados na resposta da API
        return pessoas.stream().map(PessoaResponseDTO::new).collect(Collectors.toList()); 

    }


    public PessoaResponseDTO getPessoabyId(Long idCategory) {
        Pessoa pessoa = pessoaRepository.findById(idCategory).orElseThrow(()-> new RuntimeException("Pessoa não encontrada")); //Faz a busca pelo id e retorna uma mensagem caso não encontre

        return new PessoaResponseDTO(pessoa);
    }


}

