package mapper;

import dto.PlayerReadDto;
import entity.Player;

public class PlayerReadMapper implements Mapper<Player, PlayerReadDto> {
    @Override
    public PlayerReadDto mapFrom(Player object) {
        return new PlayerReadDto(object.getId(),
                object.getName());
    }
}
