package com.harliton.refeicoes_api.exception;

public class ResourceNotFoundException extends RuntimeException {
     public ResourceNotFoundException(String message) {
          super(message);
     }
}