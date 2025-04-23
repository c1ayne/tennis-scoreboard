package entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Score {
    private Long player1;
    private Long player2;

    @Builder.Default
    private Integer set1 = 0;
    @Builder.Default
    private Integer set2 = 0;

    @Builder.Default
    private Integer game1 = 0;
    @Builder.Default
    private Integer game2 = 0;

    @Builder.Default
    private Integer point1 = 0;
    @Builder.Default
    private Integer point2 = 0;

    @Builder.Default
    private Integer tiebreakPoint1 = 0;
    @Builder.Default
    private Integer tiebreakPoint2 = 0;

    @Builder.Default
    private Long winner = null;
    @Builder.Default
    private boolean isTiebreak = false;
}