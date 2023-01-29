package com.harisspahija.cobaltwindsbackend.exception;

import com.harisspahija.cobaltwindsbackend.Role;
import com.harisspahija.cobaltwindsbackend.advice.CustomExceptionInterface;
import java.util.HashMap;
import java.util.Map;

public class PlayerHasPrimaryRoleFillAndSecondaryRoleNotNullException extends RuntimeException implements CustomExceptionInterface {
    public PlayerHasPrimaryRoleFillAndSecondaryRoleNotNullException() {
        super();
    }

    public Map<String, String> getErrors() {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("secondaryRole", "role cannot be set if primary role is set to " + Role.Fill);
        return errors;
    }
}
