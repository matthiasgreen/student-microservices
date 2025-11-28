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

    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    private String institution;

    private String course;

    @ElementCollection
    @Fetch(FetchMode.JOIN)
    private List<String> skills;

    @ElementCollection
    @Fetch(FetchMode.JOIN)
    private List<LocalDateTime> availabilities;

    @Column(name = "created_at", nullable = false)
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void setAvailabilities(List<LocalDateTime> availabilities) {
        this.availabilities = availabilities;
    }
}
