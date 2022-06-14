package com.example.cinema.auth.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor(access = PROTECTED)
public class LoginRequest {
    private String memberNumber;
    private String password;
}
