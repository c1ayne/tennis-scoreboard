package dto;

import entity.Score;

import java.util.UUID;

public record OngoingMatchCreateDto (UUID id,
                                     Score score) {
}
