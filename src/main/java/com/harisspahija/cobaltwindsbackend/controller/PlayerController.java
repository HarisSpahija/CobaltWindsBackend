package com.harisspahija.cobaltwindsbackend.controller;

import com.harisspahija.cobaltwindsbackend.dto.PlayerDto;
import com.harisspahija.cobaltwindsbackend.dto.PlayerInputDto;
import com.harisspahija.cobaltwindsbackend.exception.BadRequestBindingException;
import com.harisspahija.cobaltwindsbackend.security.JwtService;
import com.harisspahija.cobaltwindsbackend.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("players")
public class PlayerController {

    private final PlayerService playerService;
    private final JwtService jwtService;


    @Autowired
    public PlayerController(PlayerService playerService, JwtService jwtService) {
        this.playerService = playerService;
        this.jwtService = jwtService;
    }

    @GetMapping("")
    public ResponseEntity<List<PlayerDto>> getAllPlayers(@RequestParam(value = "freeAgent", required = false) Optional<Boolean> freeAgent) {
        List<PlayerDto> dtos;

        if (freeAgent.isEmpty()) {
            dtos = playerService.getAllPlayers();
        } else {
            dtos = playerService.getAllPlayersByFreeAgent(freeAgent.get());
        }
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<PlayerDto> getPlayer(@PathVariable("id") String id) {
        PlayerDto player = playerService.getPlayerById(id);
        return ResponseEntity.ok().body(player);
    }

    @PostMapping("")
    public ResponseEntity<Object> createPlayer(@RequestHeader(name = "Authorization") String token,
                                               @Valid @RequestBody PlayerInputDto playerInputDto,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestBindingException(bindingResult);
        }

        String username = jwtService.extractUsername(token.substring(7));
        PlayerDto dto = playerService.createPlayer(playerInputDto, username);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updatePlayer(@PathVariable("id") String id, @Valid @RequestBody PlayerInputDto playerInputDto) {
        PlayerDto dto = playerService.updatePlayer(id, playerInputDto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletePlayer(@PathVariable("id") String id) {
        playerService.deletePlayerById(id);
        return ResponseEntity.noContent().build();
    }
}
