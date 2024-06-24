package br.com.sistema.clinica.ApiClinicSystem.infra.error;

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
    public ResponseEntity errorHandling404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandling400(MethodArgumentNotValidException methodArgumentNotValidException) {

        // OBTENDO O ERRO QUE OCORREU
        var error = methodArgumentNotValidException.getFieldErrors();

        return ResponseEntity.badRequest().body(
                error.
                        stream().
                        map(ErrorHandlingDTO::new)
                        .toList());
    }

    @ExceptionHandler(ExceptionValidation.class)
    public ResponseEntity treatBusinessRuleError(ExceptionValidation exceptionValidation) {
        return ResponseEntity.badRequest().body(exceptionValidation.getMessage());
    }

    //TODO

//    @RestControllerAdvice
//    public class TratadorDeErros {
//
//        @ExceptionHandler(EntityNotFoundException.class)
//        public ResponseEntity tratarErro404() {
//            return ResponseEntity.notFound().build();
//        }

//        @ExceptionHandler(MethodArgumentNotValidException.class)
//        public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
//            var erros = ex.getFieldErrors();
//            return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
//        }

//        @ExceptionHandler(HttpMessageNotReadableException.class)
//        public ResponseEntity tratarErro400(HttpMessageNotReadableException ex) {
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        }

//        @ExceptionHandler(BadCredentialsException.class)
//        public ResponseEntity tratarErroBadCredentials() {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
//        }

//        @ExceptionHandler(AuthenticationException.class)
//        public ResponseEntity tratarErroAuthentication() {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
//        }

//        @ExceptionHandler(AccessDeniedException.class)
//        public ResponseEntity tratarErroAcessoNegado() {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
//        }

//        @ExceptionHandler(Exception.class)
//        public ResponseEntity tratarErro500(Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
//        }

//        private record DadosErroValidacao(String campo, String mensagem) {
//            public DadosErroValidacao(FieldError erro) {
//                this(erro.getField(), erro.getDefaultMessage());
//            }
//        }
}

