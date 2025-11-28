package fr.mgreen.student_help.auth_microservice.db;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private SessionManager sessionManager;

    public void save(User user) {
        sessionManager.getSessionFactory().inTransaction(session -> session.persist(user));
    }

    public User findByUsername(String username) {
        var sessionFactory = sessionManager.getSessionFactory();
        return sessionFactory.fromSession(session -> {
            HibernateCriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> userQuery = query.from(User.class);
            query.select(userQuery).where(builder.like(userQuery.get(User_.username), username));
            return session.createSelectionQuery(query).getSingleResult();
        });
    }

    public User findById(Long id) {
        var sessionFactory = sessionManager.getSessionFactory();
        return sessionFactory.fromSession(session -> {
            HibernateCriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> userQuery = query.from(User.class);
            query.select(userQuery).where(builder.equal(userQuery.get(User_.id), id));
            return session.createSelectionQuery(query).getSingleResult();
        });
    }

    public User deleteById(Long id) {
        return sessionManager.getSessionFactory().fromTransaction(session -> {
            User user = session.find(User.class, id);
            if (user != null) {
                session.remove(user);
            }
            return user;
        });
    }

    public List<User> getAll() {
        var sessionFactory = sessionManager.getSessionFactory();
        return sessionFactory.fromSession(session -> {
            HibernateCriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> user = query.from(User.class);
            query.select(user);
            return session.createSelectionQuery(query).getResultList();
        });
    }
}
