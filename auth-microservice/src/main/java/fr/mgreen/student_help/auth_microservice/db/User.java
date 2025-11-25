package fr.mgreen.student_help.auth_microservice.db;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
public class User {
    public User() {}

    public User(String username, String hashedPassword) {
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String username;

    private String hashedPassword;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
