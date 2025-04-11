package dto;

import java.util.UUID;

public record MatchReadDto(UUID id,
                           PlayerReadDto player1,
                           PlayerReadDto player2,
                           PlayerReadDto winner
                            ) { }