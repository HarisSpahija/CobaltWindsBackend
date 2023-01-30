package com.harisspahija.cobaltwindsbackend.dto;

import jakarta.validation.constraints.NotBlank;

public class TeamJoinInputDto {
    // For security reasons we don't specify password length\
    @NotBlank
    private String password;

    @NotBlank
    private String playerId;

    public String getPassword() {
        return password;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
