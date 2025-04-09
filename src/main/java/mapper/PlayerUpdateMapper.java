package mapper;

import dto.PlayerUpdateDto;
import entity.Player;

public class PlayerUpdateMapper implements Mapper<PlayerUpdateDto, Player> {
    @Override
    public Player mapFrom(PlayerUpdateDto object) {
        return Player.builder()
                .id(object.id())
                .name(object.name())
                .build();
    }
}
