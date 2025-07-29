package com.br.emakers.apiProjeto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.emakers.apiProjeto.data.dto.request.PessoaRequestDTO;
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


    public PessoaResponseDTO getPessoabyId(Long idPessoa) {
        Pessoa pessoa =getPessoaEntityById(idPessoa);

        return new PessoaResponseDTO(pessoa);
    }


    public PessoaResponseDTO createPessoa(PessoaRequestDTO pessoaRequestDTO) {
        Pessoa pessoa = new Pessoa(pessoaRequestDTO);
        pessoaRepository.save(pessoa);

        return new PessoaResponseDTO(pessoa);
    }

    public PessoaResponseDTO updatePessoa(Long idPessoa, PessoaRequestDTO pessoaRequestDTO) {
        Pessoa pessoa = getPessoaEntityById(idPessoa);
        if(pessoaRequestDTO.nome() != null) {
            pessoa.setNome(pessoaRequestDTO.nome());
        }
        
        if(pessoaRequestDTO.cep() != null) {
            pessoa.setCep(pessoaRequestDTO.cep());
        }

        if(pessoaRequestDTO.cpf() != null) {
            pessoa.setCpf(pessoaRequestDTO.cpf());
        }

        if(pessoaRequestDTO.email() != null) {
            pessoa.setEmail(pessoaRequestDTO.email());
        }

        if(pessoaRequestDTO.senha() != null) {
            pessoa.setSenha(pessoaRequestDTO.senha());
        }

        pessoaRepository.save(pessoa);

        return new PessoaResponseDTO(pessoa);

    }

    public String deletePessoa(Long idPessoa, PessoaRequestDTO pessoaRequestDTO) {
        Pessoa pessoa = getPessoaEntityById(idPessoa);
        pessoaRepository.delete(pessoa);

        return "A pessoa: '" + pessoa.getNome() + "' foi deletada!";  
    }

    private Pessoa getPessoaEntityById(Long idPessoa) {

        return pessoaRepository.findById(idPessoa).orElseThrow(()-> new RuntimeException("Pessoa não encontrada"));  //Faz a busca pelo id e retorna uma mensagem caso não encontre
    }

}


