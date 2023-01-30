package com.harisspahija.cobaltwindsbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.harisspahija.cobaltwindsbackend.Role;
import com.harisspahija.cobaltwindsbackend.model.Player;

import java.time.LocalDate;
import java.util.List;

public class TeamDto {
    private String id;
    private String name;
    private String tag;
    private String teamLogo;
    private String biography;
    private LocalDate creationDate;
    private LocalDate disbandDate;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Player teamCaptain;
    private List<Role> openRoles;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDisbandDate() {
        return disbandDate;
    }

    public void setDisbandDate(LocalDate disbandDate) {
        this.disbandDate = disbandDate;
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
