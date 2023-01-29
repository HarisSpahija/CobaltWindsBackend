package com.harisspahija.cobaltwindsbackend.dto;

import com.harisspahija.cobaltwindsbackend.Role;
import com.harisspahija.cobaltwindsbackend.model.Player;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

public class TeamInputDto {
    private String id;
    @NotBlank
    @Size(min = 4, max = 16)
    private String name;

    @NotBlank
    @Length(min = 3, max = 4)
    private String tag;

    @Length(min = 5, max = 16)
    private String teamLogo;

    @Length(min = 5, max = 16)
    private String password;

    @Length(max = 250)
    private String biography;

    @NotEmpty
    private Player teamCaptain;

    // Team can only recruit for 5 unique roles
    // Top, Jungle, Mid, ADC, Support
    // Fill is not considered a role but is a wildcard role

    @Size(max=5)
    private List<Role> openRoles;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Player getTeamCaptain() {
        return teamCaptain;
    }

    public void setTeamCaptain(Player teamCaptain) {
        this.teamCaptain = teamCaptain;
    }

    public List<Role> getOpenRoles() {
        return openRoles;
    }

    public void setOpenRoles(List<Role> openRoles) {
        this.openRoles = openRoles;
    }
}
