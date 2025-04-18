package dao;

import entity.Match;
import entity.Player;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.UUID;

public class MatchRepository extends BaseRepository<UUID, Match> {

    public MatchRepository(EntityManager entityManager) {
        super(entityManager, Match.class);
    }

    public List<Match> findByPlayer(Player player) {
        return getEntityManager()
                .createQuery("select m from Match m where m.player1 = :player or m.player2 = :player", Match.class)
                .setParameter("player", player)
                .getResultList();
    }
}
