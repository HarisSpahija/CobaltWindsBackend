package com.harisspahija.cobaltwindsbackend.exception;

import com.harisspahija.cobaltwindsbackend.enums.Role;
import com.harisspahija.cobaltwindsbackend.advice.CustomExceptionInterface;
import java.util.HashMap;
import java.util.Map;

public class PlayerHasDuplicateRoleException extends RuntimeException implements CustomExceptionInterface {
    private final Role primary;
    private final Role secondary;

    public PlayerHasDuplicateRoleException(Role primary, Role secondary) {
        super();
        this.primary = primary;
        this.secondary = secondary;
    }

    public Map<String, String> getErrors() {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("secondaryRole", "invalid role " + secondary + ": cannot be duplicate to primary role " + primary);
        return errors;
    }
}
