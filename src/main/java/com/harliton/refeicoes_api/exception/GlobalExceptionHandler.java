package com.harliton.refeicoes_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Captura exceções e retorna o JSON de erro correto
@ControllerAdvice
public class GlobalExceptionHandler {

     @ExceptionHandler(ResourceNotFoundException.class)
     public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
          // Retorna 404 Not Found
          return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
     }

}