package com.harisspahija.cobaltwindsbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.harisspahija.cobaltwindsbackend.dto.TeamDto;
import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "CompetitionRegistrations")
public class CompetitionRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @JsonProperty
    public TeamDto getTeam() {
        var dto = new TeamDto();

        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setTag(team.getTag());
        dto.setTeamLogo(team.getTeamLogo());

        return dto;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}