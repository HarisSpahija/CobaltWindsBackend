package com.harisspahija.cobaltwindsbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.harisspahija.cobaltwindsbackend.model.AuthRole;
import com.harisspahija.cobaltwindsbackend.model.Player;

import java.util.Collection;

public class UserDto {
    private String id;
    private String email;

    @JsonIgnoreProperties({"user"})
    private Collection<AuthRole> authRoles;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "team"})
    private Player playerProfile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<AuthRole> getAuthRoles() {
        return authRoles;
    }

    public void setAuthRoles(Collection<AuthRole> authRoles) {
        this.authRoles = authRoles;
    }

    public Player getPlayerProfile() {
        return playerProfile;
    }

    public void setPlayerProfile(Player playerProfile) {
        this.playerProfile = playerProfile;
    }
}
