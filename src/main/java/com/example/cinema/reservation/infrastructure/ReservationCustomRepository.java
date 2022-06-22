package com.example.cinema.reservation.infrastructure;

import com.example.cinema.member.domain.Member;
import com.example.cinema.reservation.application.dto.ReservationCountResponse;
import com.example.cinema.reservation.application.dto.ReservationRankResponse;
import com.example.cinema.reservation.domain.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ReservationCustomRepository {
    Page<Reservation> findAllReservation(Pageable pageable);
    Page<Reservation> findMyReservation(Pageable pageable, Member member, LocalDate start, LocalDate end);
    Page<Reservation> findAllCanceledReservation(Pageable pageable, Member member, LocalDate start, LocalDate end);
    Page<Reservation> findAllViewHistory(Pageable pageable, Member member, LocalDate start, LocalDate end);
    Page<ReservationRankResponse> findReservationRank(Pageable pageable);
    Page<ReservationCountResponse> findMembersReservationCount(Pageable pageable);
}
