package fr.mgreen.student_help.profile_microservice.api;

import fr.mgreen.student_help.profile_microservice.db.SessionManager;
import fr.mgreen.student_help.profile_microservice.db.User;
import fr.mgreen.student_help.profile_microservice.db.User_;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
public class ProfileResource {
    @Autowired
    private SessionManager sessionManager;

    @PostMapping("/users")
    public UserGet createUser(@RequestBody UserPost userPost) {
        User user = userPost.toEntity();
        sessionManager.getSessionFactory().inTransaction(session -> session.persist(user));
        return UserGet.fromEntity(user);
    }

    @GetMapping("/users")
    public List<UserGet> getUsers() {
        var sessionFactory = sessionManager.getSessionFactory();
        return sessionFactory.fromSession(session -> {
            HibernateCriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> user = query.from(User.class);
            query.select(user);
            return session.createSelectionQuery(query).getResultList();
        }).stream().map(UserGet::fromEntity).toList();
    }

    @GetMapping("/users/{id}")
    public UserGet getUser(@PathVariable("id") Long userId) {
        var sessionFactory = sessionManager.getSessionFactory();
        return UserGet.fromEntity(sessionFactory.fromSession(session -> {
            HibernateCriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> user = query.from(User.class);
            query.select(user).where(builder.equal(user.get(User_.id), userId));
            return session.createSelectionQuery(query).getSingleResult();
        }));
    }
}
