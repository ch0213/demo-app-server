package com.example.cinema.reservation.application.dto;

import com.example.cinema.reservation.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReservationResponse {

    private Long id;
    private String memberName;
    private String memberNumber;
    private int seatCount;
    private String movieTitle;
    private String theaterName;
    private LocalDateTime startAt;
    private LocalDateTime reserveAt;
    private LocalDateTime cancelAt;

    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getMember().getName(),
                reservation.getMember().getMemberNumber(),
                reservation.getSeatCount(),
                reservation.getScreening().getMovie().getTitle(),
                reservation.getScreening().getTheater().getName(),
                reservation.getScreening().getStartAt(),
                reservation.getReserveAt(),
                reservation.getCancelAt()
        );
    }
}
