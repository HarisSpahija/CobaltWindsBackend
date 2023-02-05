package com.harisspahija.cobaltwindsbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.harisspahija.cobaltwindsbackend.enums.CompetitionStatus;
import com.harisspahija.cobaltwindsbackend.model.CompetitionRegistration;

import java.time.LocalDate;
import java.util.Collection;

public class CompetitionDto {
    private String id;
    private String name;
    private String logo;
    private String description;
    private short maxTeamCount;
    private LocalDate startDate;
    private LocalDate registrationDate;
    private CompetitionStatus status;

    private Collection<CompetitionRegistration> registrations;

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

    @JsonIgnoreProperties({"signups", "registrations", "teamCaptain", "players", "competition" })
    public Collection<CompetitionRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Collection<CompetitionRegistration> registrations) {
        this.registrations = registrations;
    }
}
