package com.br.emakers.apiProjeto.exceptions.general;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.emakers.apiProjeto.exceptions.RestErrorMensagem;
import com.br.emakers.apiProjeto.exceptions.entity.EmailCadastradoException;
import com.br.emakers.apiProjeto.exceptions.entity.EmprestimoDevolvido;
import com.br.emakers.apiProjeto.exceptions.entity.LivroIndisponivelException;
import com.br.emakers.apiProjeto.infra.security.SecurityCustomException;



@RestControllerAdvice
@Order(1)
public class GeneralExceptionHadler {

    @ExceptionHandler(EntidadeNaoEncontrada.class)
    public ResponseEntity<RestErrorMensagem> handleEntidadeNaoEncontrada(EntidadeNaoEncontrada entidadeNaoEncontrada){
        RestErrorMensagem errorMensagem = new RestErrorMensagem(HttpStatus.BAD_REQUEST, entidadeNaoEncontrada.getMessage());
        return ResponseEntity.status(errorMensagem.status()).body(errorMensagem);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<RestErrorMensagem> handleIllegalState(IllegalStateException excessao) {
        RestErrorMensagem errorMensagem = new RestErrorMensagem((HttpStatus.CONFLICT), excessao.getMessage());
        return ResponseEntity.status(errorMensagem.status()).body(errorMensagem);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<RestErrorMensagem> handleAuthException(AuthenticationException excessao) {
        RestErrorMensagem errorMensagem = new RestErrorMensagem(HttpStatus.UNAUTHORIZED, "Email ou Senha inválidos!");
        return ResponseEntity.status(errorMensagem.status()).body(errorMensagem);
    }

    @ExceptionHandler(SecurityCustomException.class)
    public ResponseEntity<String> handleSecurityException(SecurityCustomException excessao) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(excessao.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<RestErrorMensagem>> handleValidationErrors(MethodArgumentNotValidException ex) {

        List<RestErrorMensagem> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new RestErrorMensagem(
                        HttpStatus.BAD_REQUEST,
                        fieldError.getField() + ": " + fieldError.getDefaultMessage()
                ))
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RestErrorMensagem> handleEmailjaUtilizado(DataIntegrityViolationException ex) {
        RestErrorMensagem error = new RestErrorMensagem(HttpStatus.BAD_REQUEST, "O email já está cadastrado!");
        return ResponseEntity.status(error.status()).body(error);
    }

    @ExceptionHandler(LivroIndisponivelException.class)
    public ResponseEntity<RestErrorMensagem> handleLivroJaEmprestado(LivroIndisponivelException excessao) {
        RestErrorMensagem error = new RestErrorMensagem(HttpStatus.BAD_REQUEST, excessao.getMessage());
        return ResponseEntity.status(error.status()).body(error);
    }

    @ExceptionHandler(EmprestimoDevolvido.class)
    public ResponseEntity<RestErrorMensagem> handleEmprestimoDevolvido(EmprestimoDevolvido excessao) {
        RestErrorMensagem error = new RestErrorMensagem(HttpStatus.BAD_REQUEST, excessao.getMessage());
        return ResponseEntity.status(error.status()).body(error);
    }

    @ExceptionHandler(LivroNaoEncontrado.class)
    public ResponseEntity<RestErrorMensagem> handleLivroNaoEncontrado(LivroNaoEncontrado excessao) {
        RestErrorMensagem error = new RestErrorMensagem(HttpStatus.BAD_REQUEST, excessao.getMessage());
        return ResponseEntity.status(error.status()).body(error);
    }

}
