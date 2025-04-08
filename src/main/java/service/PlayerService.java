package service;

import dao.PlayerRepository;
import dto.ReadPlayerDto;
import lombok.RequiredArgsConstructor;
import mapper.PlayerReadMapper;

import java.util.Optional;

@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerReadMapper playerReadMapper;

    public boolean delete(Long id) {
        var maybePlayer = playerRepository.findById(id);
        maybePlayer.ifPresent(player -> playerRepository.delete(id));
        return maybePlayer.isPresent();
    }

    public Optional<ReadPlayerDto> findPlayerById(Long id) {
        return playerRepository.findById(id).map(playerReadMapper::mapFrom);
    }
}
