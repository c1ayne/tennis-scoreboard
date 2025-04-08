package mapper;

import dao.PlayerRepository;
import dto.MatchCreateDto;
import entity.Match;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatchCreateMapper implements Mapper<MatchCreateDto, Match> {
    private final PlayerRepository playerRepository;

    @Override
    public Match mapFrom(MatchCreateDto object) {
        return Match.builder()
                .player1(playerRepository.findById(object.player1())
                        .orElseThrow(IllegalArgumentException::new))
                .player2(playerRepository.findById(object.player2())
                        .orElseThrow(IllegalArgumentException::new))
                .winner(playerRepository.findById(object.winner())
                        .orElseThrow(IllegalArgumentException::new))
                .build();
    }
}
