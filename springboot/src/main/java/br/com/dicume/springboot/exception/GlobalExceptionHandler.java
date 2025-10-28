package br.com.dicume.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.dicume.springboot.models.ProductModel;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
        // Personalizando o cabeçalho e retornando um ResponseEntity com status 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("X-Custom-Header", "Produto não encontrado!")
                .body(ex.getMessage()); // Passando a mensagem de erro no corpo
    }
        
}
