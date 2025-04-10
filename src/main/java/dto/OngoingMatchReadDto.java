package dto;

import entity.Score;

public record OngoingMatchReadDto (Long id,
                                   Score score) {
}
