package com.example.cinema.screening.exception;

import com.example.cinema.common.exception.BaseException;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.example.cinema.screening.exception.ScreeningExceptionType.NOT_ENOUGH_SEAT;

@Getter
public class NotEnoughSeatException extends BaseException {
    public NotEnoughSeatException() {
        super(NOT_ENOUGH_SEAT.getMessage(), LocalDateTime.now(), NOT_ENOUGH_SEAT.getStatus());
    }
}
