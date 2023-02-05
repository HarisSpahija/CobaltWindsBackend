package com.harisspahija.cobaltwindsbackend.controller;

import com.harisspahija.cobaltwindsbackend.dto.TeamDto;
import com.harisspahija.cobaltwindsbackend.dto.TeamInputDto;
import com.harisspahija.cobaltwindsbackend.dto.TeamJoinInputDto;
import com.harisspahija.cobaltwindsbackend.dto.TeamPrivateDto;
import com.harisspahija.cobaltwindsbackend.exception.BadRequestBindingException;
import com.harisspahija.cobaltwindsbackend.security.JwtService;
import com.harisspahija.cobaltwindsbackend.service.TeamService;
import com.harisspahija.cobaltwindsbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
        TeamPrivateDto team = teamService.getTeamByPlayerId(playerId);
        return ResponseEntity.ok().body(team);
    }

    @PostMapping("")
    public ResponseEntity<Object> createTeam(@RequestHeader(name = "Authorization") String token,
                                             @Valid @RequestBody TeamInputDto teamInputDto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestBindingException(bindingResult);
        }

        String username = jwtService.extractUsername(token.substring(7));
        TeamPrivateDto dto = teamService.createTeam(teamInputDto, username);

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

        TeamPrivateDto dto = teamService.updateTeamByCaptainId(playerId, teamInputDto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("my-team")
    public ResponseEntity<Object> deleteTeamMe(
            @RequestHeader(name = "Authorization") String token
    ) {
        String username = jwtService.extractUsername(token.substring(7));
        String playerId = userService.getPlayerIdByUsername(username);

        teamService.disbandTeamByCaptainId(playerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("my-team/leave")
    public ResponseEntity<Object> leaveTeamMe(
            @RequestHeader(name = "Authorization") String token
    ) {
        String username = jwtService.extractUsername(token.substring(7));
        String playerId = userService.getPlayerIdByUsername(username);

        teamService.leaveTeam(playerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/join")
    public ResponseEntity<Object> joinTeam(@RequestHeader(name = "Authorization") String token, @PathVariable("id") String teamId, @Valid @RequestBody TeamJoinInputDto teamJoinInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestBindingException(bindingResult);
        }
        String username = jwtService.extractUsername(token.substring(7));
        String playerId = userService.getPlayerIdByUsername(username);

        TeamPrivateDto dto = teamService.joinTeam(teamId, playerId, teamJoinInputDto.getPassword());
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateTeam(@PathVariable("id") String teamId, @Valid @RequestBody TeamInputDto teamInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestBindingException(bindingResult);
        }
        TeamPrivateDto dto = teamService.updateTeamByTeamId(teamId, teamInputDto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("{id}")
    public ResponseEntity<TeamDto> getTeam(@PathVariable("id") String id) {
        TeamDto team = teamService.getTeamById(id);
        return ResponseEntity.ok().body(team);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> disbandTeam(@PathVariable("id") String id) {
        teamService.disbandTeamById(id);
        return ResponseEntity.noContent().build();
    }
}
