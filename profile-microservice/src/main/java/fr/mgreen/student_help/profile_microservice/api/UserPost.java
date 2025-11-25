package fr.mgreen.student_help.profile_microservice.api;

import fr.mgreen.student_help.profile_microservice.db.User;

import java.time.LocalDateTime;
import java.util.List;

public record UserPost(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        String institution,
        String course,
        List<String> skills,
        List<LocalDateTime> availabilities
) {
    public User toEntity() {
        return new User(
                id(),
                username(),
                firstName(),
                lastName(),
                email(),
                institution(),
                course(),
                skills(),
                availabilities()
        );
    }
}
