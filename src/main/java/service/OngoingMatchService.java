package service;

import dao.MatchRepository;
import dao.OngoingMatchRepository;
import dao.PlayerRepository;
import dto.*;
import entity.Score;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import mapper.*;

import java.util.Optional;

@RequiredArgsConstructor
public class OngoingMatchService {
    private final OngoingMatchRepository ongoingMatchRepository;
    private final PlayerRepository playerRepository;
    private final PlayerReadMapper playerReadMapper;
    private final PlayerCreateMapper playerCreateMapper;
    private final OngoingMatchCreateMapper ongoingMatchCreateMapper;
    private final OngoingMatchReadMapper ongoingMatchReadMapper;
    private final OngoingMatchUpdateMapper ongoingMatchUpdateMapper;
    private final MatchRepository matchRepository;
    private final MatchCreateMapper matchCreateMapper;
    private final MatchReadMapper matchReadMapper;

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

    public OngoingMatchReadDto winPoint(Long matchId, Long player) {
        OngoingMatchReadDto match = ongoingMatchRepository.findById(matchId)
                .map(ongoingMatchReadMapper::mapFrom)
                .orElseThrow(() -> new EntityNotFoundException("match not founded"));
        if (match.score().getPlayer1().equals(player)) {
            if (match.score().getPoint1().equals(0)) {
                match.score().setPoint1(15);
            } else if (match.score().getPoint1().equals(15)) {
                match.score().setPoint1(30);
            } else if (match.score().getPoint1().equals(30)) {
                match.score().setPoint1(40);
            } else if (match.score().getPoint1().equals(40)) {
                if (match.score().getPoint2() < 40) {
                    match = winGame(matchId, player);
                } else if (match.score().getPoint2().equals(40)) {
                    match.score().setPoint1(41);
                } else if (match.score().getPoint2().equals(41)) {
                    match.score().setPoint2(40);
                }
            } else if (match.score().getPoint1().equals(41)) {
                match = winGame(matchId, player);
            }
        } else if (match.score().getPlayer2().equals(player)) {
            if (match.score().getPoint2().equals(0)) {
                match.score().setPoint2(15);
            } else if (match.score().getPoint2().equals(15)) {
                match.score().setPoint2(30);
            } else if (match.score().getPoint2().equals(30)) {
                match.score().setPoint2(40);
            } else if (match.score().getPoint2().equals(40)) {
                if (match.score().getPoint1() < 40) {
                    match = winGame(matchId, player);
                } else if (match.score().getPoint1().equals(40)) {
                    match.score().setPoint2(41);
                } else if (match.score().getPoint1().equals(41)) {
                    match.score().setPoint1(40);
                }
            } else if (match.score().getPoint2().equals(41)) {
                match = winGame(matchId, player);
            }
        } else {
            throw new RuntimeException("Invalid player id");
        }

        ongoingMatchRepository.update(ongoingMatchUpdateMapper.mapFrom(match));

        return match;
    }

    private OngoingMatchReadDto winGame(Long matchId, Long player) {
        OngoingMatchReadDto match = ongoingMatchRepository.findById(matchId)
                .map(ongoingMatchReadMapper::mapFrom)
                .orElseThrow(() -> new EntityNotFoundException("match not founded"));
        if (match.score().getPlayer1().equals(player)) {
            match.score().setGame1(match.score().getGame1() + 1);
            if (match.score().getGame1() >= 6 && (match.score().getGame1() - match.score().getGame2() >=2)) {
                match = winSet(matchId, player);
            }
        } else if (match.score().getPlayer2().equals(player)) {
            match.score().setGame2(match.score().getGame2() + 1);
            if (match.score().getGame2() >= 6 && (match.score().getGame2() - match.score().getGame1() >=2)) {
                match = winSet(matchId, player);
            }
        }
        match.score().setPoint1(0);
        match.score().setPoint2(0);

        ongoingMatchRepository.update(ongoingMatchUpdateMapper.mapFrom(match));

        return match;
    }

    private OngoingMatchReadDto winSet(Long matchId, Long player) {
        OngoingMatchReadDto match = ongoingMatchRepository.findById(matchId)
                .map(ongoingMatchReadMapper::mapFrom)
                .orElseThrow(() -> new EntityNotFoundException("match not founded"));
        if (match.score().getPlayer1().equals(player)) {
            match.score().setSet1(match.score().getSet1() + 1);
            if (match.score().getSet1() == 2) {
                winMatch(matchId, player);
            }
        } else if (match.score().getPlayer2().equals(player)) {
            match.score().setSet2(match.score().getSet2() + 1);
            if (match.score().getSet2() == 2) {
                winMatch(matchId, player);
            }
        }

        ongoingMatchRepository.update(ongoingMatchUpdateMapper.mapFrom(match));

        return match;
    }

    private MatchReadDto winMatch(Long matchId, Long winner) {
        OngoingMatchReadDto match = ongoingMatchRepository.findById(matchId)
                .map(ongoingMatchReadMapper::mapFrom)
                .orElseThrow(() -> new EntityNotFoundException("match not founded"));
        MatchCreateDto matchCreateDto = new MatchCreateDto(match.score().getPlayer1(),
                match.score().getPlayer2(),
                winner);

        ongoingMatchRepository.delete(matchId);

        return matchReadMapper.mapFrom(
                matchRepository.save(matchCreateMapper.mapFrom(matchCreateDto)));
    }
}
