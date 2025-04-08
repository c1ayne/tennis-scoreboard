package dto;

public record MatchReadDto(Long id,
                           PlayerReadDto player1,
                           PlayerReadDto player2,
                           PlayerReadDto winner
                            ) {
}
