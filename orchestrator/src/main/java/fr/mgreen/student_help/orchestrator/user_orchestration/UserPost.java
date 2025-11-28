package fr.mgreen.student_help.orchestrator.user_orchestration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

public record UserPost(
        @NotNull @Length(min = 3, max = 20) String username,
        @NotNull @Length(min = 10, max = 100) String password,
        @NotNull @NotBlank String firstName,
        @NotNull @NotBlank String lastName,
        @NotNull @Email String email,
        @NotBlank String institution,
        @NotBlank String course,
        List<String> skills,
        List<LocalDateTime> availabilities
) { }
