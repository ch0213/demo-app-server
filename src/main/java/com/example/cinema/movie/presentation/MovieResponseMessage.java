package com.example.cinema.movie.presentation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MovieResponseMessage {
    FIND_MOVIE("영화 조회 성공");

    private final String message;
}
