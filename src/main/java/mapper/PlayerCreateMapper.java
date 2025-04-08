package mapper;

import dto.PlayerCreateDto;
import entity.Player;

public class PlayerCreateMapper implements Mapper<PlayerCreateDto, Player> {
    @Override
    public Player mapFrom(PlayerCreateDto object) {
        return Player.builder()
                .name(object.name())
                .build();
    }
}
