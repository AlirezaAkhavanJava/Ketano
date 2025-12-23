package com.arcade.ketano.error.exceptions;

public class EmailAlreadyExistsException extends UserExistException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
