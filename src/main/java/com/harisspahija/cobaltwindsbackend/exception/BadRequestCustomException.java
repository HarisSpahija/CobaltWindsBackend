package com.harisspahija.cobaltwindsbackend.exception;

public class BadRequestCustomException extends RuntimeException {
    public BadRequestCustomException(String message) {
        super(message);
    }
}
