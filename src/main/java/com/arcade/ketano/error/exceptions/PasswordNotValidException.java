package com.arcade.ketano.error.exceptions;

public class PasswordNotValidException extends RuntimeException {
  public PasswordNotValidException(String message) {
    super(message);
  }
}
