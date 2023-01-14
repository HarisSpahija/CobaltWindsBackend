package com.harisspahija.cobaltwindsbackend;

import java.time.LocalDate;
import java.util.UUID;

public class Player {
    private String id;
    private String name;

    private LocalDate dateOfBirth;
    private Role primaryRole;
    private Role secondaryRole;
    private String opggLink;
    private Boolean freeAgent;

    public Player(String name, LocalDate dateOfBirth, Role primaryRole, Role secondaryRole, String opggLink, Boolean freeAgent) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.primaryRole = primaryRole;
        if (primaryRole != Role.Fill) {
            this.secondaryRole = secondaryRole;
        }
        this.opggLink = opggLink;
        this.freeAgent = freeAgent;
    }

    public void setId(String id) { this.id = id; }
    public String getId() { return id; }
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
        if (primaryRole == Role.Fill) {
            this.secondaryRole = null;
        }
    }

    public Boolean hasDuplicateRole() {
        return this.primaryRole == this.secondaryRole;
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

    public Boolean getFreeAgent() {
        return freeAgent;
    }

    public void setFreeAgent(Boolean freeAgent) {
        this.freeAgent = freeAgent;
    }
}
