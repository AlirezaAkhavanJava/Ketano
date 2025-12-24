package com.arcade.ketano.error.dependencies;


import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class NoResourceFoundMessage {
    String message;
    HttpStatus httpStatus;
}
