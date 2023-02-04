package com.harisspahija.cobaltwindsbackend.exception;

import com.harisspahija.cobaltwindsbackend.enums.Role;
import com.harisspahija.cobaltwindsbackend.advice.CustomExceptionInterface;

import java.util.HashMap;
import java.util.Map;

public class PlayerHasPrimaryRoleAndSecondaryRoleIsNullException extends RuntimeException implements CustomExceptionInterface {
    private final Role primary;

    public PlayerHasPrimaryRoleAndSecondaryRoleIsNullException(Role primary) {
        super();
        this.primary = primary;
    }

    public Map<String, String> getErrors() {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("secondaryRole", "cannot be null if primary role is set to " + primary);
        return errors;
    }
}
