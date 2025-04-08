package service;

import dao.PlayerRepository;
import dto.PlayerCreateDto;
import dto.PlayerReadDto;
import entity.Player;
import lombok.RequiredArgsConstructor;
import mapper.PlayerCreateMapper;
import mapper.PlayerReadMapper;

import java.util.Optional;

@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerReadMapper playerReadMapper;
    private final PlayerCreateMapper playerCreateMapper;

    public Long create(PlayerCreateDto playerDto) {
        Player playerEntity = playerCreateMapper.mapFrom(playerDto);
        return playerRepository.save(playerEntity).getId();
    }

    public boolean delete(Long id) {
        var maybePlayer = playerRepository.findById(id);
        maybePlayer.ifPresent(player -> playerRepository.delete(id));
        return maybePlayer.isPresent();
    }

    public Optional<PlayerReadDto> findPlayerById(Long id) {
        return playerRepository.findById(id).map(playerReadMapper::mapFrom);
    }
}
