package com.br.emakers.apiProjeto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.emakers.apiProjeto.data.dto.request.PessoaRequestDTO;
import com.br.emakers.apiProjeto.data.dto.response.PessoaResponseDTO;
import com.br.emakers.apiProjeto.data.entity.Pessoa;
import com.br.emakers.apiProjeto.feign.PessoaFeign;
import com.br.emakers.apiProjeto.repository.PessoaRepository;

import com.br.emakers.apiProjeto.exceptions.general.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaService {

    @Autowired
    private PessoaFeign pessoaFeign;

    @Autowired
    private PessoaRepository pessoaRepository;

    

    public List<PessoaResponseDTO> getAllPessoas() {
       List<Pessoa> pessoas = pessoaRepository.findAll();
        
       // Converte cada objeto Pessoa da lista original em um PessoaResponseDTO, criando uma nova lista apenas com os dados que serão enviados na resposta da API
        return pessoas.stream().map(PessoaResponseDTO::new).collect(Collectors.toList()); 

    }


    public Pessoa getPessoabyId(Long idPessoa) {
    

        return pessoaRepository.findById(idPessoa).orElseThrow(() -> new EntityNotFoundException(idPessoa));
    }


    public PessoaResponseDTO createPessoa(PessoaRequestDTO pessoaRequestDTO, String senha) {

        PessoaResponseDTO endereco = pessoaFeign.buscaEnderecoCEP(pessoaRequestDTO.cep()); //Pegando o endereço através do cep, utilizando API externa
        Pessoa pessoa = new Pessoa(pessoaRequestDTO, senha);

        pessoa.setLogradouro(endereco.logradouro());
        pessoa.setBairro(endereco.bairro());
        pessoa.setLocalidade(endereco.localidade());
        pessoa.setUf(endereco.uf());

        pessoaRepository.save(pessoa);

        return new PessoaResponseDTO(pessoa);
    }

    public PessoaResponseDTO updatePessoa(Long idPessoa, PessoaRequestDTO pessoaRequestDTO) {
        Pessoa pessoa = getPessoaEntityById(idPessoa);
        if(pessoaRequestDTO.nome() != null) {
            pessoa.setNome(pessoaRequestDTO.nome());
        }
        
        if(pessoaRequestDTO.cep() != null) {
            PessoaResponseDTO endereco = pessoaFeign.buscaEnderecoCEP(pessoaRequestDTO.cep());
            pessoa.setCep(pessoaRequestDTO.cep());
            pessoa.setLogradouro(endereco.logradouro());
            pessoa.setBairro(endereco.bairro());
            pessoa.setLocalidade(endereco.localidade());
            pessoa.setUf(endereco.uf());
        }

        if(pessoaRequestDTO.cpf() != null) {
            pessoa.setCpf(pessoaRequestDTO.cpf());
        }

        if(pessoaRequestDTO.email() != null) {
            pessoa.setEmail(pessoaRequestDTO.email());
        }

        if(pessoaRequestDTO.senha() != null) {
            String encryptedSenha = new BCryptPasswordEncoder().encode(pessoaRequestDTO.senha());
            pessoa.setSenha(encryptedSenha);
        }

        pessoaRepository.save(pessoa);

        return new PessoaResponseDTO(pessoa);

    }

    public String deletePessoa(Long idPessoa) {
        Pessoa pessoa = getPessoaEntityById(idPessoa);
        pessoaRepository.delete(pessoa);

        return "A pessoa: '" + pessoa.getNome() + "' foi deletada!";  
    }

    private Pessoa getPessoaEntityById(Long idPessoa) {

        return pessoaRepository.findById(idPessoa).orElseThrow(()-> new RuntimeException("Pessoa não encontrada!"));  //Faz a busca pelo id e retorna uma mensagem caso não encontre
    }

    

}


