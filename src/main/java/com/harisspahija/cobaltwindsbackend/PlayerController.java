package com.harisspahija.cobaltwindsbackend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PlayerController {
    ArrayList<Player> players = new ArrayList<>();
    Player sampleTopPlayer = new Player("Haris", LocalDate.of(1996, 11, 30), Role.Top, Role.Jungle, "https://www.op.gg/summoners/euw/Beedle", true);
    Player sampleMiddlePlayer = new Player("Haris", LocalDate.of(1996, 11, 30), Role.Middle, Role.ADC, "https://www.op.gg/summoners/euw/Beedle", true);
    Player sampleBotPlayer = new Player("Haris", LocalDate.of(1996, 11, 30), Role.Support, Role.ADC, "https://www.op.gg/summoners/euw/Beedle", true);
    Player sampleFillPlayer = new Player("Haris", LocalDate.of(1996, 11, 30), Role.Fill, Role.Support, "https://www.op.gg/summoners/euw/Beedle", true);

    public PlayerController() {
        sampleTopPlayer.setId("1");
        sampleMiddlePlayer.setId("2");
        sampleBotPlayer.setId("3");
        sampleFillPlayer.setId("4");

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
    public ResponseEntity<Object> getPlayer(@PathVariable String id) {
        Optional<Player> player = players.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (player.isPresent()) {
            return new ResponseEntity<>(player.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Player not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/player")
    public ResponseEntity<Object> createPlayer(@RequestBody Player player) {
        if (player.hasDuplicateRole()) {
            return new ResponseEntity<>("A player cannot have the same role for primary and secondary. Please choose a different role for primary and secondary.", HttpStatus.BAD_REQUEST);
        }
        player.setId(UUID.randomUUID().toString());
        players.add(player);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<Object> updatePlayer(@PathVariable String id, @RequestBody Player updatedPlayer) {
        if (updatedPlayer.hasDuplicateRole())
        {
            return new ResponseEntity<>("A player cannot have the same role for primary and secondary. Please choose a different role for primary and secondary.", HttpStatus.BAD_REQUEST);
        }

        for (Player player : players) {
            if (player.getId().equals(id)) {
                player.setName(updatedPlayer.getName());
                player.setDateOfBirth(updatedPlayer.getDateOfBirth());
                player.setPrimaryRole(updatedPlayer.getPrimaryRole());
                player.setSecondaryRole(updatedPlayer.getSecondaryRole());
                player.setOpggLink(updatedPlayer.getOpggLink());
                player.setFreeAgent(updatedPlayer.getFreeAgent());
                return new ResponseEntity<>(player, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Player not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
