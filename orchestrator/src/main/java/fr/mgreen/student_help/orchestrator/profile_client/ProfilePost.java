package fr.mgreen.student_help.orchestrator.profile_client;

import java.time.LocalDateTime;
import java.util.List;

public record ProfilePost(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        String institution,
        String course,
        List<String> skills,
        List<LocalDateTime> availabilities
) { }
