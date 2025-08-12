package com.br.emakers.apiProjeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.emakers.apiProjeto.data.dto.request.EmprestimoRequestDTO;
import com.br.emakers.apiProjeto.data.dto.response.EmprestimoResponseDTO;
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

    @GetMapping("/all")
    public ResponseEntity<List<EmprestimoResponseDTO>> getAllEmprestimos() {
        // Retorna a resposta HTTP com:
        // - status 200 (OK), indicando que deu tudo certo
        // - o corpo da resposta será a lista de pessoas (em formato JSON)
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getAllEmprestimos());
    }

    @PutMapping("/return/{idEmprestimo}")
    public ResponseEntity<EmprestimoResponseDTO> devolverEmprestimo(@PathVariable Long idEmprestimo) {
        EmprestimoResponseDTO emprestimoResponseDTO = emprestimoService.devolverLivro(idEmprestimo);
        return ResponseEntity.ok(emprestimoResponseDTO);
    }

    @GetMapping("/{idEmprestimo}")
    public ResponseEntity<EmprestimoResponseDTO> buscarEmprestimoID(@PathVariable Long idEmprestimo) {
        EmprestimoResponseDTO emprestimoResponseDTO = emprestimoService.procurarPeloId(idEmprestimo);
        return ResponseEntity.ok(emprestimoResponseDTO);
    }


}
