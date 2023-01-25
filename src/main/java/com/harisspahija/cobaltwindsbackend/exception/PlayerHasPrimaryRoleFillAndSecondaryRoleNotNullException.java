package com.harisspahija.cobaltwindsbackend.exception;

public class PlayerHasPrimaryRoleFillAndSecondaryRoleNotNullException extends RuntimeException {
    public PlayerHasPrimaryRoleFillAndSecondaryRoleNotNullException() {
        super("Player cannot have primary (Fill) and a secondary, secondary must be null");
    }
}
