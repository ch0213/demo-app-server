package com.example.cinema.reservation.application.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class ReservationCountResponse {

    private String memberNumber;
    private String name;
    private int count;

    @QueryProjection
    public ReservationCountResponse(String memberNumber, String name, int count) {
        this.memberNumber = memberNumber;
        this.name = name;
        this.count = count;
    }
}
