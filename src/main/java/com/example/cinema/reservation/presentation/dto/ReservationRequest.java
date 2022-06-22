package com.example.cinema.reservation.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@NoArgsConstructor
public class ReservationRequest {

    @Min(message = "한 자리 이상 예매 가능합니다.", value = 1)
    @Max(message = "최대 열자리 예매 가능합니다.", value = 10)
    private int seatCount;
}
