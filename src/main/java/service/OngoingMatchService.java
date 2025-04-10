package service;

import dao.OngoingMatchRepository;
import dao.PlayerRepository;
import dto.OngoingMatchCreateDto;
import dto.OngoingMatchReadDto;
import dto.PlayerCreateDto;
import entity.Score;
import lombok.RequiredArgsConstructor;
import mapper.OngoingMatchCreateMapper;
import mapper.OngoingMatchReadMapper;
import mapper.PlayerCreateMapper;
import mapper.PlayerReadMapper;

@RequiredArgsConstructor
public class OngoingMatchService {
    private final OngoingMatchRepository ongoingMatchRepository;
    private final PlayerRepository playerRepository;
    private final PlayerReadMapper playerReadMapper;
    private final PlayerCreateMapper playerCreateMapper;
    private final OngoingMatchCreateMapper ongoingMatchCreateMapper;
    private final OngoingMatchReadMapper ongoingMatchReadMapper;

    public OngoingMatchReadDto startMatch(String player1, String player2) {
        Long id1 = playerRepository.findByName(player1) == null
                // name not found, create new player
                ? playerRepository.save(playerCreateMapper.mapFrom(new PlayerCreateDto(player1))).getId()
                // name found
                : playerReadMapper.mapFrom(playerRepository.findByName(player1)).id();
        Long id2 = playerRepository.findByName(player2) == null
                // name not found, create new player
                ? playerRepository.save(playerCreateMapper.mapFrom(new PlayerCreateDto(player2))).getId()
                // name found
                : playerReadMapper.mapFrom(playerRepository.findByName(player2)).id();
        return ongoingMatchReadMapper.mapFrom(ongoingMatchRepository.save(
                ongoingMatchCreateMapper.mapFrom(
                        new OngoingMatchCreateDto(Score.builder()
                                .player1(id1)
                                .player2(id2)
                                .build()))));
    }

    public OngoingMatchReadDto winPoint(Long player) {
        return null;
    }

    private void winMatch() {

    }
}
