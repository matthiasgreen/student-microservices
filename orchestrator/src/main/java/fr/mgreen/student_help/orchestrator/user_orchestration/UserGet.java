package fr.mgreen.student_help.orchestrator.user_orchestration;

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

}
