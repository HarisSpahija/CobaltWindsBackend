package com.harisspahija.cobaltwindsbackend.service;

import com.harisspahija.cobaltwindsbackend.dto.PlayerDto;
import com.harisspahija.cobaltwindsbackend.dto.PlayerInputDto;
import com.harisspahija.cobaltwindsbackend.exception.*;
import com.harisspahija.cobaltwindsbackend.model.Player;
import com.harisspahija.cobaltwindsbackend.repository.PlayerRepository;

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
        if (playerList.isEmpty()) {
            throw new RepositoryNoRecordException();
        }

        List<PlayerDto> playerDtoList = new ArrayList<>();

        for(Player player : playerList) {
            PlayerDto dto = transferToDto(player);
            playerDtoList.add(dto);
        }

        return playerDtoList;
    }

    public PlayerDto getPlayerById(String id) {
        Optional<Player> playerOptional = playerRepository.findById(id);
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            return transferToDto(player);
        } else {
            throw new RepositoryNoRecordException(id);
        }
    }

    public PlayerDto createPlayer(PlayerInputDto dto) {
        if (dto.hasInvalidRoles()) {
            if (dto.hasDuplicateRole())
                throw new PlayerHasDuplicateRoleException(dto.getPrimaryRole(), dto.getSecondaryRole());

            if (dto.hasFillAndSecondaryRole())
                throw new PlayerHasPrimaryRoleFillAndSecondaryRoleNotNullException();

            if (dto.hasPrimaryAndNoSecondaryRole())
                throw new PlayerHasPrimaryRoleAndSecondaryRoleIsNullException(dto.getPrimaryRole());
        }

        Player player = transferToPlayer(dto);
        playerRepository.save(player);

        return transferToDto(player);
    }

    public PlayerDto updatePlayer(String id, PlayerInputDto dto) {
        if (dto.hasInvalidRoles()) {
            if (dto.hasDuplicateRole())
                throw new PlayerHasDuplicateRoleException(dto.getPrimaryRole(), dto.getSecondaryRole());

            if (dto.hasFillAndSecondaryRole())
                throw new PlayerHasPrimaryRoleFillAndSecondaryRoleNotNullException();
        }

        Optional<Player> playerOptional = playerRepository.findById(id);
        if (playerOptional.isPresent()) {
            Player player1 = playerOptional.get();

            player1.setName(dto.getName());
            player1.setDateOfBirth(dto.getDateOfBirth());
            player1.setFreeAgent(dto.isFreeAgent());
            player1.setOpggLink(dto.getOpggLink());
            player1.setPrimaryRole(dto.getPrimaryRole());
            player1.setSecondaryRole(dto.getSecondaryRole());
            Player returnPlayer = playerRepository.save(player1);

            return transferToDto(returnPlayer);
        } else {
            throw new RepositoryNoRecordException(id);
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
