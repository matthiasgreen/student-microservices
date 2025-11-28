package fr.mgreen.student_help.profile_microservice.db;

import fr.mgreen.student_help.profile_microservice.api.UserPut;
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

    public void saveUser(User user) {
        sessionManager.getSessionFactory().inTransaction(session -> session.persist(user));
    }

    public User updateUser(Long id, UserPut userPut) {
        return sessionManager.getSessionFactory().fromTransaction(session -> {
            User user = session.find(User.class, id);

            if (userPut.firstName() != null) {
                user.setFirstName(userPut.firstName());
            }
            if (userPut.lastName() != null) {
                user.setLastName(userPut.lastName());
            }
            if (userPut.email() != null) {
                user.setEmail(userPut.email());
            }
            if (userPut.institution() != null) {
                user.setInstitution(userPut.institution());
            }
            if (userPut.course() != null) {
                user.setCourse(userPut.course());
            }
            if (userPut.skills() != null) {
                user.setSkills(userPut.skills());
            }
            if (userPut.availabilities() != null) {
                user.setAvailabilities(userPut.availabilities());
            }

            return user;
        });
    }

    public User findById(Long id) {
        var sessionFactory = sessionManager.getSessionFactory();
        return sessionFactory.fromSession(session -> {
            return session.find(User.class, id);
        });
    }

    public List<User> getAllUsers() {
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
