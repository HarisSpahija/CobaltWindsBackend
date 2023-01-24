package com.harisspahija.cobaltwindsbackend.exception;

public class RepositoryException extends RuntimeException {
    public RepositoryException() {
        super("Something went wrong, please try later");
    }
}
