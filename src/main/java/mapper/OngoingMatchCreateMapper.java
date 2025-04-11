package mapper;

import dto.OngoingMatchCreateDto;
import entity.OngoingMatch;

public class OngoingMatchCreateMapper implements Mapper<OngoingMatchCreateDto, OngoingMatch>{
    @Override
    public OngoingMatch mapFrom(OngoingMatchCreateDto object) {
        return OngoingMatch.builder()
                .id(object.id())
                .score(object.score())
                .build();
    }
}
