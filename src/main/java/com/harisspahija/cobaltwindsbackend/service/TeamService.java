package com.harisspahija.cobaltwindsbackend.service;

import com.harisspahija.cobaltwindsbackend.dto.TeamDto;
import com.harisspahija.cobaltwindsbackend.dto.TeamInputDto;
import com.harisspahija.cobaltwindsbackend.exception.ForbiddenActionException;
import com.harisspahija.cobaltwindsbackend.exception.RepositoryNoRecordException;
import com.harisspahija.cobaltwindsbackend.model.Player;
import com.harisspahija.cobaltwindsbackend.model.Team;
import com.harisspahija.cobaltwindsbackend.repository.PlayerRepository;
import com.harisspahija.cobaltwindsbackend.repository.TeamRepository;
import org.springframework.dao.DataIntegrityViolationException;
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

    public TeamDto getTeamById(String id) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        if (teamOptional.isEmpty()) {
            throw new RepositoryNoRecordException(id);
        }

        Team team = teamOptional.get();
        return transferToDto(team);
    }

    public TeamDto createTeam(TeamInputDto dto, String captainId) {
          Optional<Player> teamCaptain = playerRepository.findById(captainId);
          if (teamCaptain.isEmpty()) {
              throw new RepositoryNoRecordException(captainId);
          }
          if (teamCaptain.get().getTeam() != null) {
              throw new DataIntegrityViolationException("You are already in a team");
          }

          handleDuplicate(dto);

          Team team = transferToTeam(dto);

          List<Player> players = new ArrayList<>();
          players.add(teamCaptain.get());

          team.setPassword("00000");
          team.setTeamCaptain(teamCaptain.get());
          team.setCreationDate(LocalDate.now());
          team.setPlayers(players);

          teamRepository.save(team);

          teamCaptain.get().setFreeAgent(false);
          teamCaptain.get().setTeam(team);

          playerRepository.save(teamCaptain.get());

          return transferToDto(team);
    }

    public TeamDto updateTeam(String id, TeamInputDto teamInputDto) {
        handleDuplicate(teamInputDto);

        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isEmpty()) {
            throw new RepositoryNoRecordException(id);
        }

        Team team = optionalTeam.get();

        team.setPassword(teamInputDto.getPassword());
        team.setName(teamInputDto.getName());
        team.setTeamLogo(teamInputDto.getTeamLogo());
        team.setTag(teamInputDto.getTag());
        team.setBiography(teamInputDto.getBiography());
        team.setOpenRoles(teamInputDto.getOpenRoles());

        teamRepository.save(team);
        return transferToDto(team);
    }

    private void handleDuplicate(TeamInputDto teamInputDto) {
        Optional<Team> teamWithMatchingName = teamRepository.findTeamByNameAndDisbandDateIsNotNull(teamInputDto.getName());
        if (teamWithMatchingName.isPresent()) {
            throw new DataIntegrityViolationException("Team with name already exists");
        }
        Optional<Team> teamWithMatchingTag = teamRepository.findTeamByTagAndDisbandDateIsNotNull(teamInputDto.getTag());
        if (teamWithMatchingTag.isPresent()) {
            throw new DataIntegrityViolationException("Team with tag already exists");
        }
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
        dto.setPlayers(team.getPlayers() == null ? new ArrayList<>() : team.getPlayers());
        dto.setOpenRoles(team.getOpenRoles() == null ? new ArrayList<>() : team.getOpenRoles());

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

    public TeamDto joinTeam(String teamId, String playerId, String password) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new RepositoryNoRecordException(teamId));

        if (team.getPlayers().size() >= 7) {
            throw new DataIntegrityViolationException("Max player count reached");
        }
        if (!password.equals(team.getPassword())) {
            throw new ForbiddenActionException("Wrong password");
        }

        Player player = playerRepository.findById(playerId).orElseThrow(() -> new RepositoryNoRecordException(playerId));

        if (player.getTeam() != null) {
            throw new DataIntegrityViolationException("You are already in a team");
        }
        List<Player> players = team.getPlayers();
        players.add(player);

        team.setPlayers(players);
        teamRepository.save(team);

        return transferToDto(team);
    }

    public void disbandTeam(String id) {
        Optional<Team> team = teamRepository.findById(id);

        if (team.isEmpty()) {
            throw new RepositoryNoRecordException(id);
        }

        List<Player> players = team.get().getPlayers();

        for (Player player : team.get().getPlayers()) {
            player.setTeam(null);
        }

        playerRepository.saveAll(players);

        // TODO: #12 - Find team in matches
        if (false) {
            teamRepository.deleteById(id);
        } else {
            team.get().setOpenRoles(null);
            team.get().setTeamCaptain(null);
            team.get().setDisbandDate(LocalDate.now());
            teamRepository.save(team.get());
        }
    }
}
