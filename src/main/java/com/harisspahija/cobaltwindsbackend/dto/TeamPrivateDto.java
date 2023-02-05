package com.harisspahija.cobaltwindsbackend.dto;

public class TeamPrivateDto extends TeamDto {
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
