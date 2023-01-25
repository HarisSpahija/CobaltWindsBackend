package com.harisspahija.cobaltwindsbackend.repository;

import com.harisspahija.cobaltwindsbackend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, String> {
    List<Player> findAllPlayersByFreeAgent(boolean freeAgent);
}
