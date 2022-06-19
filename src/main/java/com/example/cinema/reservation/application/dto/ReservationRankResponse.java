package com.example.cinema.reservation.application.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class ReservationRankResponse {

    private String memberNumber;
    private String name;
    private int rank;
    private int count;

    @QueryProjection
    public ReservationRankResponse(String memberNumber, String name, int rank, int count) {
        this.memberNumber = memberNumber;
        this.name = name;
        this.rank = rank;
        this.count = count;
    }
}
