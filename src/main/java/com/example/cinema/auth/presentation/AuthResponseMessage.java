package com.example.cinema.auth.presentation;

import lombok.Getter;

@Getter
public enum AuthResponseMessage {
    LOGIN_SUCCESS("로그인 성공");

    private final String message;

    AuthResponseMessage(String message) {
        this.message = message;
    }
}
