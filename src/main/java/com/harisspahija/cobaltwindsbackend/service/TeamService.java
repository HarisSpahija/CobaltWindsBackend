package com.harisspahija.cobaltwindsbackend.service;

import com.harisspahija.cobaltwindsbackend.dto.TeamDto;
import com.harisspahija.cobaltwindsbackend.exception.RepositoryNoRecordException;
import com.harisspahija.cobaltwindsbackend.model.Team;
import com.harisspahija.cobaltwindsbackend.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
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
}
