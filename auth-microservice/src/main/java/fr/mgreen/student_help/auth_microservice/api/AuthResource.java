package fr.mgreen.student_help.auth_microservice.api;

import fr.mgreen.student_help.auth_microservice.db.SessionManager;
import fr.mgreen.student_help.auth_microservice.db.User;
import fr.mgreen.student_help.auth_microservice.db.UserRepository;
import fr.mgreen.student_help.auth_microservice.db.User_;
import fr.mgreen.student_help.auth_microservice.jwt.JwtService;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.validation.Valid;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
public class AuthResource {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Deprecated
    @PostMapping("/users")
    public UserGet createUser(@Valid @RequestBody UserPost userPost) {
        String hashedPassword = BCrypt.hashpw(userPost.password(), BCrypt.gensalt());
        User user = new User(userPost.username(), hashedPassword);
        userRepository.save(user);
        return UserGet.fromEntity(user);
    }

    @GetMapping("/users")
    public List<UserGet> getUsers() {
        return userRepository.getAll().stream().map(UserGet::fromEntity).toList();
    }

    @PostMapping("/login")
    public String getToken(@Valid @RequestBody UserPost userPost) {
        User user = userRepository.findByUsername(userPost.username());

        if (BCrypt.checkpw(userPost.password(), user.getHashedPassword())) {
            return jwtService.generateToken(user.getId());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Incorrect username or password"
            );
        }
    }

    @Deprecated
    @DeleteMapping("/users/{id}")
    public UserGet deleteUser(@PathVariable("id") Long userId) {
        return UserGet.fromEntity(userRepository.deleteById(userId));
    }
}
