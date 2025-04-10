package mapper;

import entity.OngoingMatch;

public class OngoingMatchReadMapper implements Mapper<OngoingMatch, dto.OngoingMatchReadDto>{
    @Override
    public dto.OngoingMatchReadDto mapFrom(OngoingMatch object) {
        return new dto.OngoingMatchReadDto(object.getId(),
                object.getScore());
    }
}
