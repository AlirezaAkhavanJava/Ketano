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

/**
 * Global exception handler specifically designed for user registration-related exceptions.
 * This class uses Spring's @RestControllerAdvice to centralize exception handling
 * across all controllers, ensuring consistent error responses for user existence conflicts.
 */
@RestControllerAdvice
@Slf4j
public class UserExistExceptionHandler {

    /**
     * Helper method to construct a standardized error response body.
     * Ensures all error responses have the same structure: timestamp, status code,
     * error reason, custom message, and request path.
     *
     * @param message The detailed error message to return to the client
     * @param status  The HTTP status to associate with the error
     * @param request The current web request (for extracting the path)
     * @return A fully built UserExistMessage object
     */
    private UserExistMessage buildError(String message, HttpStatus status, WebRequest request) {
        return UserExistMessage.builder()
                .time(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
    }

    /**
     * Handles the general UserExistException, typically thrown when a user
     * attempts to register with credentials that already exist.
     *
     * @param ex      The thrown UserExistException
     * @param request The current web request (used for logging and path info)
     * @return ResponseEntity with BAD_REQUEST status and structured error body
     */
    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<UserExistMessage> handleUserExist(UserExistException ex, WebRequest request) {
        log.warn("User registration failed: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildError(ex.getMessage(), HttpStatus.BAD_REQUEST, request));
    }

    /**
     * Handles IllegalArgumentException, often used for validation failures
     * such as duplicate username/email detected during service-layer checks.
     *
     * @param ex      The thrown IllegalArgumentException
     * @param request The current web request
     * @return ResponseEntity with BAD_REQUEST status and structured error body
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<UserExistMessage> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        log.warn("Validation failed: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildError(ex.getMessage(), HttpStatus.BAD_REQUEST, request));
    }

    /**
     * Specific handler for UsernameAlreadyExistsException.
     * Provides a clear, targeted response when only the username is duplicated.
     *
     * @param ex      The thrown UsernameAlreadyExistsException
     * @param request The current web request
     * @return ResponseEntity with BAD_REQUEST status and structured error body
     */
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<UserExistMessage> handleUsernameExists(UsernameAlreadyExistsException ex, WebRequest request) {
        log.warn("Username already exists: {}", ex.getMessage());
        return ResponseEntity.badRequest()
                .body(buildError(ex.getMessage(), HttpStatus.BAD_REQUEST, request));
    }

    /**
     * Specific handler for EmailAlreadyExistsException.
     * Provides a clear, targeted response when only the email is already registered.
     *
     * @param ex      The thrown EmailAlreadyExistsException
     * @param request The current web request
     * @return ResponseEntity with BAD_REQUEST status and structured error body
     */
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<UserExistMessage> handleEmailExists(EmailAlreadyExistsException ex, WebRequest request) {
        log.warn("Email already exists: {}", ex.getMessage());
        return ResponseEntity.badRequest()
                .body(buildError(ex.getMessage(), HttpStatus.BAD_REQUEST, request));
    }

    /**
     * Fallback handler for any uncaught exceptions.
     * Prevents exposure of internal details and returns a generic error message
     * while logging the full stack trace for debugging purposes.
     *
     * @param ex      The unexpected exception
     * @param request The current web request
     * @return ResponseEntity with INTERNAL_SERVER_ERROR status and safe error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserExistMessage> handleAllExceptions(Exception ex, WebRequest request) {
        log.error("Unexpected error occurred", ex);
        String safeMessage = "An unexpected error occurred. Please try again later.";
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildError(safeMessage, HttpStatus.INTERNAL_SERVER_ERROR, request));
    }
}