package hibernate;

import entity.Match;
import entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HibernateUtil;

public class HibernateRunner {
    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactoryPostgres();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

//            session.persist(Player.builder()
//                            .name("Sinner")
//                    .build());
//
//            session.persist(Player.builder()
//                    .name("Alcaraz")
//                    .build());

            Player player1 = session.get(Player.class, 1);
            Player player2 = session.get(Player.class, 2);

            Match match = Match.builder()
                    .player1(player1)
                    .player2(player2)
                    .winner(player2)
                    .build();

            session.persist(match);

            session.getTransaction().commit();
        }
    }
}
