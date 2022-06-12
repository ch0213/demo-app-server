package com.example.cinema.auth.exception;

import com.example.cinema.common.exception.BaseException;

import java.time.LocalDateTime;

import static com.example.cinema.auth.exception.AuthExceptionType.JWT_TOKEN_NOT_EXIST;

public class JwtTokenNotExistException extends BaseException {
    public JwtTokenNotExistException() {
        super(JWT_TOKEN_NOT_EXIST.getMessage(), LocalDateTime.now(), JWT_TOKEN_NOT_EXIST.getStatus());
    }
}
