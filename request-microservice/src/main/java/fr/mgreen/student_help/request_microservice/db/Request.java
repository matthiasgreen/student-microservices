package fr.mgreen.student_help.request_microservice.db;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column(nullable = false, name = "poster_id")
    private Long posterId;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String description;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(name = "date_wanted")
    private LocalDateTime dateWanted;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private RequestStatus status;

    @ElementCollection
    @Fetch(FetchMode.JOIN)
    private List<String> keywords;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Request() {
    }

    public Request(Long posterId,
                   String title,
                   String description,
                   String email,
                   LocalDateTime dateWanted,
                   RequestStatus status,
                   List<String> keywords) {
        this.posterId = posterId;
        this.title = title;
        this.description = description;
        this.email = email;
        this.dateWanted = dateWanted;
        this.status = status;
        this.keywords = keywords;
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

    public String getEmail() {
        return email;
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
