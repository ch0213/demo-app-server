package com.example.cinema.screening.exception;

import com.example.cinema.common.exception.BaseException;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.example.cinema.screening.exception.ScreeningExceptionType.ALREADY_MOVE_START;

@Getter
public class AlreadyStartMovieException extends BaseException {
    public AlreadyStartMovieException() {
        super(ALREADY_MOVE_START.getMessage(), LocalDateTime.now(), ALREADY_MOVE_START.getStatus());
    }
}
