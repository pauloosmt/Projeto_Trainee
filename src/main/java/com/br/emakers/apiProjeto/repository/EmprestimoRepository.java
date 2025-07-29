package com.br.emakers.apiProjeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.emakers.apiProjeto.data.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByPessoaEmail(String email);
    List<Emprestimo> findByLivroId(Long idLivro);
}
