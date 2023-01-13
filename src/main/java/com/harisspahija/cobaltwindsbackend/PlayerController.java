package com.harisspahija.cobaltwindsbackend;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
public class PlayerController {
    ArrayList<Player> players = new ArrayList<>();
    Player samplePlayer = new Player("Haris", LocalDate.of(1996, 11, 30), Role.Top, Role.Jungle, "https://www.op.gg/summoners/euw/Beedle", true);

    public PlayerController() {
        this.players.add(samplePlayer);
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
