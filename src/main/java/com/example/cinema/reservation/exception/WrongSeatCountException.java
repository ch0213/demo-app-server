package com.example.cinema.reservation.exception;

import com.example.cinema.common.exception.BaseException;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.example.cinema.reservation.exception.ReservationExceptionType.WRONG_SEAT_COUNT;

@Getter
public class WrongSeatCountException extends BaseException {
    public WrongSeatCountException() {
        super(WRONG_SEAT_COUNT.getMessage(), LocalDateTime.now(), WRONG_SEAT_COUNT.getStatus());
    }
}
