package com.example.cinema.auth.exception;

import com.example.cinema.common.exception.BaseException;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.example.cinema.auth.exception.AuthExceptionType.WRONG_PASSWORD;

@Getter
public class WrongPasswordException extends BaseException {
    public WrongPasswordException() {
        super(WRONG_PASSWORD.getMessage(), LocalDateTime.now(), WRONG_PASSWORD.getStatus());
    }
}
