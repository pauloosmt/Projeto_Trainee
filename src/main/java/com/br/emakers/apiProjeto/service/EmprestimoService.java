package com.br.emakers.apiProjeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.emakers.apiProjeto.data.dto.request.EmprestimoRequestDTO;
import com.br.emakers.apiProjeto.data.dto.response.EmprestimoResponseDTO;
import com.br.emakers.apiProjeto.data.entity.Emprestimo;
import com.br.emakers.apiProjeto.data.entity.Livro;
import com.br.emakers.apiProjeto.data.entity.Pessoa;
import com.br.emakers.apiProjeto.repository.EmprestimoRepository;
import com.br.emakers.apiProjeto.repository.LivroRepository;
import com.br.emakers.apiProjeto.repository.PessoaRepository;

import java.time.LocalDate;


@Service
public class EmprestimoService {
@Autowired
private LivroRepository livroRepository;

@Autowired
private PessoaRepository pessoaRepository;

@Autowired
private EmprestimoRepository emprestimoRepository;

public EmprestimoResponseDTO emprestarLivroParaUsuarioLogado(EmprestimoRequestDTO emprestimoRequestDTO,  String emailUsuario) {
    Livro livro = livroRepository.findByNome(emprestimoRequestDTO.nome_livro())
        .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

    Pessoa pessoa = (Pessoa) pessoaRepository.findByEmail(emailUsuario);

    Emprestimo emprestimo = Emprestimo.builder()
        .livro(livro)
        .pessoa(pessoa)
        .dataEmprestimo(LocalDate.now())
        .build();

    emprestimoRepository.save(emprestimo);

    return new EmprestimoResponseDTO(emprestimo);
}
   
}