package com.example.cinema.reservation.exception;

import com.example.cinema.common.exception.BaseException;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.example.cinema.reservation.exception.ReservationExceptionType.NON_EXISTENT_RESERVATION;

@Getter
public class NonExistentReservationException extends BaseException {
    public NonExistentReservationException() {
        super(NON_EXISTENT_RESERVATION.getMessage(), LocalDateTime.now(), NON_EXISTENT_RESERVATION.getStatus());
    }
}
