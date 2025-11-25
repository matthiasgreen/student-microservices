package fr.mgreen.student_help.auth_microservice.api;

import fr.mgreen.student_help.auth_microservice.db.SessionManager;
import fr.mgreen.student_help.auth_microservice.db.User;
import fr.mgreen.student_help.auth_microservice.db.User_;
import fr.mgreen.student_help.auth_microservice.jwt.JwtService;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController()
public class AuthResource {
    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/signup")
    public UserGet createUser(@RequestBody UserPost userPost) {
        String hashedPassword = BCrypt.hashpw(userPost.password(), BCrypt.gensalt());
        User user = new User(userPost.username(), hashedPassword);
        sessionManager.getSessionFactory().inTransaction(session -> session.persist(user));
        return UserGet.fromEntity(user);
    }

    @PostMapping("/login")
    public String getToken(@RequestBody UserPost userPost) {
        var sessionFactory = sessionManager.getSessionFactory();
        User user = sessionFactory.fromSession(session -> {
            HibernateCriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> userQuery = query.from(User.class);
            query.select(userQuery).where(builder.like(userQuery.get(User_.username), userPost.username()));
            return session.createSelectionQuery(query).getSingleResult();
        });

        if (BCrypt.checkpw(userPost.password(), user.getHashedPassword())) {
            return jwtService.generateToken(user.getId());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Incorrect username or password"
            );
        }
    }
}
