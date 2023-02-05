package com.harisspahija.cobaltwindsbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.harisspahija.cobaltwindsbackend.enums.CompetitionStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "Competitions")
public class Competition {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @SuppressWarnings("unused")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "logo")
    private String logo;

    @Column(name = "description")
    private String description;

    @Column(name = "max_team_count")
    private short maxTeamCount;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "registration_start_date")
    private LocalDate registrationDate;

    @Column(name = "status")
    private CompetitionStatus status;

    @OneToMany(mappedBy = "competition")
    private Collection<CompetitionSignup> signups;

    @OneToMany(mappedBy = "competition")
    private Collection<CompetitionRegistration> registrations;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getMaxTeamCount() {
        return maxTeamCount;
    }

    public void setMaxTeamCount(short maxTeamCount) {
        this.maxTeamCount = maxTeamCount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public CompetitionStatus getStatus() {
        return status;
    }

    public void setStatus(CompetitionStatus status) {
        this.status = status;
    }

    public Collection<CompetitionSignup> getSignups() {
        return signups;
    }

    public void setSignups(Collection<CompetitionSignup> signups) {
        this.signups = signups;
    }

    @JsonIgnore
    public Collection<CompetitionRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Collection<CompetitionRegistration> registrations) {
        this.registrations = registrations;
    }
}
