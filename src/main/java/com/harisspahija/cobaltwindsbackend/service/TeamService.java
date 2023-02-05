package com.harisspahija.cobaltwindsbackend.service;

import com.harisspahija.cobaltwindsbackend.dto.TeamDto;
import com.harisspahija.cobaltwindsbackend.dto.TeamInputDto;
import com.harisspahija.cobaltwindsbackend.dto.TeamPrivateDto;
import com.harisspahija.cobaltwindsbackend.exception.ForbiddenActionException;
import com.harisspahija.cobaltwindsbackend.exception.RepositoryNoRecordException;
import com.harisspahija.cobaltwindsbackend.model.Player;
import com.harisspahija.cobaltwindsbackend.model.Team;
import com.harisspahija.cobaltwindsbackend.repository.PlayerRepository;
import com.harisspahija.cobaltwindsbackend.repository.TeamRepository;
import com.harisspahija.cobaltwindsbackend.util.ImageUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    private final UserService userService;
    private final PlayerService playerService;

    public TeamService(TeamRepository teamRepository, PlayerRepository playerRepository, UserService userService, PlayerService playerService) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.userService = userService;
        this.playerService = playerService;
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

        for (Team team : teamList) {
            TeamDto dto = transferToDto(team);
            teamDtoList.add(dto);
        }

        return teamDtoList;
    }

    public TeamDto getTeamById(String id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new RepositoryNoRecordException(id));
        return transferToDto(team);
    }

    public TeamPrivateDto getTeamByPlayerId(String id) {
        Team team = playerService.getPlayerById(id).getTeam();
        return transferToPrivateDto(team);
    }

    public TeamPrivateDto createTeam(TeamInputDto dto, String username) {
        String playerProfileId = userService.getPlayerIdByUsername(username);

        handleDuplicate(dto);
        Team team = transferToTeam(dto);
        team.setPassword("00000");
        team.setCreationDate(LocalDate.now());

        List<Player> players = new ArrayList<>();
        Player captain = playerService.addTeamToPlayer(playerProfileId, team);
        players.add(captain);

        team.setTeamCaptain(captain);
        team.setPlayers(players);

        teamRepository.save(team);
        playerRepository.save(captain);

        userService.addRoleToUsername(username, "TEAM_CAPTAIN");

        return transferToPrivateDto(team);
    }

    public TeamPrivateDto updateTeamByTeamId(String id, TeamInputDto teamInputDto) {
        handleDuplicate(teamInputDto);
        Team team = teamRepository.findById(id).orElseThrow(() -> new RepositoryNoRecordException(id));
        updateTeamDetails(team, teamInputDto);
        return transferToPrivateDto(team);
    }

    public TeamPrivateDto updateTeamByCaptainId(String playerId, TeamInputDto teamInputDto) {
        Team team = teamRepository.findTeamByTeamCaptainId(playerId).orElseThrow(() -> new RepositoryNoRecordException(playerId));
        updateTeamDetails(team, teamInputDto);

        return transferToPrivateDto(team);
    }

    private void updateTeamDetails(Team team, TeamInputDto teamInputDto) {
        team.setPassword(teamInputDto.getPassword());
        team.setName(teamInputDto.getName());
        team.setTeamLogo(teamInputDto.getTeamLogo());
        team.setTag(teamInputDto.getTag());
        team.setBiography(teamInputDto.getBiography());
        team.setOpenRoles(teamInputDto.getOpenRoles());

        teamRepository.save(team);
    }

    private void handleDuplicate(TeamInputDto teamInputDto) {
        Optional<Team> teamWithMatchingName = teamRepository.findByNameAndDisbandDateIsNull(teamInputDto.getName());
        if (teamWithMatchingName.isPresent()) {
            throw new DataIntegrityViolationException("Team with name already exists");
        }
        Optional<Team> teamWithMatchingTag = teamRepository.findByTagAndDisbandDateIsNull(teamInputDto.getTag());
        if (teamWithMatchingTag.isPresent()) {
            throw new DataIntegrityViolationException("Team with tag already exists");
        }
    }

    private void transferCommonProperties(Team team, TeamDto dto) {
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
    }

    private TeamDto transferToDto(Team team) {
        TeamDto dto = new TeamDto();
        transferCommonProperties(team, dto);
        return dto;
    }

    private TeamPrivateDto transferToPrivateDto(Team team) {
        TeamPrivateDto dto = new TeamPrivateDto();
        transferCommonProperties(team, dto);
        dto.setPassword(team.getPassword());
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

    public TeamPrivateDto joinTeam(String teamId, String playerId, String password) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new RepositoryNoRecordException(teamId));

        if (team.getPlayers().size() >= 7) {
            throw new DataIntegrityViolationException("Max player count reached");
        }
        if (!password.equals(team.getPassword())) {
            throw new ForbiddenActionException("Wrong password");
        }

        Player player = playerService.getPlayerById(playerId);

        if (player.getTeam() != null) {
            throw new DataIntegrityViolationException("You are already in a team");
        }

        List<Player> players = team.getPlayers();
        players.add(player);
        team.setPlayers(players);
        Team savedTeam = teamRepository.save(team);
        Player newTeamMember = playerService.addTeamToPlayer(playerId, savedTeam);
        playerRepository.save(newTeamMember);
        return transferToPrivateDto(savedTeam);
    }

    private void disbandTeam(Team team) {
        List<Player> players = team.getPlayers();

        for (Player player : team.getPlayers()) {
            player.setTeam(null);
        }

        playerRepository.saveAll(players);

        // TODO: #12 - Find team in matches
        if (false) {
            teamRepository.deleteById(team.getId());
        } else {
            team.setOpenRoles(null);
            team.setTeamCaptain(null);
            team.setDisbandDate(LocalDate.now());
            teamRepository.save(team);
        }
    }

    public void leaveTeam(String playerId) {
        Player player = playerService.getPlayerById(playerId);

        if(player.getTeam() == null) {
            throw new DataIntegrityViolationException("You are not in a team");
        }

        Team team = player.getTeam();
        List<Player> players = team.getPlayers();
        players.remove(player);

        if (team.getTeamCaptain() == player) {
            userService.removeRoleFromUserByPlayerId(playerId, "TEAM_CAPTAIN");
            if (!players.isEmpty()) {
                Player newCaptain = players.get(0);
                team.setTeamCaptain(newCaptain);
                userService.addRoleToUserByPlayerId(newCaptain.getId(), "TEAM_CAPTAIN");
            } else {
                disbandTeamByCaptainId(playerId);
            }
        }

        player.setTeam(null);

        playerRepository.save(player);
        teamRepository.save(team);
    }

    public void disbandTeamById(String id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new RepositoryNoRecordException(id));
        disbandTeam(team);
    }

    public void disbandTeamByCaptainId(String captainId) {
        Team team = teamRepository.findTeamByTeamCaptainId(captainId).orElseThrow(() -> new RepositoryNoRecordException(captainId));
        disbandTeam(team);
    }
}
