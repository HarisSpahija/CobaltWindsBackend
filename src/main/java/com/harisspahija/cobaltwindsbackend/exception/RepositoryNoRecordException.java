package com.harisspahija.cobaltwindsbackend.exception;

public class RepositoryNoRecordException extends RuntimeException {
    public RepositoryNoRecordException() {
        super("Could not find any records");
    }

    public RepositoryNoRecordException(String id) {
        super("Could not find any record for given id: " + id);
    }
}
