package br.com.sistema.clinica.ApiClinicSystem.dto.errorDto;

import org.springframework.validation.FieldError;

public record ErrorHandlingDTO(String field, String message) {

    // OBTÉM QUAL É CAMPO, E A MENSAGEM PADRÃO

    public ErrorHandlingDTO(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
