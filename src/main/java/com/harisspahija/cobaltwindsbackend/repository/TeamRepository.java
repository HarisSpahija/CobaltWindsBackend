package com.harisspahija.cobaltwindsbackend.repository;

import com.harisspahija.cobaltwindsbackend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TeamRepository extends JpaRepository<Team, String> {
    Optional<Team> findTeamByTagAndDisbandDateIsNotNull(String tag);
    Optional<Team> findTeamByNameAndDisbandDateIsNotNull(String name);
}
