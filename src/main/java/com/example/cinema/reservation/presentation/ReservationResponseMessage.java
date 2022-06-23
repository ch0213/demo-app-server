package com.example.cinema.reservation.presentation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReservationResponseMessage {
    RESERVATION_SUCCESS("예매 성공"),
    CANCEL_SUCCESS("취소 성공"),
    FIND_RESERVATION("예매내역 조회 성공"),
    FIND_CANCELED_RESERVATION("취소내역 조회 성공"),
    FIND_VIEW_HISTORY("관람내역 조회 성공"),
    FIND_MEMBERS_RESERVATION("모든 회원의 예매내역 조회 성공"),
    FIND_MEMBERS_RESERVATION_COUNT("모든 회원의 예매 횟수 조휘 성공"),
    FIND_MEMBERS_RESERVATION_RANK("모든 회원의 예매 횟수 순위 조회 성공");

    private final String message;
}
