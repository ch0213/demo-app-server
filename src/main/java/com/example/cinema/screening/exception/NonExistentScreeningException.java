package com.example.cinema.screening.exception;

import com.example.cinema.common.exception.BaseException;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.example.cinema.screening.exception.ScreeningExceptionType.NOT_EXIST_SCREENING;

@Getter
public class NonExistentScreeningException extends BaseException {
    public NonExistentScreeningException() {
        super(NOT_EXIST_SCREENING.getMessage(), LocalDateTime.now(), NOT_EXIST_SCREENING.getStatus());
    }
}
