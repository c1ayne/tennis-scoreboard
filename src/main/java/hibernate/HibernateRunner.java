package hibernate;

import dao.MatchRepository;
import dao.PlayerRepository;
import entity.Match;
import entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HibernateUtil;

import java.lang.reflect.Proxy;

public class HibernateRunner {
    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactoryPostgres()) {

            Session session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                    new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(),args1));

            session.beginTransaction();

            PlayerRepository playerRepository = new PlayerRepository(session);
//            System.out.println(playerRepository.findByName("sinner"));
            Player player = playerRepository.findById(1L).orElseThrow();
//            Player player1 = playerRepository.findById(2L).orElseThrow();
//
            MatchRepository matchRepository = new MatchRepository(session);
//            Match match = Match.builder()
//                    .player1(player1)
//                    .player2(player)
//                    .winner(player)
//                    .build();
//            matchRepository.save(match);
            System.out.println(matchRepository.findByPlayer(player));

//            var players = session.createQuery("select p from Player p").list();
//            System.out.println(players);

            session.getTransaction().commit();
        }
    }
}
