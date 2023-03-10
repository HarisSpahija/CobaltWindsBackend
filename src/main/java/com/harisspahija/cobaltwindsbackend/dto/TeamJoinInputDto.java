package com.harisspahija.cobaltwindsbackend.dto;

import jakarta.validation.constraints.NotBlank;

public class TeamJoinInputDto {
    // For security reasons we don't specify password length\
    @NotBlank
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
