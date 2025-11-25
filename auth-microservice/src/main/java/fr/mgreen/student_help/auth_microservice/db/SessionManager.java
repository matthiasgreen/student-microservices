package fr.mgreen.student_help.auth_microservice.db;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernatePersistenceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {
    @Autowired
    public SessionManager(DatabaseConfig config) {
        sessionFactory = buildSessionFactory(config);
    }

    private final SessionFactory sessionFactory;

    private SessionFactory buildSessionFactory(DatabaseConfig config) {
        System.out.println(config.getUrl());
        var hibernateConfig = new HibernatePersistenceConfiguration("Auth")
                .managedClass(User.class)
                .jdbcUrl(config.getUrl())
                .jdbcCredentials(config.getUsername(), config.getPassword())
                .showSql(true, true, true);

        try {
            var sessionFactory = hibernateConfig.createEntityManagerFactory();
            sessionFactory.getSchemaManager().exportMappedObjects(true);
            return sessionFactory;
        } catch (Exception e) {
            throw new RuntimeException("Unable to build SessionFactory", e);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void shutdown() {
        sessionFactory.close();
    }
}
