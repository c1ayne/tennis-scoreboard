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
    private Long winner = null;

    public void winPoint(Long player) {
        if (player1.equals(player)) {
            if (point1.equals(0)) {
                point1 = 15;
            } else if (point1.equals(15)) {
                point1 = 30;
            } else if (point1.equals(30)) {
                point1 = 40;
            } else if (point1.equals(40)) {
                if (point2 < 40) {
                    winGame(player1);
                } else if (point2.equals(40)) {
                    point1 = 41;
                } else if (point2.equals(41)) {
                    point2 = 40;
                }
            } else if (point1.equals(41)) {
                winGame(player1);
            }
        } else if (player2.equals(player)) {
            if (point2.equals(0)) {
                point2 = 15;
            } else if (point2.equals(15)) {
                point2 = 30;
            } else if (point2.equals(30)) {
                point2 = 40;
            } else if (point2.equals(40)) {
                if (point1 < 40) {
                    winGame(player2);
                } else if (point1.equals(40)) {
                    point2 = 41;
                } else if (point1.equals(41)) {
                    point1 = 40;
                }
            } else if (point2.equals(41)) {
                winGame(player2);
            }
        } else {
            throw new RuntimeException("Invalid player id");
        }
    }

    private void winGame(Long player) {
        if (player1.equals(player)) {
            game1++;
            if (game1 >= 6 && (game1 - game2 >=2)) {
                winSet(player);
            }
        } else if (player2.equals(player)) {
            game2++;
            if (game2 >= 6 && (game2 - game1 >=2)) {
                winSet(player);
            }
        }
        point1 = 0;
        point2 = 0;
    }

    private void winSet(Long player){
        if (player1.equals(player)) {
            set1++;
            if (set1 == 2) {
                winMatch(player);
            }
        } else if (point2.equals(player)) {
            set2++;
            if (set2 == 2) {
                winMatch(player);
            }
        }
    }

    private void winMatch(Long player) {
        winner = player;
    }
}