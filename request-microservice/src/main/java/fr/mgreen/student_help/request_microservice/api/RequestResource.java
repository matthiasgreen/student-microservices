package fr.mgreen.student_help.request_microservice.api;

import fr.mgreen.student_help.request_microservice.db.Request_;
import fr.mgreen.student_help.request_microservice.db.SessionManager;
import fr.mgreen.student_help.request_microservice.db.Request;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class RequestResource {
    @Autowired
    private SessionManager sessionManager;

    @PostMapping("/requests")
    public RequestGet createUser(@RequestBody RequestPost requestPost) {
        Request request = requestPost.toEntity();
        sessionManager.getSessionFactory().inTransaction(session -> session.persist(request));
        return RequestGet.fromEntity(request);
    }

    @GetMapping("/requests")
    public List<RequestGet> getUsers() {
        var sessionFactory = sessionManager.getSessionFactory();
        return sessionFactory.fromSession(session -> {
            HibernateCriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<Request> query = builder.createQuery(Request.class);
            Root<Request> user = query.from(Request.class);
            query.select(user);
            return session.createSelectionQuery(query).getResultList();
        }).stream().map(RequestGet::fromEntity).toList();
    }

    @GetMapping("/users/{id}")
    public RequestGet getUser(@PathVariable("id") Long userId) {
        var sessionFactory = sessionManager.getSessionFactory();
        return RequestGet.fromEntity(sessionFactory.fromSession(session -> {
            HibernateCriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<Request> query = builder.createQuery(Request.class);
            Root<Request> user = query.from(Request.class);
            query.select(user).where(builder.equal(user.get(Request_.id), userId));
            return session.createSelectionQuery(query).getSingleResult();
        }));
    }
}
