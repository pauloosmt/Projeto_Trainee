package com.br.emakers.apiProjeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.emakers.apiProjeto.data.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

}
