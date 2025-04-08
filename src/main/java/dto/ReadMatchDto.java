package dto;

public record ReadMatchDto (Long id,
                            ReadPlayerDto player1,
                            ReadPlayerDto player2,
                            ReadPlayerDto winner
                            ) {
}
