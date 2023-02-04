package com.harisspahija.cobaltwindsbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.harisspahija.cobaltwindsbackend.enums.Role;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "Players")
public class Player {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @SuppressWarnings("unused")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "primary_role")
    private Role primaryRole;

    @Column(name = "secondary_role")
    private Role secondaryRole;

    @Column(name = "opgg_link", unique = true)
    private String opggLink;

    @Column(name = "free_agent")
    private boolean freeAgent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

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

    public Boolean isFreeAgent() {
        return freeAgent;
    }

    public void setFreeAgent(Boolean freeAgent) {
        this.freeAgent = freeAgent;
    }

    @JsonIgnore
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
