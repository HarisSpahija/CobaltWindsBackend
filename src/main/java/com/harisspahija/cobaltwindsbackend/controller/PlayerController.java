package com.harisspahija.cobaltwindsbackend.controller;

import com.harisspahija.cobaltwindsbackend.model.Player;
import com.harisspahija.cobaltwindsbackend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PlayerController {
    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/players")
    public ResponseEntity<List<Player>> getAllPlayers() {
        try {
            List<Player> players = new ArrayList<>(playerRepository.findAll());

            return new ResponseEntity<>(players, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/players/free-agents")
    public ResponseEntity<List<Player>> getAllFreeAgents() {
        try {
            List<Player> players = playerRepository.findByFreeAgent(true);

            if (players.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(players, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<Object> getPlayer(@PathVariable String id) {
        Optional<Player> playerData = playerRepository.findById(id);

        if (playerData.isPresent()) {
            return new ResponseEntity<>(playerData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Player not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/players")
    public ResponseEntity<Object> createPlayer(@RequestBody Player player) {
        if (player.hasDuplicateRole())
            return new ResponseEntity<>("player can not have duplicate roles", HttpStatus.BAD_REQUEST);

        try {
            Player _player = playerRepository.save(new Player(player.getName(), player.getDateOfBirth(), player.getPrimaryRole(), player.getSecondaryRole(), player.getOpggLink(), player.getFreeAgent()));
            return new ResponseEntity<>(_player, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<Object> updatePlayer(@PathVariable("id") String id, @RequestBody Player player) {
        if (player.hasDuplicateRole())
            return new ResponseEntity<>("player can not have duplicate roles", HttpStatus.BAD_REQUEST);

        Optional<Player> playerData = playerRepository.findById(id);

        if (playerData.isPresent()) {
            Player _player = playerData.get();
            _player.setName(player.getName());
            _player.setDateOfBirth(player.getDateOfBirth());
            _player.setPrimaryRole(player.getPrimaryRole());
            _player.setSecondaryRole(player.getSecondaryRole());
            _player.setOpggLink(player.getOpggLink());
            _player.setFreeAgent(player.getFreeAgent());

            return new ResponseEntity<>(playerRepository.save(_player), HttpStatus.OK);
        }
        return new ResponseEntity<>("Player not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity<Object> deletePlayer(@PathVariable("id") String id) {
        Optional<Player> playerData = playerRepository.findById(id);

        if (playerData.isPresent()) {
            playerRepository.delete(playerData.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Player not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
