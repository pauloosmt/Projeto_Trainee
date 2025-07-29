package com.br.emakers.apiProjeto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.emakers.apiProjeto.data.entity.Livro;


@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByNome(String nome);
}
