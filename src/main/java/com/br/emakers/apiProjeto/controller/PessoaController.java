package com.br.emakers.apiProjeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.emakers.apiProjeto.data.dto.request.PessoaRequestDTO;
import com.br.emakers.apiProjeto.data.dto.response.PessoaResponseDTO;
import com.br.emakers.apiProjeto.data.entity.Pessoa;
import com.br.emakers.apiProjeto.repository.PessoaRepository;
import com.br.emakers.apiProjeto.service.PessoaService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/pessoa")

public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<PessoaResponseDTO>> getAllPessoas() {
        // Retorna a resposta HTTP com:
        // - status 200 (OK), indicando que deu tudo certo
        // - o corpo da resposta será a lista de pessoas (em formato JSON)
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getAllPessoas());
    }

    @GetMapping(value = "/{idPessoa}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long idPessoa)  { //PathVariable serve para indicar que o idPessoa que esta sendo passado como parametro é o mesmo do GetMapping
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getPessoabyId(idPessoa));

    }

    @PostMapping(value = "/create")
    public ResponseEntity<PessoaResponseDTO> createPessoa(@Valid @RequestBody PessoaRequestDTO pessoaRequestDTO) { //RequestBody serve para informar que o parametro foi informado no corpo da requisição
        
        if(this.pessoaRepository.findByEmail(pessoaRequestDTO.email()) != null) {
            return ResponseEntity.badRequest().build(); 
        }

        String encryptedSenha = new BCryptPasswordEncoder().encode(pessoaRequestDTO.senha());
        
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.createPessoa(pessoaRequestDTO, encryptedSenha));
    }

    @PutMapping(value = "/update/{idPessoa}")
    public ResponseEntity<PessoaResponseDTO> updatePessoa(@PathVariable Long idPessoa , @RequestBody PessoaRequestDTO pessoaRequestDTO) {
         return ResponseEntity.status(HttpStatus.OK).body(pessoaService.updatePessoa(idPessoa, pessoaRequestDTO));
    }

    @DeleteMapping(value = "/delete/{idPessoa}")
    public ResponseEntity<String> deletePessoa(@PathVariable Long idPessoa) {
        String msg = pessoaService.deletePessoa(idPessoa);
        return ResponseEntity.ok(msg);
    }
}
