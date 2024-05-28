package br.com.sistema.clinica.ApiClinicSystem.infra;

import br.com.sistema.clinica.ApiClinicSystem.dto.errorDto.ErrorHandlingDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// CLASSE PARA TRATAMENTO DE EXCEÇÃO

@RestControllerAdvice
public class ErrorHandling {

    // A anotação @ExceptionHandler, indica qual exception um determinado método da classe
    // de tratamento de erros deve capturar;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorHandling404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandling400(MethodArgumentNotValidException methodArgumentNotValidException){

        // OBTENDO O ERRO QUE OCORREU
        var error = methodArgumentNotValidException.getFieldErrors();

        return ResponseEntity.badRequest().body(
                        error.
                        stream().
                        map(ErrorHandlingDTO::new)
                        .toList());
    }
}
