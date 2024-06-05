package br.com.sistema.clinica.ApiClinicSystem.infra.error;

public class ExceptionValidation extends RuntimeException{
    public ExceptionValidation(String message) {
        super(message);
    }
}
