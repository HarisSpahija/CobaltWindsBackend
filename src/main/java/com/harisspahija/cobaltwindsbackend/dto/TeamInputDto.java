package com.harisspahija.cobaltwindsbackend.dto;

import com.harisspahija.cobaltwindsbackend.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public class TeamInputDto {
    private String id;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 16)
    private String name;

    @NotNull
    @NotBlank
    @Length(min = 3, max = 4)
    private String tag;

    @Length(min = 5, max = 16)
    private String teamLogo;

    @Length(min = 5, max = 16)
    private String password;

    @Length(max = 250)
    private String biography;

    @UniqueElements
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

    public List<Role> getOpenRoles() {
        return openRoles;
    }

    public void setOpenRoles(List<Role> openRoles) {
        this.openRoles = openRoles;
    }
}
