package mapper;

import dto.ReadMatchDto;
import entity.Match;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatchReadMapper implements Mapper<Match, ReadMatchDto> {
    private final PlayerReadMapper playerReadMapper;

    @Override
    public ReadMatchDto mapFrom(Match object) {
        return new ReadMatchDto(object.getId(),
                playerReadMapper.mapFrom(object.getPlayer1()),
                playerReadMapper.mapFrom(object.getPlayer2()),
                playerReadMapper.mapFrom(object.getWinner())
                );
    }
}
