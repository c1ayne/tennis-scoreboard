package hibernate;

import entity.Match;
import entity.OngoingMatch;
import entity.Player;
import entity.Score;
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
            var players = session.createQuery("select p from Player p").list();
            System.out.println(players);

            session.getTransaction().commit();
        }

        try (SessionFactory sessionFactoryH2 = HibernateUtil.buildSessionFactoryH2();
             Session session = sessionFactoryH2.openSession()) {
            session.beginTransaction();

            OngoingMatch match1 = OngoingMatch.builder()
                    .score(Score.builder()
                            .player1(1)
                            .player2(2)
                            .build())
                    .build();

            OngoingMatch match2 = OngoingMatch.builder()
                    .score(Score.builder()
                            .player1(3)
                            .player2(4)
                            .build())
                    .build();

            session.persist(match1);

            session.persist(match2);

            match2.getScore().winPoint(3);
            match2.getScore().winPoint(3);
            match2.getScore().winPoint(3);
            match2.getScore().winPoint(3);
            match2.getScore().winPoint(3);

            System.out.println(match2);
            System.out.println("---------\n\n\n");


//            OngoingMatch ongoingMatch = session.get(OngoingMatch.class, 1);
//            System.out.println(ongoingMatch.getScore().getSet2());

            System.out.println(session.get(OngoingMatch.class, 2));
            System.out.println("---------\n\n\n");

            session.getTransaction().commit();
        }

        try (SessionFactory sessionFactoryH2 = HibernateUtil.buildSessionFactoryH2();
             Session session = sessionFactoryH2.openSession()) {
            session.beginTransaction();


            System.out.println(session.get(OngoingMatch.class, 2));

            session.getTransaction().commit();
        }
    }
}
