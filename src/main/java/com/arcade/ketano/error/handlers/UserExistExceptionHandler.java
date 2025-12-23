package com.arcade.ketano.error.handlers;

import com.arcade.ketano.error.dependencies.UserExistMessage;
import com.arcade.ketano.error.exceptions.EmailAlreadyExistsException;
import com.arcade.ketano.error.exceptions.UserExistException;
import com.arcade.ketano.error.exceptions.UsernameAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class UserExistExceptionHandler {

    // Helper to build consistent error response
    private UserExistMessage buildError(String message, HttpStatus status, WebRequest request) {
        return UserExistMessage.builder()
                .time(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
    }

    // Catching custom exceptions
    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<UserExistMessage> handleUserExist(UserExistException ex, WebRequest request) {
        log.warn("User registration failed: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildError(ex.getMessage(), HttpStatus.BAD_REQUEST, request));
    }

    // Catching IllegalArgumentException (for duplicate username/email)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<UserExistMessage> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        log.warn("Validation failed: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildError(ex.getMessage(), HttpStatus.BAD_REQUEST, request));
    }

    //Catching general runtime exceptions (fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserExistMessage> handleAllExceptions(Exception ex, WebRequest request) {
        log.error("Unexpected error occurred", ex);
        String safeMessage = "An unexpected error occurred. Please try again later.";
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildError(safeMessage, HttpStatus.INTERNAL_SERVER_ERROR, request));
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<UserExistMessage> handleUsernameExists(UsernameAlreadyExistsException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(buildError(ex.getMessage(), HttpStatus.BAD_REQUEST, request));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<UserExistMessage> handleEmailExists(EmailAlreadyExistsException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(buildError(ex.getMessage(), HttpStatus.BAD_REQUEST, request));
    }
}