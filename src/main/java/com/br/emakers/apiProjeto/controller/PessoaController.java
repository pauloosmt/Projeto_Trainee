package com.br.emakers.apiProjeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/pessoa")
@Tag(name = "Pessoas", description = "Operações relacionadas a pessoas")
public class PessoaController {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @Operation(summary = "Listar todas as pessoas", description = "Lista todas as pessoas ")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Todas as pessoa cadastradas"),
        @ApiResponse(responseCode = "403", description =  "Usuario não autenticado"),
        
    })
    @GetMapping(value = "/all")
    public ResponseEntity<List<PessoaResponseDTO>> getAllPessoas() {
        // Retorna a resposta HTTP com:
        // - status 200 (OK), indicando que deu tudo certo
        // - o corpo da resposta será a lista de pessoas (em formato JSON)
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getAllPessoas());
    }

    @Operation(summary = "Fornecer uma pessoa pelo ID", description = "Lista uma pessoa pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna a pessoa do ID"),
        @ApiResponse(responseCode = "400", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "403", description =  "Usuario não autenticado"),
        
    })
    @GetMapping(value = "/{idPessoa}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long idPessoa)  { //PathVariable serve para indicar que o idPessoa que esta sendo passado como parametro é o mesmo do GetMapping
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getPessoabyId(idPessoa));

    }

    @Operation(summary = "Cadastrar uma nova pessoa", description = "Cria uma pessoa com nome, CPF, endereço, email e senha")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "403", description =  "Usuario não autenticado"),
        @ApiResponse(responseCode = "409", description = "Dados ja existentes")
        
    })
    @PostMapping(value = "/create")
    public ResponseEntity<PessoaResponseDTO> createPessoa(@Valid @RequestBody PessoaRequestDTO pessoaRequestDTO) { //RequestBody serve para informar que o parametro foi informado no corpo da requisição
        
        if(this.pessoaRepository.findByEmail(pessoaRequestDTO.email()) != null) {
            throw new DataIntegrityViolationException("Email já cadastrado");
        }

        String encryptedSenha = new BCryptPasswordEncoder().encode(pessoaRequestDTO.senha());
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.createPessoa(pessoaRequestDTO, encryptedSenha));
        
    }

    @Operation(summary = "Editar uma pessoa", description = "Edita a pessoa do ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Pessoa não encontrada"),
        @ApiResponse(responseCode = "403", description =  "Usuario não autenticado"),
        @ApiResponse(responseCode = "409", description = "Dados ja existentes")
        
    })
    @PutMapping(value = "/update/{idPessoa}")
    public ResponseEntity<PessoaResponseDTO> updatePessoa(@PathVariable Long idPessoa , @RequestBody PessoaRequestDTO pessoaRequestDTO) {
         return ResponseEntity.status(HttpStatus.OK).body(pessoaService.updatePessoa(idPessoa, pessoaRequestDTO));
    }

    @Operation(summary = "Deletar uma pessoa", description = "Deleta a pessoa do ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoa deletada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Pessoa não encontrado"),
        @ApiResponse(responseCode = "403", description =  "Usuario não autenticado"),
        @ApiResponse(responseCode = "409", description = "Pessoa com empréstimo pendente")
        
    })
    @DeleteMapping(value = "/delete/{idPessoa}")
    public ResponseEntity<String> deletePessoa(@PathVariable Long idPessoa) {
        String msg = pessoaService.deletePessoa(idPessoa);
        return ResponseEntity.ok(msg);
    }
}
