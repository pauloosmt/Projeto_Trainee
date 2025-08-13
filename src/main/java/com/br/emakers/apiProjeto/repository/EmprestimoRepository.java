package com.br.emakers.apiProjeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.emakers.apiProjeto.data.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByPessoaEmail(String email);
    @Query("SELECT e FROM Emprestimo e WHERE e.livro.idLivro = :idLivro")
    List<Emprestimo> findByLivroId(@Param("idLivro") Long idLivro);
    
    @Query("SELECT e FROM Emprestimo e WHERE e.pessoa.idPessoa = :idPessoa")
    List<Emprestimo> findByPessoaId(@Param("idPessoa") Long idPessoa);
}
