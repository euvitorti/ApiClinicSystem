package br.com.sistema.clinica.ApiClinicSystem.dto.errorDto;

import org.springframework.validation.FieldError;

public record ErrorHandlingDTO(String field, String message) {

    public ErrorHandlingDTO(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
