package com.br.emakers.apiProjeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.emakers.apiProjeto.data.dto.request.EmprestimoRequestDTO;
import com.br.emakers.apiProjeto.data.dto.response.EmprestimoResponseDTO;
import com.br.emakers.apiProjeto.data.entity.Emprestimo;
import com.br.emakers.apiProjeto.data.entity.Livro;
import com.br.emakers.apiProjeto.data.entity.Pessoa;
import com.br.emakers.apiProjeto.exceptions.entity.EmprestimoDevolvido;
import com.br.emakers.apiProjeto.exceptions.entity.LivroIndisponivelException;
import com.br.emakers.apiProjeto.exceptions.general.EntidadeNaoEncontrada;
import com.br.emakers.apiProjeto.repository.EmprestimoRepository;
import com.br.emakers.apiProjeto.repository.LivroRepository;
import com.br.emakers.apiProjeto.repository.PessoaRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmprestimoService {
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public EmprestimoResponseDTO emprestarLivroParaUsuarioLogado(EmprestimoRequestDTO emprestimoRequestDTO,
            String emailUsuario) {
        Livro livro = livroRepository.findByNome(emprestimoRequestDTO.nome_livro())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Pessoa pessoa = (Pessoa) pessoaRepository.findByEmail(emailUsuario);

        if (!livro.getLivro_disponivel()) {
            throw new LivroIndisponivelException();
        }

        Emprestimo emprestimo = Emprestimo.builder()
                .livro(livro)
                .pessoa(pessoa)
                .dataEmprestimo(LocalDate.now())
                .build();

        emprestimoRepository.save(emprestimo);

        livro.setLivro_disponivel(false);
        livroRepository.save(livro);

        return new EmprestimoResponseDTO(emprestimo);
    }

    public List<EmprestimoResponseDTO> getAllEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();

        // Converte cada objeto Livro da lista original em um LivroResponseDTO, criando
        // uma nova lista apenas com os dados que serão enviados na resposta da API
        return emprestimos.stream().map(EmprestimoResponseDTO::new).collect(Collectors.toList());

    }

    public EmprestimoResponseDTO devolverLivro(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontrada(id));

        Livro livro = emprestimo.getLivro();

        if (emprestimo.getDataDevolucao() != null) {
            throw new EmprestimoDevolvido();
        }

        livro.setLivro_disponivel(true);

        emprestimo.setDataDevolucao(LocalDate.now());

        livroRepository.save(livro);
        emprestimoRepository.save(emprestimo);

        return new EmprestimoResponseDTO(emprestimo);

    }

    public EmprestimoResponseDTO procurarPeloId(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontrada(id));
        return new EmprestimoResponseDTO(emprestimo);

    }

}