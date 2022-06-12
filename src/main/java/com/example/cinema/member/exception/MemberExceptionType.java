package com.example.cinema.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum MemberExceptionType {
    NOT_EXIST_USER("존재하지 않는 유저입니다.", BAD_REQUEST),
    NOT_EXIST_ROLE("존재하지 않는 역할입니다.", BAD_REQUEST);

    private final String message;
    private HttpStatus status;
}
