package com.example.cinema.auth.exception;

import com.example.cinema.common.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static com.example.cinema.auth.exception.AuthExceptionType.PERMISSION_DENIED;

@Getter
public class PermissionDeniedException extends BaseException {
    public PermissionDeniedException() {
        super(PERMISSION_DENIED.getMessage(), LocalDateTime.now(), PERMISSION_DENIED.getStatus());
    }
}
