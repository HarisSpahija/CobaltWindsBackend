package com.harisspahija.cobaltwindsbackend.exception;

import com.harisspahija.cobaltwindsbackend.Role;

public class PlayerHasDuplicateRoleException extends RuntimeException {
    public PlayerHasDuplicateRoleException(Role primary, Role secondary) {
        super("Player cannot have duplicate primary (" + primary + ") and secondary (" + secondary + ")");
    }
}
