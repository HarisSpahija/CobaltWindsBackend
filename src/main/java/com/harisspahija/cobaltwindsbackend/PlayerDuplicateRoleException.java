package com.harisspahija.cobaltwindsbackend;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PlayerDuplicateRoleException extends RuntimeException {
    public PlayerDuplicateRoleException() {
        super("A player cannot have the same role for primary and secondary. Please choose a different role for primary and secondary.");
    }
}
