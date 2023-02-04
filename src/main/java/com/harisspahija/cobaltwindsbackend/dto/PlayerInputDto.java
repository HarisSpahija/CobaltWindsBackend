package com.harisspahija.cobaltwindsbackend.dto;

import com.harisspahija.cobaltwindsbackend.enums.Role;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class PlayerInputDto {
    private String id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull(message = "Date of birth must be set")
    private LocalDate dateOfBirth;

    @NotNull(message = "Primary role must be set")
    private Role primaryRole;

    private Role secondaryRole;

    @Size(min = 1, max = 250, message = "Opgg link must be between 1 and 250 characters long")
    @NotBlank(message = "Opgg link must be given")
    @Pattern(regexp = "^https://www\\.op\\.gg/summoners.*$", message = "Opgg link must start with https://www.op.gg/summoners")
    private String opggLink;

    @NotNull(message = "Free agent must be passed")
    private boolean freeAgent;

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
