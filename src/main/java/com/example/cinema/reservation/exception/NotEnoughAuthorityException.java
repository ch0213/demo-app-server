package com.example.cinema.reservation.exception;

import com.example.cinema.common.exception.BaseException;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.example.cinema.reservation.exception.ReservationExceptionType.NOT_ENOUGH_AUTHORITY;

@Getter
public class NotEnoughAuthorityException extends BaseException {
    public NotEnoughAuthorityException() {
        super(NOT_ENOUGH_AUTHORITY.getMessage(), LocalDateTime.now(), NOT_ENOUGH_AUTHORITY.getStatus());
    }
}
