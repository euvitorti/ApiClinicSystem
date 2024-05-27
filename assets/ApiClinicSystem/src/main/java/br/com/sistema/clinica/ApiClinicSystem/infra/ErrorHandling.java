package br.com.sistema.clinica.ApiClinicSystem.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// CLASSE PARA TRATAMENTO DE EXCEÇÃO

@RestControllerAdvice
public class ErrorHandling {

    // DEFININDO PARA QUAL EXCEÇÃO ESSE MÉTODO SERÁ CHAMADO, USA A ANOTAÇÃO A SEGUIR

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorHandling404(){
        return ResponseEntity.notFound().build();
    }
}
