package util;

import dao.MatchRepository;
import dao.OngoingMatchRepository;
import dao.PlayerRepository;
import jakarta.persistence.EntityManager;
import mapper.*;
import service.OngoingMatchService;

public class ServiceFactory {
    public static OngoingMatchService createOngoingMatchService(EntityManager postgresEM, EntityManager H2EM) {
        return new OngoingMatchService(
                new OngoingMatchRepository(H2EM),
                new PlayerRepository(postgresEM),
                new PlayerReadMapper(),
                new PlayerCreateMapper(),
                new OngoingMatchCreateMapper(),
                new OngoingMatchReadMapper(),
                new OngoingMatchUpdateMapper(),
                new MatchRepository(postgresEM),
                new MatchCreateMapper(
                        new PlayerRepository(postgresEM)
                ),
                new MatchReadMapper(
                        new PlayerReadMapper()
                )
        );
    }
}
