package com.harisspahija.cobaltwindsbackend.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String id) {
       super("Could not find player " + id);
   }
}
