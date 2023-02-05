package com.harisspahija.cobaltwindsbackend.service;

import com.harisspahija.cobaltwindsbackend.dto.CompetitionDto;
import com.harisspahija.cobaltwindsbackend.model.Competition;
import com.harisspahija.cobaltwindsbackend.repository.CompetitionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CompetitionService {
    private final CompetitionRepository competitionRepository;

    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public List<CompetitionDto> getAllCompetitions() {
        Collection<Competition> competitions = competitionRepository.findAll();

        return competitionsToDto(competitions);
    }

    private List<CompetitionDto> competitionsToDto(Collection<Competition> competitions) {
        List<CompetitionDto> dtoList = new ArrayList<>();

        for (Competition competition : competitions
        ) {
            dtoList.add(competitionToDto(competition));
        }

        return dtoList;
    }

    private CompetitionDto competitionToDto(Competition competition) {
        var dto = new CompetitionDto();

        dto.setId(competition.getId());
        dto.setName(competition.getName());
        dto.setLogo(competition.getLogo());
        dto.setDescription(competition.getDescription());
        dto.setMaxTeamCount(competition.getMaxTeamCount());
        dto.setStartDate(competition.getStartDate());
        dto.setRegistrationDate(competition.getRegistrationDate());
        dto.setStatus(competition.getStatus());
        dto.setRegistrations(competition.getRegistrations());

        return dto;
    }
}
