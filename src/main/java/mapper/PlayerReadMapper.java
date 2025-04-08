package mapper;

import dto.ReadPlayerDto;
import entity.Player;

public class PlayerReadMapper implements Mapper<Player, ReadPlayerDto> {
    @Override
    public ReadPlayerDto mapFrom(Player object) {
        return new ReadPlayerDto(object.getId(),
                object.getName());
    }
}
