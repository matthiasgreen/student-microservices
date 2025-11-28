package fr.mgreen.student_help.request_microservice.api;

import fr.mgreen.student_help.request_microservice.db.Request;
import fr.mgreen.student_help.request_microservice.db.RequestStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record RequestPost(
        @NotNull Long posterId,
        @NotNull @NotBlank String title,
        @NotNull @NotBlank String description,
        @NotNull LocalDateTime dateWanted,
        @NotNull List<String> keywords
) {
    public Request toEntity() {
        return new Request(
                posterId(),
                title(),
                description(),
                dateWanted(),
                keywords()
        );
    }
}
