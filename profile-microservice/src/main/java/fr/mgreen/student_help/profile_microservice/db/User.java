package fr.mgreen.student_help.profile_microservice.db;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    private Long id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String username;

    @NotNull
    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    private String institution;

    @NotBlank
    private String course;

    @ElementCollection
    @Fetch(FetchMode.JOIN)
    private List<String> skills;

    @ElementCollection
    @Fetch(FetchMode.JOIN)
    private List<LocalDateTime> availabilities;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public User() {
    }

    public User(
            Long id,
            String username,
            String firstName,
            String lastName,
            String email,
            String institution,
            String course,
            List<String> skills,
            List<LocalDateTime> availabilities
    ) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.institution = institution;
        this.course = course;
        this.skills = skills;
        this.availabilities = availabilities;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getInstitution() {
        return institution;
    }

    public String getCourse() {
        return course;
    }

    public List<String> getSkills() {
        return skills;
    }

    public List<LocalDateTime> getAvailabilities() {
        return availabilities;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
