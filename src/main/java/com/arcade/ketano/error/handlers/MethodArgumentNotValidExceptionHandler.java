package com.arcade.ketano.error.handlers;

import com.arcade.ketano.error.dependencies.MethodArgumentNotValidMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class MethodArgumentNotValidExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MethodArgumentNotValidMessage> handleValidation(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(err ->
                        errors.put(err.getField(), err.getDefaultMessage())
                );

        MethodArgumentNotValidMessage body =
                new MethodArgumentNotValidMessage(
                        "Validation failed",
                        errors,
                        LocalDateTime.now()
                );

        return ResponseEntity.badRequest().body(body);
    }
}



