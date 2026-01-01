package com.vitoruk.api.domain.user;

public record RegisterRequestDTO(String login, String password, UserRole role) {
}
