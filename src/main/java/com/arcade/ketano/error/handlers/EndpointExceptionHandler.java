package com.arcade.ketano.error.handlers;

import com.arcade.ketano.error.dependencies.NoResourceFoundMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;


/**
 * Global exception handler for the application.
 * This class centralizes the handling of specific exceptions that can occur across all @RestController endpoints.
 * It is annotated with @RestControllerAdvice to apply to all controllers without needing individual handlers.
 */
@RestControllerAdvice
@Log4j2 // Enables Log4j2 logging (provides a 'log' instance)
public class EndpointExceptionHandler {

    /**
     * Handles Spring's NoResourceFoundException, which is typically thrown when a requested resource
     * (e.g., a static file or endpoint path) cannot be found in Spring MVC 6+.
     * <p>
     * The method provides a graceful, content-negotiated 404 response:
     * - If the client accepts JSON (common for APIs), it returns a structured JSON error message.
     * - Otherwise (e.g., browsers requesting HTML or other content), it falls back to a plain text response.
     *
     * @param ex      the caught NoResourceFoundException
     * @param request the current HTTP request (used for logging and header inspection)
     * @return a ResponseEntity with an appropriate 404 body and content type
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceFound(
            NoResourceFoundException ex,
            HttpServletRequest request) {

        // Log the missing resource URI at INFO level for monitoring and debugging
        log.info("No resource found: {}", request.getRequestURI());

        // Check the Accept header to determine the client's preferred response format
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            // API clients expecting JSON get a structured error object
            NoResourceFoundMessage msg = NoResourceFoundMessage.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("The requested resource was not found." + ex.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(msg);
        }

        // Fallback for non-JSON clients (e.g., browsers loading static resources or direct URL access)
        // Returns a simple plain-text 404 message
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.TEXT_PLAIN)
                .body("404 Not Found");
    }
}