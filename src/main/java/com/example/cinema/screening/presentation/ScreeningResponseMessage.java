package com.example.cinema.screening.presentation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ScreeningResponseMessage {

    FIND_SCHEDULE("영화 스케쥴 조회 성공");

    private final String message;
}
