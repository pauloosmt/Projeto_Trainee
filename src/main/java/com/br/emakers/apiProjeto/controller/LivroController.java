package com.br.emakers.apiProjeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.emakers.apiProjeto.data.dto.request.LivroRequestDTO;
import com.br.emakers.apiProjeto.data.dto.response.LivroResponseDTO;
import com.br.emakers.apiProjeto.service.LivroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livro")
@Tag(name = "Livro", description = "Operações relacionadas aos livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Operation(summary = "Listar todos os livros", description = "Lista todos os livros")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listados todos os livros"),
        @ApiResponse(responseCode = "403", description =  "Usuario não autenticado")

    })
    //Buscar todos os Livros
    @GetMapping(value = "/all")
    public ResponseEntity<List<LivroResponseDTO>> getAllLivro() {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.getAllLivro());
    }

    @Operation(summary = "Fornecer um livro pelo seu ID", description = "Fornece o livro pelo seu ID ")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna o Livro pelo seu ID"),
        @ApiResponse(responseCode = "400", description = "Livro não encontrado"),
        @ApiResponse(responseCode = "403", description =  "Usuario não autenticado")
    })
    //Buscar Livro por id
    @GetMapping(value = "/{idLivro}")
    public ResponseEntity<LivroResponseDTO> getLivroById(@PathVariable  Long idLivro){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.getLivrobyId(idLivro));

    }

    @Operation(summary = "Cadastrar um livro", description = "Cria um livro no Banco de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Livro criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados Inválidos"),
        @ApiResponse(responseCode = "403", description =  "Usuario não autenticado"),
        @ApiResponse(responseCode = "409", description = "Dados ja existentes")
        
    })
    //Criar Livro
    @PostMapping(value = "/create")
    public ResponseEntity<LivroResponseDTO> createLivro(@Valid @RequestBody LivroRequestDTO livroRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.createLivro(livroRequestDTO));            

    }

    @Operation(summary = "Editar um livro ", description = "Edita o livro do ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Livro não encontrado"),
        @ApiResponse(responseCode = "403", description =  "Usuario não autenticado"),
        @ApiResponse(responseCode = "409", description = "Dados ja existentes")
        
    })
    //Atualizar Livro
    @PutMapping(value = "/update/{idLivro}")
    public ResponseEntity<LivroResponseDTO> updateLivro(@PathVariable Long idLivro, @Valid @RequestBody LivroRequestDTO livroRequestDTO ) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.updateLivro(idLivro, livroRequestDTO));
    }

    @Operation(summary = "Deletar um livro", description = "Deleta o livro do ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Livro deletado com sucesso"),
        @ApiResponse(responseCode = "400", description = "LIvro não encontrado"),
        @ApiResponse(responseCode = "403", description =  "Usuario não autenticado"),
        @ApiResponse(responseCode = "409", description = "Livro emprestado")
        
    })
    //Deletar Livro
    @DeleteMapping(value = "/delete/{idLivro}") 
    public ResponseEntity<String> deleteLivro(@PathVariable Long idLivro, @RequestBody LivroRequestDTO livroRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.deleteLivro(idLivro, livroRequestDTO));
    }


}
