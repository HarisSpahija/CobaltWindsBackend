package com.harisspahija.cobaltwindsbackend.service;

import com.harisspahija.cobaltwindsbackend.dto.PlayerDto;
import com.harisspahija.cobaltwindsbackend.dto.PlayerInputDto;
import com.harisspahija.cobaltwindsbackend.exception.*;
import com.harisspahija.cobaltwindsbackend.model.Player;
import com.harisspahija.cobaltwindsbackend.repository.PlayerRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerDto> getAllPlayers() {
        List<Player> playerList = playerRepository.findAll();
        return getPlayerDtos(playerList);
    }

    public List<PlayerDto> getAllPlayersByFreeAgent(Boolean freeAgent) {
        List<Player> playerList = playerRepository.findAllPlayersByFreeAgent(freeAgent);
        return getPlayerDtos(playerList);
    }

    private List<PlayerDto> getPlayerDtos(List<Player> playerList) {
        List<PlayerDto> playerDtoList = new ArrayList<>();

        if (playerList.isEmpty()) {
            return playerDtoList;
        }

        for(Player player : playerList) {
            PlayerDto dto = transferToDto(player);
            playerDtoList.add(dto);
        }

        return playerDtoList;
    }

    public PlayerDto getPlayerById(String id) {
        Optional<Player> playerOptional = playerRepository.findById(id);
        if (playerOptional.isEmpty()) {
            throw new RepositoryNoRecordException(id);
        }

        Player player = playerOptional.get();
        return transferToDto(player);
    }

    public PlayerDto createPlayer(PlayerInputDto dto) {
        checkValidRoles(dto);

        // TODO: #11 Handle duplicate check
        Optional<Player> playerWithMatchingOpgg = playerRepository.findPlayerByOpggLink(dto.getOpggLink());
        if (playerWithMatchingOpgg.isPresent()) {
            throw new DataIntegrityViolationException("player with opggLink already exists");
        }

        Player player = transferToPlayer(dto);
        playerRepository.save(player);
        return transferToDto(player);

    }

    public PlayerDto updatePlayer(String id, PlayerInputDto dto) {
        checkValidRoles(dto);

        // TODO: #11 Handle duplicate check
        Optional<Player> playerWithMatchingOpgg = playerRepository.findPlayerByOpggLink(dto.getOpggLink());
        if (playerWithMatchingOpgg.isPresent()) {
            throw new DataIntegrityViolationException("player with opggLink already exists");
        }

        Optional<Player> playerOptional = playerRepository.findById(id);
        if (playerOptional.isEmpty()) {
            throw new RepositoryNoRecordException(id);
        }

        Player player = playerOptional.get();

        player.setName(dto.getName());
        player.setDateOfBirth(dto.getDateOfBirth());
        player.setFreeAgent(dto.isFreeAgent());
        player.setOpggLink(dto.getOpggLink());
        player.setPrimaryRole(dto.getPrimaryRole());
        player.setSecondaryRole(dto.getSecondaryRole());

        playerRepository.save(player);
        return transferToDto(player);
    }

    private static void checkValidRoles(PlayerInputDto dto) {
        if (dto.hasInvalidRoles()) {
            if (dto.hasDuplicateRole())
                throw new PlayerHasDuplicateRoleException(dto.getPrimaryRole(), dto.getSecondaryRole());

            if (dto.hasFillAndSecondaryRole())
                throw new PlayerHasPrimaryRoleFillAndSecondaryRoleNotNullException();
        }
    }

    public Player transferToPlayer(PlayerInputDto dto) {
        var player = new Player();

        player.setName(dto.getName());
        player.setDateOfBirth(dto.getDateOfBirth());
        player.setFreeAgent(dto.isFreeAgent());
        player.setPrimaryRole(dto.getPrimaryRole());
        player.setSecondaryRole(dto.getSecondaryRole());
        player.setOpggLink(dto.getOpggLink());

        return player;
    }

    public PlayerDto transferToDto(Player player) {
        PlayerDto dto = new PlayerDto();

        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setDateOfBirth(player.getDateOfBirth());
        dto.setFreeAgent(player.isFreeAgent());
        dto.setPrimaryRole(player.getPrimaryRole());
        dto.setSecondaryRole(player.getSecondaryRole());
        dto.setOpggLink(player.getOpggLink());

        return dto;
    }

    public void deletePlayerById(String id) {
        playerRepository.deleteById(id);
    }
}
