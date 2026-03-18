package com.juan1497.securityjwt.excepciones;

public class CredentialsIncorrectException extends RuntimeException {
    public CredentialsIncorrectException(String message) {
        super(message);
    }

}
