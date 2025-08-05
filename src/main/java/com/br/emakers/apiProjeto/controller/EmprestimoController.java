package com.br.emakers.apiProjeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.emakers.apiProjeto.data.dto.request.EmprestimoRequestDTO;
import com.br.emakers.apiProjeto.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")

public class EmprestimoController {
    @Autowired
    private EmprestimoService emprestimoService;


    @PostMapping("/create")
    
    public ResponseEntity<?> criarEmprestimos(@RequestBody EmprestimoRequestDTO emprestimoRequestDTO) {
        //Vai pegar o email da pessoa
        String emailPessoa = SecurityContextHolder.getContext().getAuthentication().getName();
        
        //Criando um emprestimo para o usuario logado
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(emprestimoService.emprestarLivroParaUsuarioLogado(emprestimoRequestDTO, emailPessoa));

    }


}
