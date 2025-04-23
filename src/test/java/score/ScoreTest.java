package score;

import dto.OngoingMatchReadDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import service.OngoingMatchService;
import util.HibernateUtil;
import util.ServiceFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScoreTest {
    SessionFactory sessionFactoryPsql = HibernateUtil.buildSessionFactoryPostgres();
    SessionFactory sessionFactoryH2 = HibernateUtil.buildSessionFactoryH2();
    Session sessionH2 = sessionFactoryH2.getCurrentSession();
    Session sessionPostgres = sessionFactoryPsql.getCurrentSession();

    OngoingMatchService ongoingMatchService = ServiceFactory.createOngoingMatchService(sessionPostgres, sessionH2);

    @Test
    void winGame() {
        sessionH2.beginTransaction();
        sessionPostgres.beginTransaction();
        OngoingMatchReadDto newMatch = ongoingMatchService.startMatch("Federer", "Djokovic");
        ongoingMatchService.winPoint(newMatch.id(), newMatch.score().getPlayer1());
        ongoingMatchService.winPoint(newMatch.id(), newMatch.score().getPlayer1());
        ongoingMatchService.winPoint(newMatch.id(), newMatch.score().getPlayer1());
        OngoingMatchReadDto match = ongoingMatchService.winPoint(newMatch.id(), newMatch.score().getPlayer1());
        Integer gamePlayer1 = match.score().getGame1();
        sessionH2.getTransaction().commit();
        sessionPostgres.getTransaction().commit();

        assertEquals(1, gamePlayer1);
    }

    @Test
    void tiebreakInSet() {
        sessionH2.beginTransaction();
        sessionPostgres.beginTransaction();
        OngoingMatchReadDto newMatch = ongoingMatchService.startMatch("Federer", "Djokovic");

        for (int i = 0; i < 20; i++) {
            ongoingMatchService.winPoint(newMatch.id(), newMatch.score().getPlayer1());
        }
        for (int i = 0; i < 24; i++) {
            ongoingMatchService.winPoint(newMatch.id(), newMatch.score().getPlayer2());
        }
        for (int i = 0; i < 4; i++) {
            ongoingMatchService.winPoint(newMatch.id(), newMatch.score().getPlayer1());
        }

        OngoingMatchReadDto match = ongoingMatchService.findOngoingMatchById(newMatch.id()).get();
        sessionH2.getTransaction().commit();
        sessionPostgres.getTransaction().commit();

        assertTrue(match.score().isTiebreak());
    }

    @Test
    void notWinGame() {
        sessionH2.beginTransaction();
        sessionPostgres.beginTransaction();
        OngoingMatchReadDto newMatch = ongoingMatchService.startMatch("Federer", "Djokovic");

        for (int i = 0; i < 3; i++) {
            ongoingMatchService.winPoint(newMatch.id(), newMatch.score().getPlayer1());
        }
        for (int i = 0; i < 3; i++) {
            ongoingMatchService.winPoint(newMatch.id(), newMatch.score().getPlayer2());
        }

        ongoingMatchService.winPoint(newMatch.id(), newMatch.score().getPlayer1());


        OngoingMatchReadDto match = ongoingMatchService.findOngoingMatchById(newMatch.id()).get();
        sessionH2.getTransaction().commit();
        sessionPostgres.getTransaction().commit();

        assertEquals(0, match.score().getGame1());
    }
}
