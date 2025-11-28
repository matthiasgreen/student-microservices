package fr.mgreen.student_help.request_microservice.db;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Requests")
public class Request {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, name = "poster_id")
    private Long posterId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "date_wanted")
    private LocalDateTime dateWanted;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;

    @ElementCollection
    @Fetch(FetchMode.JOIN)
    private List<String> keywords;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Request() {
    }

    public Request(Long posterId,
                   String title,
                   String description,
                   LocalDateTime dateWanted,
                   List<String> keywords) {
        this.posterId = posterId;
        this.title = title;
        this.description = description;
        this.dateWanted = dateWanted;
        this.status = RequestStatus.WAITING;
        this.keywords = keywords;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getPosterId() {
        return posterId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateWanted() {
        return dateWanted;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
