package dao;

import entity.OngoingMatch;
import jakarta.persistence.EntityManager;

public class OngoingMatchRepository extends BaseRepository<Long, OngoingMatch> {

    public OngoingMatchRepository(EntityManager entityManager) {
        super(entityManager, OngoingMatch.class);
    }
}
