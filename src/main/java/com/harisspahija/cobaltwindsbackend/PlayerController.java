package com.harisspahija.cobaltwindsbackend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class PlayerController {
    ArrayList<Player> players = new ArrayList<>();
    Player sampleTopPlayer = new Player("Haris", LocalDate.of(1996, 11, 30), Role.Top, Role.Jungle, "https://www.op.gg/summoners/euw/Beedle", true);
    Player sampleMiddlePlayer = new Player("Haris", LocalDate.of(1996, 11, 30), Role.Middle, Role.ADC, "https://www.op.gg/summoners/euw/Beedle", true);
    Player sampleBotPlayer = new Player("Haris", LocalDate.of(1996, 11, 30), Role.Support, Role.ADC, "https://www.op.gg/summoners/euw/Beedle", true);
    Player sampleFillPlayer = new Player("Haris", LocalDate.of(1996, 11, 30), Role.Fill, Role.Support, "https://www.op.gg/summoners/euw/Beedle", true);

    public PlayerController() {
        this.players.add(sampleTopPlayer);
        this.players.add(sampleMiddlePlayer);
        this.players.add(sampleBotPlayer);
        this.players.add(sampleFillPlayer);
    }

    @GetMapping("/player")
    ArrayList<Player> getPlayers() {
        return this.players;
    }

    @GetMapping("/player/{id}")
    public Player getPlayer(@PathVariable String id) {
        for (Player player : players) {
            if (player.getId().equals(id)) {
                return player;
            }
        }
        throw new PlayerNotFoundException();
    }

    @PostMapping("/player")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        if (player.hasDuplicateRole()) {
            throw new PlayerDuplicateRoleException();
        }
        player.setId(UUID.randomUUID().toString());
        players.add(player);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @PutMapping("/player/{id}")
    public Player updatePlayer(@PathVariable String id, @RequestBody Player updatedPlayer) {
        if (updatedPlayer.hasDuplicateRole())
        {
            throw new PlayerDuplicateRoleException();
        }

        for (Player player : players) {
            if (player.getId().equals(id)) {
                player.setName(updatedPlayer.getName());
                player.setDateOfBirth(updatedPlayer.getDateOfBirth());
                player.setPrimaryRole(updatedPlayer.getPrimaryRole());
                player.setSecondaryRole(updatedPlayer.getSecondaryRole());
                player.setOpggLink(updatedPlayer.getOpggLink());
                player.setFreeAgent(updatedPlayer.getFreeAgent());
                return player;
            }
        }
        throw new PlayerNotFoundException();
    }
}
