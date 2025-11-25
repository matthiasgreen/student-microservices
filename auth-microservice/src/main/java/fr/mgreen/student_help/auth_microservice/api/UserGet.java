package fr.mgreen.student_help.auth_microservice.api;

import fr.mgreen.student_help.auth_microservice.db.User;

public record UserGet(Long id, String username) {
    public static UserGet fromEntity(User user) {
        return new UserGet(user.getId(), user.getUsername());
    }
}
