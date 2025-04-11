package mapper;

import dto.OngoingMatchReadDto;
import entity.OngoingMatch;

public class OngoingMatchUpdateMapper implements Mapper<OngoingMatchReadDto, OngoingMatch>{
    @Override
    public OngoingMatch mapFrom(OngoingMatchReadDto object) {
        return OngoingMatch.builder()
                .id(object.id())
                .score(object.score())
                .build();
    }
}
