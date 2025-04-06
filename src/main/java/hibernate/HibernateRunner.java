package hibernate;

import dao.PlayerRepository;
import entity.Player;
import jakarta.persistence.EntityNotFoundException;
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
            System.out.println(playerRepository.findByName("Sinner"));


//            var players = session.createQuery("select p from Player p").list();
//            System.out.println(players);

            session.getTransaction().commit();
        }
    }
}
