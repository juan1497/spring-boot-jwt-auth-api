package com.juan1497.securityjwt.excepciones;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String mensaje) {
        super(mensaje);
    }

}