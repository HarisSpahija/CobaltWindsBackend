package com.harisspahija.cobaltwindsbackend.model;

import com.harisspahija.cobaltwindsbackend.enums.Role;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Teams")
public class Team {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "tag")
    private String tag;

    @Column(name = "password")
    private String password;

    @Column(name = "logo")
    private String teamLogo;

    @Column(name = "biography")

    private String biography;

    @Column(name = "creation_date")

    private LocalDate creationDate;

    @Column(name = "disband_date")

    private LocalDate disbandDate;

    @OneToOne(fetch = FetchType.LAZY)
    private Player teamCaptain;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> players;

    @OneToMany(mappedBy = "team")
    private Collection<CompetitionSignup> signups;

    @OneToMany(mappedBy = "team")
    private Collection<CompetitionRegistration> registrations;

    @Column(name = "open_roles")
    private List<Role> openRoles;

    public String getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Role> getOpenRoles() {
        return openRoles;
    }

    public void setOpenRoles(List<Role> openRoles) {
        this.openRoles = openRoles;
    }
}
