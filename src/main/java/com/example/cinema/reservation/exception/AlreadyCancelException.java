package com.example.cinema.reservation.exception;

import com.example.cinema.common.exception.BaseException;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.example.cinema.reservation.exception.ReservationExceptionType.ALREADY_CANCEL;

@Getter
public class AlreadyCancelException extends BaseException {
    public AlreadyCancelException() {
        super(ALREADY_CANCEL.getMessage(), LocalDateTime.now(), ALREADY_CANCEL.getStatus());
    }
}
