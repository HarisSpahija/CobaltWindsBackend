package com.harisspahija.cobaltwindsbackend.repository;

import com.harisspahija.cobaltwindsbackend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, String> {
    List<Player> findAllPlayersByFreeAgent(boolean freeAgent);

    Optional<Player> findPlayerByOpggLink(String opggLink);

    @Query("SELECT p FROM Player p WHERE p.opggLink = :opggLink AND p.id <> :id")
    Optional<Player> findPlayerByOpggLinkExcludingId(@Param("opggLink") String opggLink, @Param("id") String id);
}
