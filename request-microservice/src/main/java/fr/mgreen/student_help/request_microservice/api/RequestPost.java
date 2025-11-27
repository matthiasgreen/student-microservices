package fr.mgreen.student_help.request_microservice.api;

import fr.mgreen.student_help.request_microservice.db.Request;
import fr.mgreen.student_help.request_microservice.db.RequestStatus;

import java.time.LocalDateTime;
import java.util.List;

public record RequestPost(
        Long posterId,
        String title,
        String description,
        String email,
        LocalDateTime dateWanted,
        RequestStatus status,
        List<String> keywords
) {
    public Request toEntity() {
        return new Request(
                posterId(),
                title(),
                description(),
                email(),
                dateWanted(),
                status(),
                keywords()
        );
    }
}
