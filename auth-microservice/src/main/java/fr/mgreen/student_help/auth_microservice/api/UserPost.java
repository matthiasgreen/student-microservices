package fr.mgreen.student_help.auth_microservice.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UserPost(
        @NotNull @Length(min = 3, max = 20) String username,
        @NotNull @Length(min = 10, max = 100) String password
) {}
