package service;

import dao.PlayerRepository;
import dto.PlayerCreateDto;
import dto.PlayerReadDto;
import dto.PlayerUpdateDto;
import entity.Player;
import lombok.RequiredArgsConstructor;
import mapper.PlayerCreateMapper;
import mapper.PlayerReadMapper;
import mapper.PlayerUpdateMapper;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerReadMapper playerReadMapper;
    private final PlayerCreateMapper playerCreateMapper;
    private final PlayerUpdateMapper playerUpdateMapper;

    public Long create(PlayerCreateDto playerDto) {
        Player playerEntity = playerCreateMapper.mapFrom(playerDto);
        return playerRepository.save(playerEntity).getId();
    }

    public boolean delete(Long id) {
        var maybePlayer = playerRepository.findById(id);
        maybePlayer.ifPresent(player -> playerRepository.delete(id));
        return maybePlayer.isPresent();
    }

    public void update(PlayerUpdateDto playerDto) {
        Player playerEntity = playerUpdateMapper.mapFrom(playerDto);
        playerRepository.update(playerEntity);
    }

    public Optional<PlayerReadDto> findPlayerById(Long id) {
        return playerRepository.findById(id).map(playerReadMapper::mapFrom);
    }

    public List<PlayerReadDto> findAllPlayers() {
        return playerRepository.findAll().stream().map(playerReadMapper::mapFrom).toList();
    }
}
