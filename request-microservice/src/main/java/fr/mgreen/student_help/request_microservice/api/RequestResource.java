package fr.mgreen.student_help.request_microservice.api;

import fr.mgreen.student_help.request_microservice.db.Request_;
import fr.mgreen.student_help.request_microservice.db.SessionManager;
import fr.mgreen.student_help.request_microservice.db.Request;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.validation.Valid;
import jakarta.ws.rs.QueryParam;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class RequestResource {
    @Autowired
    private SessionManager sessionManager;

    @PostMapping("/requests")
    public RequestGet createRequest(@RequestHeader(value = "Authorization", required = false) String bearerKey, @Valid @RequestBody RequestPost requestPost) {
        Request request = requestPost.toEntity();
        sessionManager.getSessionFactory().inTransaction(session -> session.persist(request));
        return RequestGet.fromEntity(request);
    }

    @GetMapping("/requests")
    public List<RequestGet> getRequests(@Nullable @QueryParam("keyword") String keyword) {
        var sessionFactory = sessionManager.getSessionFactory();
        var res = sessionFactory.fromSession(session -> {
            HibernateCriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<Request> query = builder.createQuery(Request.class);
            Root<Request> user = query.from(Request.class);
            query.select(user);
            return session.createSelectionQuery(query).getResultList();
        }).stream().map(RequestGet::fromEntity);
        if (keyword != null) {
            res = res.filter(requestGet -> requestGet.keywords().contains(keyword));
        }

        return res.toList();
    }

    @GetMapping("/requests/{id}")
    public RequestGet getRequest(@PathVariable("id") Long id) {
        var sessionFactory = sessionManager.getSessionFactory();
        return RequestGet.fromEntity(sessionFactory.fromSession(session -> {
            HibernateCriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<Request> query = builder.createQuery(Request.class);
            Root<Request> user = query.from(Request.class);
            query.select(user).where(builder.equal(user.get(Request_.id), id));
            return session.createSelectionQuery(query).getSingleResult();
        }));
    }
}
