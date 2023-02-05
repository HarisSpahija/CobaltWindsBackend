package com.harisspahija.cobaltwindsbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.harisspahija.cobaltwindsbackend.enums.Role;
import com.harisspahija.cobaltwindsbackend.model.Team;

import java.time.LocalDate;

public class PlayerDto {
    private String id;
    private String name;

    private LocalDate dateOfBirth;

    private Role primaryRole;
    private Role secondaryRole;

    private String opggLink;

    private boolean freeAgent;

    private Team team;

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

    @JsonIgnoreProperties({ "teamCaptain", "players", "password"})
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Role getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(Role primaryRole) {
        this.primaryRole = primaryRole;
    }

    public Role getSecondaryRole() {
        return secondaryRole;
    }

    public void setSecondaryRole(Role secondaryRole) {
        this.secondaryRole = secondaryRole;
    }

    public String getOpggLink() {
        return opggLink;
    }

    public void setOpggLink(String opggLink) {
        this.opggLink = opggLink;
    }

    public boolean isFreeAgent() {
        return freeAgent;
    }

    public void setFreeAgent(boolean freeAgent) {
        this.freeAgent = freeAgent;
    }

    public Boolean hasFillAndSecondaryRole() {
        return this.primaryRole == Role.Fill && this.secondaryRole != null;
    }
    public Boolean hasDuplicateRole() {
        return this.primaryRole == this.secondaryRole;
    }

    public Boolean hasInvalidRoles() {
        return this.hasFillAndSecondaryRole() || this.hasDuplicateRole();
    }
}
