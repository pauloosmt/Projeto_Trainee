package com.br.emakers.apiProjeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.emakers.apiProjeto.data.dto.request.AuthenticationDTO;
import com.br.emakers.apiProjeto.data.dto.request.PessoaRequestDTO;
import com.br.emakers.apiProjeto.data.dto.response.AuthenticationResponseDTO;
import com.br.emakers.apiProjeto.data.entity.Pessoa;
import com.br.emakers.apiProjeto.infra.security.TokenService;
import com.br.emakers.apiProjeto.repository.PessoaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private AuthenticationManager authMan; //Acessa atraves do SecurityConfiguration
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
         System.out.println("Tentando login com: " + data.email());
        var emailSenha = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authMan.authenticate(emailSenha);

        var token = tokenService.gerarToken((Pessoa) auth.getPrincipal());

        return ResponseEntity.ok(new AuthenticationResponseDTO(token));
    }

    @PostMapping("/register")

    public ResponseEntity registro(@RequestBody @Valid PessoaRequestDTO pessoaRequestDTO) {
        if(this.pessoaRepository.findByEmail(pessoaRequestDTO.email()) != null) {
            return ResponseEntity.badRequest().build(); 
        }

        String encryptedSenha = new BCryptPasswordEncoder().encode(pessoaRequestDTO.senha());
        Pessoa pessoa = new Pessoa(pessoaRequestDTO, encryptedSenha);
        pessoaRepository.save(pessoa);

        return ResponseEntity.ok().build();
    }



}
