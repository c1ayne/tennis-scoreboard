package dto;

import entity.Score;

import java.util.UUID;

public record OngoingMatchReadDto (UUID id,
                                   Score score) {
}
