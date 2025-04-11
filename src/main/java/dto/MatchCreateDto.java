package dto;

import java.util.UUID;

public record MatchCreateDto (UUID id,
                              Long player1,
                              Long player2,
                              Long winner) {
}
