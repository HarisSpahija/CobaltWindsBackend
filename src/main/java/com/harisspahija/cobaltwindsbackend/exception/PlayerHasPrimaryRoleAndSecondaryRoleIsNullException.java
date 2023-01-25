package com.harisspahija.cobaltwindsbackend.exception;

import com.harisspahija.cobaltwindsbackend.Role;

public class PlayerHasPrimaryRoleAndSecondaryRoleIsNullException extends Exception {
    public PlayerHasPrimaryRoleAndSecondaryRoleIsNullException(Role primary) {
        super("Player cannot have primary role ("  + primary + ") and no secondary, secondary must be given");
    }
}
