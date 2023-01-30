package com.harisspahija.cobaltwindsbackend.controller;

import com.harisspahija.cobaltwindsbackend.dto.TeamDto;
import com.harisspahija.cobaltwindsbackend.dto.TeamInputDto;
import com.harisspahija.cobaltwindsbackend.exception.BadRequestBindingException;
import com.harisspahija.cobaltwindsbackend.exception.BadRequestCustomException;
import com.harisspahija.cobaltwindsbackend.service.TeamService;
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

    @Autowired
    public TeamController(TeamService teamService) { this.teamService = teamService; }

    @GetMapping("")
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        List<TeamDto> dtos = teamService.getAllTeams();

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<TeamDto> getTeam(@PathVariable("id")String id) {
        TeamDto team = teamService.getTeamById(id);
        return ResponseEntity.ok().body(team);
    }

    @PostMapping("")
    public ResponseEntity<Object> createTeam(@RequestParam(value = "captainId") String captainId, @Valid @RequestBody TeamInputDto teamInputDto, BindingResult bindingResult) {
        if (captainId.isBlank() || captainId.isEmpty()) {
            throw new BadRequestCustomException("Must provide captain id");
        }
        if (bindingResult.hasErrors()) {
            throw new BadRequestBindingException(bindingResult);
        }

        TeamDto dto = teamService.createTeam(teamInputDto, captainId);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateTeam(@PathVariable("id") String id, @Valid @RequestBody TeamInputDto teamInputDto) {
        // TODO: Add authentication that only team owner can adjust team
        TeamDto dto = teamService.updateTeam(id, teamInputDto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> disbandTeam(@PathVariable("id") String id) {
        teamService.disbandTeam(id);
        return ResponseEntity.noContent().build();
    }
}
