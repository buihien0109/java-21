package org.example.movie.app.model.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("Admin"),
    USER("User");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }
}
