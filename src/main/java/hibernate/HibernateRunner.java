package hibernate;

import entity.Match;
import entity.OngoingMatch;
import entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

//            Player player1 = session.get(Player.class, 1);
//            Player player2 = session.get(Player.class, 2);
//
//            Match match = Match.builder()
//                    .player1(player1)
//                    .player2(player2)
//                    .winner(player2)
//                    .build();
//
//            session.persist(match);

//            System.out.println(session.get(Match.class, 1));

            session.getTransaction().commit();
        }

        try (SessionFactory sessionFactoryH2 = HibernateUtil.buildSessionFactoryH2();
             Session session = sessionFactoryH2.openSession()) {
            session.beginTransaction();

            session.persist(OngoingMatch.builder()
                    .player1(1)
                    .player2(2)
                    .build()
            );

            session.persist(OngoingMatch.builder()
                    .player1(3)
                    .player2(4)
                    .build()
            );

            session.get(OngoingMatch.class, 1);

            System.out.println(session.get(OngoingMatch.class, 2));

            session.getTransaction().commit();
        }
    }
}
