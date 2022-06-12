package com.example.cinema.member.exception;

import com.example.cinema.common.exception.BaseException;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.example.cinema.member.exception.MemberExceptionType.NOT_EXIST_USER;

@Getter
public class NotExistMemberException extends BaseException {
    public NotExistMemberException() {
        super(NOT_EXIST_USER.getMessage(), LocalDateTime.now(), NOT_EXIST_USER.getStatus());
    }
}
