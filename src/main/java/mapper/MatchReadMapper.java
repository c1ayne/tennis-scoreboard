package mapper;

import dto.MatchReadDto;
import entity.Match;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatchReadMapper implements Mapper<Match, MatchReadDto> {
    private final PlayerReadMapper playerReadMapper;

    @Override
    public MatchReadDto mapFrom(Match object) {
        return new MatchReadDto(
                object.getId(),
                playerReadMapper.mapFrom(object.getPlayer1()),
                playerReadMapper.mapFrom(object.getPlayer2()),
                playerReadMapper.mapFrom(object.getWinner())
                );
    }
}
