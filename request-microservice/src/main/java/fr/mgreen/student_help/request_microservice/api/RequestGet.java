package fr.mgreen.student_help.request_microservice.api;

import fr.mgreen.student_help.request_microservice.db.Request;
import fr.mgreen.student_help.request_microservice.db.RequestStatus;

import java.time.LocalDateTime;
import java.util.List;

public record RequestGet(
        Long id,
        Long posterId,
        String title,
        String description,
        LocalDateTime dateWanted,
        RequestStatus status,
        List<String> keywords,
        LocalDateTime createdAt
) {
    public static RequestGet fromEntity(Request request) {
        return new RequestGet(
                request.getId(),
                request.getPosterId(),
                request.getTitle(),
                request.getDescription(),
                request.getDateWanted(),
                request.getStatus(),
                request.getKeywords(),
                request.getCreatedAt()
        );
    }
}
