package com.br.emakers.apiProjeto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.emakers.apiProjeto.data.dto.request.LivroRequestDTO;
import com.br.emakers.apiProjeto.data.dto.response.LivroResponseDTO;
import com.br.emakers.apiProjeto.data.entity.Emprestimo;
import com.br.emakers.apiProjeto.data.entity.Livro;
import com.br.emakers.apiProjeto.repository.EmprestimoRepository;
import com.br.emakers.apiProjeto.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public List<LivroResponseDTO> getAllLivro() {
       List<Livro> livros = livroRepository.findAll();
        
       // Converte cada objeto Livro da lista original em um LivroResponseDTO, criando uma nova lista apenas com os dados que serão enviados na resposta da API
        return livros.stream().map(LivroResponseDTO::new).collect(Collectors.toList()); 

    }

    public LivroResponseDTO getLivrobyId(Long idLivro) {
        Livro livro = getLivroEntityById(idLivro);

        return new LivroResponseDTO(livro);
    }

    public LivroResponseDTO createLivro(LivroRequestDTO livroRequestDTO) {
        Livro livro = new Livro(livroRequestDTO);
        livroRepository.save(livro);

        return new LivroResponseDTO(livro);
    }

    public LivroResponseDTO updateLivro(Long idLivro, LivroRequestDTO livroRequestDTO) {
        Livro livro = getLivroEntityById(idLivro);
        if(livroRequestDTO.nome() != null) {
            livro.setNome(livroRequestDTO.nome());
        }
        
        if(livroRequestDTO.autor() != null) {
            livro.setAutor(livroRequestDTO.autor());
        }

        if(livroRequestDTO.data_lancamento() != null) {
            livro.setData_lancamento(livroRequestDTO.data_lancamento());
        }

        livroRepository.save(livro);

        return new LivroResponseDTO(livro);

    }

    public String deleteLivro(Long idLivro, LivroRequestDTO livroRequestDTO) {

        Livro livro = getLivroEntityById(idLivro);
        List<Emprestimo> emprestimos = emprestimoRepository.findByLivroId(idLivro);
        boolean temEmprestimoPendente = emprestimos.stream()
        .anyMatch(e -> e.getDataDevolucao() == null);

        if (temEmprestimoPendente) {
            throw new IllegalStateException("Não é possível deletar: existem empréstimos pendentes para este livro.");
        }
        else {
            emprestimoRepository.deleteAll(emprestimos);
            livroRepository.delete(livro);
            return "O livro: '" + livro.getNome() + "' foi deletado!";  
        }
    }

    private Livro getLivroEntityById(Long idLivro) {

        return livroRepository.findById(idLivro).orElseThrow(()-> new RuntimeException("Livro não encontrado!"));  //Faz a busca pelo id e retorna uma mensagem caso não encontre
    }



}
