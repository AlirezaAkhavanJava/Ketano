package com.arcade.ketano.error.dependencies;


import java.time.LocalDateTime;
import java.util.Map;

public record MethodArgumentNotValidMessage(
        String message,
        Map<String, String> fieldErrors,
        LocalDateTime timestamp
) {
}