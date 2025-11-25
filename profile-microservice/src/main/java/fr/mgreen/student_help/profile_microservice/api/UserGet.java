package fr.mgreen.student_help.profile_microservice.api;

import fr.mgreen.student_help.profile_microservice.db.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public record UserGet(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        String institution,
        String course,
        List<String> skills,
        List<LocalDateTime> availabilities,
        LocalDateTime createdAt
) {
    public static UserGet fromEntity(User user) {
        return new UserGet(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getInstitution(),
                user.getCourse(),
                user.getSkills(),
                user.getAvailabilities(),
                user.getCreatedAt()
        );
    }
}
