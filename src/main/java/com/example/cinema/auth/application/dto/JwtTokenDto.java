package com.example.cinema.auth.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtTokenDto {
    private String accessToken;

    public static JwtTokenDto from(String accessToken) {
        return new JwtTokenDto(accessToken);
    }
}
