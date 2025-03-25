package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    public static SessionFactory buildSessionFactoryPostgres() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        return configuration.buildSessionFactory();
    }
    public static SessionFactory buildSessionFactoryH2() {
        Configuration configuration = new Configuration().configure("hibernate.h2.cfg.xml");
        return configuration.buildSessionFactory();
    }
}
