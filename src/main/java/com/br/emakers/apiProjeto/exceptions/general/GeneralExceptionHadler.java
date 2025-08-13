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
    public ResponseEntity<RestErrorMensagem> handleIllegalState(IllegalStateException ex) {
        RestErrorMensagem errorMensagem = new RestErrorMensagem((HttpStatus.CONFLICT), ex.getMessage());
        return ResponseEntity.status(errorMensagem.status()).body(errorMensagem);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<RestErrorMensagem> handleAuthException(AuthenticationException ex) {
        RestErrorMensagem errorMensagem = new RestErrorMensagem(HttpStatus.UNAUTHORIZED, "Email ou Senha inválidos!");
        return ResponseEntity.status(errorMensagem.status()).body(errorMensagem);
    }

    @ExceptionHandler(SecurityCustomException.class)
    public ResponseEntity<String> handleSecurityException(SecurityCustomException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
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
    public ResponseEntity<RestErrorMensagem> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String mensagem = "O email informado já está cadastrado.";
        RestErrorMensagem erro = new RestErrorMensagem(HttpStatus.BAD_REQUEST, mensagem);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
