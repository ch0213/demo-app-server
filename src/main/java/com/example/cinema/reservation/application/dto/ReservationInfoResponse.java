package com.example.cinema.reservation.application.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationInfoResponse {

    private String name;
    private String movieTitle;
    private String theaterName;
    private LocalDateTime startAt;
    private int seatCount;
    private int totalViewCount;

    @QueryProjection
    public ReservationInfoResponse(String name, String movieTitle, String theaterName, LocalDateTime startAt, int seatCount, int totalViewCount) {
        this.name = name;
        this.movieTitle = movieTitle;
        this.theaterName = theaterName;
        this.startAt = startAt;
        this.seatCount = seatCount;
        this.totalViewCount = totalViewCount;
    }
}
