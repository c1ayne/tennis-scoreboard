package dao;

import entity.Player;
import jakarta.persistence.EntityManager;

public class PlayerRepository extends BaseRepository<Long, Player>{

    public PlayerRepository(EntityManager entityManager) {
        super(entityManager, Player.class);
    }

    public Player findByName(String name) {
        return getEntityManager()
                .createQuery("select p from Player p where lower(p.name) = lower(:name)", Player.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
