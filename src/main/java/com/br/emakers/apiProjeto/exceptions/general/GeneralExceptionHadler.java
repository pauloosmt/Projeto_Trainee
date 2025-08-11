package com.br.emakers.apiProjeto.exceptions.general;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHadler {

    @ExceptionHandler
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException entityNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundException.getMessage());
    }
}
