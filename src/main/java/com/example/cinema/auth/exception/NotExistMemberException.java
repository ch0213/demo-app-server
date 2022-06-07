package com.example.cinema.auth.exception;

import com.example.cinema.common.exception.BaseException;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.example.cinema.auth.exception.AuthExceptionType.NOT_EXIST_MEMBER;

@Getter
public class NotExistMemberException extends BaseException {
    public NotExistMemberException() {
        super(NOT_EXIST_MEMBER.getMessage(), LocalDateTime.now(), NOT_EXIST_MEMBER.getStatus());
    }
}
