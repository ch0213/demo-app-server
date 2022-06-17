package com.example.cinema.screening.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@RequiredArgsConstructor
public enum ScreeningExceptionType {
    NOT_EXIST_SCREENING("존재하지 상영 스케줄입니다.", BAD_REQUEST),
    NOT_ENOUGH_SEAT("상영관의 자리가 부족합니다.", BAD_REQUEST),
    ALREADY_MOVE_START("이미 영화가 시작했습니다.", BAD_REQUEST);

    private final String message;
    private final HttpStatus status;
}
