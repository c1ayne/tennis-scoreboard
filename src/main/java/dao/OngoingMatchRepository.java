package dao;

import entity.OngoingMatch;
import jakarta.persistence.EntityManager;

import java.util.UUID;

public class OngoingMatchRepository extends BaseRepository<UUID, OngoingMatch> {

    public OngoingMatchRepository(EntityManager entityManager) {
        super(entityManager, OngoingMatch.class);
    }
}
