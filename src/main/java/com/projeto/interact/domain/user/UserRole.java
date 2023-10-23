package com.projeto.interact.domain.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    MONITOR("monitor");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

}
