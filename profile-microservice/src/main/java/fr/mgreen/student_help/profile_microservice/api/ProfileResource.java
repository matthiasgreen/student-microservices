package fr.mgreen.student_help.profile_microservice.api;

import fr.mgreen.student_help.profile_microservice.db.User;
import fr.mgreen.student_help.profile_microservice.db.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class ProfileResource {
    @Autowired
    private UserRepository userRepository;

    @Deprecated
    @PostMapping("/users")
    public UserGet createUser(@Valid @RequestBody UserPost userPost) {
        User user = userPost.toEntity();
        userRepository.saveUser(user);
        return UserGet.fromEntity(user);
    }

    @GetMapping("/users")
    public List<UserGet> getUsers() {
        return userRepository.getAllUsers().stream().map(UserGet::fromEntity).toList();
    }

    @GetMapping("/users/{id}")
    public UserGet getUser(@PathVariable("id") Long userId) {
        return UserGet.fromEntity(userRepository.findById(userId));
    }

    @PutMapping("/users/{id}")
    public UserGet updateUser(@RequestHeader(value = "Authorization", required = false) String bearerKey, @PathVariable("id") Long userId, @Valid @RequestBody UserPut userPut) {
        User user = userRepository.updateUser(userId, userPut);
        return UserGet.fromEntity(user);
    }
}
