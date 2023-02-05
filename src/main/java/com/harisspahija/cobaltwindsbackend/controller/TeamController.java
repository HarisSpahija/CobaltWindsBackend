package com.harisspahija.cobaltwindsbackend.controller;

import com.harisspahija.cobaltwindsbackend.dto.TeamDto;
import com.harisspahija.cobaltwindsbackend.dto.TeamInputDto;
import com.harisspahija.cobaltwindsbackend.dto.TeamJoinInputDto;
import com.harisspahija.cobaltwindsbackend.exception.BadRequestBindingException;
import com.harisspahija.cobaltwindsbackend.security.JwtService;
import com.harisspahija.cobaltwindsbackend.service.PlayerService;
import com.harisspahija.cobaltwindsbackend.service.TeamService;
import com.harisspahija.cobaltwindsbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("teams")
public class TeamController {
    private final TeamService teamService;
    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public TeamController(TeamService teamService, UserService userService, JwtService jwtService) {
        this.teamService = teamService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("")
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        List<TeamDto> dtos = teamService.getAllTeams();

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("my-team")
    public ResponseEntity<Object> getTeamMe(@RequestHeader(name = "Authorization") String token
    ) {
        String username = jwtService.extractUsername(token.substring(7));
        String playerId = userService.getPlayerIdByUsername(username);
        TeamDto team = teamService.getTeamByCaptainId(playerId);
        return ResponseEntity.ok().body(team);
    }

    @PostMapping("")
    public ResponseEntity<Object> createTeam(@RequestHeader(name = "Authorization") String token, @Valid @RequestBody TeamInputDto teamInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestBindingException(bindingResult);
        }

        String username = jwtService.extractUsername(token.substring(7));
        TeamDto dto = teamService.createTeam(teamInputDto, username);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/my-team").build().toUri();

        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping("my-team")
    public ResponseEntity<Object> updateTeamMe(@RequestHeader(name = "Authorization") String token, @Valid @RequestBody TeamInputDto teamInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestBindingException(bindingResult);
        }

        String username = jwtService.extractUsername(token.substring(7));
        String playerId = userService.getPlayerIdByUsername(username);

        TeamDto dto = teamService.updateTeamByCaptainId(playerId, teamInputDto);
        return ResponseEntity.ok().body(dto);
    }

    // TODO: Implement disbanding based on team captain id
    @DeleteMapping("my-team")
    public void deleteTeamMe() {
    }

    // TODO: Implement leaving based on player id
    @PutMapping("my-team/leave")
    public void leaveTeamMe() {}

    @PostMapping("{id}/join")
    public ResponseEntity<Object> joinTeam(@PathVariable("id") String teamId, @Valid @RequestBody TeamJoinInputDto teamJoinInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestBindingException(bindingResult);
        }

        TeamDto dto = teamService.joinTeam(teamId, teamJoinInputDto.getPlayerId(), teamJoinInputDto.getPassword());
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateTeam(@PathVariable("id") String teamId, @Valid @RequestBody TeamInputDto teamInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestBindingException(bindingResult);
        }
        TeamDto dto = teamService.updateTeamByTeamId(teamId, teamInputDto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("{id}")
    public ResponseEntity<TeamDto> getTeam(@PathVariable("id") String id) {
        TeamDto team = teamService.getTeamById(id);
        return ResponseEntity.ok().body(team);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> disbandTeam(@PathVariable("id") String id) {
        teamService.disbandTeam(id);
        return ResponseEntity.noContent().build();
    }
}
