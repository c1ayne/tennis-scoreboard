package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "matches", schema = "tennis")
public class Match {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "player1")
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player2")
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "winner")
    private Player winner;
}