package com.harisspahija.cobaltwindsbackend;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

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

    @GetMapping("/players")
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    @PostMapping("/player")
    public Player addPlayer(@RequestBody Player player) {
        return player;
    }
}
