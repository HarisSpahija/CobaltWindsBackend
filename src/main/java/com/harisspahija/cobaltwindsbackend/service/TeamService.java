package com.harisspahija.cobaltwindsbackend.service;

import com.harisspahija.cobaltwindsbackend.dto.TeamDto;
import com.harisspahija.cobaltwindsbackend.dto.TeamInputDto;
import com.harisspahija.cobaltwindsbackend.exception.BadRequestCustomException;
import com.harisspahija.cobaltwindsbackend.exception.RepositoryNoRecordException;
import com.harisspahija.cobaltwindsbackend.model.Player;
import com.harisspahija.cobaltwindsbackend.model.Team;
import com.harisspahija.cobaltwindsbackend.repository.PlayerRepository;
import com.harisspahija.cobaltwindsbackend.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public TeamService(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public List<TeamDto> getAllTeams() {
        List<Team> teamList = teamRepository.findAll();
        return getTeamDtos(teamList);
    }

    private List<TeamDto> getTeamDtos(List<Team> teamList) {
        List<TeamDto> teamDtoList = new ArrayList<>();

        if (teamList.isEmpty()) {
            return teamDtoList;
        }

        for(Team team : teamList) {
            TeamDto dto = transferToDto(team);
            teamDtoList.add(dto);
        }

        return teamDtoList;
    }

    public TeamDto createTeam(TeamInputDto dto, String captainId) {
          Optional<Player> teamCaptain = playerRepository.findById(captainId);
          if (teamCaptain.isEmpty()) {
              throw new RepositoryNoRecordException(captainId);
          }
          if (teamCaptain.get().getTeam() != null) {
              throw new BadRequestCustomException("You are already in a team");
          }

          Team team = transferToTeam(dto);

          team.setPassword("0000");
          team.setTeamCaptain(teamCaptain.get());
          team.setCreationDate(LocalDate.now());

          teamRepository.save(team);

          teamCaptain.get().setFreeAgent(false);
          teamCaptain.get().setTeam(team);
          playerRepository.save(teamCaptain.get());

          return transferToDto(team);
    }

    private TeamDto transferToDto(Team team) {
        TeamDto dto = new TeamDto();

        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setTag(team.getTag());
        dto.setTeamLogo(team.getTeamLogo());
        dto.setBiography(team.getBiography());
        dto.setCreationDate(team.getCreationDate());
        dto.setDisbandDate(team.getDisbandDate());
        dto.setTeamCaptain(team.getTeamCaptain());
        dto.setOpenRoles(team.getOpenRoles());

        return dto;
    }

    private Team transferToTeam(TeamInputDto dto) {
        var team = new Team();

        team.setName(dto.getName());
        team.setTag(dto.getTag());
        team.setPassword(dto.getPassword());
        team.setTeamLogo(dto.getTeamLogo());
        team.setBiography(dto.getBiography());

        return team;
    }
}
