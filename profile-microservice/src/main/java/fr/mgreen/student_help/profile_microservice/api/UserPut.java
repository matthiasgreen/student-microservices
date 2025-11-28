package fr.mgreen.student_help.profile_microservice.api;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

public record UserPut(
        @Nullable String firstName,
        @Nullable String lastName,
        @Nullable @Email String email,
        @Nullable String institution,
        @Nullable String course,
        @Nullable List<String> skills,
        @Nullable List<LocalDateTime> availabilities
) { }