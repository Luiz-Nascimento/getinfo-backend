package com.getinfo.contratos.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.json.JsonParseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, JsonParseException.class})
    public ResponseEntity<?> handleJsonParseException(Exception ex) {
        String mensagem = "Erro ao processar o JSON: verifique se o formato está correto e os campos de texto estão entre aspas.";
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "status", 400,
                        "error", "Bad Request",
                        "message", mensagem
                ));
    }
    // Novo handler para IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "status", HttpStatus.BAD_REQUEST.value(),
                        "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        "message", ex.getMessage()
                ));
    }

    // Handler adicional para tratar exceções de tipo inválido (comumente associadas)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String mensagem = "Parâmetro inválido: " + ex.getName() +
                ". Tipo esperado: " + (ex.getRequiredType() != null ?
                ex.getRequiredType().getSimpleName() : "não especificado");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "status", HttpStatus.BAD_REQUEST.value(),
                        "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        "message", mensagem
                ));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "status", HttpStatus.NOT_FOUND.value(),
                        "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        // Verifica se é erro de CNPJ duplicado
        if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            org.hibernate.exception.ConstraintViolationException cause =
                    (org.hibernate.exception.ConstraintViolationException) ex.getCause();

            if (cause.getSQLException().getMessage().contains("Duplicate entry")
                    && cause.getSQLException().getMessage().contains("UK74xhe5obsc7li6x4q5wi75pd5")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Já existe uma empresa cadastrada com este CNPJ");
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno ao processar a requisição");
    }

}

