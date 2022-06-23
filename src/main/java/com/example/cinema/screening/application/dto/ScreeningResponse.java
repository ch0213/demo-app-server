package com.example.cinema.screening.application.dto;

import com.example.cinema.screening.domain.Screening;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScreeningResponse {

    private Long id;
    private String theater;
    private String movie;
    private LocalDateTime startAt;
    private int leftSeatCount;

    public static ScreeningResponse from(Screening schedule) {
        return new ScreeningResponse(
                schedule.getId(),
                schedule.getTheater().getName(),
                schedule.getMovie().getTitle(),
                schedule.getStartAt(),
                schedule.getLeftSeatCount()
        );
    }
}
