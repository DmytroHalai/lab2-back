
package org.example.lab2back.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 1. Валідація — всі помилки
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                errors
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    // 2. Не знайдено — 404
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    // 3. Бізнес-логіка (наприклад, IllegalArgumentException)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    // 4. Усі інші непередбачені помилки — 500 (БЕЗ стек-трейсу!)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllExceptions(Exception ex) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal server error",
                null
        );
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}