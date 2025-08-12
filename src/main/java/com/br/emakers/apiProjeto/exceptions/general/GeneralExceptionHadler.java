package com.br.emakers.apiProjeto.exceptions.general;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.emakers.apiProjeto.exceptions.RestErrorMenssagem;



@RestControllerAdvice
public class GeneralExceptionHadler {

    @ExceptionHandler(EntidadeNaoEncontrada.class)
    public ResponseEntity<RestErrorMenssagem> handleEntidadeNaoEncontrada(EntidadeNaoEncontrada entidadeNaoEncontrada){
        RestErrorMenssagem errorMenssagem = new RestErrorMenssagem(HttpStatus.BAD_REQUEST, entidadeNaoEncontrada.getMessage());
        return ResponseEntity.status(errorMenssagem.status()).body(errorMenssagem);
    }

    

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<RestErrorMenssagem> handleAuthException(AuthenticationException ex) {
        RestErrorMenssagem errorMenssagem = new RestErrorMenssagem(HttpStatus.UNAUTHORIZED, "Email ou Senha inválidos!");
        return ResponseEntity.status(errorMenssagem.status()).body(errorMenssagem);
    }
}
