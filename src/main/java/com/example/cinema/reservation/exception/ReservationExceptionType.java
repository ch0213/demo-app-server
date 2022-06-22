package com.example.cinema.reservation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Getter
@RequiredArgsConstructor
public enum ReservationExceptionType {
    WRONG_SEAT_COUNT("최대 10개의 좌석만 예매할 수 있습니다.", BAD_REQUEST),
    NON_EXISTENT_RESERVATION("존재하지 않는 예약입니다.", BAD_REQUEST),
    ALREADY_CANCEL("이미 취소된 예약입니다.", BAD_REQUEST),
    NOT_ENOUGH_AUTHORITY("예매를 취소할 수 있는 권한이 없습니다.", FORBIDDEN);

    private final String message;
    private final HttpStatus status;
}
