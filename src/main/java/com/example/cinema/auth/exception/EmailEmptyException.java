package com.example.cinema.auth.exception;

import com.example.cinema.common.exception.BaseException;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.example.cinema.auth.exception.AuthExceptionType.EMAIL_EMPTY;

@Getter
public class EmailEmptyException extends BaseException {
    public EmailEmptyException() {
        super(EMAIL_EMPTY.getMessage(), LocalDateTime.now(), EMAIL_EMPTY.getStatus());
    }
}
