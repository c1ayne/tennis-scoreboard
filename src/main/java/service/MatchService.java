package service;

import dao.MatchRepository;
import dto.MatchCreateDto;
import dto.MatchReadDto;
import dto.PlayerReadDto;
import entity.Match;
import entity.Player;
import lombok.RequiredArgsConstructor;
import mapper.MatchCreateMapper;
import mapper.MatchReadMapper;
import mapper.PlayerReadMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final MatchCreateMapper matchCreateMapper;
    private final MatchReadMapper matchReadMapper;

    public UUID create(MatchCreateDto matchDto) {
        Match match = matchCreateMapper.mapFrom(matchDto);
        return matchRepository.save(match).getId();
    }

    public boolean delete(UUID id) {
        var maybeMatch = matchRepository.findById(id);
        maybeMatch.ifPresent(match -> matchRepository.delete(id));
        return maybeMatch.isPresent();
    }

    public Optional<MatchReadDto> findMatchById(UUID id) {
        return matchRepository.findById(id).map(matchReadMapper::mapFrom);
    }

    public List<MatchReadDto> findAllMatches() {
        return matchRepository.findAll().stream().map(matchReadMapper::mapFrom).toList();
    }

    public List<MatchReadDto> findMatchByPlayer(PlayerReadDto playerReadDto) {
        return matchRepository.findByPlayer(Player.builder()
                                .id(playerReadDto.id())
                                .name(playerReadDto.name())
                                .build())
                .stream().map(matchReadMapper::mapFrom).toList();
    }
}
