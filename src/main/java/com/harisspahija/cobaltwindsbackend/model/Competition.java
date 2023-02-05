package com.harisspahija.cobaltwindsbackend.model;

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
}
